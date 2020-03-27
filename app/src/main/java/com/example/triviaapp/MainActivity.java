package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Button next;
EditText name;
    SharedPreferences.Editor myEdit;
    String current_date_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next=findViewById(R.id.next_button);
        name=findViewById(R.id.name);
        Date date=new Date();

        Calendar now = Calendar.getInstance();
        if(now.get(Calendar.AM_PM) == Calendar.AM){
            // AM
            current_date_time="GAME :: "+getFormattedDate(date)+" "+now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)+" AM";
            System.out.println("datetime1 "+getFormattedDate(date)+" "+now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)+" AM");
        }else{
            // PM
            current_date_time="GAME :: "+getFormattedDate(date)+" "+now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)+" PM";
            System.out.println("datetime2 "+getFormattedDate(date)+" "+now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)+" PM");
        }
        Log.i("datetime",getFormattedDate(date));
//Storing data into SharedPreferences
        SharedPreferences sharedPreferences
                = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);

// Creating an Editor object
// to edit(write to the file)
        myEdit
                = sharedPreferences.edit();


    }

    public void run(View view) {


        if (  name.getText().toString().trim().length()==0 )
        {
            name.setError("Username is not entered");
            name.requestFocus();

            Toast.makeText(getApplicationContext(),"Please enter your name",Toast.LENGTH_LONG).show();
        }
        else {
// Storing the key and its value
            myEdit.putString("name", "Name :: "+name.getText().toString());
            myEdit.putString("time", current_date_time);
            myEdit.commit();

            Intent intent = new Intent(MainActivity.this, Cricketer.class);
            startActivity(intent);
        }
    }
    public static String getFormattedDate(Date date){
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int day=cal.get(Calendar.DATE);

        if(!((day>10) && (day<19)))
            switch (day % 10) {
                case 1:
                    return new SimpleDateFormat("d'st'  MMMM").format(date);
                case 2:
                    return new SimpleDateFormat("d'nd'  MMMM").format(date);
                case 3:
                    return new SimpleDateFormat("d'rd'  MMMM").format(date);
                default:
                    return new SimpleDateFormat("d'th' MMMM").format(date);
            }
        return new SimpleDateFormat("d'th' MMMM").format(date);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(getApplicationContext(),"Please submit your name", Toast.LENGTH_LONG).show();

            super.onKeyDown(keyCode, event);
            return false;
        }
        return false;
    }
}

