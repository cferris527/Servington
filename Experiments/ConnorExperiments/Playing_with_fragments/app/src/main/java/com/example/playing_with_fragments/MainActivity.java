package com.example.playing_with_fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button swapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        CoolFragment c = new CoolFragment();

        swapBtn = findViewById(R.id.swapBtn);
        swapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager f = getSupportFragmentManager();

                f.beginTransaction()
                        .replace(R.id.fragmentContainerView, MathFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
                System.out.println("SWITCHED_________________________________");
            }
        });
    }
}