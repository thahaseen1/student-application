import java.awt.Color; 

import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.Toolkit;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.BorderFactory;



import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")

public class MarksForm extends JFrame{   


      Connection con;
      Statement stmt;

       PreparedStatement preStatement,updatePreStmt;

      JLabel title, idLabel, htmlLabel, javaLabel, dbmsLabel;

      JTextField idField, htmlField, javaField, dbmsField;

      JButton registerButton, exitButton,updateButton,deleteButton,resetButton,

              refresh;

      

      JPanel panel;
      JTable table;

      DefaultTableModel model;


      JScrollPane scrollpane;
      public MarksForm() {
           // TODO Auto-generated constructor stub

           super("MARKS FORM");
            setSize(770, 420);
            setLayout(null);

            // Calling connect method, this will connect us to database
            connect();

            // Defining Labels 

            title = new JLabel("Marks Form");

            title.setBounds(60, 7, 200, 30);

            idLabel = new JLabel("ID");

            idLabel.setBounds(30, 60, 60, 30);

            htmlLabel = new JLabel("html"); 

            htmlLabel.setBounds(30, 95, 60, 30);

            

            javaLabel = new JLabel("java"); 

            javaLabel.setBounds(30, 125, 60, 30); 

            dbmsLabel = new JLabel("dbms"); 

            dbmsLabel.setBounds(30, 155, 60, 30);


            // Defining ID field
            idField = new JTextField(); 

            idField.setBounds(95, 50, 130, 30);
            idField.setEnabled(false);


            // Defining Name field
            htmlField = new JTextField(); 

            htmlField.setBounds(95, 95, 130, 30);         


            

            javaField = new JTextField(); 

            javaField.setBounds(95,125, 130, 30);

            dbmsField = new JTextField(); 

            dbmsField.setBounds(95, 155, 130, 30);

 

            // fixing all Label,TextField,RadioButton

            add(title);

            add(idLabel);

            add(htmlLabel);

            

            add(javaLabel);

            add(dbmsLabel);

            add(idField);

            add(htmlField);

            

            add(javaField);

            add(dbmsField);



            // Defining Exit Button

            exitButton = new JButton("Exit"); 

            exitButton.setBounds(25, 250, 80, 25);            


            // Defining Register Button

            registerButton = new JButton("Register");

            registerButton.setBounds(110, 250, 100, 25);



            // Defining Update Button

            updateButton = new JButton("Update");

            updateButton.setBounds(110, 285, 100, 25);

            updateButton.setEnabled(false);



            // Defining Delete Button

            deleteButton = new JButton("Delete");

            deleteButton.setBounds(25, 285, 80, 25);

            deleteButton.setEnabled(false);



            // Defining Reset Button

            resetButton = new JButton("Reset");

            resetButton.setBounds(60, 320, 100, 25);

            resetButton.setEnabled(false); 



            // fixing all Buttons

            add(exitButton);

            add(registerButton);

            add(updateButton);

            add(deleteButton);

            add(resetButton);    



            // Defining Panel

            panel = new JPanel();

            panel.setLayout(new GridLayout());

            panel.setBounds(250, 20, 480, 330);

            panel.setBorder(BorderFactory.createDashedBorder(Color.blue));

            add(panel);


            // Defining Refresh Button

            refresh = new JButton("Refresh Table");

            refresh.setBounds(350, 350, 270, 15);

            add(refresh);



            //Defining Model for table

            model = new DefaultTableModel();

            //Adding object of DefaultTableModel into JTable

            table = new JTable(model);



            //Fixing Columns move

            table.getTableHeader().setReorderingAllowed(false);



            // Defining Column Names on model

            model.addColumn("S.No");

            model.addColumn("ID");

            model.addColumn("Html");

            

            model.addColumn("Java");
            model.addColumn("Dbms");

 

            // Enable Scrolling on table

           scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,

                                           JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            panel.add(scrollpane);



            //Displaying Frame in Center of the Screen

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            this.setLocation(dim.width/2-this.getSize().width/2,

                             dim.height/2-this.getSize().height/2);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setResizable(false);

            setVisible(true);

      }



      // Connection with Database

      public void connect(){

            try{

            	Class.forName("com.mysql.jdbc.Driver");
            	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","thahaseen","thahaseen");
                  stmt = con.createStatement();

                  preStatement = con.prepareStatement("insert into marksform(html,java,dbms) values(?,?,?)");

            }catch(Exception e){

                  System.out.print(e.getMessage());

            }

      }

}