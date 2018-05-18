package com.example.lassi.pingponggame;

public class Ball {

    final private int diameter;
    private int speed;
    private int xCord;
    private int yCord;

    public Ball(int diameter, int xCord, int yCord, int speed){

        this.diameter = diameter;
        this.speed = speed;
        this.xCord = xCord;
        this.yCord = yCord;
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

    public int getDiameter(){

        return this.diameter;
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
}
