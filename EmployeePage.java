import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class EmployeePage implements ActionListener{
	String username;
	String password;
	Connection c;
	Statement s;
	
	JFrame f;
	JMenuBar m;
    JTextField textField;
    JPasswordField passwordField;
    JButton btnNewButton;
    JLabel label;
    JTable j;
    Box b;
    EmployeePage(String s1,String s2){
    	username = s1;
    	password = s2;
    	
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
		f.setVisible(true);
		
		setConnection();
		Display();
		
    }
    public void setConnection() {
    	try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection
					("jdbc:postgresql://localhost:5432/postgres","postgres","##########");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	
    }
    public void Display() {
		try {
			
			s = c.createStatement();
			
			PreparedStatement st = (PreparedStatement)c
	                .prepareStatement("Select e_id from Login_emp where username =?;");  
			
	        st.setString(1,username);
	        
	        ResultSet rs = st.executeQuery() ;
			rs.next();
			
			String E_id = rs.getString(1);
			
			PreparedStatement st1 = (PreparedStatement)c
                    .prepareStatement
                    ("select  E_id, first_name || ' ' || last_name as EName,E_city, E_state,Salary,DOB,H_id from employee where e_id =?;");
			
			st1.setString(1,E_id); 
			ResultSet r = st1.executeQuery() ;
			
			r.next();
			
			String eid = r.getString(1);
			String ename = r.getString(2);
			String ecity = r.getString(3);
			String estate = r.getString(4);
			double sal = r.getDouble(5);
			String dob = r.getString(6);
			String hid = r.getString(7);
			
			String salary = Double.toString(sal);
			
			Font f7 = new Font(Font.DIALOG_INPUT,Font.BOLD,20);
			
			Box b1 = Box.createVerticalBox();
			
			JLabel Title = new JLabel("EMPLOYEE DETAILS");
			Title.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD + Font.ITALIC,26));
			Title.setForeground(Color.black);
			Title.setAlignmentX( Box.CENTER_ALIGNMENT);
			
			String Eid = "EMPLOYEE ID : " + eid;
			JLabel Empid = new JLabel(Eid);
			Empid.setFont(f7);
			Empid.setForeground(Color.DARK_GRAY);
			
			String ENAME = "EMPLOYEE NAME : " + ename;
			JLabel empname  = new JLabel(ENAME);
			empname.setFont(f7);
			empname.setForeground(Color.DARK_GRAY);
			
			String ECITY = "EMPLOYEE CITY : " + ecity;
			JLabel empCity  = new JLabel(ECITY);
			empCity.setFont(f7);
			empCity.setForeground(Color.DARK_GRAY);
			
			String ESTATE = "EMPLOYEE STATE : " + estate;
			JLabel empState  = new JLabel(ESTATE);
			empState.setFont(f7);
			empState.setForeground(Color.DARK_GRAY);
			
			String Salary = "EMPLOYEE SALARY : " + salary;
			JLabel empSAL = new JLabel(Salary);
			empSAL.setFont(f7);
			empSAL.setForeground(Color.DARK_GRAY);
			
			String DOB = "EMPLOYEE DOB : " + dob;
			JLabel empdob = new JLabel(DOB);
			empdob.setFont(f7);
			empdob.setForeground(Color.DARK_GRAY);
			
			String HID = "CURRENTLY WORKING IN : " + hid;
			JLabel empHid = new JLabel(HID);
			empHid.setFont(f7);
			empHid.setForeground(Color.DARK_GRAY);
			
			
			
			b1.add(Title);
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(Empid); 
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(empname); 
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(empCity);
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(empState);
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(empSAL);
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(empdob);
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(empHid);
			
			f.add(b1);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
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
	        
