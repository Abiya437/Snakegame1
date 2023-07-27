package SnakeGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    static final int Screen_Width = 600;     //Screen width
    static final int Screen_Height = 600;    //Screen Height
    static final int Ob_Size = 20;      //how big do we want the Object size 
    static final int Game_Ob = (Screen_Width * Screen_Height) / Ob_Size;  //Calculate how many objects we can actually fit on the screen
    static final int Delay = 100;
    final int x[] = new int[Game_Ob];
    final int y[] = new int[Game_Ob];
    int Bodyparts = 4; //Snake initial bodyparts
    int foodEaten; // declare foodeaten initially be zero 
    int foodX;     // the x-corodinate of where the food is located
    int foodY;     // the y-corodinate of where the food is located 
    char direction = 'R'; // initialze the direction of snake
    boolean running = false;
    Timer time;
    Random ran;

    GamePanel() {
        ran = new Random();
        this.setPreferredSize(new Dimension(Screen_Width, Screen_Height));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        GameStart();
    }

    public void newFood() {
        foodX = ran.nextInt((int) (Screen_Width / Ob_Size)) * Ob_Size;
        foodY = ran.nextInt((int) (Screen_Height / Ob_Size)) * Ob_Size;
    }

    public void GameStart() {
        newFood();
        running = true;
        time = new Timer(Delay, this);
        time.start();
    }

    public void Moving() {
        for (int i = Bodyparts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - Ob_Size;
                break;
            case 'D':
                y[0] = y[0] + Ob_Size;
                break;
            case 'L':
                x[0] = x[0] - Ob_Size;
                break;
            case 'R':
                x[0] = x[0] + Ob_Size;
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            /*for (int i = 0; i < Screen_Height / Ob_Size; i++) {
                g.drawLine(i * Ob_Size, 0, i * Ob_Size, Screen_Height);
                g.drawLine(0, i * Ob_Size, Screen_Width, i * Ob_Size);
            }*/
            g.setColor(new Color(181, 78, 155));
            g.fillOval(foodX, foodY, Ob_Size, Ob_Size);

            for (int i = 0; i < Bodyparts; i++) {
                if (i == 0) {
                    g.setColor(new Color(17, 153, 55));
                    g.fillRect(x[i], y[i], Ob_Size, Ob_Size);
                } else {
                    g.setColor(new Color(16, 235, 53));
                    g.fillRect(x[i], y[i], Ob_Size, Ob_Size);
                }
            }
            g.setColor(new Color(161, 55, 104));
            g.setFont(new Font("Sancerif", Font.BOLD, 20));
            FontMetrics metric = getFontMetrics(g.getFont());//alligning of the text in center of Scrren
            g.drawString("Your Score: " + foodEaten, (Screen_Width - metric.stringWidth("Your Score: " + foodEaten))/12, g.getFont().getSize());

        } else {
            gameOver(g);
        }
    }

    public void ChkFood() {
        if ((x[0] == foodX) && (y[0] == foodY)) {
            Bodyparts++;
            foodEaten++;
            newFood();
        }

    }

    public void ChkCollision() {
        for (int i = Bodyparts; i > 0; i--) {      //Check head with body  collides the object
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        if (x[0] < 0) {               //check head touch the left border
            running = false;
        }
        if (x[0] > Screen_Width) {   //check head touch the right border
            running = false;
        }
        if (y[0] < 0) {
            running = false;          //Check head touch the top 
        }
        if (y[0] > Screen_Height) {
            running = false;           //Check head touch the bottom
        }
        if (!running) {
            time.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            Moving();
            ChkFood();
            ChkCollision();
        }
        repaint();
    }

    public void gameOver(Graphics g) {
        //Game Over
        g.setColor(Color.RED);
        g.setFont(new Font("Sancerif", Font.BOLD, 78));
        FontMetrics metric1 = getFontMetrics(g.getFont());//alligning of the text in center of Scrren
        g.drawString("Game Over", (Screen_Width - metric1.stringWidth("Game Over"))/2, Screen_Height / 2);
           //Score displaying after game over
        g.setColor(new Color(235, 104, 75));
        g.setFont(new Font("Sancerif", Font.BOLD, 20));
        FontMetrics metric2 = getFontMetrics(g.getFont());//alligning of the text in center of Scrren
        g.drawString("Your Score is: " + foodEaten, (Screen_Width - metric2.stringWidth("Your Score: " + foodEaten))/12, g.getFont().getSize());
    }

    public class MyKeyAdapter implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
