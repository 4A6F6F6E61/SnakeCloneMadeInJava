package com.joona;

// import com.github.psnrigner.discordrpcjava.DiscordRpc;
// import com.github.psnrigner.discordrpcjava.DiscordRichPresence;
import org.ini4j.Wini;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

@Author(
        name = "Joona Brueckner",
        github = "@Zockedidock"
)
public class Settings extends JDialog
{
  // start attributes
  private final JTextField tfUnit = new JTextField();
  private final JTextField tfWidth = new JTextField();
  private final JTextField tfHeight = new JTextField();
  private final JTextField tfDelay = new JTextField();
  static int width;
  static int height;
  static int unit;
  static int delay;

  static int inputX = 68;
  static int saveX = 210;
  private final MainMenu main;
  boolean build = Main.BUILD;
  private final String[] cbOptions = {
          "Material Dark", "Material Light", "Windows Light", "Default Java L&F", "Motif L&F", "Default OS L&F", "Nimbus", "MacOS Aqua"
  };
  private final JComboBox cbTheme = new JComboBox(cbOptions);
  private Wini ini;

  public Settings(MainMenu owner, boolean modal)
  {

    // Dialog-Init
    super(owner, modal);
    try {
      if (build)
        ini = new Wini(new File("settings.ini"));
      else
        ini = new Wini(new File("src/main/java/com/joona/settings.ini"));

      unit = (int) ini.get("Grid Settings", "unit", int.class);
      width = (int) ini.get("Grid Settings", "width", int.class);
      height = (int) ini.get("Grid Settings", "height", int.class);
      delay = (int) ini.get("Timer", "delay", int.class);
    } catch(Exception e) {
      System.out.println("error:  " + e);
    }
    this.main = owner;
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
    
    tfUnit.setBounds(inputX, 8, 137, 25);
    tfUnit.setToolTipText("Unit...");
    tfUnit.setText("" + unit);
    cp.add(tfUnit);
    tfWidth.setBounds(inputX, 40, 137, 25);
    tfWidth.setToolTipText("Width...");
    tfWidth.setText("" + width);
    cp.add(tfWidth);
    tfHeight.setBounds(inputX, 72, 137, 25);
    tfHeight.setToolTipText("Height...");
    tfHeight.setText("" + height);
    cp.add(tfHeight);
    tfDelay.setBounds(inputX, 104, 137, 25);
    tfDelay.setToolTipText("Thread time...");
    tfDelay.setText("" + delay);
    cp.add(tfDelay);
    JButton bUnit = new JButton();
    bUnit.setBounds(saveX, 8, 57, 25);
    bUnit.setText("Save");
    bUnit.setMargin(new Insets(2, 2, 2, 2));
    bUnit.addActionListener(this::bUnit_ActionPerformed);
    cp.add(bUnit);
    JButton bWidth = new JButton();
    bWidth.setBounds(saveX, 40, 57, 25);
    bWidth.setText("Save");
    bWidth.setMargin(new Insets(2, 2, 2, 2));
    bWidth.addActionListener(this::bWidth_ActionPerformed);
    cp.add(bWidth);
    JButton bHeight = new JButton();
    bHeight.setBounds(saveX, 72, 57, 25);
    bHeight.setText("Save");
    bHeight.setMargin(new Insets(2, 2, 2, 2));
    bHeight.addActionListener(this::bHeight_ActionPerformed);
    cp.add(bHeight);
    JButton bDelay = new JButton();
    bDelay.setBounds(saveX, 104, 57, 25);
    bDelay.setText("Save");
    bDelay.setMargin(new Insets(2, 2, 2, 2));
    bDelay.addActionListener(this::bDelay_ActionPerformed);
    cp.add(bDelay);
    JLabel lUnit = new JLabel();
    lUnit.setBounds(8, 8, 33, 25);
    lUnit.setText("Unit:");
    cp.add(lUnit);
    JLabel lWidth = new JLabel();
    lWidth.setBounds(8, 40, 40, 25);
    lWidth.setText("Width:");
    cp.add(lWidth);
    JLabel lHeight = new JLabel();
    lHeight.setBounds(8, 72, 43, 25);
    lHeight.setText("Height:");
    cp.add(lHeight);
    JLabel lDelay = new JLabel();
    lDelay.setBounds(8, 104, 38, 25);
    lDelay.setText("Delay:");
    cp.add(lDelay);
    JLabel lTheme = new JLabel();
    lTheme.setBounds(8, 134, 43, 25);
    lTheme.setText("Theme:");
    cp.add(lTheme);
    cbTheme.setBounds(61, 134, 124, 25);
    int temp = 0;
    try {
      temp = (int) ini.get("Theme", "current_theme", int.class);
    } catch (Exception e) {
      System.out.println("Error: "+e);
    }
    cbTheme.setSelectedIndex(temp);
    cbTheme.setToolTipText("Theme...");
    cp.add(cbTheme);
    JButton bTheme = new JButton();
    bTheme.setBounds(saveX, 134, 57, 25);
    bTheme.setText("Save");
    bTheme.setMargin(new Insets(2, 2, 2, 2));
    bTheme.addActionListener(this::bTheme_ActionPerformed);
    cp.add(bTheme);
    JButton bCancel = new JButton();
    bCancel.setBounds(24, 164, 97, 25);
    bCancel.setText("cancel");
    bCancel.setMargin(new Insets(2, 2, 2, 2));
    bCancel.addActionListener(this::bCancle_ActionPerformed);
    cp.add(bCancel);
    JButton bSaveall = new JButton();
    bSaveall.setBounds(128, 164, 97, 25);
    bSaveall.setText("Save all");
    bSaveall.setMargin(new Insets(2, 2, 2, 2));
    bSaveall.addActionListener(this::bSaveall_ActionPerformed);
    cp.add(bSaveall);
    // end components
    
    setResizable(false);
    setVisible(true);
  }
  public void saveUnit()
  {
    try {
      ini.put("Grid Settings", "unit", ""+tfUnit.getText());
      ini.store();
    } catch(Exception e) {
      System.out.println("Error: "+e);
    }
  }
  public void saveWidth()
  {
    try {
      ini.put("Grid Settings", "width", ""+ tfWidth.getText());
      ini.store();
    } catch(Exception e) {
      System.out.println("Error: "+e);
    }
  }
  public void saveHeight()
  {
    try {
      ini.put("Grid Settings", "height", ""+tfHeight.getText());
      ini.store();
    } catch(Exception e) {
      System.out.println("Error: "+e);
    }
  }
  public void saveDelay()
  {
    try {
      ini.put("Timer", "delay", ""+tfDelay.getText());
      ini.store();
    } catch(Exception e) {
      System.out.println("Error: "+e);
    }
  }
  public void sTheme(String theme) throws Exception
  {
    UIManager.setLookAndFeel(theme);
    SwingUtilities.updateComponentTreeUI(this);
  }
  public void changeTheme(int theme_number)
  {
    try {
      String theme = switch (theme_number) {
        case 0 -> "com.formdev.flatlaf.FlatDarculaLaf";
        case 2 -> "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        case 3 -> "javax.swing.plaf.metal.MetalLookAndFeel";
        case 4 -> "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        case 5 -> UIManager.getCrossPlatformLookAndFeelClassName();
        case 6 -> "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        case 7 -> "com.apple.laf.AquaLookAndFeel";
        default -> "com.formdev.flatlaf.FlatIntelliJLaf";
      };
      main.theme(theme);
      sTheme(theme);
      ini.put("Theme", "current_theme", ""+theme_number);
      ini.store();
    } catch(Exception e) {
      System.out.println("Error: "+e);
    }
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
    int index = cbTheme.getSelectedIndex();
    changeTheme(index);
  } 
  public void bCancle_ActionPerformed(ActionEvent evt)
  {
    updateDiscord("Main Menu");
    this.dispose();
  } 
  public void bSaveall_ActionPerformed(ActionEvent evt)
  {
    int index = cbTheme.getSelectedIndex();
    if (!tfHeight.getText().equals("")) {
      saveHeight();
    }
    if (!tfWidth.getText().equals("")) {
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
  } 
  private static void updateDiscord(String message)
  {
    // DiscordRichPresence.Builder discordPresence = new DiscordRichPresence.Builder(""+message);
    // discordPresence.setDetails("");
    // DiscordRPC.discordUpdatePresence(discordPresence.build());
  }
} 

