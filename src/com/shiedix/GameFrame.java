package com.shiedix;
import javax.swing.*;

public class GameFrame extends JFrame{
    GameFrame(){
        this.add(new GamePanel(this));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Snake");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public void restart()
    {
        this.dispose();
        new GameFrame();
    }
}