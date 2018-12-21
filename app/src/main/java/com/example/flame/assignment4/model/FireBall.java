package com.example.flame.assignment4.model;

/**
 * Created by flame on 6/13/2017.
 */

public class FireBall {
    public int XPos;
    public int InitialXPos;
    public int InitialYPos;
    public int YPos;
    public int XPixelPos;
    public int YPixelPos;
    public int icon = 6;
    public boolean Life;

    public void SpawnFire(int x, int y){
        XPixelPos = x;
        YPixelPos = y;
        Life = true;
    }
}
