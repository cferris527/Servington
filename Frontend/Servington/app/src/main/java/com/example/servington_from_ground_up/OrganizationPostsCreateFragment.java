package com.example.servington_from_ground_up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.utils.Const;
import com.example.servington_from_ground_up.utils.Singleton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganizationPostsCreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganizationPostsCreateFragment extends Fragment {
    View view;

    Button createpost;
    private EditText newtitle;
    private EditText newdescription;

    private EditText newdate;

    public OrganizationPostsCreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static OrganizationPostsCreateFragment newInstance(String param1, String param2) {
        OrganizationPostsCreateFragment fragment = new OrganizationPostsCreateFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_organization_create_posts, container, false);

        createpost = view.findViewById(R.id.button_create_post);
        newtitle = view.findViewById(R.id.create_posts_title);
        newdescription = view.findViewById(R.id.create_post_description);
        newdate = view.findViewById(R.id.create_post_date);

        createpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postrequest();
            }
        });

        return view;
    }

    private void postrequest() {
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

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

        Singleton data = Singleton.getInstance();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.URL_CREATE_POST + data.getId(), post,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO: action for successful response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request);

    }
}