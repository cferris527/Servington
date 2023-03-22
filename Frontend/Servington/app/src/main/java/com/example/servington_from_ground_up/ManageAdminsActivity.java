package com.example.servington_from_ground_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity for Admin accounts to create/manage other admins.
 *
 * @author Connor Ferris
 */
public class ManageAdminsActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView status;
    Button createAdmin;
    Button back, refresh;

    ScrollView scroller;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_admins);
        username = findViewById(R.id.username2);
        password = findViewById(R.id.password2);
        status = findViewById(R.id.statusMessageA);
        createAdmin = findViewById(R.id.createAdmin);
        back = findViewById(R.id.backButton);
        refresh = findViewById(R.id.refreshButton);
        layout = findViewById(R.id.linearLayout);
        loadAllAdmins();

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageAdminsActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        createAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                postRequest();
                status.setText("New admin successfully created.");
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAllAdmins();
            }
        });

    }

    private void postRequest() {
        RequestQueue queue = Volley.newRequestQueue(ManageAdminsActivity.this);

        // create a new JSON object
        JSONObject body = new JSONObject();

        // grabs Strings from Username and Password fields.
        String user_name = username.getText().toString();
        String pass_word = password.getText().toString();

        // adds fields to JSON object
        try {
            body.put("username", user_name);
            body.put("password", pass_word);
            body.put("accountType", "ADMIN");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // users/account/ADMIN

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.URL_CREATE_ACCOUNT_POST, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO: action for successful response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ManageAdminsActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request); // send request
    }

    /**
     * Loads all accounts of ADMIN type into TextView.
     */
    private void loadAllAdmins() {
        //clear current layout
        layout.removeAllViews();

        TextView valueTV = new TextView(this);
        RequestQueue queue = Volley.newRequestQueue(ManageAdminsActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Const.SERVER + "/users/account/ADMIN", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject admin;
                        String name;
                        String pass;
                        String id;
                        String[] usernameList = new String[response.length()];
                        int i;
                        for (i = 0; i < response.length(); ++i) {
                            try {
                                admin = response.getJSONObject(i);
                                name = admin.getString("username");
                                pass = admin.getString("password");
                                id = admin.getString("id");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            usernameList[i] = id + " | " + name + " | " + pass;
                        }
                        TextView valueTV;
                        for (i = 0; i < usernameList.length; ++i) {
                            valueTV = new TextView(getApplicationContext());
                            valueTV.setText(usernameList[i]);
                            valueTV.setLayoutParams(new LayoutParams(
                                    LayoutParams.FILL_PARENT,
                                    LayoutParams.WRAP_CONTENT));

                            valueTV.setTextSize(20);
                            layout.addView(valueTV);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ManageAdminsActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(jsonArrayRequest); // send request
    }

}