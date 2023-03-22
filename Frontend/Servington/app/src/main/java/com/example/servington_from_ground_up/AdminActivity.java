package com.example.servington_from_ground_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servington_from_ground_up.utils.Singleton;

/**
 * Admin account type main page.
 *
 * @author Connor Ferris
 */
public class AdminActivity extends AppCompatActivity {

    private Button manageButton;
    private Button deleteButton;

    private TextView adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        manageButton = findViewById(R.id.manageButton);
        deleteButton = findViewById(R.id.deleteButton);
        adminName = findViewById(R.id.adminName);
       
        Singleton data = Singleton.getInstance();

        adminName.setText("Admin: " + data.getDisplayName());

        //button that leads to managing admins
        manageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ManageAdminsActivity.class);
                startActivity(intent);
            }
        });

        //button that leads to deletion of accounts/posts
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