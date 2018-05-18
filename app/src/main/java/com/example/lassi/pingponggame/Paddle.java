package com.example.lassi.pingponggame;

public class Paddle {

    final private int length;
    final private int width;
    private int xCord;
    private int yCord;

    public Paddle(int length, int width, int xCord, int yCord){

        this.length = length;
        this.width = width;
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public void setxCord(int xCord){

        this.xCord = xCord;
    }

    public void setyCord(int yCord){

        this.yCord = yCord;
    }

    public int getLength(){

        return this.length;
    }

    public int getWidth(){

        return this.width;
    }

    public int getxCord(){

        return this.xCord;
    }

    public int getyCord(){

        return this.yCord;
    }
}
