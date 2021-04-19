import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
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
 * @JFK_Bruechner
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
  static int unit = 1;
  static int width = 1;
  static int height = 1;
  boolean launched = false;
  int snake_lenght = 2;
    
  public GameCanvas()
  {
    System.out.println("GameCanvas");
    ppl = new Apple();
    ppl.newPos(this);
    x.add(0);
    y.add(0);
  }
  
  public void paint(Graphics g)
  {
    if(!launched){
      try {
        ini = new Wini(new File("settings.ini")); 
        unit = ini.get("Grid Settings", "unit", int.class);
        width = ini.get("Grid Settings", "width", int.class);
        height = ini.get("Grid Settings", "height", int.class);
      } catch(Exception e) {}
      launched = true;
    }
    System.out.println(unit);
    g.setColor(Color.green);
    g.fillRect(0, 0, 700, 550);
    
    if (x.size() != 0) {
      g.fillRect(x.get(0), y.get(0), this.unit, this.unit);
      for (int i = 0; i <= (x.size()-1); i++) {
        g.setColor(Color.black);
        g.fillRect(x.get(i), y.get(i), this.unit, this.unit);
      } 
    }
    
  
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
                               
          xTEMP = x.get(0);
          yTEMP = y.get(0);
            
          switch (direction) {
            case "R" : 
              x.set(x.size()-1, (x.get(x.size()-1) + unit));
              break;
            case "L" :        
              x.set(x.size()-1, (x.get(x.size()-1) - unit));
              break;
            case "U" : 
              y.set(x.size()-1, (y.get(x.size()-1) + unit));
              break;
            case "D" :       
              y.set(x.size()-1, (y.get(x.size()-1) - unit));   
              break;
            default:  
          } // end of switch
          
          if ((x.get(0) == ppl.getX()) && (y.get(0) == ppl.getY())) {
            //System.out.println("test222222");
            switch (direction) {
              case "R" : 
                x.add(ppl.getX() + unit);
                y.add(y.get(y.size()-1));
                if (x.size() > 2) {
                  x.remove(0);
                  y.remove(0);  
                } // end of if
                for (int i = 1; i <= (x.size()-1); i++) {
                  x.set(i-1, x.get(i));
                  y.set(i-1, y.get(i));
                }
                break;
              case "L" : 
                x.add(ppl.getX() - unit);
                y.add(y.get(y.size()-1));
                if (x.size() > 2) {
                  x.remove(0);
                  y.remove(0);  
                } // end of if
                for (int i = 1; i <= (x.size()-1); i++) {
                  x.set(i-1, x.get(i));
                  y.set(i-1, y.get(i));
                }
                break;
              case "U" : 
                x.add(x.get(x.size()-1));
                y.add(ppl.getY() + unit);
                if (x.size() > 2) {
                  x.remove(0);
                  y.remove(0);  
                } // end of if
                for (int i = 1; i <= (x.size()-1); i++) {
                  x.set(i-1, x.get(i));
                  y.set(i-1, y.get(i));
                }
                break;
              case "D" : 
                x.add(x.get(x.size()-1));
                y.add(ppl.getY() - unit);
                if (x.size() > 2) {
                  x.remove(0);
                  y.remove(0);  
                } // end of if
                for (int i = 1; i <= (x.size()-1); i++) {
                  x.set(i-1, x.get(i));
                  y.set(i-1, y.get(i));
                }
                break;
              default:  
            } // end of switch
          }
          /*x.add(xTEMP);
          y.add(yTEMP);
          x.remove(x.size()-1);
          y.remove(y.size()-1);
          
          for (int i = 1;i <= (x.size()-1); i++) {
            x.set(i, x.get(i+1));
            x.set(i, x.get(i+1));
          } // end of for
            */
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
