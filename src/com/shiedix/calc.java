package com.shiedix;

import org.mariuszgromada.math.mxparser.Expression;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.script.*;

@Author(
        name = "Joona Brueckner",
        github = "@Zockedidock"
)
public class calc extends JFrame
{
  JTextField calcTF = new JTextField();
  String x = "";
  String result;
  static ScriptEngine engine;

  public calc(String title)
  {
    super(title);
    try {
      UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
    } catch (Exception e){
      System.out.println("" + e);
    }
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 222;
    int frameHeight = 235;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);

    calcTF.setBounds(8, 8, 193, 25);
    cp.add(calcTF);
    JButton button1 = new JButton();
    button1.setBounds(8, 40, 33, 33);
    button1.setText("1");
    button1.setMargin(new Insets(2, 2, 2, 2));
    button1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button1_ActionPerformed(evt);
      }
    });
    cp.add(button1);
    JButton button2 = new JButton();
    button2.setBounds(48, 40, 33, 33);
    button2.setText("2");
    button2.setMargin(new Insets(2, 2, 2, 2));
    button2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button2_ActionPerformed(evt);
      }
    });
    cp.add(button2);
    JButton button3 = new JButton();
    button3.setBounds(88, 40, 33, 33);
    button3.setText("3");
    button3.setMargin(new Insets(2, 2, 2, 2));
    button3.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button3_ActionPerformed(evt);
      }
    });
    cp.add(button3);
    JButton button4 = new JButton();
    button4.setBounds(8, 80, 33, 33);
    button4.setText("4");
    button4.setMargin(new Insets(2, 2, 2, 2));
    button4.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button4_ActionPerformed(evt);
      }
    });
    cp.add(button4);
    JButton button5 = new JButton();
    button5.setBounds(48, 80, 33, 33);
    button5.setText("5");
    button5.setMargin(new Insets(2, 2, 2, 2));
    button5.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button5_ActionPerformed(evt);
      }
    });
    cp.add(button5);
    JButton button6 = new JButton();
    button6.setBounds(88, 80, 33, 33);
    button6.setText("6");
    button6.setMargin(new Insets(2, 2, 2, 2));
    button6.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button6_ActionPerformed(evt);
      }
    });
    cp.add(button6);
    JButton button7 = new JButton();
    button7.setBounds(8, 120, 33, 33);
    button7.setText("7");
    button7.setMargin(new Insets(2, 2, 2, 2));
    button7.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button7_ActionPerformed(evt);
      }
    });
    cp.add(button7);
    JButton button8 = new JButton();
    button8.setBounds(48, 120, 33, 33);
    button8.setText("8");
    button8.setMargin(new Insets(2, 2, 2, 2));
    button8.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button8_ActionPerformed(evt);
      }
    });
    cp.add(button8);
    JButton button9 = new JButton();
    button9.setBounds(88, 120, 33, 33);
    button9.setText("9");
    button9.setMargin(new Insets(2, 2, 2, 2));
    button9.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button9_ActionPerformed(evt);
      }
    });
    cp.add(button9);
    JButton button0 = new JButton();
    button0.setBounds(48, 160, 33, 33);
    button0.setText("0");
    button0.setMargin(new Insets(2, 2, 2, 2));
    button0.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        button0_ActionPerformed(evt);
      }
    });
    cp.add(button0);
    JButton buttonPlus = new JButton();
    buttonPlus.setBounds(128, 40, 33, 33);
    buttonPlus.setText("+");
    buttonPlus.setMargin(new Insets(2, 2, 2, 2));
    buttonPlus.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        buttonPlus_ActionPerformed(evt);
      }
    });
    cp.add(buttonPlus);
    JButton buttonMinus = new JButton();
    buttonMinus.setBounds(128, 80, 33, 33);
    buttonMinus.setText("-");
    buttonMinus.setMargin(new Insets(2, 2, 2, 2));
    buttonMinus.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        buttonMinus_ActionPerformed(evt);
      }
    });
    cp.add(buttonMinus);
    JButton buttonMul = new JButton();
    buttonMul.setBounds(168, 40, 33, 33);
    buttonMul.setText("*");
    buttonMul.setMargin(new Insets(2, 2, 2, 2));
    buttonMul.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        buttonMul_ActionPerformed(evt);
      }
    });
    cp.add(buttonMul);
    JButton buttonDiv = new JButton();
    buttonDiv.setBounds(168, 80, 33, 33);
    buttonDiv.setText("/");
    buttonDiv.setMargin(new Insets(2, 2, 2, 2));
    buttonDiv.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        buttonDiv_ActionPerformed(evt);
      }
    });
    cp.add(buttonDiv);
    JButton buttonBr1 = new JButton();
    buttonBr1.setBounds(128, 120, 33, 33);
    buttonBr1.setText("(");
    buttonBr1.setMargin(new Insets(2, 2, 2, 2));
    buttonBr1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        buttonBr1_ActionPerformed(evt);
      }
    });
    cp.add(buttonBr1);
    JButton buttonBr2 = new JButton();
    buttonBr2.setBounds(168, 120, 33, 33);
    buttonBr2.setText(")");
    buttonBr2.setMargin(new Insets(2, 2, 2, 2));
    buttonBr2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        buttonBr2_ActionPerformed(evt);
      }
    });
    cp.add(buttonBr2);
    JButton buttonEnter = new JButton();
    buttonEnter.setBounds(128, 160, 73, 33);
    buttonEnter.setText("=");
    buttonEnter.setMargin(new Insets(2, 2, 2, 2));
    buttonEnter.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) {
        try {
          buttonEnter_ActionPerformed(evt);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    cp.add(buttonEnter);
    JButton buttonSin = new JButton();
    buttonSin.setBounds(8, 160, 33, 33);
    buttonSin.setText("sin");
    buttonSin.setMargin(new Insets(2, 2, 2, 2));
    buttonSin.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        buttonSin_ActionPerformed(evt);
      }
    });
    cp.add(buttonSin);
    JButton buttonCos = new JButton();
    buttonCos.setBounds(88, 160, 33, 33);
    buttonCos.setText("cos");
    buttonCos.setMargin(new Insets(2, 2, 2, 2));
    buttonCos.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        buttonCos_ActionPerformed(evt);
      }
    });
    cp.add(buttonCos);
    
    setVisible(true);
  }
  
  public static void main(String[] args) {
    new calc("calc");
  }

  public void add(String x)
  {
    calcTF.setText(calcTF.getText() + x);
  }
  
  public void button1_ActionPerformed(ActionEvent evt)
  {
    add("1");
  }

  public void button2_ActionPerformed(ActionEvent evt)
  {
    add("2");
  }

  public void button3_ActionPerformed(ActionEvent evt)
  {
    add("3");
  }

  public void button4_ActionPerformed(ActionEvent evt)
  {
    add("4");
  }

  public void button5_ActionPerformed(ActionEvent evt)
  {
    add("5");
  }

  public void button6_ActionPerformed(ActionEvent evt)
  {
    add("6");
  }

  public void button7_ActionPerformed(ActionEvent evt)
  {
    add("7");
  }

  public void button8_ActionPerformed(ActionEvent evt)
  {
    add("8");
  }

  public void button9_ActionPerformed(ActionEvent evt)
  {
    add("9");
  }

  public void button0_ActionPerformed(ActionEvent evt)
  {
    add("0");
  }

  public void buttonPlus_ActionPerformed(ActionEvent evt)
  {
    add("+");
  }

  public void buttonMinus_ActionPerformed(ActionEvent evt)
  {
    add("-");
  }

  public void buttonMul_ActionPerformed(ActionEvent evt)
  {
    add("*");
  }

  public void buttonDiv_ActionPerformed(ActionEvent evt)
  {
    add("/");
  }

  public void buttonBr1_ActionPerformed(ActionEvent evt)
  {
    add("(");
  }

  public void buttonBr2_ActionPerformed(ActionEvent evt)
  {
    add(")");
  }

  public void buttonSin_ActionPerformed(ActionEvent evt)
  {
    add("sin(");
  }

  public void buttonCos_ActionPerformed(ActionEvent evt)
  {
    add("cos(");
  }

  public void buttonEnter_ActionPerformed(ActionEvent evt) throws Exception
  {
    if(!calcTF.getText().equals("")) {
      Expression test = new Expression(calcTF.getText());
      calcTF.setText("" + test.calculate());
    } else {
      System.out.println("error");
    }
  }
}
