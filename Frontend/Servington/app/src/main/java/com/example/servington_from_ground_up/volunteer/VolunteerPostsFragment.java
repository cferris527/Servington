package com.example.servington_from_ground_up.volunteer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.servington_from_ground_up.utils.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment to allow volunteers to see a list of posts using a recycler view
 *
 * @author Mike
 */
public class VolunteerPostsFragment extends Fragment {
    View view;


    private RecyclerView pList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<PostData> PostList;
    private RecyclerView.Adapter adapter;
    private String spinnerText;
    private EditText volInput;
    Button postBtn;

    public VolunteerPostsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static VolunteerPostsFragment newInstance(String param1, String param2) {
        VolunteerPostsFragment fragment = new VolunteerPostsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_volunteer_posts, container, false);

        //TODO
        pList = view.findViewById(R.id.recyclerView);

        PostList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(getActivity().getApplicationContext(), PostList);

        postBtn = view.findViewById(R.id.VolSelect);
        volInput = view.findViewById(R.id.postinput);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(pList.getContext(), linearLayoutManager.getOrientation());

        pList.setHasFixedSize(true);
        pList.setLayoutManager(linearLayoutManager);
        pList.addItemDecoration(dividerItemDecoration);
        pList.setAdapter(adapter);

        getData();

        Singleton data = Singleton.getInstance();
        System.out.println(data.getId());

        Spinner spMethod = view.findViewById(R.id.postselect);
        String[] postOptions = new String[]{"Apply", "Report"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, postOptions);
        spMethod.setAdapter(adapter);

        spMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int selection, long l) {
                spinnerText = (String) parent.getItemAtPosition(selection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinnerText.equals("Apply"))
                {
                    VolApply();
                }
                else if(spinnerText.equals("Report"))
                {
                    VolReport();
                }

            }
        });


        return view;
    }

    private void VolReport() {

        Fragment fragment = new VolunteerReportFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    /*
    VolApply() method for sending a volley POST request to have users apply to posts
     */
    private void VolApply() {

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        Singleton data = Singleton.getInstance();

        String url = Const.URL_ADD_VOLUNTEER + "/" + volInput.getText().toString() + "/" + data.getId();
        JSONObject body = new JSONObject();


               JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request); // send request
    }





/*
    getData() is a helper method that will send a volley request for the Array of posts
 */
    private void getData() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Const.URL_View_All_Posts, new Response.Listener<JSONArray>() {
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