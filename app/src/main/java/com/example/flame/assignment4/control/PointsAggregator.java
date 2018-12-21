package com.example.flame.assignment4.control;

import com.example.flame.assignment4.model.GameItems;

/**
 * Created by flame on 5/24/2017.
 */

public class PointsAggregator {
    int currScore;

    public int addPoints(GameItems gameItem){
        SuperMarioVisitor visitor = new SuperMarioVisitorImpl();
        currScore += gameItem.getPoints(visitor);
        return currScore;
    }
}
