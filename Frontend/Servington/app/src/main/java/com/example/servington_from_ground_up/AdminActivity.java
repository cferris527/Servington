package com.example.servington_from_ground_up;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private Button manageButton;
    private Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        manageButton = findViewById(R.id.manageButton);
        deleteButton = findViewById(R.id.deleteButton);

        manageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //TODO: admin account creation activity
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //TODO: post/account deletion activity
            }
        });
    }
}