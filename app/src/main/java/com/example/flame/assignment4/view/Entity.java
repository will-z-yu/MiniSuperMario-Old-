package com.example.flame.assignment4.view;

/**
 * Created by flame on 6/9/2017.
 */

public class Entity {
    int icon;
    public boolean isAlive;
    public int Xpos;
    public int Ypos;

    public void SetIcon(String a){
        icon = Integer.parseInt(a);
    }


    public void SetLife(){
        isAlive = true;
    }

    public void SetXpos(int a){
        Xpos = a;
    }

    public void SetYpos(int a){
        Ypos = a;
    }
}
