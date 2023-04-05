package com.example.servington_from_ground_up.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminReportsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminReportsFragment extends Fragment {

    View view;
    LinearLayout layout;
    ArrayList<TextView> usernames = new ArrayList<TextView>();
    ArrayList<TextView> titles = new ArrayList<TextView>();
    ArrayList<TextView> descs = new ArrayList<TextView>();
    ArrayList<Button> buttons = new ArrayList<Button>();

    public AdminReportsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static AdminReportsFragment newInstance(String param1, String param2) {
        AdminReportsFragment fragment = new AdminReportsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_reports, container, false);

        layout = (LinearLayout) view.findViewById(R.id.reportsLayout);

        loadAllReports();

        return view;
    }

    private void addUsername(String name) {
        TextView username = new TextView(getActivity());
        username = new TextView(getContext());
        username.setText(name);
        username.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        username.setTextSize(20);
        username.setBackgroundColor(Color.parseColor("#fcfcfc"));

        usernames.add(username);
        layout.addView(username);
    }

    private void addTitle(String titleText) {
        TextView title = new TextView(getActivity());
        title = new TextView(getContext());
        title.setText(titleText);
        title.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        title.setTextSize(30);
        title.setBackgroundColor(Color.parseColor("#fcfcfc"));

        titles.add(title);
        layout.addView(title);
    }

    private void addDescription(String description) {
        TextView desc = new TextView(getActivity());
        desc = new TextView(getContext());
        desc.setText(description);
        desc.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        desc.setTextSize(20);
        desc.setBackgroundColor(Color.parseColor("#fcfcfc"));

        descs.add(desc);
        layout.addView(desc);
    }

    private void addButton(int index) {
        buttons.add(new Button(getContext()));
        buttons.get(index).setText("Remove");
        buttons.get(index).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.removeView(usernames.get(index));
                layout.removeView(titles.get(index));
                layout.removeView(descs.get(index));
                layout.removeView(buttons.get(index));

                //TODO removing report from actual backend report list

            }
        });
        layout.addView(buttons.get(index));
    }

    private void addSeperator() {
        TextView sep = new TextView(getActivity());
        sep = new TextView(getContext());
        sep.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        sep.setTextSize(2);
        sep.setBackgroundColor(Color.parseColor("#000000"));
        layout.addView(sep);
    }

    private void loadAllReports() {
        layout.removeAllViews();

        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/reportlist", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject report;
                        int i;
                        for(i = 0; i < response.length(); ++i) {
                            try {
                                report = response.getJSONObject(i);
                                addUsername(report.getString("username"));
                                addTitle(report.getString("title"));
                                addDescription(report.getString("reportDescription"));
                                addButton(i);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
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