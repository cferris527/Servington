package com.example.servington_from_ground_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.utils.Const;
import com.example.servington_from_ground_up.utils.Singleton;
import com.example.servington_from_ground_up.utils.userType;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main activity when app is opened. User may either log in, or
 * create an account.
 *
 * @author Connor Ferris
 */
public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView status;
    private String spinnerText;
    private String accountType;
    Button loginButton;
    Button createButton;
    String initial_url = "http://coms-309-029.class.las.iastate.edu:8080/users/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        status = findViewById(R.id.statusMessage);
        loginButton = findViewById(R.id.loginButton);
        createButton = findViewById(R.id.createButton);

        Spinner spMethod = findViewById(R.id.spinner2);
        String[] methods = new String[]{"Volunteer", "Organization", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, methods);
        spMethod.setAdapter(adapter);

        //Login button, sends entered info
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("Loading...");
                sendUsername();
            }
        });

        //Button to account creation
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        //Dropdown menu for account creation
        spMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerText = (String) parent.getItemAtPosition(position);
                if (spinnerText.equals("Volunteer")) {
                    accountType = "VOLUNTEER";
                } else if (spinnerText.equals("Organization")) {
                    accountType = "ORGANIZATION";
                } else if (spinnerText.equals("Admin")){
                    accountType = "ADMIN";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {accountType = "VOLUNTEER";}
        });
    }

    /**
     * Method sending GET request to server with username and password in url.
     * Will receive response with username and accountType. If username
     * is null, this implies the password did not match or the account name doesn't
     * exist.
     */
    private void sendUsername() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String user_name = username.getText().toString();
        String pass_word = password.getText().toString();

        // empty JSON object for body
        JSONObject body = new JSONObject();

        String loginType = "";

        if(accountType.equals("VOLUNTEER")) {
            loginType = "volunteerLogin";
        }
        else if(accountType.equals("ORGANIZATION")) {
            loginType = "orgLogin";
        }
        else if(accountType.equals("ADMIN")) {
            loginType = "adminLogin";
        }

        String url = Const.SERVER + "/" + loginType + "/" + user_name + "/" + pass_word;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String userName;
                        String accountType;
                        try {
                            userName = response.getString("username");
                            accountType = response.getString("accountType");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        //if Login attempt is invalid...
                        if (userName.equals("null")) {
                            status.setText("Invalid username/password!");
                            username.setText("", TextView.BufferType.EDITABLE);
                            password.setText("", TextView.BufferType.EDITABLE);
                            return;
                        }
                        Intent intent;
                        if (accountType.equals("VOLUNTEER")) {
                            intent = new Intent(MainActivity.this, UserActivity.class);
                        }
                        else if (accountType.equals("ORGANIZATION")) {
                            intent = new Intent(MainActivity.this, OrganizationActivity.class);
                        }
                        else if (accountType.equals("ADMIN")) {
                            intent = new Intent(MainActivity.this, AdminActivity.class);
                        }
                        else {
                            System.out.println(accountType.toString());
                            System.out.println(userType.VOLUNTEER.toString());
                            status.setText("Account has no type?");
                            return;
                        }

                        Singleton data = Singleton.getInstance();
                        try {
                            data.setData(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request); // send request
    }

}