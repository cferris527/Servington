package com.example.servington_from_ground_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    EditText newUsername;
    EditText newPassword;
    Button confirmButton;
    Button cancelButton;
    Spinner spMethod;
    private String method;
    private String accountType;
    private String url = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingpost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        newUsername = findViewById(R.id.newUsername);
        newPassword = findViewById(R.id.newPassword);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        Spinner spMethod = findViewById(R.id.spinner);
        String[] methods = new String[]{"User", "Organization"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, methods);
        spMethod.setAdapter(adapter);
        spMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                method = (String) parent.getItemAtPosition(position);
                if (method.equals("User")) {
                    accountType = "USER";
                    // System.out.println("account type switched to USER");
                } else {
                    accountType = "ORGANIZATION";
                    // System.out.println("account type switched to ORG");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                method = "User";
            }
        });
        /**
         * Create account button, sends POST with new account info
         * and returns to login screen.
         */
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest();
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Cancel button action, returns to login screen.
         */
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

        /**
         * Creates POST with new login credentials. Sends JSON obj:
         * username : ___
         * password : ___
         * accountType : ___
         */
        private void postRequest() {
            RequestQueue queue = Volley.newRequestQueue(CreateAccountActivity.this);

            // create a new JSON object
            JSONObject body = new JSONObject();

            // grabs Strings from Username and Password fields.
            String user_name = newUsername.getText().toString();
            String pass_word = newPassword.getText().toString();

            // adds fields to JSON object
            try {
                body.put("username", user_name);
                body.put("password", pass_word);
                body.put("accountType", accountType);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //TODO: action for successful response
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CreateAccountActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            queue.add(request); // send request
        }



}