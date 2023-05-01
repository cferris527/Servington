package com.example.servington_from_ground_up.volunteer;

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
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Const;
import com.example.servington_from_ground_up.utils.Singleton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Fragment to allow volunteers to create reports on posts
 *
 * @author Mike
 */
public class VolunteerReportFragment extends Fragment {

    View view;

    EditText postTitle;
    EditText reportDesc;

    Button sendReport;

    public VolunteerReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static VolunteerReportFragment newInstance(String param1, String param2) {
        VolunteerReportFragment fragment = new VolunteerReportFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_volunteer_report, container, false);

        postTitle = view.findViewById(R.id.reportTitleInput);
        reportDesc = view.findViewById(R.id.reportDescInput);
        sendReport = view.findViewById(R.id.reportBtn);

        sendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostRequest();
            }
        });


        return view;
    }

    private void PostRequest() {

        System.out.println("report sent");
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        // create the new JSON object
        JSONObject report = new JSONObject();
        //grab string from title and description fields
        String title = postTitle.getText().toString();
        String PostDesc = reportDesc.getText().toString();

        Singleton data = Singleton.getInstance();

        //add fields to the JSON object
        try {
            report.put("title",title);
            report.put("username", data.getUsername());
            report.put("reportDescription", PostDesc);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        String url = Const.URL_CREATE_REPORT + title;


        System.out.println(title);
        System.out.println(PostDesc);
        System.out.println(report);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, report,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    System.out.println("responce");
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
    }

