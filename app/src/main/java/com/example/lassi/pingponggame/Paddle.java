package com.example.lassi.pingponggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Paddle {

    private Bitmap paddlePic;

    private int xCord;
    private int yCord;

    public Paddle(int xCord, int yCord){

        this.xCord = xCord;
        this.yCord = yCord;

        //paddlePic = BitmapFactory.decodeResource();
    }

    public void setxCord(int xCord){

        this.xCord = xCord;
    }

    public void setyCord(int yCord){

        this.yCord = yCord;
    }

    public int getxCord(){

        return this.xCord;
    }

    public int getyCord(){

        return this.yCord;
    }

    public void updtaePaddlePos(){

    }
}
