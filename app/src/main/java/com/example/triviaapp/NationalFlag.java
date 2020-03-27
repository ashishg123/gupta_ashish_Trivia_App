package com.example.triviaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class NationalFlag extends AppCompatActivity {
    CheckBox White, Yellow, Orange, Green;
    TextView answer2;
    SharedPreferences.Editor myEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nationalflag);
        White = (CheckBox)findViewById(R.id.checkBox1);
        Yellow = (CheckBox)findViewById(R.id.checkBox2);
        Orange = (CheckBox)findViewById(R.id.checkBox3);
        Green = (CheckBox)findViewById(R.id.checkBox4);
        answer2=findViewById(R.id.answer2);
        Button btn = (Button)findViewById(R.id.next_button1);
        SharedPreferences sharedPreferences
                = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);

// Creating an Editor object
         myEdit = sharedPreferences.edit();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "What are the colors in the national flag?::"+"\n"+"Answers::";
                if(White.isChecked()){
                    result += "\nWhite";
                }
                if(Yellow.isChecked()){
                    result += "\nYellow";
                }
                if(Orange.isChecked()){
                    result += "\nOrange";
                }
                if(Green.isChecked()){
                    result += "\nGreen";
                }
// Storing the key and its value
                myEdit.putString("result", result);

                myEdit.commit();

                Intent intent = new Intent(NationalFlag.this,Summary.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str="";
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox1:
                str = checked?"White Selected":"White Deselected";
                break;
            case R.id.checkBox2:
                str = checked?"Yellow Selected":"Yellow Deselected";
                break;
            case R.id.checkBox3:
                str = checked?"Orange Selected":"Orange Deselected";
                break;
            case R.id.checkBox4:
                str = checked?"Green Selected":"Green Deselected";
                break;
        }

        answer2.setText(str);
       // Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(getApplicationContext(),"Please select the answer",Toast.LENGTH_LONG).show();

            super.onKeyDown(keyCode, event);
            return false;
        }
        return false;
    }
}