package com.example.lassi.pingponggame;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends AppCompatActivity {

    //Present game view object
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Access screen
        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        // Initialize GameView and set it as the view
        gameView = new GameView(this, size.x, size.y);
        setContentView(gameView);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Call the GameView resume method
        gameView.resume();
    }

    // If player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Call GameView pause method
        gameView.pause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        gameView.musicStop();
    }
}
