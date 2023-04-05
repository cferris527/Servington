package com.example.servington_from_ground_up.organization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.servington_from_ground_up.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganizationPostsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganizationPostsFragment extends Fragment {
    View view;
    Button CreatePostBtn;
    Button ViewPostsBtn;
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
        CreatePostBtn = view.findViewById(R.id.createposts);
        ViewPostsBtn = view.findViewById(R.id.viewposts);

        CreatePostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new OrganizationPostsCreateFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        ViewPostsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new OrganizationPostsViewFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}