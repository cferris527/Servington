package com.example.servington_from_ground_up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VolunteerHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VolunteerHomeFragment extends Fragment {
    View view;
    public VolunteerHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static VolunteerHomeFragment newInstance(String param1, String param2) {
        VolunteerHomeFragment fragment = new VolunteerHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_volunteer_home, container, false);

        //TODO

        return view;
    }
}