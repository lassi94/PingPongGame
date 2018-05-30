package com.example.lassi.pingponggame;

import android.graphics.RectF;

import java.util.Random;

public class Ball {

    //Present the RectF object
    private RectF ballObj;

    //Variables for speed and dimension
    private float speedX;
    private float speedY;
    private float width;
    private float height;

    //Place coordinates
    private float xCord;
    private float yCord;

    public Ball(int screenX, int screenY, int speedX, int speedY){

        // Set the ball eidth and height
        width = screenX / 100;
        height = width;

        //Set the ball coordinates in the middle of screen
        xCord = screenX / 2;
        yCord = screenY / 2;

        //Set the speeds
        this.speedX = speedY;
        this.speedY = speedX;

        // Initialize the object
        ballObj = new RectF(xCord, yCord, xCord + width, yCord + height);

    }

    //Get the paddle object
    public RectF getRect(){
        return ballObj;
    }

    // Update the ball position
    public void update(int screenSizeX, int screenSizeY){

        //Update balls left side coordinates
        ballObj.left = ballObj.left + this.speedX;

        //Update balls top side coordinates
        ballObj.top = ballObj.top + this.speedY;

        //Update balls right side coordinates
        ballObj.right = ballObj.left + width;

        //Update balls bottom side coordinates
        ballObj.bottom = ballObj.top - height;

    }

    //Reverse y speed
    public void reverseYSpeed(){

        speedY = -speedY;
    }

    // Reverse x speed
    public void reverseXSpeed(){

        speedX = -speedX;
    }


    //Increase the speed with the passed value
    public void increaseVelocity(int speedX, int speedY){

        this.speedX = speedX;
        this.speedY = speedY;
    }

    //Reset the ball place to center
    public void resetPlace(int x, int y){
        ballObj.left = x / 2;
        ballObj.top = y / 2;
        ballObj.right = x / 2 + width;
        ballObj.bottom = y / 2 - height;
    }

    //Get the x coordinate
    public float getxCord() {
        return xCord;
    }

    //Set the x coordinate
    public void setxCord(float xCord) {
        this.xCord = xCord;
    }

    //Clear the speed
    public void clearSpeed(int startSpeedX, int startSpeedY){
        speedY = startSpeedY;
        speedX = startSpeedX;
    }

    public float getmSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }
}
