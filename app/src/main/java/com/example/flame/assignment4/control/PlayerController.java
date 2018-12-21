package com.example.flame.assignment4.control;

import com.example.flame.assignment4.model.Player;

/**
 * Created by Banananana on 6/11/2017.
 */

public class PlayerController {
    private Player player;

    public PlayerController(Player player){
        this.player = player;
    }

    public void moveLeft(int movePixel){
        //move left
            //If player is still within the screen,
            //move
            if(player.getxPosPixel() > 0) {
                player.MoveLeftPixel(movePixel);
                player.increamentMoveLeftCount();
                if (player.getMoveRightCount() != 0)
                    player.decreamentMoveRightCount();
            }
            else {
                System.out.println("Sorry you can't go further/ Level complete!");
                player.Next = true;
            }



            //After 6 moves, player is at another xPos
            //moveRightCount is assigned to be 5 because if player move right immediately,
            //player goes back to previous xPos
            if(player.getMoveLeftCount() == 6) {
                player.setMoveLeftCount(0);
                player.setMoveRightCount(5);
                if(player.getxPos() != 0) {
                    player.MoveLeft();
                }

            }
    }

    public void moveRight(int movePixel, int pixelBoundary, int xBoundary){
        //Player can move right as long as player is not at the right edge of the screen
        if(player.getxPosPixel() < pixelBoundary) {
            player.MoveRightPixel(movePixel);
            if(player.getMoveLeftCount() != 0)
                player.decreamentMoveLeftCount();
            player.increamentMoveRightCount();
        }
        else
            System.out.println("Sorry you can't go back");

        //After 6 moves, player is at another xPos
        //moveLeftCount is assigned to be 5 because if player move right immediately,
        //player goes back to previous xPos
        if(player.getMoveRightCount() == 6) {
            player.setMoveRightCount(0);
            player.setMoveLeftCount(5);
            if(player.getxPos() != xBoundary) {
                player.MoveRight();
            }
        }
    }

    public void attack(){
        player.ShootFire();
    }
}

