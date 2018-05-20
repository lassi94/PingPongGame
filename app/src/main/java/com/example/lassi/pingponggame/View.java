package com.example.lassi.pingponggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class View extends SurfaceView implements Runnable{

    private boolean playerPlaying = true;
    private boolean check;
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

        check = checkValid(sh);

        if(check == true) {

            canvas = sh.lockCanvas();
            canvas.drawColor(Color.BLUE);
            canvas.drawBitmap(ball.getBitmap(), ball.getxCord(), ball.getyCord(), paint);
            canvas.drawBitmap(paddle.getBitmap(), paddle.getxCord(), paddle.getyCord(), paint);
            canvas.drawBitmap(cpuPaddle.getBitmap(), cpuPaddle.getxCord(), cpuPaddle.getyCord(), paint);
            sh.unlockCanvasAndPost(canvas);

        }
    }

    private void updateFrame() {
    }

    private void controlFrame() {

        try {
            thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    @Override
    public boolean onTouchEvent(MotionEvent event){

        try {
            thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Release event
        if(event.getAction() == MotionEvent.ACTION_UP){
            paddle.setxCord(event.getX());
            paddle.setyCord(event.getY());
        }

        //Push event
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            paddle.setxCord(event.getX());
            paddle.setyCord(event.getY());
        }

        //Drag
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            paddle.setxCord(event.getX());
            paddle.setyCord(event.getY());
        }
        return true;
    }

    public boolean checkValid(SurfaceHolder holder){
        if(holder.getSurface().isValid()){
            return true;
        }else{
            return false;
        }
    }
}
