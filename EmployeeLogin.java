import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

public class EmployeeLogin implements ActionListener{
	
	JFrame f;
	JMenuBar m;
    JTextField textField;
    JPasswordField passwordField;
    JButton btnNewButton;
    JLabel label;
    Box b;
     
    EmployeeLogin(){
    	f = new JFrame("Employee Login");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.getContentPane().setBackground(Color.LIGHT_GRAY);
		f.setLayout(new BorderLayout(30,30));
		
		Font ff = new Font(Font.MONOSPACED,Font.BOLD,18);
		//menubar
		m = new JMenuBar();
		m.setBackground(Color.WHITE);//Giving color--Comes under swing 
		
		//shifting to right
		m.add(Box.createGlue());
		JButton m2 = new JButton("LOGIN");
		m2.setBackground(Color.black);
		m2.setFont(ff);
		m2.setForeground(Color.white);//Changing font color
		m2.setBounds(10,10,15,15);
		
		m.add(m2);
		m.add(new JMenu(" ")); //Giving space   
		
		m2.addActionListener(this);
		
		ImageIcon i = new ImageIcon("home.png"); // load the image to a imageIcon
		Image image = i.getImage(); // transform it to image
		Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		i = new ImageIcon(newimg); 
		
		JButton m1 = new JButton();
		m1.setIcon(i);
		m1.setBackground(Color.white);
		m1.setToolTipText("HOME");
		
		m1.addActionListener(this);
		m.add(m1);
		
		f.add(m,BorderLayout.NORTH);//for having the menubar on top 
		 
	    JLabel title = new JLabel("EMPLOYEE LOGIN PAGE",JLabel.CENTER);
        title.setForeground(Color.BLACK);
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 26));
        title.setBounds(500, 50, 273, 93);
        
        f.add(title);
        
        
        Font f1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 20);
        
        textField = new JTextField();
        textField.setFont(f1);
        textField.setBounds(555, 170, 281, 40);
        f.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(f1);
        passwordField.setBounds(555, 286, 281, 40);
        f.add(passwordField);
        
        Font f2 = new Font(Font.MONOSPACED, Font.PLAIN, 20);
        JLabel lblUsername = new JLabel("Username :");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(f2);
        lblUsername.setBounds(400, 170, 193, 52);
        f.add(lblUsername);

        JLabel lblPassword = new JLabel("Password :");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(f2);
        lblPassword.setBounds(400, 286, 193, 52);
        f.add(lblPassword);
        
        JCheckBox ch=new JCheckBox();
        ch.setBounds(900,286,40,50);
        ch.setBackground(Color.LIGHT_GRAY);
        f.add(ch);
         ch.addItemListener(new ItemListener() {
        	 public void itemStateChanged(ItemEvent e) {
        		if(e.getStateChange()==ItemEvent.SELECTED)
        		{
        			passwordField.setEchoChar((char)0);
        		}
        		else
        		{
        		passwordField.setEchoChar('\u2022');
        		}
        	
        	 }
         });
         
         Font f3 = new Font(Font.MONOSPACED, Font.BOLD, 20);
         JLabel lblshow = new JLabel("Show Password");
         lblshow.setBackground(Color.BLACK);
         lblshow.setForeground(Color.BLACK);
         lblshow.setFont(f3);
         lblshow.setBounds(950, 286, 193, 52);
         f.add(lblshow);
         
        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
        btnNewButton.setBackground(Color.white);
        btnNewButton.setForeground(Color.black);
        btnNewButton.setBounds(900, 392, 162, 40);

        btnNewButton.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                 String userName = textField.getText();
                 String password = passwordField.getText();
                 try {
                     Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                             "postgres", "##########");

                     PreparedStatement st = (PreparedStatement) connection
                         .prepareStatement("Select username,password from Login_Emp where username =? and password =?");
                     
                     

                     st.setString(1, userName);
                     st.setString(2, password);
                     
                     ResultSet rs = st.executeQuery();
                     
                     if (rs.next()) {
                    	 
                         f.hide();
                         new EmployeePage(userName,password);
                         JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                     } else {
                    	 
                    	 new EmployeeLogin();
                    	 f.dispose();
                         JOptionPane.showMessageDialog(btnNewButton, "Wrong Username 'OR' Password");
                     }
                     connection.close();
                     
                 } catch (SQLException sqlException) {
                     sqlException.printStackTrace();
                 }
             
             }
         });
        
         f.add(btnNewButton);

         label = new JLabel("");
         label.setBounds(0, 0, 1008, 562);
         
         f.add(label);
         f.setVisible(true);
        }
    
    
    	public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			if(s.equals("LOGIN")) {
				DefaultLogin.fr.show();
				f.hide();
			}
			else {
				home.f.show();
				f.hide();
			}
    	}
		
}
        
	

