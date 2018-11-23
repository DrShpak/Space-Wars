package com.vsu;

import javax.swing.*;
import java.awt.*;

public class DrawScreenSaver extends JPanel {

    Image img = new ImageIcon("/Users/test/Documents/Game/MyGame/Images/spaceX.jpg").getImage();
//    BufferedImage img = ImageIO.read(new File("/Users/test/Downloads/imgonline-com-ua-Resize-qUFglzk2iQTYRIBa.png"));


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics = (Graphics2D) graphics;
        graphics.drawImage(img, 0, 0, null);

        graphics.setColor(Color.ORANGE);
        Font font = new Font("Arial", Font.BOLD, 25);
        graphics.setFont(font);
        graphics.drawString("КОСМИЧЕСКИЕ ВОЙНЫ", 90, 55);

        graphics.setColor(Color.ORANGE);
        Font font2 = new Font("Arial", Font.BOLD, 25);
        graphics.setFont(font2);
        graphics.drawString("CREATED BY TITOV DMITRIY ©", 65, 660);
    }
}