package com.example.servington_from_ground_up.volunteer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.PostData;
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.RecyclerViewAdapter;
import com.example.servington_from_ground_up.utils.Const;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Similar to VolunteerPostsFragment but allows users to search specified posts
 *
 * @author Mike
 */
public class VolunteerSearchFragment extends Fragment {
    View view;


    Button SearchBtn;
    private RecyclerView pList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<PostData> PostList;
    private RecyclerView.Adapter adapter;



    EditText keyword;

    public VolunteerSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static VolunteerSearchFragment newInstance(String param1, String param2) {
        VolunteerSearchFragment fragment = new VolunteerSearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_volunteer_search, container, false);

        //TODO
        pList = view.findViewById(R.id.SearchRecy);

        PostList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(getActivity().getApplicationContext(), PostList);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(pList.getContext(), linearLayoutManager.getOrientation());

        pList.setHasFixedSize(true);
        pList.setLayoutManager(linearLayoutManager);
        pList.addItemDecoration(dividerItemDecoration);
        pList.setAdapter(adapter);
        SearchBtn = view.findViewById(R.id.SearchBtn);
        keyword = view.findViewById(R.id.keywordsearch);

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PostList.clear();
                getData();
            }
        });

        return view;
    }

    private void getData() {

        String search = keyword.getText().toString();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Const.URL_SEARCH_POST_TITLE + search, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        PostData Posts = new PostData();
                        Posts.setTitle(jsonObject.getString("title"));
                        Posts.setDescription(jsonObject.getString("description"));
                        Posts.setDate(jsonObject.getString("date"));

                        PostList.add(Posts);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }

}


