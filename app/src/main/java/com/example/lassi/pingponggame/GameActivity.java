package com.example.lassi.pingponggame;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends AppCompatActivity {


    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get screen dimensions
        Display disp = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        //Set the gameview into gameactivity view
        view = new View(this, size.x, size.y);
        setContentView(view);


    }

    public void onResume(){
        super.onResume();
        view.resume();
    }

    public void onPause(){
        super.onPause();
        try {
            view.pause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
