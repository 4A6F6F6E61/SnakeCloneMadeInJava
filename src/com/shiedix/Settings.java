package com.shiedix;

import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import org.ini4j.Wini;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Description
 *
 * @version 1.0 from 10/05/2021
 * @author Joona Brückner
 */

public class Settings extends JDialog
{
  // start attributes
  private JTextField tfUnit = new JTextField();
  private JTextField tfwidth = new JTextField();
  private JTextField tfHeight = new JTextField();
  private JTextField tfDelay = new JTextField();
  private JButton bUnit = new JButton();
  private JButton bWidth = new JButton();
  private JButton bHeight = new JButton();
  private JButton bDelay = new JButton();
  private JButton bTheme = new JButton();
  private JLabel lUnit = new JLabel();
  private JLabel lWidth = new JLabel();
  private JLabel lHeight = new JLabel();
  private JLabel lDelay = new JLabel();
  private JLabel lTheme = new JLabel();
  private MainMenu main;
  private String[] cbOptions = {
          "Material Dark", "Material Light", "Windows Light"
  };
  private JComboBox cbTheme = new JComboBox(cbOptions);
  private JButton bCancle = new JButton();
  private JButton bSaveall = new JButton();
  private Wini ini;
  private boolean build = Main.BUILD;
  // end attributes
  
