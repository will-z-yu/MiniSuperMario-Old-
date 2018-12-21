package com.example.flame.assignment4.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.flame.assignment4.R;
import com.example.flame.assignment4.control.FallThread;
import com.example.flame.assignment4.control.JumpThread;
import com.example.flame.assignment4.control.MonsterController;
import com.example.flame.assignment4.control.MonsterThread;
import com.example.flame.assignment4.model.Enemy;
import com.example.flame.assignment4.model.FireBall;
import com.example.flame.assignment4.model.Goomba;
import com.example.flame.assignment4.model.Koopa;
import com.example.flame.assignment4.model.BulletBill;
import com.example.flame.assignment4.control.DrawingThread;
import com.example.flame.assignment4.control.PlayerController;
import com.example.flame.assignment4.model.Orb;
import com.example.flame.assignment4.model.Player;
import com.example.flame.assignment4.model.Sword;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.attr.columnWidth;
import static android.R.attr.rowHeight;
import static android.os.SystemClock.sleep;

/**
 * Created by flame on 6/7/2017.
 */

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    //Bitmap
    private Bitmap background[];
    private Bitmap player[];
    private Bitmap icons[];
    private Entity Level[][];
    private Player Lucina;
    private Goomba Goomba;
    private Koopa Koopa;
    private BulletBill Bill;
    private Orb Orb;
    private Sword Sword;
    private FireBall FireBall;
    private MonsterController Monster;
    private int vertnum = 6;
    private int horinum = 10;
    private int level_num = 1;
    private int start = horinum * 3 -1;
    private boolean Win;
    private boolean Lose;
    private int mapX;
    MonsterThread MonsterThread;
    FallThread FallThread;

    private PlayerController playerController;


    public Game(Context context){
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

        icons = new Bitmap[12];
        player = new Bitmap[55];
        background = new Bitmap[5];
        Level = new Entity[horinum * 3][vertnum];
        Lucina = new Player();
        Goomba = new Goomba();
        Koopa = new Koopa();
        Bill = new BulletBill();
        Orb = new Orb();
        Sword = new Sword();
        FireBall = new FireBall();

        playerController = new PlayerController(Lucina);

        Level = Loadlevel(level_num);

        Monster = new MonsterController(Lucina, Goomba, Koopa, Bill, Orb, Sword, FireBall);

        MonsterThread = new MonsterThread(Monster);

        MonsterThread.start();

        FallThread = new FallThread(Lucina, Level);

        FallThread.start();







    }

    public void drawPlayer(Canvas canvas, Rect rect){
        bringToFront();
        if (Lucina.IsAlive) {
            canvas.drawBitmap(player[Lucina.getIcon()], null, rect, null);
            invalidate();
        }
    }

    public void drawGoomba(Canvas canvas, Rect rect){
        if (Goomba.Life && Goomba.OnScreen) {
            bringToFront();
            canvas.drawBitmap(icons[Goomba.Icon], null, rect, null);
            invalidate();
        }
    }

    public void drawKoopa(Canvas canvas, Rect rect){
        if (Koopa.Life && Koopa.OnScreen) {
            bringToFront();
            canvas.drawBitmap(icons[Koopa.Icon], null, rect, null);
            invalidate();
        }
    }

    public void drawBill(Canvas canvas, Rect rect){
        if (Bill.Life && Bill.OnScreen) {
            bringToFront();
            canvas.drawBitmap(icons[Bill.Icon], null, rect, null);
            invalidate();
        }
    }

    public void drawOrb(Canvas canvas, Rect rect){
        if (Orb.Life && Orb.OnScreen){
            bringToFront();
            canvas.drawBitmap(icons[Orb.icon], null, rect, null);
            invalidate();
        }
    }

    public void drawSword(Canvas canvas, Rect rect){
        if (Sword.Life && Sword.OnScreen){
            bringToFront();
            canvas.drawBitmap(icons[Sword.icon], null, rect, null);
            invalidate();
        }
    }

    public void drawFireBall(Canvas canvas, Rect rect){
        if(FireBall.Life){
            bringToFront();
            canvas.drawBitmap(icons[FireBall.icon], null, rect, null);
            invalidate();
        }
    }

    public void drawPoints(Canvas canvas, int Points){
        //Score
        bringToFront();
        Paint scores = new Paint(Color.BLACK);
        scores.setTextSize(80);
        scores.setTextAlign(Paint.Align.CENTER);
        Paint scores2 = new Paint(Color.BLACK);
        scores2.setTextSize(80);
        scores2.setTextAlign(Paint.Align.LEFT);

        canvas.drawText("Points: ",200, 100, scores);
        canvas.drawText(Integer.toString(Points),320,100, scores2);
        invalidate();
    }

    public void CheckLevel(Player Lucina){
        if (Lucina.Next){
            level_num++;
            if(level_num == 4){
                Win = true;
                Goomba.OnScreen = false;
                Goomba.Life = false;
                Bill.OnScreen = false;
                Bill.Life = false;
                Koopa.OnScreen = false;
                Koopa.Life = false;
            }
            if(!Win) {
                //reset the level array
                Arrays.fill(Level,null);
                //kill off all monsters and set them offscreen
                Goomba.OnScreen = false;
                Goomba.Life = false;
                Bill.OnScreen = false;
                Bill.Life = false;
                Koopa.OnScreen = false;
                Koopa.Life = false;

                //Load next level
                Level = Loadlevel(level_num);

                //resetting Lucina and draw coordinates
                Lucina.YposPixel = 592;
                Lucina.XposPixel = 1629;
                Lucina.Xpos = 29;
                Lucina.Ypos = 4;
                Lucina.Next = false;
                start = horinum * 3 - 1;
                mapX = start - horinum + 1;
                //Resetting threads
                Monster = new MonsterController(Lucina, Goomba, Koopa, Bill, Orb, Sword, FireBall);

                MonsterThread = new MonsterThread(Monster);

                MonsterThread.start();

                FallThread = new FallThread(Lucina, Level);

                FallThread.start();
            }
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        Rect rect = new Rect();

        //Screen width = 1810, height = 891 for future reference
        int width = getWidth();
        int height = getHeight();
        int rowheight = height/vertnum;
        int columnwidth = width/horinum;

        //Checks level before drawing
        CheckLevel(Lucina);

        //If we beat the game
        if(Win){
            rect.set(0,0, width, height);
            canvas.drawBitmap(background[3], null, rect, null);
            rect.set(0,height/4,width,height);
            canvas.drawBitmap(background[4],null,rect,null);
        }

        if(!Lucina.IsAlive){
            rect.set(0,0, width, height);
            canvas.drawBitmap(background[1], null, rect, null);
            rect.set(width/4, height/4, width * 3/4, height * 3/4);
            canvas.drawBitmap(background[2],null,rect,null);

        }

        if (Lucina.IsAlive && !Win) {
            //if Lucina passes 7/10 of the map, we shift the map as well as the player
            while (Lucina.Xpos + 6 < start) {
                if (start > horinum - 1) {
                    start--;
                    Lucina.setxPosPixel(Lucina.getxPosPixel() + columnwidth);
                    //If monster is at edge of screen, we shift the monster too
                    if (Goomba.OnScreen) {
                        Goomba.XPixelPos = Goomba.XPixelPos + columnwidth;
                    }
                    if (Koopa.OnScreen) {
                        Koopa.XPixelPos = Koopa.XPixelPos + columnwidth;
                    }
                    if (Bill.OnScreen) {
                        Bill.XPixelPos = Bill.XPixelPos + columnwidth;
                    }
                    if (Orb.OnScreen) {
                        Orb.XPixelPos = Orb.XPixelPos + columnwidth;
                    }
                    if (Sword.OnScreen) {
                        Sword.XPixelPos = Sword.XPixelPos + columnwidth;
                    }
                } else
                    break;
            }

            //background
            rect.set(0, 0, width, height);
            canvas.drawBitmap(background[0], null, rect, null);

            //points
            drawPoints(canvas, Lucina.Points);

            //player
            //System.out.println("Player's initial y " + rowheight*(vertnum - 2));
            int x = Lucina.getxPosPixel();
            int y = Lucina.getyPosPixel();
            rect.set(x, y, x + columnwidth, y + rowheight);
            drawPlayer(canvas, rect);

            //Goomba
            //First time draw
            if (Goomba.first) {
                Goomba.SetXPixelPos(Goomba.InitialXPos * columnwidth);
                Goomba.SetYPixelPos(Goomba.InitialYPos * rowheight);
                x = Goomba.XPixelPos;
                y = Goomba.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawGoomba(canvas, rect);
                Goomba.first = false;
            }

            if (!Goomba.first) {
                x = Goomba.XPixelPos;
                y = Goomba.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawGoomba(canvas, rect);
            }

            //Koopa
            //First time draw
            if (Koopa.first) {
                Koopa.SetXPixelPos(Koopa.InitialXPos * columnwidth);
                Koopa.SetYPixelPos(Koopa.InitialYPos * rowheight);
                x = Koopa.XPixelPos;
                y = Koopa.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawKoopa(canvas, rect);
                Koopa.first = false;
            }

            if (!Koopa.first) {
                x = Koopa.XPixelPos;
                y = Koopa.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawKoopa(canvas, rect);
            }

            //bill
            //First time draw
            if (Bill.first) {
                Bill.SetXPixelPos(Bill.InitialXPos * columnwidth);
                Bill.SetYPixelPos(Bill.InitialYPos * rowheight);
                x = Bill.XPixelPos;
                y = Bill.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawBill(canvas, rect);
                Bill.first = false;
            }

            if (!Bill.first) {
                x = Bill.XPixelPos;
                y = Bill.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawBill(canvas, rect);
            }

            //Orb
            if (Orb.first) {
                Orb.XPixelPos = (Orb.InitialXPos * columnwidth);
                Orb.YPixelPos = (Orb.InitialYPos * rowheight);
                x = Orb.XPixelPos;
                y = Orb.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawOrb(canvas, rect);
                Orb.first = false;
            }

            if (!Orb.first) {
                x = Orb.XPixelPos;
                y = Orb.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawOrb(canvas, rect);
            }

            //Sword
            if (Sword.first) {
                Sword.XPixelPos = (Sword.InitialXPos * columnwidth);
                Sword.YPixelPos = (Sword.InitialYPos * rowheight);
                x = Sword.XPixelPos;
                y = Sword.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawSword(canvas, rect);
                Sword.first = false;
            }

            if (!Sword.first) {
                x = Sword.XPixelPos;
                y = Sword.YPixelPos;
                rect.set(x, y, x + columnwidth, y + rowheight);
                drawSword(canvas, rect);
            }

            //Fireball
            x = FireBall.XPixelPos;
            y = FireBall.YPixelPos;
            rect.set(x, y, x + columnwidth, y + rowheight);
            drawFireBall(canvas, rect);


            //Drawing dirt, starts at 26 because we are drawing the file backwards
            mapX = start - horinum + 1;
            for (int i = 0; i < horinum; i++) {
                for (int j = 0; j < vertnum; j++) {
                    rect.set(i * columnwidth, j * rowheight, (i + 1) * columnwidth, (j + 1) * rowheight);
                    if (Level[mapX][j].isAlive) {
                        canvas.drawBitmap(icons[Level[mapX][j].icon], null, rect, null);
                    }
                }
                if (Goomba.XPos >= mapX) {
                    Goomba.OnScreen = true;
                    Goomba.idle = false;
                }

                if (Koopa.XPos >= mapX) {
                    Koopa.OnScreen = true;
                    Koopa.idle = false;
                }

                if (Bill.XPos >= mapX) {
                    Bill.OnScreen = true;
                    Bill.idle = false;
                }
                if (Orb.Xpos >= mapX) {
                    Orb.OnScreen = true;
                }
                if (Sword.Xpos >= mapX) {
                    Sword.OnScreen = true;
                }
                mapX++;
            }

            //button
            //left
            rect.set(0, (vertnum - 1) * rowheight, (horinum - horinum + 1) * columnwidth, height);
            canvas.drawBitmap(icons[2], null, rect, null);
            //right
            rect.set((horinum - horinum + 1) * columnwidth, (vertnum - 1) * rowheight, (horinum - horinum + 2) * columnwidth, height);
            canvas.drawBitmap(icons[3], null, rect, null);
            //a (attack)
            rect.set((horinum - 1) * columnwidth, (vertnum - 1) * rowheight, horinum * columnwidth, height);
            canvas.drawBitmap(icons[7], null, rect, null);
            //b (jump)
            rect.set((horinum - 2) * columnwidth, (vertnum - 1) * rowheight, (horinum - 1) * columnwidth, height);
            canvas.drawBitmap(icons[8], null, rect, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int clickX;
        int clickY;
        clickX = (int) event.getX();
        clickY = (int) event.getY();

        int width = getWidth();
        int height = getHeight();
        int rowheight = height/vertnum;
        int columnwidth = width/horinum;
        int checker;
        int checker2;

        JumpThread jump = new JumpThread(Lucina);

        if(!Lucina.IsAlive){
            System.exit(0);
        }

        if(Win){
            System.exit(0);
        }



        //Coordinates to move left
            if (clickX > 0 && clickX < columnwidth && clickY > rowheight * (vertnum - 1) && clickY < height) {
                playerController.moveLeft(columnwidth / 6);
            }

            //Coordinates to move right
            if (clickX > columnwidth && clickX < columnwidth * (horinum - horinum + 2) && clickY > rowheight * (vertnum - 1) && clickY < height) {
                playerController.moveRight(columnwidth / 6, width - columnwidth, horinum * 3 - 1);
            }

            //Attack
            if (clickX > (horinum - 1) * columnwidth && clickX < horinum * columnwidth && clickY > rowheight * (vertnum - 1) && clickY < height) {
                if(Lucina.IsRed) {
                    playerController.attack();
                    FireBall.SpawnFire(Lucina.XposPixel, Lucina.YposPixel);
                }
            }

            //Jump
            if (clickX > (horinum - 2) * columnwidth && clickX < (horinum - 1) * columnwidth && clickY > rowheight * (vertnum - 1) && clickY < height) {
                if (Lucina.Ypos -1 <0){
                    checker = 0;
                }
                else{checker = Lucina.Ypos-1;}

                if (Lucina.Ypos -2 <0){
                    checker2 = 0;
                }
                else{checker2 = Lucina.Ypos-2;}

                if (!Level[Lucina.Xpos][checker].isAlive && !Level[Lucina.Xpos][checker2].isAlive) {
                    jump.start();
                }
                if ((Level[Lucina.Xpos][checker].isAlive || Level[Lucina.Xpos][checker2].isAlive) && Lucina.IsSuper) {
                    Level[Lucina.Xpos][checker].isAlive = false;
                    Level[Lucina.Xpos][checker2].isAlive = false;
                    jump.start();
                }
            }
            //System.out.println("Lucina X: " + Lucina.Xpos + " Lucina Y: " + Lucina.Ypos);


            return false;
    }

    public Entity[][] Loadlevel(int a){
        String filename = Integer.toString(a) + ".txt";
        String line;
        int i = 0;
        int j = 0;
        int pos = 0;
        Level = new Entity[horinum * 3][vertnum];
        String lines[];
        List<String> temp = new ArrayList<String>();
        //Goes through the array and creates Entities
        for(i = 0; i<horinum * 3; i++){
            for (j = 0; j<vertnum; j++){
                Level[i][j] = new Entity();
                Level[i][j].SetXpos(i);
                Level[i][j].SetYpos(j);
            }
        }

        try

        {
            //Opening the file and appending each number separated by a space to a List
            Context context = getContext();
            BufferedReader buffer = new BufferedReader(new InputStreamReader((context.getAssets().open(filename))));
            while ((line = buffer.readLine()) != null){
                lines = line.split(" ");
                for(i = 0; i < lines.length; i++) {
                    temp.add(lines[i]);
                }
        }

        } catch(Exception e){}
        //Now we go through the list and assign its values to our Level
        for (j = 0; j<vertnum; j++) {
            for (i = 0; i < horinum * 3 ; i++) {
                Level[i][j].SetIcon(temp.get(i + (horinum *3 )* j));
                if (!temp.get(i+(horinum*3)*j).equals("0")) {
                    Level[i][j].SetLife();
                }
                //Goomba is 5 in our level, once we find it we set the icon to transparent and create our monster
                if (temp.get(i+(horinum * 3) *j).equals("5")){
                    if (i>=20){
                        Goomba.InitialXPos = i-20;
                        Goomba.InitialYPos = j;
                        Goomba.XPos = i;
                        Goomba.Life = true;
                        Goomba.first = true;
                        Level[i][j].SetIcon("0");
                    }
                    if (i<20){
                        Goomba.InitialXPos = 0;
                        Goomba.InitialYPos = j;
                        Goomba.XPos = i;
                        Goomba.Life = true;
                        Goomba.first = true;
                        Level[i][j].SetIcon("0");
                    }
                }
                //Looking for the Koopa on our map
                if (temp.get(i+(horinum * 3) *j).equals("4")){
                    if (i>=20){
                        Koopa.InitialXPos = i-20;
                        Koopa.InitialYPos = j;
                        Koopa.XPos = i;
                        Koopa.Life = true;
                        Koopa.first = true;
                        Level[i][j].SetIcon("0");
                    }
                    if (i<20){
                        Koopa.InitialXPos = 0;
                        Koopa.InitialYPos = j;
                        Koopa.XPos = i;
                        Koopa.Life = true;
                        Koopa.first = true;
                        Level[i][j].SetIcon("0");
                    }
                }
                //Looking for the Bullet bill
                if (temp.get(i+(horinum * 3) *j).equals("9")){
                    if (i>=20){
                        Bill.InitialXPos = i-20;
                        Bill.InitialYPos = j;
                        Bill.XPos = i;
                        Bill.Life = true;
                        Bill.first = true;
                        Level[i][j].SetIcon("0");
                    }
                    if (i<20){
                        Bill.InitialXPos = 0;
                        Bill.InitialYPos = j;
                        Bill.XPos = i;
                        Bill.Life = true;
                        Bill.first = true;
                        Level[i][j].SetIcon("0");
                    }
                }
                //Checking level for Orb
                if (temp.get(i+(horinum * 3) *j).equals("2")){
                    if (i>=20) {
                        Orb.InitialXPos = i-20;
                        Orb.InitialYPos = j;
                        Orb.Xpos = i;
                        Orb.Life = true;
                        Orb.first = true;
                        Level[i][j].SetIcon("0");
                        Level[i][j].isAlive = false;
                    }
                    if (i<20){
                        Orb.InitialXPos = 0;
                        Orb.InitialYPos = j;
                        Orb.Xpos = i;
                        Orb.Life = true;
                        Orb.first = true;
                        Level[i][j].SetIcon("0");
                        Level[i][j].isAlive = false;
                    }
                }
                //Checking level for Sword
                if (temp.get(i+(horinum * 3) *j).equals("3")){
                    if (i>=20) {
                        Sword.InitialXPos = i-20;
                        Sword.InitialYPos = j;
                        Sword.Xpos = i;
                        Sword.Life = true;
                        Sword.first = true;
                        Level[i][j].SetIcon("0");
                        Level[i][j].isAlive = false;
                    }
                    if (i<20){
                        Sword.InitialXPos = 0;
                        Sword.InitialYPos = j;
                        Sword.Xpos = i;
                        Sword.Life = true;
                        Sword.first = true;
                        Level[i][j].SetIcon("0");
                        Level[i][j].isAlive = false;
                    }
                }
            }
        }
        return Level;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false);
        background[0] = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        background[1] = BitmapFactory.decodeResource(getResources(), R.drawable.go_background);
        background[2] = BitmapFactory.decodeResource(getResources(), R.drawable.go_text);
        background[3] = BitmapFactory.decodeResource(getResources(), R.drawable.v_background);
        background[4] = BitmapFactory.decodeResource(getResources(), R.drawable.stage_clear);
        //player idle
        player[0] = BitmapFactory.decodeResource(getResources(), R.drawable.playeridleright);
        player[1] = BitmapFactory.decodeResource(getResources(), R.drawable.playeridleleft);
        player[2] = BitmapFactory.decodeResource(getResources(), R.drawable.playeridleup);
        player[3] = BitmapFactory.decodeResource(getResources(), R.drawable.playeridledown);
        //player attack
        player[4] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattackright);
        player[5] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattackleft);
        player[6] = BitmapFactory.decodeResource(getResources(), R.drawable.playerjumpleft);
        player[7] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattackdown);
        player[8] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattack2right);
        player[9] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattack2left);
        player[10] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattack2up);
        player[11] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattack2down);
        //player move
        player[12] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkright);
        player[13] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkleft);
        player[14] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkup);
        player[15] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkdown);
        player[16] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalk2right);
        player[17] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalk2left);
        player[18] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalk2up);
        player[19] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalk2down);
        player[20] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalk3right);
        player[21] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalk3left);
        player[22] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalk3up);
        player[23] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalk3down);
        //player attacked
        player[24] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattackedright);
        player[25] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattackedleft);
        player[26] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattackedup);
        player[27] = BitmapFactory.decodeResource(getResources(), R.drawable.playerattackeddown);

        //Red Player attacked
        player[28] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerattacked);

        //Red player Jump
        player[29] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerjump);

        //Red player walking Right
        player[30] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerwalk1);
        player[31] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerwalk2);
        player[32] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerwalk3);

        //Red player walk left
        player[33] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerleftwalk1);
        player[34] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerleftwalk2);
        player[35] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerleftwalk3);

        //Red player standing left and right
        player[36] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerleftstand);
        player[37] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerstand);

        //Red player attack left
        player[38] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerleftattack1);
        player[39] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerleftattack2);

        //Red player attack right
        player[40] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerattack1);
        player[41] = BitmapFactory.decodeResource(getResources(), R.drawable.redplayerattack2);

        //Super jump
        player[42] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerjump);

        //Super attack left
        player[43] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerleftattack1);
        player[44] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerleftattack2);

        //Super attack right
        player[45] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerattack1);
        player[46] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerattack2);

        //super move left
        player[47] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerleftwalk1);
        player[48] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerleftwalk2);
        player[49] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerleftwalk3);

        //super move right
        player[50] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerwalk1);
        player[51] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerwalk2);
        player[52] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerwalk3);

        //super idle left then right
        player[53] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerstand);
        player[54] = BitmapFactory.decodeResource(getResources(), R.drawable.superplayerleftstand);









        icons[0] = BitmapFactory.decodeResource(getResources(), R.drawable.trans);
        icons[1] = BitmapFactory.decodeResource(getResources(), R.drawable.dirt);
        icons[2] = BitmapFactory.decodeResource(getResources(), R.drawable.buttonleft);
        icons[3] = BitmapFactory.decodeResource(getResources(), R.drawable.buttonright);
        icons[4] = BitmapFactory.decodeResource(getResources(), R.drawable.monster);
        icons[5] = BitmapFactory.decodeResource(getResources(), R.drawable.firemonster);
        icons[6] = BitmapFactory.decodeResource(getResources(), R.drawable.fireball);
        icons[7] = BitmapFactory.decodeResource(getResources(), R.drawable.buttona);
        icons[8] = BitmapFactory.decodeResource(getResources(), R.drawable.buttonb);
        icons[9] = BitmapFactory.decodeResource(getResources(), R.drawable.bill);
        icons[10] = BitmapFactory.decodeResource(getResources(), R.drawable.orb);
        icons[11] = BitmapFactory.decodeResource(getResources(), R.drawable.red);

        //DrawingThread drawingThread = new DrawingThread(this);

        //drawingThread.start();




    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

}
