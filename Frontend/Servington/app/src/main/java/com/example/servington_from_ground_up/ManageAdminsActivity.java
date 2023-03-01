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
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_admins);
        username = findViewById(R.id.username2);
        password = findViewById(R.id.password2);
        status = findViewById(R.id.statusMessageA);
        createAdmin = findViewById(R.id.createAdmin);
        back = findViewById(R.id.backButton);

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

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.URL_CREATE_ADMIN_POST, body,
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

}