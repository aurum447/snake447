package org.example;

import javax.swing.*;
import java.awt.*;

public class MyPan extends JPanel {

    private Snake s;
    //private MyField field = s.field;
    int count = 1;

    public MyPan(Snake ss) {
        s = ss;
    }

    public void paint(Graphics g) {

        if (s.DEATH) {
            g.setColor(Color.RED);
            g.fillRect(0, 0, 800, 800);
            System.exit(0);
        }
        if (s.WIN) {
            g.setColor(Color.PINK);
            g.fillRect(0, 0, 800, 800);
            System.exit(1);
        }


        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 800);


        g.setColor(Color.PINK);

        g.fillRoundRect(s.x, s.y, 50, 50, 20, 20);


        for (int k = 0; k < 255; k++) {
            if (s.snake[k][0] != -1 && s.snake[k][1] != -1) {
                int i = s.snake[k][0];
                int j = s.snake[k][1];
                int[] pair = myUtil.indexToCoordinate(i, j);
                g.fillRoundRect(pair[0], pair[1], 50, 50, 40, 40);
            }
        }

        g.setColor(Color.GREEN);
        g.fillRoundRect(s.x_apple, s.y_apple, 50, 50, 70, 70);


    }
}
