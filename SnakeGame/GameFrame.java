
package SnakeGame;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class GameFrame extends JFrame {
    JFrame f1=new JFrame("Snake Game");
   
    GamePanel pan=new GamePanel();
    Color c1=new Color(118, 215, 196);
    Border border = new LineBorder(Color.blue, 3, true);
      
    public void run(){
        //Frame
        f1.setBounds(10,10,600,600);
        f1.setResizable(false);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        f1.add(pan);
        Image img=Toolkit.getDefaultToolkit().createImage("C:\\Users\\admin\\Downloads\\log.png");
        f1.setIconImage(img);
        //Panel
        pan.setBackground(c1);
        pan.setBorder(border);
         
       
 
}
   
}
