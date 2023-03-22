package com.example.servington_from_ground_up;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserPostListActivity extends AppCompatActivity {

    ScrollView scroll;
    LinearLayout layout;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist_user);
       layout = findViewById(R.id.postlinear);
       LoadAllPosts();
    }
   private void LoadAllPosts()
   {
       //clear current layout
       layout.removeAllViews();

       TextView valueTV = new TextView(this);
       RequestQueue queue = Volley.newRequestQueue(UserPostListActivity.this);

       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Const.SERVER + "/post", null,
               new Response.Listener<JSONArray>() {
                   @Override
                   public void onResponse(JSONArray response) {
                       JSONObject post;
                       String title;
                       String date;
                       String description;
                       String[] postList = new String[response.length()];
                       int i;
                       for (i = 0; i < response.length(); ++i) {
                           try {
                               post = response.getJSONObject(i);
                               title = post.getString("title");
                               date = post.getString("date");
                               description = post.getString("description");
                           } catch (JSONException e) {
                               throw new RuntimeException(e);
                           }
                           postList[i] = title + " | " + description + " | " + date;
                       }
                       TextView valueTV;
                       for (i = 0; i < postList.length; ++i) {
                           valueTV = new TextView(getApplicationContext());
                           valueTV.setText(postList[i]);
                           valueTV.setLayoutParams(new ViewGroup.LayoutParams(
                                   ViewGroup.LayoutParams.FILL_PARENT,
                                   ViewGroup.LayoutParams.WRAP_CONTENT));

                           valueTV.setTextSize(20);
                           layout.addView(valueTV);
                       }
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(UserPostListActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                   }
               }
       );
       queue.add(jsonArrayRequest); // send request
   }

}