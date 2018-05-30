package com.example.lassi.pingponggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Start thread
        Thread th = new Thread(){
            @Override
            public void run() {

                try{

                    //Without this, the splashscreen wont wait
                    synchronized (this){
                        wait(3000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                finish();

                //Create new intent
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);

                //Start mainactivity
                startActivity(intent);

                //Animations between the activities
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };
        th.start();
    }

}

