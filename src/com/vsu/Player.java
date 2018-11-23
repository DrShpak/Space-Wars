package com.vsu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    public static final int DELAY = 200;
//    BufferedImage img = ImageIO.read(new File("Images/rocket_2.png"));
    BufferedImage img = Main.playerImage;

    private final int width = img.getWidth();
    private final int height = img.getHeight();
    private static final int maxRight = 400;
    private static final int maxLeft = 2;

    private static int speed = 7;
    private int direction = 1;
    private int acceleration = 0;
    private int distance = 0;
    private int checkPoint = 500;
    private int n = 1;

    int layer1 = 0;
    int layer2 = -1334;

    int bullets = 0;
    boolean drawBullet = false;
    private Timer bulletTimer = new Timer(DELAY, this::bulletTimerTick); //Создание пуль

    int x = 190;
    int y = 560;

    public Player() {

    }

    public Rectangle getRectangle() {
        return new Rectangle(x+20, y, width/2-15, height-10);
    }

    public void move() {

        distance += Math.round(speed/4);

        if (distance >= checkPoint) {

            checkPoint += (300 * n);
            n += 1;
            acceleration += 1;
            speed += acceleration;
        }

        x += direction;
        if (x <= maxLeft) {
            x = maxLeft;
        }
        if (x >= maxRight) {
            x = maxRight;
        }


        if (layer2 + speed >= 0) {
            layer1 = layer2 + speed;
            layer2 = layer1 - 1334;
        } else {
            layer1 += speed;
            layer2 += speed;
        }
    }

    public static int getSpeed() {
        return speed;
    }

    public int getDistance() {
        return distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void bulletTimerTick(ActionEvent e){
        if (bullets > 0)
            drawBullet = true;
        else
            bulletTimer.stop();
    }

    public void startBulletTimer() {
        bulletTimer.start();
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
