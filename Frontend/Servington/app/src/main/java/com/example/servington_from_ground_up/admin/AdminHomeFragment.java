package com.example.servington_from_ground_up.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.servington_from_ground_up.MainActivity;
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Singleton;

/**
 * Fragment that acts as a home page for admin. This fragment is the first one
 * loaded upon login. Displays Admin details and option to log out.
 *
 * @author Connor Ferris
 */
public class AdminHomeFragment extends Fragment {

    View view;
    Singleton data = Singleton.getInstance();
    TextView welcomeText;
    TextView idText;
    Button logoutBtn;

    public AdminHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static AdminHomeFragment newInstance() {
        return new AdminHomeFragment();
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

        //Logout Button
        logoutBtn = (Button) view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                data.logOut();
            }
        });
        return view;
    }
}