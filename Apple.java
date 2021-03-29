import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import org.ini4j.*;

/**
 *
 * Die Klasse die zum erstellen der Aepfel genutzt wird
 *
 * @version 1.0 from 03/29/2021
 * @JFK_Bruechner
 */

class Apple
{
  private int posX;
  private int posY;
  static Wini ini;
  static int unit = 1;
  static int width = 1;
  static int height = 1;
  boolean run = false;
  
  public Apple()
  {
    this.posX = (int)(Math.random()*(this.width/this.unit)) * this.unit;
    this.posY = (int)(Math.random()*(this.height/this.unit)) * this.unit;      
  }
  public void newPos(GameCanvas gc)
  {
    if(!run){
      try {
        ini = new Wini(new File("settings.ini")); 
        unit = ini.get("Grid Settings", "unit", int.class);
        width = ini.get("Grid Settings", "width", int.class);
        height = ini.get("Grid Settings", "height", int.class);
      } catch(Exception e) {}
      run = true;
    }
    this.posX = (int)(Math.random()*12) * unit;
    this.posY = (int)(Math.random()*12) * unit; 
  }
  public int getX()
  {
    //System.out.println(this.posX);
    return this.posX; 
  }
  public int getY()
  {
    //System.out.println(this.posY);
    return this.posY; 
  }
}
