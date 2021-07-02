import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.awt.event.*;
import java.util.regex.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class BookGUI implements ActionListener{
	
	String time = "([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
	String name = "\\b[A-Z][a-z]+([A-Z][a-z]*)*\\b";
	String lname = "\\b[A-Z][a-z]+";
	String Cid;
	JFrame f ;
	JButton sub;
	JTextField Ddur, Ddate, Dname, Dage, Drelation;
	JComboBox hid, Dcid;
	LocalDate da = LocalDate.now();
	Date d = new Date();
	SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
	Booking obj = new Booking();
	
	BookGUI (String cid){
		Cid = cid;
		f = new JFrame("BOOKING");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.getContentPane().setBackground(Color.LIGHT_GRAY);
		f.setLayout(new BorderLayout(30,30));
		
		JLabel title = new JLabel("BOOKING");
		title.setBounds(200, 100, 300, 25);
		title.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD+Font.ITALIC, 20));
		f.add(title);
		
		Font f2 = new Font(Font.SANS_SERIF, Font.PLAIN, 20);//Font for the texts
		JLabel ct = new JLabel("HOUSEBOAT : ");
		ct.setBounds(400, 150, 300, 25);
		ct.setFont(f2);
		f.add(ct);
        
		
		String Hid1[] = obj.getHid();
	    hid = new JComboBox(Hid1);
	    hid.setBounds(700, 150, 100, 25);
	    f.add(hid);
	    f.setSize(400,400);
		
	    JLabel pssd = new JLabel("PASSENGERS DETAILS");
		pssd.setBounds(400, 200, 300, 25);
		pssd.setFont(f2);
		f.add(pssd);
		
//		JLabel cid = new JLabel("CID : ");
//		cid.setBounds(500, 235, 80, 25);
//		cid.setFont(f2);
//		f.add(cid);
//        
//		String Cid1[] = obj.getCid();
//	    Dcid = new JComboBox(Cid1);
//	    Dcid.setBounds(700, 230, 100, 25);
//	    f.add(Dcid);
//	    f.setSize(400,400);
		
		JLabel name = new JLabel("NAME : ");
		name.setFont(f2);
		name.setBounds(500, 260, 100, 50);
		f.add(name);
				
		Dname = new JTextField();
	    Dname.setBounds(700, 270, 100, 25);
	    f.add(Dname);
	    
		JLabel age = new JLabel("AGE : ");
		age.setFont(f2);
		age.setBounds(500, 300, 100, 50);
		f.add(age);
		
		Dage = new JTextField();
		Dage.setBounds(700, 310, 100, 25);
		f.add(Dage);
		
		JLabel relation = new JLabel("RELATION : ");
		relation.setBounds(500, 340, 200, 50);
		relation.setFont(f2);
		f.add(relation);
		
		Drelation = new JTextField();
		Drelation.setBounds(700, 350, 100, 25);
		f.add(Drelation);
		
		JLabel date1 = new JLabel("DATE : ");
		date1.setBounds(400, 390, 100, 50);
		date1.setFont(f2);
		f.add(date1);
		
		Ddate = new JTextField();
		Ddate.setBounds(700, 400, 100, 25);
		Ddate.setText(" "+da);
		
		f.add(Ddate);
			
		JLabel duration = new JLabel("DURATION : ");
		duration.setBounds(400, 440, 200, 50);
		duration.setFont(f2);
		f.add(duration);
		
		Ddur = new JTextField();
		Ddur.setBounds(700, 455, 100, 25);
		f.add(Ddur);
	    		
		sub = new JButton("SUBMIT");
		sub.setBounds(750,500,150,40);
		sub.setForeground(Color.black);
		sub.setBackground(Color.white);
		sub.setFont(f2);
		sub.addActionListener(this);
		f.add(sub);
		
		JButton home = new JButton("Back To Login");
		home.setBounds(950,500,250,40);
		home.setForeground(Color.black);
		home.setBackground(Color.white);
		home.setFont(f2);
		home.addActionListener(this);
		f.add(home);
				
		f.setLayout(null);
	    f.setVisible(true);
	    
		
	}
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			if(s.equals("LOGIN")) {
				DefaultLogin.fr.show();
				f.hide();
			}
			else if(s.equals("SUBMIT")) {
				String HID = (String)hid.getSelectedItem();
				String DATE = Ddate.getText();
				String DUR =  Ddur.getText();
				String DNAME = Dname.getText();
				String AGE = Dage.getText();
				String RELATION =  Drelation.getText();
				
				
				if(HID.length()==0 || DUR.length()==0 || DATE.length()==0 || DNAME.length()==0 
						|| AGE.length()==0 || RELATION.length()==0) {
					JOptionPane.showMessageDialog(f,"Fill in all the details");
				}
				else if(Pattern.matches(name, DNAME) == false) {
					JOptionPane.showMessageDialog(f,"Enter valid name!!");
				}
				else if(Integer.parseInt(AGE) < 1) {
					JOptionPane.showMessageDialog(f,"Enter valid Age");
				}
				else if(Pattern.matches(lname, RELATION) == false) {
					JOptionPane.showMessageDialog(f,"Enter valid Relation!!");
					System.out.println(1);
				}
				else if(Pattern.matches(time, DUR) == false) {
					JOptionPane.showMessageDialog(f,"Enter valid Time!!");
					System.out.println(1);
				}
				else if(obj.search(HID, DATE)) {
					JOptionPane.showMessageDialog(f,"This HouseBoat is already taken on this date!!!");
				}
				
				else {
					JOptionPane.showMessageDialog(f,"Successfully saved your details!!");
					home.f.show();
					
					obj.insert_rents(HID,Cid, DATE, DUR);
					obj.insert_members(Cid,DNAME, Integer.parseInt(AGE), RELATION);
					
					f.dispose();
						
				}
			}
			else {
				CustomerLogin.f.show();
				f.hide();
			}
		}

	
}

