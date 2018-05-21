package com.example.lassi.pingponggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

import static com.example.lassi.pingponggame.R.drawable.*;

public class View extends SurfaceView implements Runnable{

    private boolean playerPlaying;
    private boolean check;
    private Thread thread = null;

    private Paddle paddle;
    private Paddle cpuPaddle;
    private Ball ball;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder sh;

    private Bitmap bm;

    public View(Context context, int x, int y){

        super(context);

        GameActivity activity;

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.asset2);
        paddle = new Paddle(context, 200, 100);
        cpuPaddle = new Paddle(context, 200, 980);
        ball = new Ball(context, 500, 500,0, 20);
        sh = getHolder();
        paint = new Paint();

        Drawable drPaddle = getResources().getDrawable(asset3);
        Drawable drBall = getResources().getDrawable(asset4);

        adjustDrawableSize(drPaddle);
        adjustDrawableSize(drBall);
    }

    @Override
    public void run() {

        do{

            drawFrame();
            updateFrame();
            controlFrame();


        }while(playerPlaying);

    }

    private void drawFrame() {

        check = checkValid(sh);

        if(check == true) {

            canvas = sh.lockCanvas();
            //onDraw(bm);
            canvas.drawColor(Color.BLUE);
            canvas.drawBitmap(ball.getBitmap(), (ball.getxCord() / 2), (ball.getyCord() / 2), paint);
            canvas.drawBitmap(paddle.getBitmap(), (paddle.getxCord() / 2), (paddle.getyCord() / 2), paint);
            canvas.drawBitmap(cpuPaddle.getBitmap(), (cpuPaddle.getxCord() / 2), cpuPaddle.getyCord(), paint);

            /*if(ball.check()){
                    drawGameOver();
            }*/

            sh.unlockCanvasAndPost(canvas);



        }

    }

    private void updateFrame() {

        if(ViewCompat.isLaidOut(getRootView())) {
            paddle.updtaePaddlePos();
            ball.move((getRootView().getWidth()*2), (getRootView().getHeight()*2), getBallWidth());
            ball.checkPaddleHit(paddle, cpuPaddle);
        }
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
        thread = new Thread(this);
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
            cpuPaddle.setxCord(event.getX());

        }

        //Push event
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            cpuPaddle.setxCord(event.getX());

        }

        //Drag
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            cpuPaddle.setxCord(event.getX());

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

    private void onDraw(Bitmap bm){
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bm, 0, 0, paint);
    }

    public void adjustDrawableSize(Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 10, 10, true));
    }

    public int getBallHeight(){

        int height;

        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.asset4, op);
        height = op.outHeight;

        return height;
    }

    public int getBallWidth(){

        int width;

        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.asset4, op);
        width = op.outWidth;

        return width;
    }

    public void drawGameOver(){
        canvas.drawColor(Color.RED);
        canvas.drawText("Game Over", getRootView().getWidth(), 500, paint);
    }



}
