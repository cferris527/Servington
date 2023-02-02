package com.example.as1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
public class LoginActivity extends AppCompatActivity {

    Button backBtn;

    @Override
 protected  void onCreate(Bundle savedInstanceStates)
 {
     super.onCreate(savedInstanceStates);
     setContentView(R.layout.activity_login);

     backBtn = findViewById(R.id.backBtn);

     backBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent back = new Intent(LoginActivity.this, MainActivity.class);
             startActivity(back);
         }
     });
 }



}
