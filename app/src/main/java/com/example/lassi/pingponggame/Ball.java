package com.example.lassi.pingponggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Ball {

    private Bitmap ballPic;
    private int speed;
    private int xCord;
    private int yCord;

    public Ball(Context context, int xCord, int yCord, int speed){

        this.speed = speed;
        this.xCord = xCord;
        this.yCord = yCord;

        ballPic = BitmapFactory.decodeResource(context.getResources(), R.drawable.asset4);
    }

    public void setSpeed(int speed){

        this.speed = speed;
    }

    public void setxCord(int xCord){

        this.xCord = xCord;
    }

    public void setyCord(){

        this.yCord = yCord;
    }

    public int getSpeed(){

        return this.speed;
    }

    public int getxCord(){

        return this.xCord;
    }

    public int getyCord(){

        return this.yCord;
    }

    public Bitmap getBitmap(){
        return this.ballPic;
    }

    public void updateBallPos(){

    }
}
