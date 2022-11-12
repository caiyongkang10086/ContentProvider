package com.cvte.visitdataprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = findViewById(R.id.buttonAdd);
        Button delete = findViewById(R.id.buttonDelete);
        Button query = findViewById(R.id.buttonQuery);
        Button update = findViewById(R.id.buttonUpdate);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.cvte.sqlite.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "The first line of Android");
                values.put("author", "guolin");
                values.put("pages", 1024);
                values.put("price", 48.8);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                Log.d(TAG, "id : " + newId);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.cvte.sqlite.provider/book/" + newId);
                getContentResolver().delete(uri,null,null);
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.cvte.sqlite.provider/book");
                Cursor cursor = getContentResolver().query(uri,null, null, null, null, null);
                if(cursor != null) {
                    while (cursor.moveToNext()) {
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                        @SuppressLint("Range") int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "name : " + name);
                        Log.d(TAG, "author : " + author);
                        Log.d(TAG, "pages : " + pages);
                        Log.d(TAG, "price : " + price);
                    }
                    cursor.close();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.cvte.sqlite.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "The");
                values.put("author", "guolin");
                values.put("pages", 999);
                values.put("price", 38.8);
                getContentResolver().update(uri,values,null,null);
            }
        });
    }
}