package com.example.lassi.pingponggame;

public class Player {

    private String name;
    private int highScore = 0;
    private int playerID;

    private static int idCounter = 0;

    public Player(String name, int highScore){

        this.playerID = idCounter++;

        this.name = name;
        this.highScore = highScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getName() {
        return name;
    }

    public int getHighScore() {
        return highScore;
    }

    @Override
    public String toString() {
        return name;
    }
}
