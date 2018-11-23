package com.vsu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    private static JFrame frame = new JFrame("SPACE BATTLE");
    private static JButton button = new JButton("PLAY");
    private static JButton buttonExit = new JButton("EXIT");
    private static JPanel panel = new JPanel();
    private static DrawScreenSaver screen = new DrawScreenSaver();
    static GameField gameField;

    static BufferedImage playerImage = getBufferedImage("Images/rocket_2.png");
    static BufferedImage bulletImage = getBufferedImage("/Users/test/Documents/Game/MyGame/Images/bullet.png");
    static BufferedImage bonusImage = getBufferedImage("/Users/test/Documents/Game/MyGame/Images/bonus.png");
    static BufferedImage enemyImage = getBufferedImage("Images/enemy.png");
    static BufferedImage getBufferedImage(String path) {
        try {
            return ImageIO.read(new java.io.File(path));
        } catch (Exception e) {
            return null;
        }
    }

    static {
        try {
            gameField = new GameField();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isGameStart = false;


    public static void main(String[] args) {
        frame.setSize(500, 700);
        frame.setResizable(false);

        button.setPreferredSize(new Dimension(150, 100));
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setForeground(new Color(255, 188, 59));

        button.addActionListener(new Handler());

        buttonExit.setPreferredSize(new Dimension(150, 100));
        buttonExit.setBorderPainted(false);
        buttonExit.setFont(new Font("Arial", Font.BOLD, 30));
        buttonExit.setForeground(new Color(255, 188, 59));
        buttonExit.addActionListener(new Handler());


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(screen);
        screen.setLayout(new GridBagLayout());
        screen.add(button);
        screen.add(buttonExit);

        frame.setVisible(true);
    }

    public static class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                isGameStart = true;
                frame.getContentPane().remove(screen);
                frame.getContentPane().add(gameField);
                frame.setVisible(true);
                gameField.requestFocusInWindow();
            }

            if (e.getSource() == buttonExit) {

                JOptionPane.showMessageDialog(null, "GOOD BYE!!!!");
                System.exit(1);
                System.out.print(-1);
            }
        }
    }
}
