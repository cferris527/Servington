package com.example.servington_from_ground_up;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
                FragmentManager f = getSupportFragmentManager();
                f.beginTransaction()
                        .replace(R.id.fragmentContainerView, VolunteerHomeFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
        postsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager f = getSupportFragmentManager();
                f.beginTransaction()
                        .replace(R.id.fragmentContainerView, VolunteerPostsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager f = getSupportFragmentManager();
                f.beginTransaction()
                        .replace(R.id.fragmentContainerView, VolunteerSearchFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
        dmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager f = getSupportFragmentManager();
                f.beginTransaction()
                        .replace(R.id.fragmentContainerView, VolunteerDmFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
    }

}