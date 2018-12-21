package com.example.flame.assignment4.control;

import com.example.flame.assignment4.model.Player;
import com.example.flame.assignment4.view.Entity;

/**
 * Created by flame on 6/12/2017.
 */

public class FallThread extends Thread {
    public Player Lucina;
    public Entity[][] level;

    public FallThread(Player Lucina, Entity[][] level) {
        this.Lucina = Lucina;
        this.level = level;
    }


    @Override
    public void run() {
        int count = 0;
        int Y;
        while (!Lucina.Next) {
            if (Lucina.Ypos + 1 == 6) {
                Y = 5;
            } else {
                Y = Lucina.Ypos + 1;
            }
            if (!Lucina.jumping) {
                if (!level[Lucina.Xpos][Y].isAlive) {
                    System.out.println("Lucina Y POS: " + Y);
                    Lucina.Ypos = Y;
                    count = 0;
                    while (count < 15) {
                        Lucina.Fall();
                        count++;
                        try {
                            sleep(50);
                        } catch (InterruptedException e) {
                            System.out.println("Exception occurred");
                        }
                    }
                }
            }
        }
    }
}