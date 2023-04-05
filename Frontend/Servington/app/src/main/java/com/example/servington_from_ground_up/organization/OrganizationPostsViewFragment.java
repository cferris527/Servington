package com.example.servington_from_ground_up.organization;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Const;
import com.example.servington_from_ground_up.utils.Singleton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganizationPostsViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganizationPostsViewFragment extends Fragment {
    View view;
    TextView postsview;
    RequestQueue queue;
    public OrganizationPostsViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static OrganizationPostsViewFragment newInstance(String param1, String param2) {
        OrganizationPostsViewFragment fragment = new OrganizationPostsViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_organization_posts, container, false);

        //TODO
        postsview = view.findViewById(R.id.OrgPostsView);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        Singleton data = Singleton.getInstance();
        StringRequest request = new StringRequest(Request.Method.GET, Const.URL_VIEW_POST_BYID + data.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                postsview.setText(response.toString());
                Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
            }
        });
        queue.add(request);
        return view;
    }
}