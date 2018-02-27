
package uppgift03_15brickor;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;

public abstract class Uppgift03_15Brickor extends JFrame implements ActionListener {
     
     JPanel js = new JPanel();
     JPanel jb = new JPanel();
     int buttonClicked;
 private JButton restart = new JButton("Shuffle");
 private JButton b1 = new JButton("1");private JButton b2 = new JButton("2");
 private JButton b3 = new JButton("3");private JButton b4 = new JButton("4");
 private JButton b5 = new JButton("5");private JButton b6 = new JButton("6");
 private JButton b7 = new JButton("7");private JButton b8 = new JButton("8");
 private JButton b9 = new JButton("9");private JButton b10 = new JButton("10");
 private JButton b11 = new JButton("11");private JButton b12 = new JButton("12");
 private JButton b13 = new JButton("13");private JButton b14 = new JButton("14");
 private JButton b15 = new JButton("15");private JButton b16 = new JButton("");
 // if you chose to have b16 invisible: JButton b16 = new JButton();
 // b16.setBorderPainted(false); b16.setContentAreaFilled(false);
   Random generator = new Random();
   int n; 
    Uppgift03_15Brickor(){
       
    //setLocationRelativeTo(null); // frame now appears in center of window
    setLocation(800,50);
    setTitle("15 Brickor");
    setSize(400,400);
    setResizable(true);
    js.setLayout(new GridLayout(1,4) ); 
    js.setBackground(Color.LIGHT_GRAY);
    js.add(restart);
    add(js, BorderLayout.NORTH); 
    setVisible(true);
//    js.repaint();
//    js.revalidate();
 
    jb.setLayout(new GridLayout(4,4));
    jb.setBackground(Color.DARK_GRAY);
    jb.add(b1);jb.add(b2);jb.add(b3);jb.add(b4);jb.add(b5);jb.add(b6);jb.add(b7);
    jb.add(b8);jb.add(b9);jb.add(b10);jb.add(b11);jb.add(b12);jb.add(b13);jb.add(b14);
    jb.add(b15);
    add(jb, BorderLayout.CENTER);

    restart.addActionListener(this);    
    b1.addActionListener(this);b2.addActionListener(this);b3.addActionListener(this);
    b4.addActionListener(this);b5.addActionListener(this);b6.addActionListener(this);
    b7.addActionListener(this);b8.addActionListener(this);b9.addActionListener(this);
    b10.addActionListener(this);b11.addActionListener(this);b12.addActionListener(this);
    b13.addActionListener(this);b14.addActionListener(this);b15.addActionListener(this);
   
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    @Override
      public void actionPerformed(ActionEvent e) {
//                 JButton b = new JButton();
//                for(int i=0; i<16; i++)
                     
    if(e.getSource()== b1||e.getSource()== b2||e.getSource()== b3||
       e.getSource()== b4||e.getSource()== b5||e.getSource()== b6||
       e.getSource()== b7||e.getSource()== b8||e.getSource()== b9||
       e.getSource()== b10||e.getSource()== b11||e.getSource()== b12||
       e.getSource()== b13||e.getSource()== b14||e.getSource()== b15)
    // skriv metod som kollar om button ligger brevid tomrum
        buttonClicked++;
    else if(e.getSource()== restart) 
       for (int i =0; i<100; i++){
       n = generator.nextInt(15)+1;
       // complete this randomised array of JButtons and show the JFrame
          }
    
    }
    public static void main(String[] args) {
      Uppgift03_15Brickor ub = new Uppgift03_15Brickor(){};
    }
        
//    label.setOpaque(true);
//    label.setBackground(Color.BLACK); label.setForeground(Color.GREEN);
//    label.setFont(new Font("SansSerif", Font.BOLD, 24));
}
