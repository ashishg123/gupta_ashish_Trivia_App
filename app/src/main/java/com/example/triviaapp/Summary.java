package com.example.triviaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Summary extends AppCompatActivity {
    TextView finish, history, user_name, ans_quest1, ans_quest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        finish = findViewById(R.id.finish);
        history = findViewById(R.id.history);
        user_name = findViewById(R.id.user_name);
        ans_quest1 = findViewById(R.id.ans_quest1);
        ans_quest2 = findViewById(R.id.ans_quest2);

        //setting the summary data to views


        //1st activity data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        user_name.setText("Hello" + "Name:" + "," + s1);
        String game_time = sh.getString("time", "");

        //2st activity data
        SharedPreferences sh1 = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s2 = sh1.getString("ans1", "");
        ans_quest1.setText("Who is the best cricketer in the world?" + "\nAnswer" + s2);

        //3rd activity data
        SharedPreferences sh2 = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s3 = sh2.getString("result", "");
        ans_quest2.setText(s3);


        //inser to sqllite database
        DBHelper dbHandler = new DBHelper(Summary.this);
        dbHandler.insertUserDetails(game_time, s1, s2, s3);


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), History.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(getApplicationContext(),"Please finish the Game",Toast.LENGTH_LONG).show();

            super.onKeyDown(keyCode, event);
            return false;
        }
        return false;
    }
}
