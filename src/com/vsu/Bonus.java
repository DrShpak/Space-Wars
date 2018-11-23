package com.vsu;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bonus {

    //BufferedImage img = ImageIO.read(new File("/Users/test/Documents/Game/MyGame/Images/bonus.png"));
    BufferedImage img = Main.bonusImage;
    private final int width = img.getWidth();
    private final int height = img.getHeight();

    private int x, y;
    private static final int speed = 3;

    public Bonus(int x, int y, int speed) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width - 35, height - 30);
    }

    public Rectangle getSpecialRectangle() {
        return new Rectangle(x, y, width - 40, height - 40);
    }

    public void move() {
        y = y + Player.getSpeed() + speed;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
