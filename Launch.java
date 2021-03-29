import javax.swing.*;
import java.io.File;
import org.ini4j.*;
import org.ini4j.spi.*;


class Launch
{
  public static MainMenu menu;
  static Wini ini;
  static int unit = 1;
  static int width = 1;
  static int height = 1;
  
  public Launch()
  {
  }
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

