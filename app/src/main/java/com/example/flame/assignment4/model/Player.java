package com.example.flame.assignment4.model;

/**
 * Created by flame on 6/10/2017.
 */

public class Player {
    private int Right = 1;
    private int Left = 2;
    private int Up = 3;
    private int Down = 4;

    //Initial State
    public boolean IsAlive = true;
    public boolean IsSuper = false;
    public boolean IsRed = false;
    public boolean jumping;
    public int Icon = 1;
    private int lives = 3;
    private int direction = Left;
    private boolean attacking = false;
    private boolean moving = false;
    private int moveCounter = 0;
    public int Xpos = 29;
    public int Ypos = 4;
    public int XposPixel = 1629;
    public int YposPixel = 592;
    private int moveLeftCount = 0;
    private int moveRightCount = 0;
    public int Points;
    public int count;
    public boolean Next;

    //Get Functions-----------------------------------------
    public int getxPos(){
        return this.Xpos;
    }

    public int getyPos(){
        return this.Ypos;
    }

    public int getxPosPixel(){
        return this.XposPixel;
    }

    public int getyPosPixel(){
        return this.YposPixel;
    }

    public int getLives(){
        return this.lives;
    }

    public int getIcon(){
        return this.Icon;
    }

    public int direction(){
        return this.direction;
    }

    public int getMoveLeftCount(){ return moveLeftCount;}

    public int getMoveRightCount(){ return moveRightCount;}

    public boolean checkAlive(){
        return this.IsAlive;
    }

    public boolean checkSuper(){
        return this.IsSuper;
    }

    public boolean checkRed(){
        return this.IsRed;
    }

    public boolean checkAttacking(){
        return this.attacking;
    }

    public boolean checkMoving(){
        return this.moving;
    }
    //-------------------------------------------------------

    //Set Functions------------------------------------------

    //Idle
    public void setIdle() {
        attacking = false;
        moving = false;
        moveCounter = 0;

        if(direction == Left){
            if(IsSuper){
                Icon = 54;
            }
            if (IsRed){
                Icon = 36;
            }
            else {
                Icon = 1;
            }
        }

        if(direction == Right){
            if(IsRed){
                Icon = 37;
            }
            if(IsSuper){
                Icon = 53;
            }
            else {
                Icon = 0;
            }
        }
    }

    //Move left
    public void MoveLeft(){
        Xpos--;
    }

    public void MoveLeftPixel(int pixels){
        direction = Left;
        XposPixel -= pixels;
        moving = true;
        if(moveCounter == 0) {
            if(IsRed){
                Icon = 33;
            }
            else if(IsSuper){
                Icon = 47;
            }
            else {
                Icon = 13;
            }
            moveCounter++;
        }
        else if (moveCounter == 1){
            if(IsRed){
                Icon = 34;
            }
            else if(IsSuper){
                Icon = 48;
            }
            else {
                Icon = 17;
            }
            moveCounter++;
        }
        else if (moveCounter == 2) {
            if(IsRed){
                Icon = 35;
            }
            else if(IsSuper){
                Icon = 49;
            }
            else {
                Icon = 21;
            }
            moveCounter = 0;
        }
        else if (moveCounter == 3) {
            if(IsRed){
                Icon = 33;
            }
            else if (IsSuper){
                Icon = 47;
            }
            else {
                Icon = 17;
            }
            moveCounter = 0;
        }
    }

    public void increamentMoveLeftCount(){
        moveLeftCount ++;
    }

    public void decreamentMoveLeftCount(){
        moveLeftCount --;
    }

    public void setMoveLeftCount(int num){
        moveLeftCount = num;
    }

    //Move right
    public void MoveRight(){
        Xpos++;
    }

    public void MoveRightPixel(int pixels){
        direction = Right;
        XposPixel += pixels;
        moving = true;
        if(moveCounter == 0) {
            if(IsRed){
                Icon = 30;
            }
            else if(IsSuper){
                Icon = 50;
            }
            else{
            Icon = 12;
            }
            moveCounter++;
        }
        else if (moveCounter == 1){
            if(IsRed){
                Icon = 31;
            }
            else if(IsSuper){
                Icon = 51;
            }
            else {
                Icon = 16;
            }
            moveCounter++;
        }
        else if (moveCounter == 2) {
            if(IsRed){
                Icon = 32;
            }
            else if(IsSuper){
                Icon = 52;
            }
            else {
                Icon = 20;
            }
            moveCounter++;
        }
        else if (moveCounter == 3) {
            if(IsRed){
                Icon = 30;
            }
            else if(IsSuper){
                Icon = 50;
            }
            else {
                Icon = 16;
            }
            moveCounter = 0;
        }
    }

    public void increamentMoveRightCount(){
        moveRightCount ++;
    }

    public void decreamentMoveRightCount(){
        moveRightCount --;
    }

    public void setMoveRightCount(int num){
        moveRightCount = num;
    }

    //Jump
    public void Jump(){
        if(IsRed){Icon = 29;}
        else if(IsSuper){Icon = 42;}
        else{Icon = 6;}
        YposPixel = YposPixel - 10;
    }

    //Fall
    public void Fall(){
        YposPixel = YposPixel + 10;
    }

    //Set Pixels
    public void setxPosPixel(int xPixel){
        XposPixel = xPixel;
    }

    public void setyPosPixel(int yPixel){
        YposPixel = yPixel;
    }

    //Set states
    public void PickupMushroom(){
        IsSuper = true;
    }

    public void PickupFlower(){
        IsRed = true;
    }

    public void ShootFire(){
        if(count == 1) {
            Icon = 39;
            count--;
        }
        else if(count == 0){
            Icon = 38;
            count++;
        }
    }

    public void Die(){
        IsAlive = false;
    }
    //---------------------------------------------------------
}
