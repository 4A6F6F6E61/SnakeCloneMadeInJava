import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;

/**
 *
 * About Fenster
 *
 * @version 1.0 from 03/29/2021
 * @JFK_Bruechner
 */

public class About extends JFrame {
  // start attributes
  private JLabel lThisisaSnakeClonebyJFKBrueckner = new JLabel();
  private JButton bGitHub = new JButton();
  private JButton bDone = new JButton();
  // end attributes
  
  public About() { 
    // Frame-Init
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
    
    lThisisaSnakeClonebyJFKBrueckner.setBounds(8, 8, 266, 49);
    lThisisaSnakeClonebyJFKBrueckner.setText("This is a Snake Clone by JFK Brueckner");
    lThisisaSnakeClonebyJFKBrueckner.setHorizontalTextPosition(SwingConstants.CENTER);
    lThisisaSnakeClonebyJFKBrueckner.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lThisisaSnakeClonebyJFKBrueckner);
    bGitHub.setBounds(8, 72, 137, 33);
    bGitHub.setText("GitHub");
    bGitHub.setMargin(new Insets(2, 2, 2, 2));
    bGitHub.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bGitHub_ActionPerformed(evt);
      }
    });
    cp.add(bGitHub);
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
  } // end of public About
  
  // start methods
  
  public static void openWebpage(String urlString) {
    try {
        Desktop.getDesktop().browse(new URL(urlString).toURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
  
  public void bGitHub_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    openWebpage("https://github.com/Zockedidock/SnakeCloneMadeInJava/");
  } // end of bGitHub_ActionPerformed

  public void bDone_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    this.dispose();
  } // end of bDone_ActionPerformed

  // end methods
} // end of class About

