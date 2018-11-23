package com.vsu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet {

    //BufferedImage img = ImageIO.read(new File("/Users/test/Documents/Game/MyGame/Images/bullet.png"));
    BufferedImage img = Main.bulletImage;

    private final int widthBullet = img.getWidth();
    private final int heightBullet = img.getHeight();
    private int time = 1000;

    private int yBullet;
    private int xBullet;
    private int speedBullet;

    public Bullet(int x, int y, int speedBullet) throws IOException {
        xBullet = x;
        yBullet = y;
        this.speedBullet = speedBullet;
    }

    public void moveBullet() {
        yBullet = yBullet - Player.getSpeed() - speedBullet;
        //xBullet += 20 * Math.sin(-time / 10.0); //just for fun
    }

    public Rectangle getRectangle() {
        return new Rectangle(xBullet - 10, yBullet, widthBullet + 10, heightBullet);
    }

    public int getSpeedBullet() {
        return speedBullet;
    }

    public int getxBullet() {
        return xBullet;
    }

    public int getyBullet() {
        return yBullet;
    }

    public int getTime() {
        return time;
    }

}
