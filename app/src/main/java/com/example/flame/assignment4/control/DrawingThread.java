package com.example.flame.assignment4.control;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.flame.assignment4.view.Game;
import com.example.flame.assignment4.view.MainActivity;

/**
 * Created by Banananana on 6/11/2017.
 */

public class DrawingThread extends Thread{
    private Game gameView;

    public DrawingThread(Game gameView){
        this.gameView = gameView;
    }

    @Override
    public void run() {
        SurfaceHolder holder;
        Canvas canvas;

        while (true){
            holder = gameView.getHolder();
            canvas = holder.lockCanvas();

            if (canvas != null) {
                gameView.draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            try{
                sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("Exception occurred");
            }
        }
    }
}