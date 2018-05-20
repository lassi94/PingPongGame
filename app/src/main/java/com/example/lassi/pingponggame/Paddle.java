package com.example.lassi.pingponggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Paddle {

    private Bitmap paddlePic;
    private boolean touch;

    private float xCord;
    private float yCord;
    private int paddleSpeed;

    private int dx;
    private int dy;

    public Paddle(Context con, float xCord, float yCord){

        this.xCord = xCord;
        this.yCord = yCord;
        paddleSpeed = 10;
        touch = false;

        paddlePic = BitmapFactory.decodeResource(con.getResources(), R.drawable.asset3);
    }

    public void setxCord(float xCord){

        this.xCord = xCord;
    }

    public void setyCord(float yCord){

        this.yCord = yCord;
    }

    public float getxCord(){

        return this.xCord;
    }

    public float getyCord(){

        return this.yCord;
    }

    public Bitmap getBitmap(){

        return this.paddlePic;
    }

    public int getPaddleSpeed(){

        return this.paddleSpeed;
    }

    public void touched(){
        touch = true;
    }

    public void notTouched(){
        touch = false;
    }

    public void updtaePaddlePos(){

        if(touch){

        }
    }
}
