package com.vsu;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {

    //BufferedImage img = ImageIO.read(new File("Images/enemy.png"));
    BufferedImage img = Main.enemyImage;
    private final int width = img.getWidth();
    private final int height = img.getHeight();

    private int x;
    private int y;
    private int speed;

    public Enemy(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x-10, y, width-15, height+5);
    }

    public void move() {
        y = y + Player.getSpeed() + speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
