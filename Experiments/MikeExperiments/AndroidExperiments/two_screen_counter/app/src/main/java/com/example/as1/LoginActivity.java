package com.example.as1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
public class LoginActivity extends AppCompatActivity {

    Button backBtn;

    @Override
 protected  void onCreate(Bundle savedInstanceStates)
 {
     super.onCreate(savedInstanceStates);
     setContentView(R.layout.activity_login);

     backBtn = findViewById(R.id.backBtn);

     Button send = findViewById(R.id.send);
     EditText inputName = findViewById(R.id.inputName);

     send.setOnClickListener(v -> {
         // get the value which input by user in EditText and convert it to string
         String str = inputName.getText().toString();
         // Create the Intent object of this class Context() to Second_activity class
         Intent intent = new Intent(getApplicationContext(), MainActivity.class);
         // now by putExtra method put the value in key, value pair key is
         // message_key by this key we will receive the value, and put the string
         intent.putExtra("message_key", str);
         // start the Intent
         startActivity(intent);
     });

     backBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent back = new Intent(LoginActivity.this, MainActivity.class);
             startActivity(back);
         }
     });
 }



}
