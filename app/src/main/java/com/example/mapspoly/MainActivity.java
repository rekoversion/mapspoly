package com.example.mapspoly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    databasehelperclass mydb;
    TextView main2;
    Button admin;
    private static final int ERROR_DIALOG__REQUEST =9001;
    private static final String TAG =" MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new databasehelperclass(this);
        main2 = (TextView) findViewById(R.id.reg);
        admin =(Button)findViewById(R.id.button1);

        if(IsServicesOK()==true)
        {init();}


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity3();
            }
        });
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity2();
            }
        });

    }
    private void init(){
        Button btnmap = (Button)findViewById(R.id.btnmap);
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,MainActivity4.class);
                startActivity(intent);
            }
        });
    }

     public void openactivity2(){
    Intent intent= new Intent(this, MainActivity2.class);
     startActivity(intent);
        }
        public void openactivity3(){
        Intent intent= new Intent(this, MainActivity3.class);
        startActivity(intent);
        }
    public boolean IsServicesOK() {
        Log.d(TAG, "isServicesOk: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS)//AIzaSyA0KoqSfSjLQvHTH7nmr9wr0uGm07taZMM
        {
            Log.d(TAG, "isServicesOk: checking google services Available");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServicesOk: error");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG__REQUEST);
            dialog.show();

        } else {
            Toast.makeText(this, "we cant make requests", Toast.LENGTH_SHORT).show();

        } return false;
    }
}