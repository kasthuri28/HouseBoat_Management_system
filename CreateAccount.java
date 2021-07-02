import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.*;

public class CreateAccount implements ActionListener{
	
	static JFrame f;
	String name = "\\b[A-Z][a-z]+([A-Z][a-z]*)*\\b";
	String laname = "\\b[A-Z][a-z]+";
	JMenuBar m;
    JTextField fname,lname,age,city,members,username,pno;
    JComboBox state; 
    JPasswordField password,cpassword;
    JButton ca;
    JLabel Fname,Lname,Age,City,State,Members,PNo,Username,Password,CPassword;
    Box b;
    ResultSet rs ;
    Connection c;
    
    
    CreateAccount(){
    	
    	f = new JFrame("Create Account");
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	f.setBackground(Color.lightGray);
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
		
		JLabel title = new JLabel("CREATE ACCOUNT (CUSTOMER)");
        title.setForeground(Color.BLACK);
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 26));
        //title.setBounds(455, 50, 273, 93);
        
        f.add(title);
        
        Font f1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 20);
        
        //Creating Fields
        fname = new JTextField();
        fname.setFont(f1);
        fname.setBounds(500, 100, 281, 40);
        f.add(fname);
        fname.setColumns(10);
        
        lname = new JTextField();
        lname.setFont(f1);
        lname.setBounds(500, 150, 281, 40);
        f.add(lname);
        lname.setColumns(10);
        
        age = new JTextField();
        age.setFont(f1);
        age.setBounds(500, 200, 100, 40);
        f.add(age);
        age.setColumns(10);
        
        city = new JTextField();
        city.setFont(f1);
        city.setBounds(500, 250, 281, 40);
        f.add(city);
        city.setColumns(10);
        
        String s1[] = { 
        		"Andaman and Nicobar Islands",
		    	"Andhra Pradesh",
		    	"Arunachal Pradesh",
		    	"Assam",
		    	"Bihar",
		    	"Chandigarh",
		    	"Chhattisgarh",
		    	"Dadra and Nagar Haveli",
		    	"Daman and Diu",
		    	"Delhi",
		    	"Goa",
		    	"Gujarat",
		    	"Haryana",
		    	"Himachal Pradesh",
		    	"Jammu and Kashmir",
		    	"Jharkhand",
		    	"Karnataka",
		    	"Kerala",
		    	"Lakshadweep",
		    	"Madhya Pradesh",
		    	"Maharashtra",
		    	"Manipur",
		    	"Meghalaya",
		    	"Mizoram",
		    	"Nagaland",
		    	"Orissa",
		    	"Pondicherry",
		    	"Punjab",
		    	"Rajasthan",
		    	"Sikkim",
		    	"Tamil Nadu",
		    	"Telangana",
		    	"Tripura",
		    	"Uttaranchal",
		    	"Uttar Pradesh",
		    	"West Bengal"}; 
        
        
    	state = new JComboBox(s1);
    	state.setBounds(500, 300, 281, 40);
    	state.setBackground(Color.white);
    	state.setFont(f1);
    	//JScrollPane sp = new JScrollPane();
    	//state.addItem();
    	f.add(state);
    	
    	members = new JTextField();
    	members.setFont(f1);
    	members.setBounds(500, 350, 100, 40);
        f.add(members);
        members.setColumns(10);
        
        pno = new JTextField();
        pno.setFont(f1);
        pno.setBounds(500, 400, 281, 40);
        f.add(pno);
        		
        username = new JTextField();
        username.setFont(f1);
        username.setBounds(500, 450, 281, 40);
        f.add(username);
        
        password = new JPasswordField();
        password.setFont(f1);
        password.setBounds(500, 500, 281, 40);
        f.add(password);
        
        cpassword = new JPasswordField();
        cpassword.setFont(f1);
        cpassword.setBounds(500, 550, 281, 40);
        f.add(cpassword);
        
        Font f2 = new Font(Font.MONOSPACED, Font.PLAIN, 20);
        
        //Creating labels
        
        Fname = new JLabel("Firstname :");
        Fname.setForeground(Color.BLACK);
        Fname.setFont(f2);
        Fname.setBounds(100, 100, 300, 52);
        f.add(Fname);
        
        Lname = new JLabel("Lastname :");
        Lname.setForeground(Color.BLACK);
        Lname.setFont(f2);
        Lname.setBounds(100, 150, 300, 52);
        f.add(Lname);
        
        Age = new JLabel("Age :");
        Age.setForeground(Color.BLACK);
        Age.setFont(f2);
        Age.setBounds(100, 200, 300, 52);
        f.add(Age);
        
        City = new JLabel("City :");
        City.setForeground(Color.BLACK);
        City.setFont(f2);
        City.setBounds(100, 250, 300, 52);
        f.add(City);
        
        State = new JLabel("State :");
        State.setForeground(Color.BLACK);
        State.setFont(f2);
        State.setBounds(100, 300, 300, 52);
        f.add(State);
        
        Members = new JLabel("No of Members :");
        Members.setForeground(Color.BLACK);
        Members.setFont(f2);
        Members.setBounds(100, 350, 300, 52);
        f.add(Members);
        
        PNo = new JLabel("Phone Number :");
        PNo.setForeground(Color.BLACK);
        PNo.setFont(f2);
        PNo.setBounds(100, 400, 300, 52);
        f.add(PNo);
        
        Username = new JLabel("Username :");
        Username.setForeground(Color.BLACK);
        Username.setFont(f2);
        Username.setBounds(100, 450, 300, 52);
        f.add(Username);
        
        Password = new JLabel("Password :");
        Password.setForeground(Color.BLACK);
        Password.setFont(f2);
        Password.setBounds(100, 500, 300, 52);
        f.add(Password);
        
        CPassword = new JLabel("Confirm Password :");
        CPassword.setForeground(Color.BLACK);
        CPassword.setFont(f2);
        CPassword.setBounds(100, 550, 300, 52);
        f.add(CPassword);
        
        ca = new JButton("Create Account");
        ca.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
        ca.setBackground(Color.white);
        ca.setForeground(Color.black);
        ca.setBounds(700, 600, 300, 40);
    	
        ca.addActionListener(new ActionListener() {
       	 public void actionPerformed(ActionEvent e) {
       		 
       		 	String Firstname = fname.getText();
       		 	String Lastname = lname.getText();
       		 	String CAge = age.getText();
       		 	String Ccity = city.getText();
       		 	String Cstate = String.valueOf(state.getSelectedItem());
       		 	String Cmembers = members.getText();
       		 	String PNO = pno.getText();
                String uname= username.getText();
                String passwd = password.getText();
                String cpasswd = cpassword.getText();
                
                
                
                try {
                
                	if(Firstname.length() == 0 || Lastname.length() == 0  || CAge.length() == 0 
                			|| Ccity.length() == 0  || Cstate.length() == 0 || Cmembers.length() == 0  || uname.length() == 0 
                			 || passwd.length() == 0 || cpasswd.length() == 0 || PNO.length()==0) {
                    	
                        JOptionPane.showMessageDialog(ca,"Fill in all the Details");
                    }
                	else if(Pattern.matches(name, Firstname)==false) {
                		JOptionPane.showMessageDialog(ca,"Enter valid name!!");
                	}
                	else if(Pattern.matches(laname,Lastname)==false) {
                		JOptionPane.showMessageDialog(ca,"Enter valid name!!");
                	}
                	
                	else if(Integer.parseInt(CAge) < 1) {
	                		
	                        JOptionPane.showMessageDialog(ca,"Please Enter valid age");  
                        
                	}
                	
                	else if(Integer.parseInt(Cmembers) < 2) {
	                		
	                        JOptionPane.showMessageDialog(ca,"Members should be at least 2"); 
                		
                	}
                	
                	else if(PNO.length()<10) {
                		
                        JOptionPane.showMessageDialog(ca,"Enter valid Phone Number"); 
            		
            	}
            	
                	
                	else if(passwd.length()<6) {
                		
                        JOptionPane.showMessageDialog(ca,"Password should have minimum of 6 characters");
                	}
                	else if(!passwd.equals(cpasswd)) {
                		
                        JOptionPane.showMessageDialog(ca,"Passwords are not same!!");
                	}
                	else {
	                	try {
	                	Connection connection  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
	                            "postgres", "##########");
	
	                    PreparedStatement st = (PreparedStatement)connection
	                        .prepareStatement("Select c_id from Login_customer where username =?;");
	                    
	                    st.setString(1, uname);
	                    
	                    rs = st.executeQuery();
	                		
	                    
	                   
	                	if (rs.next()) {
	                		
	                        JOptionPane.showMessageDialog(ca, "Username already exists");
	                        f.show();
	                        connection.close();
	                    } 
	                    else {
	                    	try {
	                			Class.forName("org.postgresql.Driver");
	                			c = DriverManager.getConnection
	                					("jdbc:postgresql://localhost:5432/postgres","postgres","rakkas1234");
	                			Statement s = c.createStatement();
	                			String sqll = "select count(c_id) from customer;";
	                			ResultSet rss = s.executeQuery(sqll);
	                			rss.next();
	                			int count = rss.getInt(1);
	                			
	                
	                			
	                			String sql = "select c_id from customer;";
	                			
	                			ResultSet r = s.executeQuery(sql);
	                			
	                			int i=1;
	                			
	                			while(r.next() && i < count) {
	                				i++;
	                			}
	                			String cid = r.getString(1);
	                			System.out.println(cid);
	                			
	                			int a = 0;
	                			
	                			for(int k=1;k<cid.length();k++) {
	                				a = a*10 + Character.getNumericValue(cid.charAt(k));
	                			}
	                			a++;
	                			String sql1 = 
	                			"insert into Customer(C_id ,Firstname, Lastname ,Age , City,State , C_Members)" +
	                			"values ( 'C"+ a +"', '"+ Firstname +"', '"
	                			+ Lastname +"', "+ Integer.parseInt(CAge) +", '"+ Ccity +"', '"+ Cstate +"', "+Integer.parseInt(Cmembers)+");"; 
	                			s.executeUpdate(sql1);
	                			
	                			String sql2 = 
	                					"insert into Login_Customer values"+
	                					"('" + uname + "', '" +passwd+ "', 'C" +  a + "');";
	                			s.executeUpdate(sql2);
	                			
	                			String sql3 = 
	                					"insert into C_Phone values"+
	                					"( 'C"+ a +"', " +  Long.parseLong(PNO) + ");";
	                			s.executeUpdate(sql3);
	                			 
	                			JOptionPane.showMessageDialog(ca, "successfully created account");
	                			f.dispose();
	                			new CustomerLogin();
	                			
	                		
	                		}
	                		catch(Exception ee) {
	                			ee.printStackTrace();
	                			System.err.println(ee.getClass().getName()+": "+ee.getMessage());
	                			System.exit(0);
	                		}
	                			
	                } 
	                    c.close();
	                		}
	                		catch (SQLException sqlException) {
	                            sqlException.printStackTrace();
	                        }
	                    
	                    }
                }     
                catch(Exception ee) {
        			ee.printStackTrace();
        			System.out.println(ee.getClass().getName()+": "+ee.getMessage());
        			System.exit(0);
        		}   
            
            }
        });
        
        f.add(ca);
        JLabel label = new JLabel("");
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
