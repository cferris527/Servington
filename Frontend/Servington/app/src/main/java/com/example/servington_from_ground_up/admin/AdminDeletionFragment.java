package com.example.servington_from_ground_up.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Fragment to delete an account or user.
 *
 * @author Connor Ferris
 */
public class AdminDeletionFragment extends Fragment {

    View view;
    Button backBtn;
    Button deleteUser;
    Button deletePost;
    EditText accountId;
    EditText postTitle;

    public AdminDeletionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static AdminDeletionFragment newInstance() {
        return new AdminDeletionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_deletion, container, false);

        //Back Button
        backBtn = (Button) view.findViewById(R.id.backBansBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AdminBansFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //Delete User Button
        deleteUser = (Button) view.findViewById(R.id.deleteAccount2);
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    deleteUser(accountId.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Delete Post Button
        deletePost = (Button) view.findViewById(R.id.deletePost2);
        deletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    deletePost(postTitle.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Account ID Text
        accountId = (EditText) view.findViewById(R.id.idText2);

        //Post Title Text
        postTitle = (EditText) view.findViewById(R.id.postTitleText2);

        return view;
    }

    /**
     * Delete post from server.
     * @param postTitle title of post to be deleted
     * @throws JSONException for handling JSON object
     */
    private void deletePost(String postTitle) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject obj = new JSONObject();
        obj.put("title", postTitle);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.URL_DELETE_POST, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        queue.add(request);
    }

    /**
     * Delete user from server.
     * @param userId id of user to be deleted
     * @throws JSONException for handling JSON object
     */
    private void deleteUser(String userId) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject obj = new JSONObject();
        obj.put("id", userId);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.URL_DELETE_ACCOUNT, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        queue.add(request);
    }
}