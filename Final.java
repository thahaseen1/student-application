import javax.swing.*;

import java.awt.event.*;

public class Final implements ActionListener
{
	JFrame f;    
	JMenuBar mb;    
	JMenu marks,form;    
	JMenuItem regForm,marksForm; 
	JLabel l;    
	Final(){    
	f=new JFrame("Home-WELCOME");    
	    
	
	    
	mb=new JMenuBar();    
	form=new JMenu("Registration form");    
	marks=new JMenu("Marks form");    
	 marksForm= new JMenuItem("student marks");
	 regForm=new JMenuItem("student registration");
	form.add(regForm);marks.add(marksForm); 
	mb.add(form);mb.add(marks);   
	l=new JLabel("STUDENT APPLICATION");    
	l.setBounds(100,50,200,100);    
	f.add(mb);f.add(l);    
	f.setJMenuBar(mb);  
	f.setLayout(null);    
	f.setSize(400,400);    
	f.setVisible(true);
	
	regForm.addActionListener(this);
	marksForm.addActionListener(this);
}
	

public void actionPerformed(ActionEvent e)
{

if(e.getSource()==regForm)
	new DynamicRegForm();
if(e.getSource()==marksForm)
	new DynamicRegForm1();
}

public static void main(String ag[])
{
new Final();
}
}
