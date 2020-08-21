package com.example.mapspoly;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class databasehelperclass  extends SQLiteOpenHelper {
     public static final String DATABASE_NAME ="user.db";
     public static final String TABLE_NAME ="usertable";
     public static final String COL_1="ID";
     public static final String COL_2 ="user";
     public static final String COL_3 ="pass";
     public static final String COL_4 = "adno";
     public databasehelperclass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, pass TEXT,adno INTEGER  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
      db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
      onCreate(db);
    }
    public boolean insertdata(String user, String pass, String adno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,user);
        contentValues.put(COL_3,pass);
        contentValues.put(COL_4,adno);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }


    public Cursor GetData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

}
