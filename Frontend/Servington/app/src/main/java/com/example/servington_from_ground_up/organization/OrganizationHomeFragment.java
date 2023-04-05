package com.example.servington_from_ground_up.organization;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.servington_from_ground_up.MainActivity;
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Singleton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganizationHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganizationHomeFragment extends Fragment {
    View view;
    Singleton data = Singleton.getInstance();
    TextView welcomeText;
    TextView idText;
    Button logoutBtn;
    Button settingsBtn;
    public OrganizationHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static OrganizationHomeFragment newInstance(String param1, String param2) {
        OrganizationHomeFragment fragment = new OrganizationHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_organization_home, container, false);

        //Welcome Text
        welcomeText = (TextView) view.findViewById(R.id.welcomeOrgText);
        welcomeText.setText("Welcome, " + data.getUsername() + "!");

        //ID text
        idText = (TextView) view.findViewById(R.id.idOrgText);
        idText.setText("ID: " + data.getId());

        //Logout Button
        logoutBtn = (Button) view.findViewById(R.id.logoutOrgBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                data.logOut();
            }
        });

        //Settings Button
        settingsBtn = (Button) view.findViewById(R.id.settingsOrgBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //Changes to Settings Fragment
            public void onClick(View view) {
                Fragment fragment = new OrganizationDmFragment();
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