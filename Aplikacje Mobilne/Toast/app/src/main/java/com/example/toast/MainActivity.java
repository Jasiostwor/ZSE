package com.example.toast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPrzycisk = (Button) findViewById(R.id.button);

        btnPrzycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText (getApplicationContext (), "Jan Horodecki", Toast.LENGTH_SHORT);
                toast.setMargin ( 50 , 50 );
                toast.show ();

            }
        });
    }
}