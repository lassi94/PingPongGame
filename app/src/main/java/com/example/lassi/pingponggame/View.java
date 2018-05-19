package com.example.lassi.pingponggame;

import android.content.Context;
import android.view.SurfaceView;

public class View extends SurfaceView implements Runnable{

    private boolean playerPlaying;
    private Thread thread = null;

    public View(Context context){

        super(context);
    }

    @Override
    public void run() {

        do{

            updateFrame();
            drawFrame();
            controlFrame();


        }while(playerPlaying == true);

    }

    private void drawFrame(){

    }

    private void updateFrame() {
    }

    private void controlFrame() {
    }

    public void pause() throws InterruptedException {

        playerPlaying = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){

        playerPlaying = true;
        thread.start();
    }
}
