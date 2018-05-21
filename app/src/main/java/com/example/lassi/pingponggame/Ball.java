package com.example.lassi.pingponggame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewCompat;


public class Ball {

    private Bitmap ballPic;
    private View view;
    private int speedX;
    private int speedY;
    private int xCord;
    private int yCord;
    private int dy;
    private int dx;

    public Ball(Context context, int xCord, int yCord, int speedX, int speedY){

        this.speedX = speedX;
        this.speedY = speedY;
        this.xCord = xCord;
        this.yCord = yCord;

        ballPic = BitmapFactory.decodeResource(context.getResources(), R.drawable.asset4);
    }

    public void move(int width, int height, int ballWidth){

        xCord += speedX;
        yCord += speedY;


            int ballHitRight = width - ballWidth/2;
            int ballHitLeft = ballWidth / 2;

            if (xCord < ballHitLeft) {

                speedX = -speedX;
            }
            if (xCord > ballHitRight) {
                speedX = -speedX;
            }
            if(this.getyCord() < -15 || this.getyCord() > height){
                check();
            }


    }

    public void setSpeedX(int speed){

        this.speedX = speed;
    }

    public void setSpeedY(int speed){
        this.speedY = speed;
    }

    public void setxCord(int xCord){

        this.xCord = xCord;
    }

    public void setyCord(){

        this.yCord = yCord;
    }

    public int getSpeedX(){

        return this.speedX;
    }

    public int getSpeedY(){
        return this.speedY;
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

    public boolean check(){
        return true;
    }


}
