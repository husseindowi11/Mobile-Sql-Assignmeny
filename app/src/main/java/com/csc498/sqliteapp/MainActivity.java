package com.csc498.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();

        ListView listView = (ListView) findViewById(R.id.exam_list);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);

        listView.setAdapter(adapter);


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

            listView.setAdapter(adapter);

        }catch(
                Exception e)

        {
            e.printStackTrace();
        }

        Intent intent = new Intent(getApplicationContext(),Webview.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startActivity(intent);

            }
        });
    }


}