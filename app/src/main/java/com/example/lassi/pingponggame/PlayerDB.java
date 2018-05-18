package com.example.lassi.pingponggame;

import java.util.ArrayList;

public class PlayerDB {

    ArrayList<Player> playerDatabase;

    public PlayerDB(){

        playerDatabase = new ArrayList<>();
    }

    public void addPlayer(Player player){

        playerDatabase.add(player);
    }

    public Player getPlayer(int id){

        return playerDatabase.get(id);
    }
}
