package com.example.servington_from_ground_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.utils.Const;

import org.json.JSONObject;

/**
 * Activity for creating a new USER or ORGANIZATION account.
 *
 * @author Connor Ferris
 */
public class CreateAccountActivity extends AppCompatActivity {

    private EditText newUsername;
    private EditText newPassword;
    Button confirmButton;
    Button cancelButton;
    private String method;
    private String accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        newUsername = findViewById(R.id.newUsername);
        newPassword = findViewById(R.id.newPassword);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        Spinner spMethod = findViewById(R.id.spinner);
        String[] methods = new String[]{"Volunteer", "Organization"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, methods);
        spMethod.setAdapter(adapter);
        spMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                method = (String) parent.getItemAtPosition(position);
                if (method.equals("Volunteer")) {
                    accountType = "VOLUNTEER";
                    // System.out.println("account type switched to USER");
                } else {
                    accountType = "ORGANIZATION";
                    // System.out.println("account type switched to ORG");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                accountType = "VOLUNTEER";
            }
        });

        //create account button, sends entered info
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest();
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //cancel button, returns to main login
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
         * accountType : USER/ORGANIZATION
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
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            String createType = "";

            if(accountType.equals("VOLUNTEER")) {
                createType = "createVolunteer";
            }
            else if(accountType.equals("ORGANIZATION")) {
                createType = "createOrg";
            }

            String url = Const.SERVER + "/" + createType;

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