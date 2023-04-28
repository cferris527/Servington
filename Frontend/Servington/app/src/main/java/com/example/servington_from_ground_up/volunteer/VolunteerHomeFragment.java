package com.example.servington_from_ground_up.volunteer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.servington_from_ground_up.MainActivity;
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.multi.ChoosePictureFragment;
import com.example.servington_from_ground_up.utils.ProfilePicture;
import com.example.servington_from_ground_up.utils.Singleton;

/**
 * Fragment that acts as a home page for Volunteer. This fragment is the first one
 * loaded upon login. Displays Volunteer details and option to log out/change settings.
 *
 * @author Connor Ferris
 */
public class VolunteerHomeFragment extends Fragment {
    View view;
    ProfilePicture pfp;
    Singleton data = Singleton.getInstance();
    ImageView picture;
    TextView welcomeText;
    TextView idText;
    TextView emailText;
    TextView phoneText;
    TextView displayName;
    Button logoutBtn;
    Button pictureBtn;
    Button settingsBtn;
    public VolunteerHomeFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     */
    public static VolunteerHomeFragment newInstance() {return new VolunteerHomeFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_volunteer_home, container, false);
        pfp = new ProfilePicture();

        picture = view.findViewById(R.id.profilePictureVol);
        picture.setImageResource(pfp.determinePicture(data.getProfilePicture()));

        //Welcome Text
        welcomeText = (TextView) view.findViewById(R.id.welcomeVolText);
        welcomeText.setText("Welcome, " + data.getUsername() + "!");

        //ID text
        idText = (TextView) view.findViewById(R.id.idVolText);
        idText.setText("ID: " + data.getId());

        //Email text
        emailText = (TextView) view.findViewById(R.id.emailVolText);
        emailText.setText(data.getEmail());

        //Phone text
        phoneText = (TextView) view.findViewById(R.id.phoneVolText);
        phoneText.setText(data.getPhone());

        //Display name
        displayName = (TextView) view.findViewById(R.id.DNvolText);
        displayName.setText(data.getDisplayName());

        //Logout Button
        logoutBtn = (Button) view.findViewById(R.id.logoutVolBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                data.logOut();
            }
        });

        //Settings Button
        settingsBtn = (Button) view.findViewById(R.id.settingsVolBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //Changes to Settings Fragment
            public void onClick(View view) {
                Fragment fragment = new VolunteerSettingsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //Picture Button
        pictureBtn = (Button) view.findViewById(R.id.picVolBtn);
        pictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ChoosePictureFragment();
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