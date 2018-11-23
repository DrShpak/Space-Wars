package com.vsu;

/**
 * Тут везде оставлены закомментированные куски кода с итераторами
 * Я специально их оставил, чтобы в понедельник спросить у Вас, почему они не работали корректно
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    public final Object lock = new Object();

    private Timer timer = new Timer(20, this); // частота обновления кадров (отрисовки) , запускает фукцию экшн перформед каждые 20 мс

    private Image img = new ImageIcon("Images/background.jpg").getImage();

    private Player player = new Player();

    boolean leftKeyPressed = false;
    boolean rightKeyPressed = false;

    //private Thread enemiesRespawn = new Thread(this);

    private java.util.List<Enemy> enemies = new ArrayList<Enemy>();
    private java.util.List<Bonus> bonuses = new ArrayList<Bonus>();
    private java.util.List<Bullet> bullets = new ArrayList<Bullet>();


    public GameField() throws IOException {
        timer.start();
        //enemiesRespawn.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    public class MyKeyAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT)
                rightKeyPressed = true;
            if (key == KeyEvent.VK_LEFT)
                leftKeyPressed = true;
        }

        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT)
                rightKeyPressed = false;
            if (key == KeyEvent.VK_LEFT)
                leftKeyPressed = false;
        }
    }

    public void paint(Graphics graphics) {

        graphics = (Graphics2D) graphics;

        graphics.drawImage(img, 0, player.layer1, null);
        graphics.drawImage(img, 0, player.layer2, null);

        graphics.drawImage(player.img, player.x, player.y, null);

        int distance = player.getDistance();
        graphics.setColor(Color.ORANGE);
        Font font = new Font("Arial", Font.BOLD, 20);
        graphics.setFont(font);
        graphics.drawString("Score: " + distance, 190, 35);

        //Iterator<Enemy> i = enemies.iterator();
        //while (i.hasNext()) {
        //    Enemy enemy = i.next();
        for (Enemy enemy : enemies) {
            if (enemy.getY() >= 700) {
                enemies.remove(enemy); // удаляем элемент из коллекции
                break;
            } else {
                enemy.move();
                graphics.drawImage(enemy.img, enemy.getX(), enemy.getY(), null);
//                    break;
            }
        }


//        Iterator<Bonus> j = bonuses.iterator();
//        while (j.hasNext()) {
//            Bonus bonus = j.next();
        for (Bonus bonus: bonuses) {

            if (bonus.getY() >= 700) {
                bonuses.remove(bonus); // удаляем элемент из коллекции
                break;
            } else {
                bonus.move();
                graphics.drawImage(bonus.img, bonus.getX(), bonus.getY(), null);
            }
        }


//        Iterator<Bullet> k = bullets.iterator();
//        while (k.hasNext()) {
//            Bullet bullet = k.next();
        for (Bullet bullet: bullets) {

            if (bullet.getyBullet() <= -100) {
                bullets.remove(bullet);
                break;
            } else {
                bullet.moveBullet();
                paintBullet(graphics, bullet);
            }
        }
    }

    public void paintBullet(Graphics graphics, Bullet bullet) {
        graphics.drawImage(bullet.img, bullet.getxBullet() + 5, bullet.getyBullet(), null);
    }

    // вызывается каждый раз, когда что-либо происходит
    @Override
    public void actionPerformed(ActionEvent e) {
        processKeys();
        player.move();
        respawn();
        addBullets(player);
        synchronized (lock) {
            repaint();
        }

        testCollision();

        testBonusCollision();

        testCollusionWithEnemy();
    }

    private void respawn() {
        Random random = new Random();
        if (Main.isGameStart) {
            if (random.nextInt(20) == 0) {
                addEnemy();
            }
            if (random.nextInt(60) == 0) {
                addBonus();
            }
        }
    }

    private void processKeys() {
        if (leftKeyPressed != rightKeyPressed) {
            if (leftKeyPressed)
                player.setDirection(-5);
            else
                player.setDirection(+5);
        } else
            player.setDirection(0);
    }

    private void testCollision() {

//        Iterator<Enemy> i = enemies.iterator();
//        while (i.hasNext()) {
//            Enemy enemy = i.next();
        for (Enemy enemy: enemies) {

            if (player.getRectangle().intersects(enemy.getRectangle())) {
                JOptionPane.showMessageDialog(null, "GAME OVER!!!!" + "\nYOUR SCORE IS " + player.getDistance());
                System.exit(1);
            }
        }
    }

    private void testBonusCollision() {
//        Iterator<Bonus> j = bonuses.iterator();
//        while (j.hasNext()) {
//            Bonus bonus = j.next();
        for (Bonus bonus: bonuses) {

            if (player.getRectangle().intersects(bonus.getRectangle())) {
                player.bullets += 7;
                player.startBulletTimer();
                bonuses.remove(bonus);
                break;
            }
        }
    }

    private void testCollusionWithEnemy() {
        boolean forWorkLoop = true;
/*        while (q) {
            Iterator<Bullet> k = bullets.iterator();
            Iterator<Enemy> i = enemies.iterator();
            start:
            while (i.hasNext()) {
                Enemy enemy = i.next();
                while (k.hasNext()) {
                    Bullet bullet = k.next();
                    if (bullet.getRectangle().intersects(enemy.getRectangle())) {
                        k.remove();
                        i.remove();
                        break start;
                    }
                }
            }
            q = false;
        }*/

        while (forWorkLoop) {
            start:
            for (Bullet bullet : bullets)
                for (Enemy enemy : enemies) {
                    if (bullet.getRectangle().intersects(enemy.getRectangle())) {
                        bullets.remove(bullet);
                        enemies.remove(enemy);
                        break start;
                    }
                }
            forWorkLoop = false;
        }

    }

    private void addBullets(Player player) {
        synchronized (lock) {
            try {
                if (player.bullets > 0 && player.drawBullet) {
                    player.bullets--;
                    bullets.add(new Bullet(player.getX(), player.getY(), 2));
                    player.drawBullet = false;
                }
            } catch (Exception ignored) {
            }
        }
    }

    private void addBonus() {
        try {
            Random random = new Random();
            bonuses.add(new Bonus(random.nextInt(400), -50, Bonus.getSpeed()));
        } catch (Exception ignored) {
        }
    }

    private void addEnemy() {
        Random random = new Random();
        enemies.add(new Enemy(random.nextInt(400), -50, random.nextInt(7) + 2));
    }
}
