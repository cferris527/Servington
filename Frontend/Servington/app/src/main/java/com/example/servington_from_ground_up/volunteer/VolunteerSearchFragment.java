package com.example.servington_from_ground_up.volunteer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.servington_from_ground_up.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VolunteerSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VolunteerSearchFragment extends Fragment {
    View view;
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

        return view;
    }
}