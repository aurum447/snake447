package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake extends JComponent {

    int x = 0;
    int y = 0;

    int x_apple = 7*50;
    int y_apple = 7*50;

    public MyPan drawPanel;
    JFrame frame;
    Timer timer = new Timer(100, new MyKey());

    int lastDir = 0;

    int[][] snake = new int[255][2];
    int lengthOfTheSnake = 3;
    int indexOfTheHead = 1;

    boolean WIN = false;
    boolean DEATH = false;

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawPanel = new MyPan(this);
        timer.start();
        frame.getContentPane().add(drawPanel);
        frame.setSize(820, 840);
        frame.setLocation(100, 10);
        frame.setVisible(true);

        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
        frame.addKeyListener(new MyKey());
        frame.setFocusable(true);

        for (int[] a : snake) {
            a[0] = -1;
            a[1] = -1;
        }

        while(true) {
            switch(lastDir) {
                case 0:
                    moveRight();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveUp();
                    break;
            }
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void moveRight() {
        x += 50;
        lastDir = 0;

        partOfMove();
    }

    public void moveLeft() {
        x -= 50;
        lastDir = 2;

        partOfMove();
    }

    public void moveDown() {
        y += 50;
        lastDir = 1;

        partOfMove();
    }

    public void moveUp() {
        y -= 50;
        lastDir = 3;

        partOfMove();
    }

    private void partOfMove() {
        int[] pair = myUtil.coordinateToIndex(x, y);
        snake[indexOfTheHead][0] = pair[0];
        snake[indexOfTheHead][1] = pair[1];

        if (indexOfTheHead > lengthOfTheSnake) {
            snake[indexOfTheHead-lengthOfTheSnake][0] = -1;
            snake[indexOfTheHead-lengthOfTheSnake][1] = -1;
        }
        indexOfTheHead++;

        apple_or_death();

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void apple_or_death() {
        if (x == x_apple && y == y_apple) {
            lengthOfTheSnake++;
            x_apple = ((int)(System.currentTimeMillis()%16)) * 50;
            y_apple = ((int)(System.currentTimeMillis()%16)) * 50;
            if (lengthOfTheSnake >= 10) WIN = true;
        }
        int count = 0;
        for (int[] temp : snake) {
            if(temp[0] != -1 && temp[1] != -1) {
                if (temp[0] * 50 == x && temp[1] * 50 == y) {
                    count++;
                }
            }
        }
        if (count >= 2) DEATH = true;
    }


    public class MyKey extends KeyAdapter implements ActionListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                lastDir = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                lastDir = 3;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                lastDir = 2;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                lastDir = 1;
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            drawPanel.repaint();
        }
    }
}
