package com.example.triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class History extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        DBHelper db = new DBHelper(this);
        ArrayList<HashMap<String, String>>    userList = db.GetUsers();
        ListView lv = (ListView) findViewById(R.id.listView1);
        ListAdapter adapter = new SimpleAdapter(History.this, userList, R.layout.list_row,new String[]{"time","name","answer1","answer2"}, new int[]{R.id.time,R.id.name, R.id.ans1, R.id.ans2});
        lv.setAdapter(adapter);



    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}