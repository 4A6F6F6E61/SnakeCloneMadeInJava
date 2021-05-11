package com.shiedix;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer; 
import java.util.TimerTask;
import org.ini4j.*;
import java.io.File;

/**
 *
 * Das GameCanvas selbst.
 *
 * @version 1.0 from 03/29/2021
 * @author JFK_Bruechner
 */

public class GameCanvas extends JPanel implements KeyListener
{
  Apple ppl;
  ArrayList<Integer> x = new ArrayList<Integer>();
  ArrayList<Integer> y = new ArrayList<Integer>(); 
  int xTEMP;
  int yTEMP;
  String direction = "R";
  Timer time = new Timer();
  static Wini ini;
  int unit;
  int width;
  int height;
  boolean launched = false;
  boolean debug = false;
  boolean wait = false;
    
  public GameCanvas()
  {
    try {
      ini = new Wini(new File("src/com/shiedix/settings.ini"));
      unit = (int) ini.get("Grid Settings", "unit", int.class);
      width = (int) ini.get("Grid Settings", "width", int.class);
      height = (int) ini.get("Grid Settings", "height", int.class);
    } catch(Exception e) {}
    System.out.println("GameCanvas");
    ppl = new Apple();
    ppl.newPos(this);
    x.add(0);
    y.add(0);
  }
  
  public void paint(Graphics g)
  {
    System.out.println(unit);
    g.setColor(Color.green);
    g.fillRect(0, 0, 700, 550);

    g.setColor(Color.black);
    for(int i = 0; i < x.size(); i++)
      g.fillRect(x.get(i), y.get(i), this.unit, this.unit);

    g.setColor(Color.red);
    g.fillRect(ppl.getX(), ppl.getY(), this.unit, this.unit);     
    try
    {
      System.out.println("test");
      time.schedule(new TimerTask(){
        @Override
        public void run()
        {
          System.out.println(unit);

          if(ppl.getX() == x.get(0) && ppl.getY() == y.get(0)) {
            System.out.println("hit!");
            System.out.println(x.size());
            x.add(x.size(), x.get(x.size()-1));
            y.add(y.size(), y.get(y.size()-1));
            wait = true;
            debug = true;
          } else wait = false;
                               
          xTEMP = x.get(0);
          yTEMP = y.get(0);

          if(!launched) {
            x.add(1, xTEMP);
            y.add(1, yTEMP);
            launched = true;
          }

          switch (direction) {
            case "R":
              x.set(0, (x.get(0) + unit));
              break;
            case "L":
              x.set(0, (x.get(0) - unit));
              break;
            case "U":
              y.set(0, (y.get(0) + unit));
              break;
            case "D":
              y.set(0, (y.get(0) - unit));
              break;
            default:
          }

          if(wait) {
            for(int i = 1; i < x.size()-1; i++) {
              x.set(i, x.get(i-1));
              y.set(i, y.get(i-1));
            }
          } else {
            for(int i = 1; i < x.size(); i++) {
              x.set(i, x.get(i-1));
              y.set(i, y.get(i-1));
            }
          }

          System.out.println("x:  " + x.get(0) + "  y:  " + y.get(0));
          System.out.println("x:  " + x.get(1) + "  y:  " + y.get(1));
          if (debug)
            System.out.println("x:  " + x.get(2) + "  y:  " + y.get(2));

          repaint();
        }
      }, 800);
    } catch(Exception e){}
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
    switch (e.getKeyCode()) {
      case 40 :  // oben
        this.direction = "U";
        break;
      case 38 :  // unten
        this.direction = "D";
        break;
      case 39 :  // rechts
        this.direction = "R";
        break;
      case 37 :  // links
        this.direction = "L";
        break;
      default: 
        
    } // end of switch
  }                              
  @Override
  public void keyReleased(KeyEvent e) {}
  @Override
  public void keyTyped(KeyEvent e) {}
  
  public void newApple()
  {
    ppl.newPos(this);
  }
}
