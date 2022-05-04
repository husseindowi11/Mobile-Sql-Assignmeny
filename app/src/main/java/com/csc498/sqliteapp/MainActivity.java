package com.csc498.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();


        try{

            SQLiteDatabase sql = this.openOrCreateDatabase("exams_db", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS exams(name VARCHAR, date Date)");
//        sql.execSQL("INSERT INTO exams(name,date) VALUES('Mobile Computing','2022-02-09')");
//        sql.execSQL("INSERT INTO exams(name,date) VALUES('Andalusian','2022-02-09')");

            Cursor cursor = sql.rawQuery("SELECT * FROM exams", null);
            int name_index = cursor.getColumnIndex("name");
            int date_index = cursor.getColumnIndex("date");

            cursor.moveToFirst();

            while (cursor != null) {
                String text = cursor.getString(name_index) + " on " + cursor.getString(date_index);

                data.add(text);

                cursor.moveToNext();
            }



        }catch(
                Exception e)

        {
            e.printStackTrace();
        }


    }


}