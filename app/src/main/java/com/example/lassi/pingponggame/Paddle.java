package com.example.lassi.pingponggame;

import android.graphics.RectF;

public class Paddle {

    // RectF object of paddle
    private RectF paddleObj;

    // Height and width of paddle
    private float width;
    private float height;

    // X coordinate of paddle
    private float xCord;

    // Y coordinate of paddle
    private float yCord;

    //Paddle speed in pixels
    private float paddleSpeed;

    // The screen length and width in pixels
    private int screenX;
    private int screenY;

    public Paddle(int x, int y, int yCordinate){

        screenX = x;
        screenY = y;

        // Set paddle width
        width = 130;

        // set paddle height
        height = 28;

        // Start mBat in roughly the sceen centre
        xCord = screenX / 2;
        yCord = screenY - yCordinate;

        //Set the starting position
        paddleObj = new RectF(xCord, yCord, xCord + width, yCord + height);

        // Set the paddle speed
        paddleSpeed = screenX;
    }

    //Getter method for the object
    public RectF getRect(){
        return paddleObj;
    }

    public void update(){

        // Paddle not leaving screen
        if(paddleObj.left < 0){ xCord = 0; } if(paddleObj.right > screenX){
            xCord = screenX - (paddleObj.right - paddleObj.left);
        }

        // Update the paddle graphics
        paddleObj.left = xCord;
        paddleObj.right = xCord + width;
    }

    //Method for updating paddle graphoch
    public void updateAIPaddle(int paddleSpeed, int screenX, RectF ballObj){

        //If ball is on the left side of screen
        if(ballObj.left < (screenX/2)){
            //Move paddle to left
            xCord = xCord - paddleSpeed;
        }

        //If paddle is on the right side of the screen
        if(ballObj.right > (screenX/2)){
            //Move paddle to right
            xCord = xCord + paddleSpeed;
        }

        //Paddle not leaving screen
        if(paddleObj.left < 0){ xCord = 0; } if(paddleObj.right > screenX){
            xCord = screenX - (paddleObj.right - paddleObj.left);
        }

        //Update the paddle graphics
        paddleObj.left = xCord;
        paddleObj.right = xCord + width;

    }

    //Setter for the x coordinate
    public void setxCord(float xCord) {

        this.xCord = xCord;
    }

}
