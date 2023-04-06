package com.example.servington_from_ground_up.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminUserBanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminUserBanFragment extends Fragment {

    View view;
    Button backBtn;
    Button banBtn;
    Button unbanBtn;
    EditText usertoBan;
    EditText usertoUnban;
    TextView statusMessage;

    public AdminUserBanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static AdminUserBanFragment newInstance() {
        AdminUserBanFragment fragment = new AdminUserBanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_user_ban, container, false);

        //Status Message
        statusMessage = (TextView) view.findViewById(R.id.statusMessage3);
        //Names
        usertoBan = (EditText) view.findViewById(R.id.banName);
        usertoUnban = (EditText) view.findViewById(R.id.unbanName);

        //Back button
        backBtn = (Button) view.findViewById(R.id.backBtn2);
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

        //Ban button
        banBtn = (Button) view.findViewById(R.id.bannedBtn);
        banBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banUser(usertoBan.getText().toString());
            }
        });

        //Unban button
        unbanBtn = (Button) view.findViewById(R.id.unbannedBtn);
        unbanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbanUser(usertoUnban.getText().toString());
            }
        });

        return view;
    }

    private void banUser(String username) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject obj = new JSONObject();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                Const.SERVER + "/banVolunteer/" + username, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        usertoBan.setText("");
                        statusMessage.setBackgroundColor(Color.parseColor("#00FF00"));
                        statusMessage.setText(username + " banned.");
                        //5 second delay for text to go away
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                statusMessage.setText("");
                            }
                        }, 5000);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);

    }

    private void unbanUser(String username) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject obj = new JSONObject();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                Const.SERVER + "/unbanVolunteer/" + username, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        usertoUnban.setText("");
                        statusMessage.setBackgroundColor(Color.parseColor("#00FF00"));
                        statusMessage.setText(username + " unbanned.");
                        //5 second delay for text to go away
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                statusMessage.setText("");
                            }
                        }, 5000);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
    }
}