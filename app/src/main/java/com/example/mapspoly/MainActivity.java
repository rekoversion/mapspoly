package com.example.mapspoly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    databasehelperclass mydb;
    TextView main2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new databasehelperclass(this);
        main2 = (TextView) findViewById(R.id.reg);
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity2();
            }
        });

    }

public void openactivity2(){
    Intent intent= new Intent(this, MainActivity2.class);
     startActivity(intent);
        }
}