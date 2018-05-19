package com.example.lassi.pingponggame;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnStart;
    private Button btnround2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btnstart);
        btnStart.setOnClickListener(this);

        btnround2 = (Button) findViewById(R.id.btnround2);
        btnround2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(btnround2.isPressed()){

            Intent intent = new Intent(this, HighScore.class);
            startActivity(intent);

        }

        if(btnStart.isPressed()){

            Intent intentGame = new Intent(this, GameActivity.class);
            startActivity(intentGame);
        }
    }
}
