package com.example.servington_from_ground_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
    private String url = "https://31fdc3aa-553a-48ec-ab03-f9d459afb08d.mock.pstmn.io/demo";


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
         * postRequest() method.
         */
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                postRequest();
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
                        //TODO: action for succesful response
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