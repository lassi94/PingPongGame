package com.example.lassi.pingponggame;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HighScore extends AppCompatActivity {


    //Present the objects
    TextView view1;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        //Fin the text field in layout xml file with id
        view1 = (TextView)findViewById(R.id.number2);

        //Get the list which is provided by game activity
        pref = getSharedPreferences("players", Context.MODE_PRIVATE);

        //Get the correct value and set text
        view1.setText("The Best Score: " + pref.getInt("num1", 0));


    }
}
