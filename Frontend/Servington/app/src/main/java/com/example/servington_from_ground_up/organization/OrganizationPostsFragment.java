package com.example.servington_from_ground_up.organization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.servington_from_ground_up.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganizationPostsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganizationPostsFragment extends Fragment {
    View view;
    public OrganizationPostsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static OrganizationPostsFragment newInstance(String param1, String param2) {
        OrganizationPostsFragment fragment = new OrganizationPostsFragment();
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

        return view;
    }
}