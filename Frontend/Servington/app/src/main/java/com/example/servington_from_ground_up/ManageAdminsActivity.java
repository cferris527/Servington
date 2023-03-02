package com.example.servington_from_ground_up;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;

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

    ScrollView scroller;
    LinearLayout layout;

    private TextView example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_admins);
        username = findViewById(R.id.username2);
        password = findViewById(R.id.password2);
        status = findViewById(R.id.statusMessageA);
        createAdmin = findViewById(R.id.createAdmin);
        back = findViewById(R.id.backButton);

        example = findViewById(R.id.example);
        //loadAllAdmins();

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

        layout = findViewById(R.id.linearLayout);

        TextView valueTV = new TextView(this);
        valueTV.setText("hallo hallo");
        valueTV.setId(5);
        valueTV.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        layout.addView(valueTV);

        valueTV = new TextView(this);
        valueTV.setText("pingas pingas");
        valueTV.setId(5);
        valueTV.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        layout.addView(valueTV);

        for(int i = 0; i < 100; ++i) {
            valueTV = new TextView(this);
            valueTV.setText(i + "one thousand");
            valueTV.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

            layout.addView(valueTV);

        }


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

//    private void loadAllAdmins() {
//        TextView valueTV;
//
//        RequestQueue queue = Volley.newRequestQueue(ManageAdminsActivity.this);
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Const.URL_ADMIN_LIST, null, new Response.Listener<JSONArray>() {
//
//            @Override
//            public void onResponse(JSONArray response) {
//                System.out.println("hi");
//            }
//        });
//
//    }

}