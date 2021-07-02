import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class OwnerPage implements ActionListener{
	
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
    OwnerPage(String s1,String s2){
    	username = s1;
    	password = s2;
    	
    	f = new JFrame("Owner Login");
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
                    .prepareStatement("Select o_id from Login_Owner where username =?;");  
			
            st.setString(1,username);
            
			ResultSet rs = st.executeQuery() ;
			rs.next();
			
			String O_id = rs.getString(1);
			
			PreparedStatement st1 = (PreparedStatement)c
                    .prepareStatement
                    ("select first_name || ' ' || last_name as OName, O_city, O_state from owner where O_id =?;");
			
			st1.setString(1,O_id); 
			ResultSet r = st1.executeQuery() ;
			
			r.next();
			
			String oname = r.getString(1);
			String ocity = r.getString(2);
			String ostate = r.getString(3);
			
			
			
			Font f7 = new Font(Font.DIALOG_INPUT,Font.BOLD,20);
			
			Box b1 = Box.createVerticalBox();
			
			JLabel Title = new JLabel("OWNER DETAILS");
			Title.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD + Font.ITALIC,26));
			Title.setForeground(Color.black);
			Title.setAlignmentX( Box.CENTER_ALIGNMENT);
			
			String ONAME = "OWNER NAME : " + oname;
			JLabel owner  = new JLabel(ONAME);
			owner.setFont(f7);
			owner.setForeground(Color.DARK_GRAY);
			
			String OCITY = "OWNER CITY : " + ocity;
			JLabel ownerCity  = new JLabel(OCITY);
			ownerCity.setFont(f7);
			ownerCity.setForeground(Color.DARK_GRAY);
			
			String OSTATE = "OWNER STATE : " + ostate;
			JLabel ownerState  = new JLabel(OSTATE);
			ownerState.setFont(f7);
			ownerState.setForeground(Color.DARK_GRAY);
			
			JLabel ownerboats  = new JLabel("<html><u>HouseBoat Details</u></html>");
			ownerboats.setFont(f7);
			ownerboats.setForeground(Color.DARK_GRAY);
			
			
			b1.add(Title);
			b1.add(Box.createVerticalStrut( 70 ) ); 
			b1.add(owner); 
			b1.add(Box.createVerticalStrut( 10 ) ); 
			b1.add(ownerCity);
			b1.add(Box.createVerticalStrut( 10 ) ); 
			b1.add(ownerState);
			b1.add(Box.createVerticalStrut( 30 ) ); 
			b1.add(ownerboats);
			
			PreparedStatement st2 = (PreparedStatement)c
                    .prepareStatement
                    ("select h.first_name || ' ' || h.last_name as HName from houseboat h , owner o where o.o_id = h.o_id and h.O_id =?;"); 

			
			st2.setString(1,O_id); 
			
			ResultSet rs1 = st2.executeQuery() ;

			String[] cName = 
				{"SI NO","HOUSEBOAT NAME"}; 
		
			
			PreparedStatement st3 = (PreparedStatement)c
                    .prepareStatement
                    ("select count(h_id) from houseboat h , owner o where o.o_id = h.o_id and h.O_id =?;"); 

			st3.setString(1,O_id); 

			ResultSet rs2 = st3.executeQuery();
			rs2.next();
			
			int count = rs2.getInt(1);
			
			String [][] data = new String[count+1][2];
			
			
			data[0][0] = "SI NO";
			data[0][1] = "HOUSEBOAT NAME"; 
			
			int i = 1;
			
			while(rs1.next()) {
					
				String h_name = rs1.getString(1);
				String ii = Integer.toString(i);
				
				data[i][0] = ii; 
				data[i][1] = h_name;				
				i++;
					
			}
			
			j = new JTable(data, cName); 
	        j.setPreferredSize(new Dimension(350,150)); 
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
	        r.close();
			s.close();
			c.close();
			
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
