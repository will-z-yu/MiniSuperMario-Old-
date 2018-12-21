package com.example.flame.assignment4.control;

import static java.lang.Thread.sleep;

/**
 * Created by flame on 6/12/2017.
 */

public class MonsterThread extends Thread {
    public MonsterController Monster;

    public MonsterThread(MonsterController Monster){
        this.Monster = Monster;


    }
    @Override
    public void run(){
            Monster.MoveMonsters();
    }

}
