import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

public class CustomerOption implements ActionListener{
	
	JFrame f;
	JMenuBar m;
    JTextField textField;
    JPasswordField passwordField;
    JButton btnNewButton;
    JLabel label;
    JTable j;
    Box b;
    
    CustomerOption(){
    	
    	f = new JFrame("??");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,500);
		f.getContentPane().setBackground(Color.LIGHT_GRAY);
		f.setLayout(new BorderLayout(30,30));
		
		b = Box.createVerticalBox();
		JButton create = new JButton("Create Account"){
			{
				setSize(200,40);
	            setMaximumSize(getSize());
			}
		};
		create.setBackground(Color.white);
		create.setForeground(Color.black);
		create.setAlignmentX(Box.CENTER_ALIGNMENT);
		JLabel or = new JLabel("OR",JLabel.CENTER);
		or.setForeground(Color.black);
		JButton login = new JButton("Login"){
			{
				setSize(200,40);
	            setMaximumSize(getSize());
			}
		};
		login.setBackground(Color.white);
		login.setForeground(Color.black);
		login.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		create.setToolTipText("New Account");
		
		b.add(Box.createVerticalStrut(100));
		b.add(login);
		b.add(Box.createVerticalStrut(10));
		b.add(or);
		b.add(Box.createVerticalStrut(10));
		b.add(create);
		b.add(Box.createVerticalStrut(10));
		
		create.addActionListener(this);
		login.addActionListener(this);
		f.add(b,BorderLayout.CENTER);
		f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
    	String s = e.getActionCommand();
    	if(s.equals("Create Account")) {
    		f.dispose();
    		DefaultLogin.fr.hide();
    		new CreateAccount();
    		
    	}
    	else {
    		f.dispose();
    		DefaultLogin.fr.hide();
    		new CustomerLogin();
    	}
    }
}
