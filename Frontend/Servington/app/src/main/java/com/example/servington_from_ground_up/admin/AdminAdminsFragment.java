package com.example.servington_from_ground_up.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Fragment to create new Admins, and view a list of all
 * Admin accounts.
 *
 * @author Connor Ferris
 */
public class AdminAdminsFragment extends Fragment {

    View view;
    EditText username;
    EditText password;
    Button refreshBtn;
    Button createBtn;
    LinearLayout layout;
    public AdminAdminsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static AdminAdminsFragment newInstance() {
        return new AdminAdminsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_admins, container, false);

        username = view.findViewById(R.id.username3);
        password = view.findViewById(R.id.password3);

        layout = view.findViewById(R.id.linearLayout);
        loadAllAdmins();

        refreshBtn = view.findViewById(R.id.refreshAdmins);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAllAdmins();
            }
        });

        createBtn = view.findViewById(R.id.createAdmin2);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createAdmin(username.getText().toString(),password.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return view;
    }

    /**
     * Parses through list of Admins on server to insert them into scrollable list
     * by calling addAdmin(). Uses /listAdmins
     */
    private void loadAllAdmins() {
        layout.removeAllViews();

        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                Const.SERVER + "/listAdmins", null,
                new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject obj;
                    int i;
                    for (i = 0; i <response.length(); ++i) {
                        try {
                            obj = response.getJSONObject(i);
                            addAdmin(obj.getString("id"), obj.getString("username"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }},
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }});
        queue.add(request);
    }

    /**
     * Adds an Admin TextView object into the Scrollable List.
     * @param id id of admin
     * @param username admin username
     */
    private void addAdmin(String id, String username) {
        TextView admin = new TextView(getContext());
        admin.setText(username + " , " + id);
        admin.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        admin.setTextSize(25);
        admin.setBackgroundColor(Color.parseColor("#F7A96F"));
        layout.addView(admin);

        TextView sep = new TextView(getActivity());
        sep = new TextView(getContext());
        sep.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        sep.setTextSize(2);
        sep.setBackgroundColor(Color.parseColor("#000000"));
        layout.addView(sep);

    }

    /**
     * Creates a new Admin Account. Uses /createAdmin
     * @param username new admin username
     * @param password new admin password
     * @throws JSONException for putting fields in JSON object
     */
    private void createAdmin(String username, String password) throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject body = new JSONObject();

        body.put("username",username);
        body.put("password", password);

        String url = Const.SERVER + "/createAdmin";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }},
            new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }});
        queue.add(request);
    }
}