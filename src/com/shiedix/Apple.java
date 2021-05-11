package com.shiedix;
import java.io.File;
import org.ini4j.*;

/**
 *
 * Die Klasse die zum erstellen der Aepfel genutzt wird
 *
 * @version 1.0 from 03/29/2021
 * @author JFK_Bruechner
 */

class Apple
{
  private int posX;
  private int posY;
  static Wini ini;
  int unit;
  int width;
  int height;
  boolean run = false;
  
  public Apple()
  {
    try {
      ini = new Wini(new File("src/com/shiedix/settings.ini"));
      unit = (int) ini.get("Grid Settings", "unit", int.class);
      width = (int) ini.get("Grid Settings", "width", int.class);
      height = (int) ini.get("Grid Settings", "height", int.class);
    } catch(Exception e) {}

    this.posX = (int)(Math.random()*(this.width/this.unit)) * this.unit;
    this.posY = (int)(Math.random()*(this.height/this.unit)) * this.unit;      
  }
  public void newPos(GameCanvas gc)
  {
    this.posX = (int)(Math.random()*12) * unit;
    this.posY = (int)(Math.random()*12) * unit; 
  }
  public int getX()
  {
    return this.posX; 
  }
  public int getY()
  {
    return this.posY; 
  }
}
