import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;

/**
 *
 * Description
 *
 * @version 1.0 from 04/02/2021
 * @author 
 */

public class GameView extends JFrame {
  // start attributes                          
  private GameCanvas gameCanvas1 = new GameCanvas();
  private JLabel lScore = new JLabel();
  private JButton bMainMenu = new JButton();
  private JButton bNewApple = new JButton();
  
  // end attributes
  public static MainMenu menu;
  
  public GameView() { 
    // Frame-Init
    super();
    setFocusable(true);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 511; 
    int frameHeight = 566;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("GameView");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    this.addKeyListener(gameCanvas1);
    // start components
    
    gameCanvas1.setBounds(8, 40, 480, 480);
    cp.add(gameCanvas1);
    lScore.setBounds(8, 8, 169, 25);
    lScore.setText("Score: ");
    cp.add(lScore);
    bMainMenu.setBounds(376, 8, 105, 25);
    bMainMenu.setText("Main Menu");
    bMainMenu.setMargin(new Insets(2, 2, 2, 2));
    bMainMenu.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bMainMenu_ActionPerformed(evt);
      }
    });
    cp.add(bMainMenu);
    bNewApple.setBounds(232, 8, 89, 25);
    bNewApple.setText("New Apple");
    bNewApple.setMargin(new Insets(2, 2, 2, 2));
    bNewApple.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bNewApple_ActionPerformed(evt);
      }
    });
    cp.add(bNewApple);
    // end components
    
    setVisible(true);
  } // end of public GameView
  
  // start methods
  public void bMainMenu_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    menu = new MainMenu("Snake");
    this.dispose();
  } // end of bMainMenu_ActionPerformed

  public void bNewApple_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    gameCanvas1.newApple();
  } // end of bNewApple_ActionPerformed  .

  // end methods
} // end of class GameView

