package com.example.lassi.pingponggame;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //Present objects
    private Button btnStart;
    private Button btnround2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the buttons in layout xml file with ids and set clickliesteners to them
        btnStart = (Button)findViewById(R.id.btnstart);
        btnStart.setOnClickListener(this);

        btnround2 = (Button) findViewById(R.id.btnround2);
        btnround2.setOnClickListener(this);

    }

    //If button is clicked
    @Override
    public void onClick(View v) {

        //If highscore button is pressed
        if(btnround2.isPressed()){

            //Start new activity
            Intent intent = new Intent(this, HighScore.class);
            startActivity(intent);

        }

        //If start game button is pressed
        if(btnStart.isPressed()){

            //Start new activity
            Intent intentGame = new Intent(this, GameActivity.class);
            startActivity(intentGame);
        }
    }
}
