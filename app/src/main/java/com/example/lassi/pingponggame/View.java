package com.example.lassi.pingponggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class View extends SurfaceView implements Runnable{

    private boolean playerPlaying;
    private Thread thread = null;

    private Paddle paddle;
    private Paddle cpuPaddle;
    private Ball ball;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder sh;

    public View(Context context){

        super(context);

        paddle = new Paddle(context, 500, 200);
        cpuPaddle = new Paddle(context, 500, 800);
        ball = new Ball(context, 500, 500,1);
        sh = getHolder();
        paint = new Paint();
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

        canvas = sh.lockCanvas();
        canvas.drawBitmap(ball.getBitmap(),ball.getxCord(),ball.getyCord(), paint);
        canvas.drawBitmap(paddle.getBitmap(),paddle.getxCord(),paddle.getyCord(), paint);
        canvas.drawBitmap(cpuPaddle.getBitmap(),cpuPaddle.getxCord(),cpuPaddle.getyCord(), paint);
        sh.unlockCanvasAndPost(canvas);


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
