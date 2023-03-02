package com.example.servington_from_ground_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Button loginButton;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        status = findViewById(R.id.statusMessage);
        loginButton = findViewById(R.id.loginButton);
        createButton = findViewById(R.id.createButton);

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
    }


    /**
     * Method sending POST request to server with username and password entered.
     * Will receive response with username, password, and accountType. If username
     * is null, this implies the password did not match or the account name doesn't
     * exist.
     */
    private void sendUsername() {

        RequestQueue queue = Volley.newRequestQueue(this);

        String user_name = username.getText().toString();
        String pass_word = password.getText().toString();

        try {
            body.put("username", user_name);
            body.put("password", pass_word);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String stringurl = "http://coms-309-029.class.las.iastate.edu:8080/users/" + user_name + "/" + pass_word;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringurl, body,
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
                        if (accountType.equals("USER")) {
                            Intent intent = new Intent(MainActivity.this, UserActivity.class);
                            startActivity(intent);
                        }
                        else if (accountType.equals("ORGANIZATION")) {
                            Intent intent = new Intent(MainActivity.this, OrganizationActivity.class);
                            startActivity(intent);
                        }
                        else if (accountType.equals("ADMIN")) {
                            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                            startActivity(intent);
                        }
                        else {
                            status.setText("Account has no type?");
                        }
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