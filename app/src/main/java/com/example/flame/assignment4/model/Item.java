package com.example.flame.assignment4.model;

import com.example.flame.assignment4.control.SuperMarioVisitor;

/**
 * Created by flame on 5/24/2017.
 */

public abstract class Item implements GameItems {

    int value;
    int x;
    int y;

    /*Write Constructor here, if necessary */

    public int getValue(){
        return value;
    }

    public int getPoints(SuperMarioVisitor visitor){
        return visitor.visit(this);
    }

}
