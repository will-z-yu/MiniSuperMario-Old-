package com.example.flame.assignment4.model;

import com.example.flame.assignment4.control.SuperMarioVisitor;

/**
 * Created by flame on 5/24/2017.
 */

public abstract class Enemy implements GameItems {
    int reward;
    int posx;
    int posy;

    public int getReward(){
        return reward;
    }

    public int getPoints(SuperMarioVisitor visitor){
        return visitor.visit(this);
    }
}
