package com.example.servington_from_ground_up;

import android.content.Intent;
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
                Intent intent = new Intent(AdminActivity.this, ManageAdminsActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminDeletionActivity.class);
                startActivity(intent);
            }
        });
    }
}