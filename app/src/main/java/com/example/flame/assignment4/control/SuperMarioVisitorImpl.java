package com.example.flame.assignment4.control;

import com.example.flame.assignment4.model.Enemy;
import com.example.flame.assignment4.model.Item;

/**
 * Created by flame on 5/24/2017.
 */

public class SuperMarioVisitorImpl implements SuperMarioVisitor {
    @Override
    public int visit(Enemy enemy){
        return enemy.getReward();
    }

    @Override
    public int visit(Item item){
        return item.getValue();

    }
}
