package com.example.servington_from_ground_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;

    Button createButton;

    /**
     * URL for POST request.
     */
    private String url = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingpost";

    private String url2 = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingget";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        createButton = findViewById(R.id.createButton);


        /**
         * Login button action, clicking button will call
         * postRequest() , then getRequest().
         */
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                postRequest(); //send user data to backend
                getRequest(); //receive from backend validity of data sent
            }

        });

        /**
         * Create Account button action, clicking button will
         * move to creating an account.
         */
        createButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * Creates POST for login credentials. Sends JSON obj:
     * username: ___
     * password: ___
     */
    private void postRequest() {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // create a new JSON object
        JSONObject body = new JSONObject();

        // grabs Strings from Username and Password fields.
        String user_name = username.getText().toString();
        String pass_word = password.getText().toString();

        // adds fields to JSON object
        try {
            body.put("username", user_name);
            body.put("password", pass_word);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO
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


    private void getRequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // String response can be converted to JSONObject via
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object.has("accountType")) {

                                String type = object.optString("accountType");

                                if(type.equals("USER")) {
                                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                                    startActivity(intent);
                                }
                                else if(type.equals("ORGANIZATION")) {
                                    Intent intent = new Intent(MainActivity.this, OrganizationActivity.class);
                                    startActivity(intent);
                                }
                                else if(type.equals("ADMIN")) {
                                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                                    startActivity(intent);
                                }

                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        //tvResponse.setText("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //tvResponse.setText("That didn't work!" + error.toString());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //Toast.makeText(getApplicationContext(),method+" request sent!",Toast.LENGTH_SHORT).show();
    }



}