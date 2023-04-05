package com.example.servington_from_ground_up.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.servington_from_ground_up.R;

public class HomeAdminActivity extends AppCompatActivity {

    Button homeBtn;
    Button adminsBtn;
    Button reportsBtn;
    Button banBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        homeBtn = findViewById(R.id.homeAdminBtn);
        adminsBtn = findViewById(R.id.adminsBtn);
        reportsBtn = findViewById(R.id.reportsBtn);
        banBtn = findViewById(R.id.banBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTo(AdminHomeFragment.class);
            }
        });
        adminsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });
        reportsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {changeTo(AdminReportsFragment.class);}
        });
        banBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
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
                .replace(R.id.fragmentContainerView2, c, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }
}