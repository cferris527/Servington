package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button settBtn;
    Button logBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.toCounterBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CounterActivity.class);
                startActivity(intent);
            }
        });

        settBtn = findViewById(R.id.settingsBtn);

        settBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        logBtn = findViewById(R.id.logBtn);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tolog = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(tolog);
            }
        });

        TextView receiver_msg;

        receiver_msg = findViewById(R.id.recievedName);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        receiver_msg.setText("Hello " + str);
    }


}