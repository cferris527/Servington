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
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.utils.Const;

import org.json.JSONObject;

/**
 * Activity for Admin to delete users and posts.
 *
 * @author Connor Ferris
 */
public class AdminDeletionActivity extends AppCompatActivity {

    Button backButton;
    Button deleteAccount;
    Button deletePost;
    EditText idText;
    EditText postTitleText;
    private String method;
    private boolean isAPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_deletion);
        backButton = findViewById(R.id.backButton2);
        deleteAccount = findViewById(R.id.deleteAccount);
        deletePost = findViewById(R.id.deletePost);
        idText = findViewById(R.id.idText);
        postTitleText = findViewById(R.id.postTitleText);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDeletionActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequestAccount();
            }
        });

        deletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequestPost();
            }
        });

    }

    /**
     * Sends DELETE with an account.
     */
    private void postRequestAccount() {
        RequestQueue queue = Volley.newRequestQueue(AdminDeletionActivity.this);

        // create a new JSON object
        JSONObject body = new JSONObject();

        // grabs Strings from idOrName field
        String text = idText.getText().toString();

        int idtext = Integer.parseInt(text);

        System.out.println(idtext);

        // adds fields to JSON object
        try {
            body.put("id", idtext);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.URL_DELETE_ACCOUNT, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO: action for successful response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminDeletionActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request); // send request
    }

    /**
     * Sends DELETE with a post.
     */
    private void postRequestPost() {
        RequestQueue queue = Volley.newRequestQueue(AdminDeletionActivity.this);

        // create a new JSON object
        JSONObject body = new JSONObject();

        // grabs Strings from idOrName field
        String text = postTitleText.getText().toString();

        // adds fields to JSON object
        try {
            body.put("title", text);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.URL_DELETE_POST, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO: action for successful response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminDeletionActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request); // send request
    }
}