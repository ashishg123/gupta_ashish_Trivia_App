package com.example.triviaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cricketer extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    TextView answer1;

    SharedPreferences.Editor myEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cricketer);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        answer1=findViewById(R.id.answer1);
// Storing data into SharedPreferences
        SharedPreferences sharedPreferences
                = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);

// Creating an Editor object
// to edit(write to the file)
         myEdit
                = sharedPreferences.edit();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup rGroup, int checkedId) {

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(radioButton);
                RadioButton r = (RadioButton) radioGroup.getChildAt(idx);
                String selectedText = r.getText().toString();
                System.out.println(idx);//For print Id
                System.out.println(selectedText);//For print Text
                answer1.setText("Answer:: "+selectedText);


                myEdit.putString(
                        "ans1",
                        "Who is the best cricketer in the world?"+"\n"+"Answers::"+"\n"+selectedText);


                myEdit.commit();
            }
        });
        btnDisplay=(Button)findViewById(R.id.next_button1);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(selectedId);
                Intent intent = new Intent(Cricketer.this,NationalFlag.class);
                startActivity(intent);


            }
        });


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