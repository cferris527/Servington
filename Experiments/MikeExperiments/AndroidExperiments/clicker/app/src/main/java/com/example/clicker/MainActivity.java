package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int numClicks = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton = (Button) findViewById(R.id.button);
        TextView mytextview = (TextView) findViewById(R.id.textView);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());


        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View textView) {
                numClicks ++;
                String num = String.valueOf(numClicks);
                mytextview.setText(num);

                
            }
        });
    }





}