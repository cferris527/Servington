package com.example.servington_from_ground_up;

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

import org.json.JSONException;
import org.json.JSONObject;
import com.example.servington_from_ground_up.utils.Singleton;

public class organizationCreatePostActivity extends AppCompatActivity {

    Button createpost;
    private EditText newtitle;
    private EditText newdescription;

    private EditText newdate;


    public void onCreate(Bundle savedInstanceStates)
    {
        super.onCreate(savedInstanceStates);
        setContentView(R.layout.activity_create_post);

        createpost = findViewById(R.id.button_create_post);
        newtitle = findViewById(R.id.create_posts_title);
        newdescription = findViewById(R.id.create_post_description);
        newdate = findViewById(R.id.create_post_date);

        createpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postrequest();
            }
        });


    }

    private void postrequest() {
        RequestQueue queue = Volley.newRequestQueue(organizationCreatePostActivity.this);

        // create the new JSON object
        JSONObject post = new JSONObject();

        //grab string from title and description fields
        String post_title = newtitle.getText().toString();
        String post_desc = newdescription.getText().toString();
        String post_date = newdate.getText().toString();

        //add fields to the JSON object
        try {
            post.put("title", post_title);
            post.put("description", post_desc);
            post.put("date", post_date);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.URL_CREATE_POST + "/" + Singleton.getId(), post,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO: action for successful response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(organizationCreatePostActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request);

    }
}
