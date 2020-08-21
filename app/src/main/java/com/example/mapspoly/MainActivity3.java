package com.example.mapspoly;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
    databasehelperclass mydb;
    Button viewdb,previous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        viewdb = (Button)findViewById(R.id.viewdb);
        previous=(Button)findViewById(R.id.previous1);
        mydb = new databasehelperclass(this);
        ViewAll();
    }
    public void ViewAll(){
        viewdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = mydb.GetData();
                if (res.getCount() == 0) {
                    ShowMsg("error", "nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID: "+ res.getString(0) +"\n");//n-1
                    buffer.append("user: "+ res.getString(1) +"\n");
                    buffer.append("pass: "+ res.getString(2) +"\n");
                    buffer.append("adno: "+ res.getString(3) +"\n");
                }
                ShowMsg("Data", buffer.toString());
            }
        });
    }
    public void ShowMsg(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                 builder.setCancelable(true);
                 builder.setTitle(title);
                 builder.setMessage(message);
                 builder.show();
    }

}