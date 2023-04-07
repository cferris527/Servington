package com.example.servington_from_ground_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servington_from_ground_up.utils.Singleton;

public class BannedActivity extends AppCompatActivity {

    Button okButton;
    Singleton data = Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banned);

        okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BannedActivity.this, MainActivity.class);
                startActivity(intent);
                data.logOut();

            }
        });

    }
}