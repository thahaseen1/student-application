
/*

////////////////Login.java     //////////////////////  
                                         */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener
{ 
public JTextField t1;
public TextField t2;
public JLabel l1,l2,l3;
public JButton b1,b2;
public JPanel panel;
public Font g;

public Login()
{
g=new Font("",Font.BOLD,18);

panel=(JPanel)getContentPane();
panel.setLayout(null);
l1=new JLabel("LOGIN FORM");
l2=new JLabel("LOGIN");
l3=new JLabel("PASSWORD");
t1=new JTextField(20);
t2=new TextField(20);
t2.setEchoChar('*');
b1=new JButton("OK");
b2=new JButton("EXIT");

panel.add(l1);
l1.setFont(g);
panel.add(l2);
panel.add(l3);

panel.add(t1);
panel.add(t2);
panel.add(b1);
panel.add(b2);


l1.setBounds(100,5,150,40);
l2.setBounds(30,60,100,30);
l3.setBounds(30,110,100,30);

t1.setBounds(140,60,150,30);
t2.setBounds(140,110,150,30);

b1.setBounds(140,180,60,30);
b2.setBounds(220,180,65,30);

t1.addActionListener(this);
t2.addActionListener(this);

b1.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent a)
{
String s1="ascentia";
String s2="ascentia";
String s3="OK";
String s4="EXIT";
String str=a.getActionCommand();

if(s3.equals(str))
{
if(s1.equals(t1.getText()) && s2.equals(t2.getText()))
{
this.dispose();
new Final();
        
this.dispose();
}
else
{
if(s3.equals(str))
{
t1.setText("");
t2.setText("");   
JOptionPane.showMessageDialog(null,"Please verify the correct Login and Password","Warning",JOptionPane.OK_CANCEL_OPTION);
}
}
}

if( str=="EXIT")
{ 
JOptionPane.showMessageDialog(null,"The project is still in progress,do u want to quit now","Warning",JOptionPane.OK_CANCEL_OPTION);
if(s4.equals(str))
    {
   System.exit(0);
    }
    repaint();
}

}
@SuppressWarnings("deprecation")
public static void main(String args[])
{
Login f=new Login();
        f.setVisible(true);
        f.setSize(600,600);
        f.show();
}

}  





 