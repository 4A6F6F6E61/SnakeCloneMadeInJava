import javax.swing.*;
import java.io.File;
import org.ini4j.*;
import org.ini4j.spi.*;

/**
 *
 * Ueber diese Java-Datei startet man alles.
 *
 * @version 1.0 from 03/29/2021
 * @JFK_Bruechner
 */

class Launch
{
  public static MainMenu menu;
  static Wini ini;
  static int unit = 1;
  static int width = 1;
  static int height = 1;
  
  public static void main(String[] args)
  {          
    menu = new MainMenu("Snake");   
    try {
      ini = new Wini(new File("settings.ini")); 
      unit = ini.get("Grid Settings", "unit", int.class);
      width = ini.get("Grid Settings", "width", int.class);
      height = ini.get("Grid Settings", "height", int.class);
    } catch(Exception e) {}
    System.out.println(unit);
  }
}

