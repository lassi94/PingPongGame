package com.example.lassi.pingponggame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class GameView extends SurfaceView implements Runnable {

    // Thread object
    private Thread thread = null;

    //Create needed objects
    Random random;
    private Bitmap gameIsOver;
    SurfaceHolder holder;

    // Volatile boolean variable to check if player is playing or not
    //Volatile because it is accessed from outride and inside thread
    volatile boolean isPlayerPlaying;

    // Boolean variable for pause
    boolean paused = true;
    //To check if game is over
    private boolean gameOver = false;

    //Canvas and paint objects for drawing
    Canvas canvas;
    Paint paint;

    // The size of the screen in pixels
    public int screenX;
    public int screenY;

    //Startspeed for the ball
    private int startSpeedX = 7;
    private int startSpeedY = 6;

    //Boolean variable to check if ball needs to be resetted
    private boolean setNewPlace = false;

    //Paddles
    Paddle paddle1;
    Paddle paddleAI;

    // Ball
    Ball ball;

    //Objects for highscore sharing between activities
    SharedPreferences pref;
    List<Integer> players;

    //For the sound
    MediaPlayer media1;
    MediaPlayer media2;

    //Score variable to hold the score
    public int score = 0;

    // Lives variable to keep track of lives
    private int lives = 3;

    public GameView(Context context, int x, int y) {
        super(context);

        // Set the screen width and height
        screenX = x;
        screenY = y;

        // Declare objects for drawing
        holder = getHolder();
        paint = new Paint();

        //Random generator for the speed setting in different levels
        random = new Random();

        // Paddles
        paddle1 = new Paddle(screenX, screenY, 150);
        paddleAI = new Paddle(screenX, screenY, 1200);

        //Ball
        ball = new Ball(screenX, screenY, startSpeedX, startSpeedY);

        //For sharing the socre to highscore activity
        pref = context.getSharedPreferences("players", Context.MODE_PRIVATE);
        players = new ArrayList<>();

        //Add a default value to the list
        players.add(pref.getInt("num1", 0));

        //Declare media objects for sound
        media1 = MediaPlayer.create(context, R.raw.gameon);
        media2 = MediaPlayer.create(context, R.raw.gameover);

        //Declare bitmap for drawing the gameover text
        gameIsOver = BitmapFactory.decodeResource(context.getResources(), R.drawable.gameover);

    }

    @Override
    public void run() {

        while (isPlayerPlaying) {

            //Start game background sound
            media1.start();

            //If game is not paused
            if (!paused) {
                update();
            }

            //If ball hits top or bottom of the screen
            if(setNewPlace){

                //Reset coordinates to center
                ball.resetPlace(screenX, screenY);
                setNewPlace = false;
                //Pause the game
                paused = true;
            }

            // Draw the objects to the screen
            draw();

        }


    }

    public void restart(){

        // reset ball place to center
        ball.resetPlace(screenX, screenY);

        //Set the speed to same level
        ball.clearSpeed(startSpeedX, startSpeedY);

        // if lives equals 0
        if(lives == 0) {

            //Stop background sound
            media1.stop();
            //Start gameover sound
            media2.start();

            gameOver = true;

            //Read the score value and share it to highscore activity
            SharedPreferences.Editor editor = pref.edit();

            for(int i = 0; i < players.size(); i++)
            {
                int index = i+1;
                if (pref.contains("num"+index)){

                    if(pref.getInt("num"+index, 0) < score){
                        editor.putInt("num"+index, score);
                    }
                }
            }
            editor.commit();
        }

    }

    public void update() {

        //Update user controlled paddle
        paddle1.update();

        //update ball coordinates
        ball.update(screenX, screenY);

        //If balls top y coordinate is smaller than the screens height divided by 2.2
        if(ball.getRect().top < (screenY/2.2)){

            //Update AI paddle
            AI(paddleAI);
        }

        // Check if ball intersects with human controlled paddle
        if(RectF.intersects(paddle1.getRect(), ball.getRect())) {

            //Reverse the y speed of ball
            ball.reverseYSpeed();

            //Reverse x speed or not to increase unpredictivity
            setRandomXSpeed(ball);

        }

        //If ball intersects with AI paddle
        if(RectF.intersects(paddleAI.getRect(), ball.getRect())) {

            //Reverse speeds
            ball.reverseYSpeed();
            setRandomXSpeed(ball);
        }

        // If ball hits bottom
        if(ball.getRect().bottom > screenY){

            // decrease lives
            lives--;
            if(lives == 0){

                //Pause the game
                paused = true;

                //Reset ball and restart
                restart();
            }

            setNewPlace = true;
        }

        // If ball hits top screen
        if(ball.getRect().top < 0){

            //Increase score
            score++;

            //define gamelevel according to score and set random speeds to increase unpredictivity
            switch (score){

                case 3: ball.increaseVelocity(random.nextInt(9 - 7 + 1) + 7, random.nextInt(9 - 7 + 1) + 7);
                    break;
                case 5: ball.increaseVelocity(random.nextInt(11 - 9 + 1) + 9, random.nextInt(11 - 9 + 1) + 9);
                    break;
                case 7: ball.increaseVelocity(random.nextInt(14 - 12 + 1) + 12, random.nextInt(14 - 12 + 1) + 12);
                    break;
                case 9: ball.increaseVelocity(random.nextInt(17 - 15 + 1) + 15, random.nextInt(17 - 15 + 1) + 15);
                    break;
            }

            //reset ball
            setNewPlace = true;

        }

        // If ball hits left wall
        if(ball.getRect().left < 0){

            ball.reverseXSpeed();

        }

        // If ball hits right wall
        if(ball.getRect().right > screenX){

            ball.reverseXSpeed();

        }
    }
    // Draw the objects
    public void draw() {

        //Check if screen is valid
        if (checkValid(holder)) {


            // Lock the canvas ready to draw
            canvas = holder.lockCanvas();

            // Set background color
            canvas.drawColor(Color.argb(255, 0, 43, 112));

            // Set new color
            paint.setColor(Color.argb(255, 255, 255, 255));

            // Draw the Paddles
            canvas.drawRect(paddle1.getRect(), paint);
            canvas.drawRect(paddleAI.getRect(), paint);

            // Draw the ball
            canvas.drawRect(ball.getRect(), paint);

            // Set new color
            paint.setColor(Color.argb(255, 255, 255, 255));

            // Draw the socre and lives
            paint.setTextSize(40);
            canvas.drawText("Score: " + score + "   Lives: " + lives, 10, 50, paint);

            //If game is over
            if(gameOver == true){

                //Draw the gameover text as a bitmap
                canvas.drawBitmap(gameIsOver, screenX/2 -194, screenY/2 - 38, paint);
            }

            // Draw everything to the screen
            holder.unlockCanvasAndPost(canvas);
        }

    }

    // If the Activity is paused/stopped shutdown our thread.
    public void pause() {
        isPlayerPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // If the Activity starts/restarts start our thread.
    public void resume() {
        isPlayerPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    //Detect taps, clicks and draggind movement
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            //If tapped set new x coordinates
            case MotionEvent.ACTION_DOWN:

                if(gameOver == false){

                    paused = false;

                    paddle1.setxCord(motionEvent.getX());
                }

                break;

            //If dragged set new x coordinates
            case MotionEvent.ACTION_MOVE:

                paddle1.setxCord(motionEvent.getX());
                break;

            case MotionEvent.ACTION_UP:

                paddle1.setxCord(motionEvent.getX());

                break;
        }
        return true;
    }

    //Method for moving AI paddle
    public void AI(Paddle paddle){

        //get ball coordinates
        RectF ballCoordinates = ball.getRect();

        //Update paddle x coordinates and set the speed
        paddle.updateAIPaddle(15, screenX, ballCoordinates);
    }

    //Check if screen is valid for drawing
    public boolean checkValid(SurfaceHolder h){
        if(h.getSurface().isValid()){
            return true;
        }else{
            return false;
        }
    }

    //Set random x speed
    public void setRandomXSpeed(Ball ball){

        // Generate a random number either 0 or 1
        Random generator = new Random();
        int number = generator.nextInt(3);

        if(number == 0){
            ball.reverseXSpeed();
        }
    }

    //Stop the music
    public void musicStop(){
        media1.stop();
    }


}
