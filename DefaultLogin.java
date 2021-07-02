import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class DefaultLogin implements ActionListener{
	
	static JFrame fr;
	JMenuBar m;
	Icon icon;
	
	DefaultLogin(){
		
		fr = new JFrame("Login??");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		fr.getContentPane().setBackground(Color.LIGHT_GRAY);
		fr.setLayout(new BorderLayout(30,30));
		
		Font f1 = new Font(Font.DIALOG_INPUT, Font.BOLD, 18);
		Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 15);
		
		//menubar
		m = new JMenuBar();
		m.setBackground(Color.WHITE);//Giving color--Comes under swing 
	
		//shifting to right
		m.add(Box.createGlue());
		
		ImageIcon i = new ImageIcon("home.png"); // load the image to a imageIcon
		Image image = i.getImage(); // transform it to image
		Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		i = new ImageIcon(newimg); 
		
		m.add(new JMenu(" ")); //Giving space    
		
		JButton m3 = new JButton();
		m3.setIcon(i);
		m3.setBackground(Color.white);
		m3.setToolTipText("HOME");
		
		m3.addActionListener(this);
		m.add(m3);
		
		fr.add(m,BorderLayout.NORTH);//for having the toolbar on top 
		
		//3 options
		Box b = Box.createVerticalBox();
		Font f3 = new Font(Font.DIALOG_INPUT, Font.BOLD, 16);
		Font f4 = new Font(Font.DIALOG_INPUT, Font.ITALIC + Font.BOLD, 24);
		
		//Heading 
		JLabel login = new JLabel("  Login privilege  ");
		login.setFont(f4);
		login.setForeground(Color.white);
		login.setAlignmentX( Box.CENTER_ALIGNMENT);
		
		b.add(login);
		
		//customer
		JButton c = new JButton("Customer?") {
			{
				setSize(200,40);
	            setMaximumSize(getSize());
			}
		};
		c.setFont(f3);
		c.setForeground(Color.black);
		c.setBackground(Color.white);
		c.setAlignmentX(Box.CENTER_ALIGNMENT);
		c.setAlignmentY(Box.CENTER_ALIGNMENT);
		
		
		//Employee
		JButton e = new JButton("Employee?") {
			{
				setSize(200,40);
	            setMaximumSize(getSize());
			}
		};
		e.setFont(f3);
		e.setForeground(Color.black);
		e.setBackground(Color.white);
		e.setAlignmentX(Box.CENTER_ALIGNMENT);
		e.setAlignmentY(Box.CENTER_ALIGNMENT);
		
		
		//Owner
		JButton o = new JButton("Owner?") {
			{
				setSize(200,40);
	            setMaximumSize(getSize());
			}
		};
		o.setFont(f3);
		o.setForeground(Color.black);
		o.setBackground(Color.white);
		o.setAlignmentX(Box.CENTER_ALIGNMENT);
		o.setAlignmentY(Box.CENTER_ALIGNMENT);
	
		b.add(Box.createRigidArea(new Dimension(50,50)));
		b.add(c);
		b.add(Box.createRigidArea(new Dimension(50,50)));
		b.add(e);
		b.add(Box.createRigidArea(new Dimension(50,50)));
		b.add(o);
		
		o.addActionListener(this);
		e.addActionListener(this);
		c.addActionListener(this);
		fr.add(b);
		fr.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.equals("Owner?")) {
			new OwnerLogin();
			fr.hide();
		}
		else if(s.equals("Employee?")) {
			new EmployeeLogin();
			fr.hide();
		}
		else if(s.equals("Customer?")) {
			new CustomerOption();
			
		}
		else {
			fr.hide();
		    home.f.show();
		}
	}
}
