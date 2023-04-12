package com.example.servington_from_ground_up.volunteer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.servington_from_ground_up.R;

/**
 * Activity that holds and displays the necessary fragments for Volunteer by
 * a bottom navigation bar.
 *
 * @author Connor Ferris
 */
public class HomeVolunteerActivity extends AppCompatActivity {

    Button homeBtn;
    Button postsBtn;
    Button searchBtn;
    Button dmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_volunteer);

        homeBtn = findViewById(R.id.homeBtn);
        postsBtn = findViewById(R.id.postsBtn);
        searchBtn = findViewById(R.id.searchBtn);
        dmBtn = findViewById(R.id.dmBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTo(VolunteerHomeFragment.class);
            }
        });
        postsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTo(VolunteerPostsFragment.class);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTo(VolunteerSearchFragment.class);
            }
        });
        dmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTo(VolunteerDmFragment.class);
            }
        });

    }

    /**
     * Used to change fragments
     * @param c fragment to be changed to
     */
    private void changeTo(Class c) {
        FragmentManager f = getSupportFragmentManager();
        f.beginTransaction()
                .replace(R.id.fragmentContainerView, c, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }

}