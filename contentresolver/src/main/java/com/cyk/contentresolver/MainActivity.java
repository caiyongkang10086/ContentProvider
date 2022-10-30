package com.cyk.contentresolver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("content://com.cyk.contentresolver.MainActivity/table1");
        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);
        Button modified = findViewById(R.id.modified);
        Button query = findViewById(R.id.query);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("sentence", "i love chenguanyi");
                contentValues.put("years", "999");
                getContentResolver().insert(uri, contentValues);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentResolver().delete(uri,"years = ?", new String[] { "1"});
            }
        });

        modified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("sentence", "");
                getContentResolver().update(uri, contentValues, "sentence = ?", new String[] {"i love chenguanyi"});
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = getContentResolver().query(
                        uri,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                while (cursor.moveToNext()) {
                    @SuppressLint("Range")
                    String sentence = cursor.getString(cursor.getColumnIndex("sentence"));
                    @SuppressLint("Range")
                    int years = cursor.getInt(cursor.getColumnIndex("years"));

                }
            }
        });
    }
}