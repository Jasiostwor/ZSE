package com.example.intencje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFirtsActivity = (Button) findViewById(R.id.btnName);
        Button btnSecondActivity = (Button) findViewById(R.id.btnAdress);
        Button btnThirdActivity = (Button) findViewById(R.id.btnPosition);

        btnFirtsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText urlPlace = (EditText) findViewById(R.id.urlEditor);
                String url = String.valueOf(urlPlace.getText());

                Intent myIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(myIntent);
            }
        });

        btnThirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(myIntent);
            }
        });
    }
}