package com.example.flame.assignment4.control;

import com.example.flame.assignment4.model.BulletBill;
import com.example.flame.assignment4.model.FireBall;
import com.example.flame.assignment4.model.Goomba;
import com.example.flame.assignment4.model.Koopa;
import com.example.flame.assignment4.model.Player;
import com.example.flame.assignment4.model.Orb;
import com.example.flame.assignment4.model.Sword;

import static java.lang.Thread.sleep;

/**
 * Created by flame on 6/12/2017.
 */

public class MonsterController {
    private Player Player;
    private Goomba Goomba;
    private Koopa Koopa;
    private BulletBill Bill;
    private Orb Orb;
    private Sword Sword;
    private FireBall FireBall;

    public MonsterController(Player Player, Goomba Goomba, Koopa Koopa, BulletBill Bill, Orb Orb, Sword Sword, FireBall FireBall) {
        this.Player = Player;
        this.Goomba = Goomba;
        this.Koopa = Koopa;
        this.Bill = Bill;
        this.Orb = Orb;
        this.Sword = Sword;
        this.FireBall = FireBall;
    }
        public void MoveMonsters() {
            int count1 = 0;
            int count2 = 0;
            boolean Goombadir = true;
            boolean Koopadir = true;
            while (!Player.Next) {

                //Logic to kill Goomba
                if (Player.YposPixel + 30 < Goomba.YPixelPos && Player.YposPixel + 200 > Goomba.YPixelPos && (Player.XposPixel < Goomba.XPixelPos + 113 && Player.XposPixel > Goomba.XPixelPos)) {
                    if (Goomba.Life) {
                        Goomba.Life = false;
                        Goomba.XPixelPos = 0;
                        Goomba.YPixelPos = 890;
                        Player.Points = Player.Points + Goomba.Reward;
                    }

                }
                //Logic to kill Goomba with fireball
                if ((FireBall.YPixelPos + 1 > Goomba.YPixelPos && FireBall.YPixelPos < Goomba.YPixelPos +40 ) && (FireBall.XPixelPos < Goomba.XPixelPos + 113 && FireBall.XPixelPos > Goomba.XPixelPos)) {
                    if (Goomba.Life) {
                        Goomba.Life = false;
                        Goomba.XPixelPos = 0;
                        Goomba.YPixelPos = 890;
                        Player.Points = Player.Points + Goomba.Reward;
                    }

                }


                //Logic to kill Koopa
                if (Player.YposPixel + 30 < Koopa.YPixelPos  && Player.YposPixel + 200 > Koopa.YPixelPos && (Player.XposPixel < Koopa.XPixelPos + 113 && Player.XposPixel > Koopa.XPixelPos)) {
                    if (Koopa.Life) {
                        Koopa.Life = false;
                        Koopa.XPixelPos = 0;
                        Koopa.YPixelPos = 890;
                        Player.Points = Player.Points + Koopa.Reward;
                    }

                }
                //Logic to kill Koopa with fireball
                if ((FireBall.YPixelPos + 1 > Koopa.YPixelPos && FireBall.YPixelPos < Koopa.YPixelPos +40 ) && (FireBall.XPixelPos < Koopa.XPixelPos + 113 && FireBall.XPixelPos > Koopa.XPixelPos)) {
                    if (Koopa.Life) {
                        Koopa.Life = false;
                        Koopa.XPixelPos = 0;
                        Koopa.YPixelPos = 890;
                        Player.Points = Player.Points + Koopa.Reward;
                    }

                }

                //Logic to kill Bill
                if (Player.YposPixel + 30 < Bill.YPixelPos && Player.YposPixel + 200 > Bill.YPixelPos && (Player.XposPixel < Bill.XPixelPos + 113 && Player.XposPixel > Bill.XPixelPos)) {
                    if (Bill.Life) {
                        Bill.Life = false;
                        Bill.XPixelPos = 0;
                        Bill.YPixelPos = 890;
                        Player.Points = Player.Points + Bill.Reward;
                    }

                }
                //Logic to kill Bill with fireball
                if ((FireBall.YPixelPos + 1 > Bill.YPixelPos && FireBall.YPixelPos < Bill.YPixelPos +40 ) && (FireBall.XPixelPos < Bill.XPixelPos + 113 && FireBall.XPixelPos > Bill.XPixelPos)) {
                    if (Bill.Life) {
                        Bill.Life = false;
                        Bill.XPixelPos = 0;
                        Bill.YPixelPos = 890;
                        Player.Points = Player.Points + Bill.Reward;
                    }

                }


                //Logic to kill player with Goomba
                if ((Player.YposPixel + 1 > Goomba.YPixelPos && Player.YposPixel < Goomba.YPixelPos + 40) && (Player.XposPixel < Goomba.XPixelPos + 113 && Player.XposPixel > Goomba.XPixelPos)) {
                    if (Player.IsSuper) {
                        Player.IsSuper = false;
                        Goomba.XPixelPos = 0;
                        Goomba.YPixelPos = 890;
                        Goomba.Life = false;
                    } else {
                        Player.IsAlive = false;
                    }
                }

                //Logic to kill player with Koopa
                if ((Player.YposPixel + 1 > Koopa.YPixelPos && Player.YposPixel < Koopa.YPixelPos + 40) && (Player.XposPixel < Koopa.XPixelPos + 113 && Player.XposPixel > Koopa.XPixelPos)) {
                    if (Player.IsSuper) {
                        Player.IsSuper = false;
                        Koopa.XPixelPos = 0;
                        Koopa.YPixelPos = 890;
                        Koopa.Life = false;
                    } else {
                        Player.IsAlive = false;
                    }
                }

                //Logic to kill player with Bill
                if ((Player.YposPixel + 1 > Bill.YPixelPos && Player.YposPixel < Bill.YPixelPos + 40) && (Player.XposPixel < Bill.XPixelPos + 113 && Player.XposPixel > Bill.XPixelPos)) {
                    if (Player.IsSuper) {
                        Player.IsSuper = false;
                        Bill.XPixelPos = 0;
                        Bill.YPixelPos = 890;
                        Bill.Life = false;
                    } else {
                        Player.IsAlive = false;
                    }
                }

                //Logic to collect Orb
                if (Player.YposPixel + 113 < Orb.YPixelPos && (Player.XposPixel < Orb.XPixelPos + 113 && Player.XposPixel > Orb.XPixelPos) && (!Player.IsRed && !Player.IsSuper)) {
                    if (Orb.Life) {
                        Player.IsSuper = true;
                        Orb.Life = false;
                        Player.Points = Player.Points + Orb.Reward;
                    }
                }
                //Logic to collect Sword
                if (Player.YposPixel + 113 < Sword.YPixelPos && (Player.XposPixel < Sword.XPixelPos + 113 && Player.XposPixel > Sword.XPixelPos) && (!Player.IsRed && !Player.IsSuper)) {
                    if (Sword.Life) {
                        Player.IsRed = true;
                        Sword.Life = false;
                        Player.Points = Player.Points + Sword.Reward;
                    }
                }


                //moving Goomba
                if (!Goomba.idle && Goombadir && Goomba.Life) {
                    Goomba.XPixelPos = Goomba.XPixelPos + 10;
                    count1++;

                    if (count1 == 30) {
                        count1 = 0;
                        Goombadir = false;
                    }

                }

                if (!Goomba.idle && !Goombadir && Goomba.Life) {
                    Goomba.XPixelPos = Goomba.XPixelPos - 10;
                    count1++;

                    if (count1 == 30) {
                        count1 = 0;
                        Goombadir = true;
                    }
                }


                //move down if direction = false;
                if (!Koopa.idle && !Koopadir && Koopa.Life) {
                    Koopa.YPixelPos = Koopa.YPixelPos + 10;
                    count2++;

                    if (count2 > 10) {
                        count2 = 0;
                        Koopadir = true;
                    }
                }

                //move up if direction = true;
                if (!Koopa.idle && Koopadir && Koopa.Life) {
                    Koopa.YPixelPos = Koopa.YPixelPos - 10;
                    count2++;

                    if (count2 > 10) {
                        count2 = 0;
                        Koopadir = false;
                    }
                }
                //moving Bill
                if (!Bill.idle && Bill.Life) {
                    Bill.XPixelPos = Bill.XPixelPos + 10;
                }
                //moving Fireball
                if (FireBall.Life) {
                    FireBall.XPixelPos = FireBall.XPixelPos - 10;
                    if (FireBall.XPixelPos <= 0) {
                        FireBall.Life = false;
                    }
                }
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        System.out.println("Exception occurred");
                    }
            }


        }



}
