package com.example.servington_from_ground_up.organization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.servington_from_ground_up.R;

/**
 * Fragment allowing Organization to message Volunteers.
 */
public class OrganizationDmFragment extends Fragment {
    View view;
    public OrganizationDmFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     */
    public static OrganizationDmFragment newInstance() {return new OrganizationDmFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_organization_dm, container, false);

        //TODO

        return view;
    }
}