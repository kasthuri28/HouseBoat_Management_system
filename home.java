import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.border.*;

public class home implements ActionListener{
	
	static JFrame f;
	JMenuBar m;
	Icon icon;
	Box b;
	JTable j ;
	Connection c;
	Statement s;
	
	home(){
		
		f = new JFrame("HouseBoats");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.getContentPane().setBackground(Color.LIGHT_GRAY);
		f.setLayout(new BorderLayout(15,15));
		
		Font f1 = new Font(Font.DIALOG_INPUT, Font.BOLD, 18);
		Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 15);
		
		//menubar
		JMenuBar m = new JMenuBar();
		m.setBackground(Color.WHITE);//Giving color--Comes under swing 
		
		//shifting to right
		m.add(Box.createGlue());
	 
		JButton m2 = new JButton("LOGIN");
		m2.setBackground(Color.black);
		m2.setFont(f1);
		m2.setForeground(Color.white);//Changing font color
		m2.setBounds(10,10,15,15);
		m.add(m2);
		
		ImageIcon i = new ImageIcon("home.png"); // load the image to a imageIcon
		Image image = i.getImage(); // transform it to image
		Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		i = new ImageIcon(newimg); 
		
		m.add(new JMenu(" ")); //Giving space    
		
		JMenu m3 = new JMenu();
		m3.setIcon(i);
		m.add(m3);
		
		
		m2.addActionListener(this);
		
		f.add(m,BorderLayout.NORTH);//for having the toolbar on top 
		
		//Heading
		b = Box.createVerticalBox();
		JLabel title = new JLabel("HOUSEBOATS");
		
		Font f3 = new Font(Font.DIALOG_INPUT, Font.ITALIC + Font.BOLD, 24);
		
		title.setFont(f3);
		title.setForeground(Color.black); 
		title.setAlignmentX( Box.CENTER_ALIGNMENT);
		
		b.add(title); // adding label to box
		f.add(b);
		
		setConnection();
		display();
		
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
	    String btn = e.getActionCommand();
	    if(btn.equals("LOGIN")) {
	    	f.hide();
	    	new DefaultLogin();
	    	//f.setVisible(false);
	    }
	    
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
	
	public void display() {
		try {
			
		
			Font f3 = new Font(Font.DIALOG_INPUT, Font.BOLD, 16);
			String[] cName = 
				{"SI NO","HOUSEBOAT ID","HOUSEBOAT NAME","RATE PER HOUR ADULT","RATE PER HOUR ADULT"}; 
			s = c.createStatement();
			
			ResultSet rs = s.executeQuery("select count(H_id) from Houseboat");
			rs.next();
			int count = rs.getInt(1);
			
			String [][] data = new String[count+1][5];
			
			ResultSet r = s.executeQuery
			("select H_id, first_name || ' ' || last_name as H_name, Rate_per_hour_adult, Rate_per_hour_child from Houseboat;");
			data[0][0] = "SI NO";
			data[0][1] = "HOUSEBOAT ID"; 
			data[0][2] = "HOUSEBOAT NAME";
			data[0][3] = "RATE PER HOUR ADULT";
			data[0][4] = "RATE PER HOUR CHILD";	
			int i = 1;
			
			while(r.next()) {
					
				String h_id = r.getString("H_id");
				String h_name = r.getString("H_name");
				float r_a = r.getFloat(3);
				float r_c = r.getFloat(4);
				
				String ra = Float. toString(r_a);
				String rc = Float. toString(r_c);
				String ii = Integer.toString(i);
				
				data[i][0] = ii; 
				data[i][1] = h_id; 
				data[i][2] = h_name;
				data[i][3] = ra;
				data[i][4] = rc;				
				i++;
				
			}
			
			j = new JTable(data, cName); 
	        j.setBounds(30, 40, 20, 300); 
	        j.setRowHeight(j.getRowHeight() + 20);
	        
	       
	        
	        b.add(Box.createRigidArea(new Dimension(50,50)));
	        
	        
	        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
	        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
	        j.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
	        j.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
	        j.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
	        j.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
	        j.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
	        
	    
	       JTableHeader jh = j.getTableHeader();
	       jh.setBackground(Color.black);
	       jh.setForeground(Color.white);
	       
	       jh.setPreferredSize(
	    		     new Dimension(400,j.getRowHeight()+20));
	       j.setShowGrid(false);
	       j.setForeground(Color.white);
	       j.setBackground(Color.DARK_GRAY);
	       Font f4 = new Font(Font.DIALOG_INPUT, Font.BOLD, 16);
	       j.setFont(f4);
	       
	      
	        b.add(j); 
	        rs.close();
	        r.close();
			s.close();
			c.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	
	
}
