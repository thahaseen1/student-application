import javax.swing.*;
import javax.swing.table.DefaultTableModel; 

import java.awt.event.*;

import java.sql.ResultSet;



@SuppressWarnings("serial")

public class DynamicRegForm1 extends MarksForm{

      

      ResultSet rst,rstLast;

      Object[][] data;

      int serialNo; 

      String SHOW = "Show";

      MarksForm formGUIObject1;



      // Defining Constructor

      DynamicRegForm1(){

            htmlField.addKeyListener(new KeyAdapter() {

                  public void keyTyped(KeyEvent e) {

                  if(htmlField.getText().length()>=15)

                        e.consume();

                  }

            });

           

            javaField.addKeyListener(new KeyAdapter() {

                  public void keyTyped(KeyEvent e) {

                  if(javaField.getText().length()>50)

                        e.consume();

                  }

            });

           dbmsField.addKeyListener(new KeyAdapter() {

                  public void keyTyped(KeyEvent e) {

                        char c = e.getKeyChar();

                        if (!((c >= '0') && (c <= '9') ||

                             (c == KeyEvent.VK_BACK_SPACE) ||

                             (c == KeyEvent.VK_DELETE))) {

                              // getToolkit().beep();

                              e.consume();

                        }

                        if(dbmsField.getText().length()>9) 

                              e.consume();

                  }

            });

            exitButton.addActionListener(new ActionListener(){

                  public void actionPerformed(ActionEvent arg0) {

                        try{

                              con.close();

                              System.exit(0);

                        }catch(Exception ex){

                               System.out.println(ex.getMessage());

                        }

                  }

            });

            registerButton.addActionListener(new AbstractAction(SHOW){

                   public void actionPerformed(ActionEvent ae){

                         try{

                            if (ae.getSource() == registerButton) {

                                if (htmlField.getText().equals(""))

                                   JOptionPane.showMessageDialog(idField, 

                                               "Please provide html_Field");

                                else if(javaField.getText().equals(""))

                                   JOptionPane.showMessageDialog(idField, 

                                               "Please provide java_Field");

                                else if(dbmsField.getText().equals(""))
                                   JOptionPane.showMessageDialog(idField, "Please provide dbms_Field");

                                

                                else {

                                 //Fetching column values from Database

                                 preStatement.setString(1,htmlField.getText());

                                 
                                 preStatement.setString(2,javaField.getText());

                                 preStatement.setString(3,dbmsField.getText());

                               //Executing MySQL Update Query

                                 int i = preStatement.executeUpdate();

                                 if(i==1){

                                  JOptionPane.showMessageDialog(panel, 

                                               "Successfully Registered");

                                 }

 

                                 // showing last row 

                                rstLast = stmt.executeQuery("select *from marksform");

                                rstLast.last();

String string = serialNo++ +","+String.valueOf(rstLast.getLong(1))+","+rstLast.getString(2)+","+rstLast.getString(3)+","+rstLast.getString(4);

                                          

                                          

                                             

                                          

                                          

                                 Object[] row = null;

                                 row = string.split(",");

                                 model.addRow(row);

                                 panel.revalidate();

 

                                 // fields are blank

                                 blankFields();

                               }

                              }

                       }catch(Exception ex){

                              System.out.println(ex.getMessage()); 

                       }

                   }

            });

 

            updateButton.addActionListener(new AbstractAction(SHOW){

              public void actionPerformed(ActionEvent e){

                  if (htmlField.getText().equals(""))

                        JOptionPane.showMessageDialog(idField,

                                    "Please provide html_Field");

                  else if(javaField.getText().equals(""))

                        JOptionPane.showMessageDialog(idField,

                                    "Please provide java_Field");

                  else if(dbmsField.getText().equals(""))

                        JOptionPane.showMessageDialog(idField,

                                    "Please provide dbms_Field");              

                                    

                  else {

                              int r = table.getSelectedRow();

                              try{

                              if(r>=0){

                                 
                                    String id = (String)table.getModel().getValueAt(r,1);

updatePreStmt = con.prepareStatement("update marksform set html=?,java=?,dbms=? where id="+id);

                                  updatePreStmt.setString(1,htmlField.getText());

                                 

                                  updatePreStmt.setString(2,javaField.getText());

                                  updatePreStmt.setString(3,dbmsField.getText());

                                  int i = updatePreStmt.executeUpdate();

                                  if(i==1){

                                     table.setValueAt(htmlField.getText(),r,2);

                                     
                                     table.setValueAt(javaField.getText(),r,3);

                                     table.setValueAt(dbmsField.getText(), r, 4);

                                  }

                                  else JOptionPane.showMessageDialog(panel,

                                     "ID does't Exists in Database");

                              }

                              }catch(Exception ex){

                                      System.out.println("Update section: "+ 

                                                          ex.getMessage()); 

                              }

                        }

              }

          });

 

            //Registering Anonymous Listener Class

            deleteButton.addActionListener(new AbstractAction(SHOW){ 

              public void actionPerformed(ActionEvent e){

                  try{ 

                  //Getting Selected Row No

                  int r = table.getSelectedRow(); 

                  if(r>=0){ 

                        if (JOptionPane.showConfirmDialog(panel,

                            "Are you sure want to Delete this 'RECORD' ?","WARNING",

                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){

                              String id = (String)table.getModel().getValueAt(r,1);

 

                              // Executing MySQL Update Command

                              int i = stmt.executeUpdate("delete from marksform where id="+id);

                              if(i==1){

                                    model.removeRow(r);

 

                                    // fields are blank

                                    blankFields();

                                    registerButton.setEnabled(true);

                                    deleteButton.setEnabled(false);

                                    updateButton.setEnabled(false);

                              }

                        }

                  }

                  }catch(Exception ex){

                         System.out.println(ex.getMessage());

                  }

              }

          });

 

           //Registering Anonymous Listener Class

            resetButton.addActionListener(new ActionListener(){

                  public void actionPerformed(ActionEvent arg0) {

                        // calling method resetFields()

                        resetFields();

                        registerButton.setEnabled(true);

                        updateButton.setEnabled(false);

                        deleteButton.setEnabled(false);

                        resetButton.setEnabled(false);

                  }

            });

           
            // Registering Anonymous Listener Class

            refresh.addActionListener(new ActionListener() {

                  public void actionPerformed(ActionEvent arg0) {

                        //calling  refresh() method

                        refreshTable();

                  }

            });



            //Registering Anonymous Listener Class

            table.addMouseListener(new MouseListener(){

                  public void mouseClicked(MouseEvent arg0){ 

                        //Getting Selected Row No

                  int r = table.getSelectedRow();

                  if(r>=0){ 

                        deleteButton.setEnabled(true);

                        updateButton.setEnabled(true);

                        resetButton.setEnabled(true);

                        registerButton.setEnabled(false); 


                        //Fetching records from Table on Fields

                        idField.setText(""+table.getModel().getValueAt(r,1));

                        htmlField.setText(""+table.getModel().getValueAt(r,2));

                      
                        javaField.setText(""+table.getModel().getValueAt(r,3));

                        dbmsField.setText(""+table.getModel().getValueAt(r,4));

                  }

                  }


               //@Override

                  public void mouseReleased(MouseEvent arg0) {}

               //@Override

                   public void mousePressed(MouseEvent arg0) {}

                // @Override 

                  public void mouseExited(MouseEvent arg0) {}

               //@Override 

                  public void mouseEntered(MouseEvent arg0) {}

            });



            // Displaying rows into the Frame table

            addRows();

      }



      // addRows method

      private void addRows(){

            try{

            Object[] row = null;

            //Generating Serial No

            serialNo=1;

            rst = stmt.executeQuery("select *from marksform");

            while(rst.next()){ 

String string = serialNo++ +","+String.valueOf(rst.getLong(1))+","+rst.getString(2)+","+rst.getString(3)+","+rst.getString(4);

                  row = string.split(",");



                  // Adding records in table model 

                  model.addRow(row);

            }

            panel.revalidate();

            }catch(Exception ex){ System.out.println(ex.getMessage()); }

      }



      private void resetFields(){ 

 

            //calling method blankfields() 

            blankFields();

      }

 

      // refresh method

      private void refreshTable(){

 

            // removing all the rows of the table

            DefaultTableModel dm = (DefaultTableModel)table.getModel();

            dm.getDataVector().removeAllElements();

            System.out.println("Refresh Table");



            //calling method addRows

            addRows();

      }



      private void blankFields(){

            // fields will be blank

            idField.setText("");

            htmlField.setText("");

           

            javaField.setText("");

            dbmsField.setText("");

      }



      // main() method

      public static void main(String[] args) {

            new DynamicRegForm1();

      }     
}