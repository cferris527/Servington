package com.example.servington_from_ground_up.organization;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.servington_from_ground_up.R;

/**
 * Activity that holds and displays the necessary fragments for Organization by
 * a bottom navigation bar.
 *
 * @author Connor Ferris
 */
public class HomeOrganizationActivity extends AppCompatActivity {

    Button homeBtn;
    Button postsBtn;
    Button dmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_organization);

        homeBtn = findViewById(R.id.homeBtn);
        postsBtn = findViewById(R.id.postsBtn);
        dmBtn = findViewById(R.id.dmBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTo(OrganizationHomeFragment.class);
            }
        });
        postsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTo(OrganizationPostsFragment.class);
            }
        });
        dmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTo(OrganizationDmFragment.class);
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