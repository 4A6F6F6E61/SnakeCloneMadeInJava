package com.shiedix;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class About extends JDialog
{
  public About(JFrame owner, boolean modal)
  {
    // Frame-Init dd
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        updateDiscord("Main Menu");
      }
    });
    updateDiscord("About");
    int frameWidth = 295; 
    int frameHeight = 149;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("About");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // start components

    // start attributes
    JLabel lThisisaSnakeClonebyJFKBrueckner = new JLabel();
    lThisisaSnakeClonebyJFKBrueckner.setBounds(8, 8, 266, 49);
    lThisisaSnakeClonebyJFKBrueckner.setText("This is a Snake Clone by JFK Brueckner");
    lThisisaSnakeClonebyJFKBrueckner.setHorizontalTextPosition(SwingConstants.CENTER);
    lThisisaSnakeClonebyJFKBrueckner.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lThisisaSnakeClonebyJFKBrueckner);
    JButton bGitHub = new JButton();
    bGitHub.setBounds(8, 72, 137, 33);
    bGitHub.setText("GitHub");
    bGitHub.setMargin(new Insets(2, 2, 2, 2));
    bGitHub.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bGitHub_ActionPerformed(evt);
      }
    });
    cp.add(bGitHub);
    JButton bDone = new JButton();
    bDone.setBounds(152, 72, 121, 33);
    bDone.setText("Done");
    bDone.setMargin(new Insets(2, 2, 2, 2));
    bDone.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDone_ActionPerformed(evt);
      }
    });
    cp.add(bDone);
    // end components
    
    setVisible(true);
  }
  public static void openWebpage(String urlString)
  {
    try {
        Desktop.getDesktop().browse(new URL(urlString).toURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
  public void bGitHub_ActionPerformed(ActionEvent evt)
  {
    openWebpage("https://github.com/Zockedidock/SnakeCloneMadeInJava/");
  }
  public void bDone_ActionPerformed(ActionEvent evt)
  {
    updateDiscord("Main Menu");
    this.dispose();
  }
  private static void updateDiscord(String message)
  {
    DiscordRichPresence.Builder discordPresence = new DiscordRichPresence.Builder(""+ message);
    discordPresence.setDetails("");
    DiscordRPC.discordUpdatePresence(discordPresence.build());
  }
}

