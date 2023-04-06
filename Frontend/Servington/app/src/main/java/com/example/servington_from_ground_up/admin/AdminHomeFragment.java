package com.example.servington_from_ground_up.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Singleton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminHomeFragment extends Fragment {

    View view;
    Singleton data = Singleton.getInstance();
    TextView welcomeText;
    TextView idText;

    public AdminHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static AdminHomeFragment newInstance() {
        AdminHomeFragment fragment = new AdminHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        //Welcome Text
        welcomeText = (TextView) view.findViewById(R.id.welcomeAdminText);
        welcomeText.setText("Admin: " + data.getUsername());

        //ID text
        idText = (TextView) view.findViewById(R.id.idAdminText);
        idText.setText("ID: " + data.getId());

        return view;
    }
}