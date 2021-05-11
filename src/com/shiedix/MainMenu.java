package com.shiedix;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * Das Hauptmenue der App
 *
 * @version 1.0 from 03/29/2021
 * @author JFK_Bruechner
 */

public class MainMenu extends JFrame {
  // start attributes
  private JLabel lSnakeClonemadeinJava = new JLabel();
  private JButton bPlay = new JButton();
  private JButton bAbout = new JButton();
  private JButton bSettings = new JButton();
  private JButton bExit = new JButton();
  // end attributes
  //Start Frames
  public static MainMenu menu;
  public static About aboutDialog;
  public static GameFrame game;
  //End Frames
  
  public MainMenu(String title) { 
    // Frame-Init
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
    
    lSnakeClonemadeinJava.setBounds(80, 16, 167, 41);
    lSnakeClonemadeinJava.setText("Snake Clone made in Java");
    lSnakeClonemadeinJava.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    cp.add(lSnakeClonemadeinJava);
    bPlay.setBounds(24, 72, 233, 37);
    bPlay.setText("Play");
    bPlay.setMargin(new Insets(2, 2, 2, 2));
    bPlay.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bPlay_ActionPerformed(evt);
      }
    });
    cp.add(bPlay);
    bSettings.setBounds(24, 114, 233, 37);
    bSettings.setText("Settings");
    bSettings.setMargin(new Insets(2, 2, 2, 2));
    bSettings.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        bSettings_ActionPerformed(evt);
      }
    });
    cp.add(bSettings);
    bAbout.setBounds(24, 157, 233, 37);
    bAbout.setText("About");
    bAbout.setMargin(new Insets(2, 2, 2, 2));
    bAbout.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAbout_ActionPerformed(evt);
      }
    });
    cp.add(bAbout);
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
      UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
      SwingUtilities.updateComponentTreeUI(this);
    } catch(Exception e) {}
    // end style
    setVisible(true);
  } // end of public MainMenu
  
  // start methods
  
  public void bPlay_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    game = new GameFrame();
    this.dispose();
  } // end of bPlay_ActionPerformed

  public void bSettings_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    new Settings(this, true);
  }

  public void bAbout_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    aboutDialog = new About(this, true);
  } // end of bAbout_ActionPerformed

  public void bExit_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    this.dispose();
  } // end of bExit_ActionPerformed

  // end methods
} // end of class MainMenu
