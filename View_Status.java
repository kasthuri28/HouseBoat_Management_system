import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class View_Status implements ActionListener{
	
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
    
    View_Status(String s1,String s2){
    	username = s1;
    	password = s2;
    	
    	f = new JFrame("View_Status");
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
		
		m.add(new JMenu(" ")); //Giving space   
		
		
		
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
		 
	    JLabel title = new JLabel("CUSTOMER STATUS",JLabel.CENTER);
        title.setForeground(Color.BLACK);
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 26));
        title.setBounds(600, 30, 273, 93);
        
        f.add(title);
        
        
        Font f1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 20);
        
  
        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        
        f.add(label);
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
	                .prepareStatement("Select C_id from Login_Customer where username =?;");  
			
	        st.setString(1,username);
	        
	        ResultSet rs = st.executeQuery() ;
			rs.next();
			
			
			
			String c_id = rs.getString(1);
			
			PreparedStatement st1 = (PreparedStatement)c
                    .prepareStatement
                    ("select  C_id, firstname || ' ' || lastname as CName,City, State,Age,C_members from Customer where c_id =?;");
			
			st1.setString(1,c_id); 
			ResultSet r = st1.executeQuery() ;
			
			r.next();
			
			String cid = r.getString(1);
			String cname = r.getString(2);
			String ccity = r.getString(3);
			String cstate = r.getString(4);
			int Age=r.getInt(5);
			
			int C_mem = r.getInt(6);
			//String h_id=r.getNString(7);
			
			String age = Integer.toString(Age);
			String C_members = Integer.toString(C_mem);
			
			Font f7 = new Font(Font.DIALOG_INPUT,Font.BOLD,20);
			
			Box b1 = Box.createVerticalBox();
			
			JLabel Title = new JLabel("CUSTOMER DETAILS");
			Title.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD + Font.ITALIC,26));
			Title.setForeground(Color.black);
			Title.setAlignmentX( Box.CENTER_ALIGNMENT);
			
			String Cid = "CUSTOMER ID : " + c_id;
			JLabel Cusid = new JLabel(c_id);
			Cusid.setFont(f7);
			Cusid.setForeground(Color.DARK_GRAY);
			
			String CNAME = "CUSTOMER NAME : " + cname;
			JLabel Cusname  = new JLabel(CNAME);
			Cusname.setFont(f7);
			Cusname.setForeground(Color.DARK_GRAY);
			
			String CCITY = "CUSTOMER CITY : " + ccity;
			JLabel CusCity  = new JLabel(CCITY);
			CusCity.setFont(f7);
			CusCity.setForeground(Color.DARK_GRAY);
			
			String CSTATE = "CUSTOMER STATE : " + cstate;
			JLabel CusState  = new JLabel(CSTATE);
			CusState.setFont(f7);
			CusState.setForeground(Color.DARK_GRAY);
			
			
			String Cage = "Customer Age : " + age;
			JLabel Cusdob = new JLabel(Cage);
			Cusdob.setFont(f7);
			Cusdob.setForeground(Color.DARK_GRAY);
			
			/*String HID = "CURRENTLY WORKING IN : " + h_id;
			JLabel CusHid = new JLabel(HID);
			CusHid.setFont(f7);
			CusHid.setForeground(Color.DARK_GRAY);
			*/
			
			
			b1.add(Title);
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(Cusid); 
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(Cusname); 
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(CusCity);
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(CusState);
			b1.add(Box.createVerticalStrut( 20 ) ); 
			b1.add(Cusdob);
			b1.add(Box.createVerticalStrut( 20 ) ); 
			//b1.add(CusHid);
			
			PreparedStatement st2 = (PreparedStatement)c
                    .prepareStatement
                    ("select  first_name || ' ' || last_name as hname,M_name from Houseboat h , rents r ,customer c,members m where r.c_id=c.c_id and r.H_id=h.H_id and m.c_id=c.c_id and date>=NOW() and c.c_id=?;"); 

			
			st2.setString(1,c_id); 
			
			ResultSet rs1 = st2.executeQuery() ;

			String[] cName = 
				{"HOUSEBOAT NAME","Members","Date","Duration"}; 
		
		
			
			
			
                    		 

			
			
			
			PreparedStatement st4 = (PreparedStatement)c
                    .prepareStatement
                    ("select Date,Duration from Rents where C_id=?");
			
			st4.setString(1,c_id); 

			ResultSet rs3 = st4.executeQuery();
			rs3.next();
			
			
			String [][] data = new String[10][5];
			
			data[0][0] = "CURRENT STATUS";
			data[0][1] = "HOUSEBOAT NAME";
			data[0][2] = "MEMBER"; 
			data[0][3]="Date";
			data[0][4]="Duration";
			
			int i=1;
			
			while(rs1.next()) {
				String ii=rs1.getString(2);
				Date d=rs3.getDate(1);
				Time t=rs3.getTime(2);
				String da=d.toString();
				String ti=t.toString();
                String CS="Booked";
				
				String h_name = rs1.getString(1);
				
				data[i][0]=CS; 
				data[i][1] = h_name; 
				data[i][2] = ii;
				data[i][3]=da;
				data[i][4]=ti;
				i++;
				
				
			}
			
			if(data[1][0]==null)
			{
				data[1][0]="Not Booked";
			}
			j = new JTable(data, cName); 
	        j.setPreferredSize(new Dimension(700,150)); 
	        j.setRowHeight(j.getRowHeight() + 20);
	        
	       
	        
	        b1.add(Box.createVerticalStrut( 30 ) ); 
	        
	        
	        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
	        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
	        j.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
	        j.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
	        
	        
	    
	       JTableHeader jh = j.getTableHeader();
	       jh.setBackground(Color.black);
	       jh.setForeground(Color.white);
	       
	       j.setShowGrid(false);
	       j.setForeground(Color.cyan);
	       j.setBackground(Color.DARK_GRAY);
	       Font f4 = new Font(Font.DIALOG_INPUT, Font.BOLD, 16);
	       j.setFont(f4);
	       JPanel jp = new JPanel();
	       jp.setBackground(Color.LIGHT_GRAY);
	       
	       jp.add(j);
	        b1.add(jp); 
	        rs.close();
			
			
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
        
