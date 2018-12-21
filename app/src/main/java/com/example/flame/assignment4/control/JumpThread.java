package com.example.flame.assignment4.control;

import com.example.flame.assignment4.model.Player;

/**
 * Created by flame on 6/12/2017.
 */

public class JumpThread extends Thread {
    public Player Lucina;

    public JumpThread(Player Lucina){
        this.Lucina = Lucina;
    }

    @Override
    public void run(){
        int count = 0;
        Lucina.jumping = true;
        Lucina.Ypos = Lucina.Ypos-2;
        System.out.println("YPOS after jumping: "+Lucina.Ypos);

        while(count < 30){
            Lucina.Jump();
            count++;
            try{
                sleep(50);
            } catch(InterruptedException e) {
                System.out.println("Exception occurred");
            }
        }
        Lucina.jumping = false;

    }
}