  public Settings(MainMenu owner, boolean modal)
  {
    // Dialog-Init
    super(owner, modal);
    this.main = owner;
    try {
      if (build) {
        ini = new Wini(new File("settings.ini"));
      } else {
        ini = new Wini(new File("src/com/shiedix/settings.ini"));
      }
    } catch (Exception e) {}
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        updateDiscord("Main Menu");
      }
    });
    updateDiscord("Settings");
    int frameWidth = 271; 
    int frameHeight = 233;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Settings");
    Container cp = getContentPane();
    cp.setLayout(null);
    // start components
    
    tfUnit.setBounds(48, 8, 137, 25);
    tfUnit.setToolTipText("Unit...");
    tfUnit.setText("");
    cp.add(tfUnit);
    tfwidth.setBounds(48, 40, 137, 25);
    tfwidth.setToolTipText("Width...");
    cp.add(tfwidth);
    tfHeight.setBounds(48, 72, 137, 25);
    tfHeight.setToolTipText("Height...");
    cp.add(tfHeight);
    tfDelay.setBounds(48, 104, 137, 25);
    tfDelay.setToolTipText("Thread time...");
    cp.add(tfDelay);
    bUnit.setBounds(192, 8, 57, 25);
    bUnit.setText("Save");
    bUnit.setMargin(new Insets(2, 2, 2, 2));
    bUnit.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bUnit_ActionPerformed(evt);
      }
    });
    cp.add(bUnit);
    bWidth.setBounds(192, 40, 57, 25);
    bWidth.setText("Save");
    bWidth.setMargin(new Insets(2, 2, 2, 2));
    bWidth.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bWidth_ActionPerformed(evt);
      }
    });
    cp.add(bWidth);
    bHeight.setBounds(192, 72, 57, 25);
    bHeight.setText("Save");
    bHeight.setMargin(new Insets(2, 2, 2, 2));
    bHeight.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bHeight_ActionPerformed(evt);
      }
    });
    cp.add(bHeight);
    bDelay.setBounds(192, 104, 57, 25);
    bDelay.setText("Save");
    bDelay.setMargin(new Insets(2, 2, 2, 2));
    bDelay.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDelay_ActionPerformed(evt);
      }
    });
    cp.add(bDelay);
    lUnit.setBounds(8, 8, 33, 25);
    lUnit.setText("Unit:");
    cp.add(lUnit);
    lWidth.setBounds(8, 40, 40, 25);
    lWidth.setText("Width:");
    cp.add(lWidth);
    lHeight.setBounds(8, 72, 43, 25);
    lHeight.setText("Height:");
    cp.add(lHeight);
    lDelay.setBounds(8, 104, 38, 25);
    lDelay.setText("Delay:");
    cp.add(lDelay);
    lTheme.setBounds(8, 134, 43, 25);
    lTheme.setText("Theme:");
    cp.add(lTheme);
    cbTheme.setBounds(61, 134, 124, 25);
    int temp = 0;
    try {
      temp = (int) ini.get("Theme", "current_theme", int.class);
    } catch (Exception e) {}
    cbTheme.setSelectedIndex(temp);
    cp.add(cbTheme);
    bTheme.setBounds(192, 134, 57, 25);
    bTheme.setText("Save");
    bTheme.setMargin(new Insets(2, 2, 2, 2));
    bTheme.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        bTheme_ActionPerformed(evt);
      }
    });
    cp.add(bTheme);
    bCancle.setBounds(24, 164, 97, 25);
    bCancle.setText("cancle");
    bCancle.setMargin(new Insets(2, 2, 2, 2));
    bCancle.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bCancle_ActionPerformed(evt);
      }
    });
    cp.add(bCancle);
    bSaveall.setBounds(128, 164, 97, 25);
    bSaveall.setText("Save all");
    bSaveall.setMargin(new Insets(2, 2, 2, 2));
    bSaveall.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSaveall_ActionPerformed(evt);
      }
    });
    cp.add(bSaveall);
    // end components
    
    setResizable(false);
    setVisible(true);
  } // end of public Settings
  public void saveUnit()
  {
    try {
      ini.put("Grid Settings", "unit", ""+tfUnit.getText());
      ini.store();
    } catch(Exception e) { }
  }
  public void saveWidth()
  {
    try {
      ini.put("Grid Settings", "width", ""+tfwidth.getText());
      ini.store();
    } catch(Exception e) { }
  }
  public void saveHeight()
  {
    try {
      ini.put("Grid Settings", "height", ""+tfHeight.getText());
      ini.store();
    } catch(Exception e) { }
  }
  public void saveDelay()
  {
    try {
      ini.put("Timer", "delay", ""+tfDelay.getText());
      ini.store();
    } catch(Exception e) { }
  }
  public void sTheme(String theme) throws Exception
  {
    UIManager.setLookAndFeel(theme);
    SwingUtilities.updateComponentTreeUI(this);
  }
  public void changeTheme(int theme_number)
  {
    try {
      switch (theme_number) {
        case 0 -> {
          main.theme("com.formdev.flatlaf.FlatDarculaLaf");
          sTheme("com.formdev.flatlaf.FlatDarculaLaf");
        }
        case 1 -> {
          main.theme("com.formdev.flatlaf.FlatIntelliJLaf");
          sTheme("com.formdev.flatlaf.FlatIntelliJLaf");
        }
        case 2 -> {
          main.theme("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
          sTheme("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
      }
      ini.put("Theme", "current_theme", ""+theme_number);
      ini.store();
    } catch(Exception e) { }
  }
  public void bUnit_ActionPerformed(ActionEvent evt)
  {
    saveUnit();
  }
  public void bWidth_ActionPerformed(ActionEvent evt)
  {
    saveWidth();
  }
  public void bHeight_ActionPerformed(ActionEvent evt)
  {
    saveHeight();
  }
  public void bDelay_ActionPerformed(ActionEvent evt)
  {
    saveDelay();
  }
  public void bTheme_ActionPerformed(ActionEvent evt)
  {
    int index = (int) cbTheme.getSelectedIndex();
    changeTheme(index);
  } // end of bUnit_ActionPerformed
  public void bCancle_ActionPerformed(ActionEvent evt)
  {
    updateDiscord("Main Menu");
    this.dispose();
  } // end of bCancle_ActionPerformed
  public void bSaveall_ActionPerformed(ActionEvent evt)
  {
    int index = (int) cbTheme.getSelectedIndex();
    if (!tfHeight.getText().equals("")) {
      saveHeight();
    }
    if (!tfwidth.getText().equals("")) {
      saveWidth();
    }
    if (!tfUnit.getText().equals("")) {
      saveUnit();
    }
    if (!tfDelay.getText().equals("")) {
      saveDelay();
    }
    changeTheme(index);
    updateDiscord("Main Menu");
    this.dispose();
  } // end of bSaveall_ActionPerformed
  private static void updateDiscord(String message)
  {
    DiscordRichPresence.Builder discordPresence = new DiscordRichPresence.Builder(""+message);
    discordPresence.setDetails("");
    DiscordRPC.discordUpdatePresence(discordPresence.build());
  }
} // end of class Settings

