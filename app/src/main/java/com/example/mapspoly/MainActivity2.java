package com.example.mapspoly;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Lifecycle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    databasehelperclass myDb;
    EditText user,pass,adno;
    Button regdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDb = new databasehelperclass(this);

        user = (EditText)findViewById(R.id.reguser);
        pass = (EditText)findViewById(R.id.regpass);
        adno = (EditText)findViewById(R.id.regadno);
        regdata = (Button) findViewById(R.id.button1);
          AddData();




    }
    public void AddData(){
        regdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean insertdata = myDb.insertdata(user.getText().toString(),pass.getText().toString(),adno.getText().toString());
           if (insertdata == true) {
               Toast.makeText(MainActivity2.this, "data inserted", Toast.LENGTH_LONG).show();
               backtoactivity();


           }
           else {
               Toast.makeText(MainActivity2.this, "data not inserted", Toast.LENGTH_LONG).show();
           }

            }
        });
    }
    public void backtoactivity(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);


    }

}