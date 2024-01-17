package com.joona;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
// import net.arikia.dev.drpc.DiscordRPC;
// import net.arikia.dev.drpc.DiscordRichPresence;
import org.ini4j.Wini;

@Author(
        name = "Joona Brueckner",
        github = "@Zockedidock"
)
public class MainMenu extends JFrame
{
  //Start Frames
  public static MainMenu menu;
  public static About aboutDialog;
  public static GameFrame game;
  static Wini ini;
  boolean build = Main.BUILD;
  //End Frames
  
  public MainMenu(String title)
  {
    // Frame-Init
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    updateDiscord();
    int frameWidth = 296; 
    int frameHeight = 283;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle(title);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // start components

    // start attributes
    JLabel lSnakeClonemadeinJava = new JLabel();
    lSnakeClonemadeinJava.setBounds(80, 16, 167, 41);
    lSnakeClonemadeinJava.setText("Snake Clone made in Java");
    lSnakeClonemadeinJava.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    cp.add(lSnakeClonemadeinJava);
    JButton bPlay = new JButton();
    bPlay.setBounds(24, 72, 233, 37);
    bPlay.setText("Play");
    bPlay.setMargin(new Insets(2, 2, 2, 2));
    bPlay.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bPlay_ActionPerformed(evt);
      }
    });
    cp.add(bPlay);
    JButton bSettings = new JButton();
    bSettings.setBounds(24, 114, 233, 37);
    bSettings.setText("Settings");
    bSettings.setMargin(new Insets(2, 2, 2, 2));
    bSettings.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        bSettings_ActionPerformed(evt);
      }
    });
    cp.add(bSettings);
    JButton bAbout = new JButton();
    bAbout.setBounds(24, 157, 233, 37);
    bAbout.setText("About");
    bAbout.setMargin(new Insets(2, 2, 2, 2));
    bAbout.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAbout_ActionPerformed(evt);
      }
    });
    cp.add(bAbout);
    JButton bExit = new JButton();
    bExit.setBounds(64, 208, 153, 25);
    bExit.setText("Exit");
    bExit.setMargin(new Insets(2, 2, 2, 2));
    bExit.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bExit_ActionPerformed(evt);
      }
    });
    cp.add(bExit);
    // end components
    // start style
    try {
      if (build)
        ini = new Wini(new File("settings.ini"));
      else
        ini = new Wini(new File("src/main/java/com/joona/settings.ini"));

      UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
      switch ((int) ini.get("Theme", "current_theme", int.class)) {
        case 0 -> theme("com.formdev.flatlaf.FlatDarculaLaf");
        case 1 -> theme("com.formdev.flatlaf.FlatIntelliJLaf");
        case 2 -> theme("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        case 3 -> theme("javax.swing.plaf.metal.MetalLookAndFeel");
        case 4 -> theme("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        case 5 -> theme(UIManager.getCrossPlatformLookAndFeelClassName());
        case 6 -> theme("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        case 7 -> theme("com.apple.laf.AquaLookAndFeel");
        default -> {
          System.out.println("Unknown theme, using FlatLaf");
          theme("com.formdev.flatlaf.FlatIntelliJLaf");
        }
      }
    } catch(Exception e) {
      System.out.println("Error: "+e);
    }
    // end style
    setVisible(true);
  }
  public void bPlay_ActionPerformed(ActionEvent evt)
  {
    // TODO add your code here
    game = new GameFrame();
    this.dispose();
  }
  public void bSettings_ActionPerformed(ActionEvent evt)
  {
    // TODO add your code here
    new Settings(this, true);
  }
  public void bAbout_ActionPerformed(ActionEvent evt)
  {
    // TODO add your code here
    aboutDialog = new About(this, true);
  }
  public void bExit_ActionPerformed(ActionEvent evt)
  {
    // TODO add your code here
    this.dispose();
  }
  public void theme(String theme) throws Exception
  {
    UIManager.setLookAndFeel(theme);
    SwingUtilities.updateComponentTreeUI(this);
  }
  private static void updateDiscord()
  {
    // DiscordRichPresence.Builder discordPresence = new DiscordRichPresence.Builder("Main menu");
    // discordPresence.setDetails("");
    // DiscordRPC.discordUpdatePresence(discordPresence.build());
  }
}
