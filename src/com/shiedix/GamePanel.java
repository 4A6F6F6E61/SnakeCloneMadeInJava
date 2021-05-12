package com.shiedix;

import java.awt.*;
import org.ini4j.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.util.Random;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class GamePanel extends JPanel implements ActionListener
{
    static int width;
    static int height;
    static int unit;
    static int game_units;
    static int delay;
    static Color background_color;
    static Color snake_color;
    static Color snake_head_color;
    static Color apple_color;
    static Color text;
    static Color text_high_score;
    static Color text_game_over;
    static Color text_current_score;
    int[] x;
    int[] y;
    int bodyParts = 6;
    static int applesEaten;
    int high_score = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    boolean build = false;
    Timer timer;
    Random random;
    static Wini ini;
    GameFrame win;

    GamePanel(GameFrame gf)
    {
        try {
            if (build) {
                ini = new Wini(new File("settings.ini"));
            } else {
                ini = new Wini(new File("src/com/shiedix/settings.ini"));
            }
            unit = (int) ini.get("Grid Settings", "unit", int.class);
            width = (int) ini.get("Grid Settings", "width", int.class);
            height = (int) ini.get("Grid Settings", "height", int.class);
            high_score = (int) ini.get("High Score", "high_score", int.class);
            delay = (int) ini.get("Timer", "delay", int.class);
            switch ((int) ini.get("Theme", "current_theme", int.class)) {
                case 0 -> setTheme("Material Dark");
                case 1, 2 -> setTheme("Material Light");
            }

        } catch(Exception e) {
            System.out.println("error:  " + e);
        }
        game_units = (width*height)/(unit*unit);
        x = new int[game_units];
        y = new int[game_units];
        random = new Random();
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(background_color);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.win = gf;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Closing Discord hook.");
            DiscordRPC.discordShutdown();
        }));
        startGame();
    }

    public void startGame()
    {
        newApple();
        running = true;
        timer = new Timer(delay,this);
        timer.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        DiscordRPC.discordRunCallbacks();
        updateDiscord();
        if(running) {
            g.setColor(apple_color);
            g.fillRect(appleX, appleY, unit, unit);

            for(int i = 0; i< bodyParts;i++) {
                if(i == 0) {
                    g.setColor(snake_head_color);
                } else {
                    g.setColor(snake_color);
                    //g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                }
                g.fillRect(x[i], y[i], unit, unit);
            }
            g.setColor(text_current_score);
            g.setFont( new Font("Times new Roman",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten, (width - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
            g.setFont( new Font("Times new Roman",Font.BOLD, 20));
            g.drawString("High-Score: "+high_score, 0, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void newApple()
    {
        appleX = random.nextInt((int)(width/unit))*unit;
        appleY = random.nextInt((int)(height/unit))*unit;
    }

    public void move()
    {
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U' -> y[0] = y[0] - unit;
            case 'D' -> y[0] = y[0] + unit;
            case 'L' -> x[0] = x[0] - unit;
            case 'R' -> x[0] = x[0] + unit;
        }
    }

    public void checkApple()
    {
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions()
    {
        //checks if head collides with body
        for(int i = bodyParts;i>0;i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }
        }

        for(int i = bodyParts;i>0;i--) {
            if((appleX == x[i])&& (appleY == y[i])) {
                System.out.println("AU");
                newApple();
            }
        }

        //check if head touches left border
        if(x[0] < 0) {
            running = false;
        }

        //check if head touches right border

        if(x[0] > width) {
            running = false;
        }

        //check if head touches top border
        if(y[0] < 0) {
            running = false;
        }

        //check if head touches bottom border
        if(y[0] > height) {
            running = false;
        }
        if(!running) {
            timer.stop();
            if(applesEaten > high_score) {
                high_score = applesEaten;
                try {
                    ini.put("High Score", "high_score", ""+applesEaten);
                    ini.store();
                } catch (Exception e) {
                    System.out.println("error:  " + e);
                }
            }
        }
    }

    public void gameOver(Graphics g)
    {
        //Score
        g.setColor(text_current_score);
        g.setFont( new Font("Times new Roman",Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (width - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
        g.setColor(text_high_score);
        g.setFont( new Font("Times new Roman",Font.BOLD, 20));
        g.drawString("High-Score: "+high_score, 0, g.getFont().getSize());

        g.setColor(text_game_over);
        g.setFont( new Font("Times new Roman",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (width - metrics2.stringWidth("Game Over"))/2, height/2);

        g.setColor(text);
        g.setFont( new Font("Times new Roman",Font.BOLD, 30));
        g.drawString("Press Enter to play again", 10, height - 4*unit);
        g.drawString("Press Backspace to Quit", 10, height - 3*unit);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }

    public void setTheme(String name)
    {
        background_color = hex2Rgb((String) ini.get(name, "background_color", String.class));
        snake_color = hex2Rgb((String) ini.get(name, "snake_color", String.class));
        snake_head_color = hex2Rgb((String) ini.get(name, "snake_head_color", String.class));
        apple_color = hex2Rgb((String) ini.get(name, "apple_color", String.class));
        text = hex2Rgb((String) ini.get(name, "text", String.class));
        text_high_score = hex2Rgb((String) ini.get(name, "text_high_score", String.class));
        text_game_over = hex2Rgb((String) ini.get(name, "text_game_over", String.class));
        text_current_score = hex2Rgb((String) ini.get(name, "text_current_score", String.class));
    }

    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    if(!running) {
                        win.dispose();
                        new MainMenu("Snake");
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if(!running) {
                        win.restart();
                    }
                    break;
            }
        }
    }

    private static void updateDiscord()
    {
        DiscordRichPresence.Builder discordPresence = new DiscordRichPresence.Builder("Score: " + applesEaten);
        discordPresence.setDetails("in Game");
        DiscordRPC.discordUpdatePresence(discordPresence.build());
    }
}