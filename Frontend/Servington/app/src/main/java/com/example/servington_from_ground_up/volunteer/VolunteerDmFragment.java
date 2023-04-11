package com.example.servington_from_ground_up.volunteer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.servington_from_ground_up.R;

/**
 * Fragment allowing Volunteer to message Organizations.
 */
public class VolunteerDmFragment extends Fragment {
    View view;
    public VolunteerDmFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     */
    public static VolunteerDmFragment newInstance() {return new VolunteerDmFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_volunteer_dm, container, false);

        //TODO

        return view;
    }
}