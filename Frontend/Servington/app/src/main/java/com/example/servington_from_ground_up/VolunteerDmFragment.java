package com.example.servington_from_ground_up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VolunteerDmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VolunteerDmFragment extends Fragment {
    View view;
    Button printBtn;
    public VolunteerDmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static VolunteerDmFragment newInstance(String param1, String param2) {
        VolunteerDmFragment fragment = new VolunteerDmFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_volunteer_dm, container, false);
        printBtn = (Button) view.findViewById(R.id.printBtn);
        printBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Yippie.");
            }
        });
        return view;
    }
}