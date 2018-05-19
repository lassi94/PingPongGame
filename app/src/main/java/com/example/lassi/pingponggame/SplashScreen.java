package com.example.lassi.pingponggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread th = new Thread(){
            @Override
            public void run() {

                try{
                    synchronized (this){
                        wait(3000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                finish();

                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        };
        th.start();
    }

}
