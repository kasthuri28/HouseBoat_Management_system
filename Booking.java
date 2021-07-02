import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Booking {
	Connection c = null;
	Statement s = null;
		Booking(){
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
	public String[] getHid() {
		int i=0;
		String Hid[] = new String[8];
		try {
			s = c.createStatement();
			ResultSet r = s.executeQuery("select H_id from Houseboat;") ;
			while(r.next()) {
				String id = r.getString(1);
				Hid[i] = id;
				i++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return Hid;
	}
	public void insert_rents(String H_id, String C_id, String Date, String duration) {
		try {
			s = c.createStatement();
			String sql = "insert into Rents(H_id,C_id,Date,duration)"
					+" values ( '"+H_id+"' , '"+C_id+"' , "+"'"+Date+"' , '"+duration+"');"; 
			s.executeUpdate(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	public void insert_members(String C_id, String M_name, Integer Age, String Relationship) {
		try {
			s = c.createStatement();
			String sql = "insert into Members(C_id,  M_name, Age, Relationship)"
					+" values ( '"+C_id+"' , '"+M_name+"' , " +Age+" , '"+Relationship+"');"; 
			s.executeUpdate(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	public boolean search(String H_id,String date) {
		try {
			s = c.createStatement();
			String sql = "select date from rents where H_id = '"+ H_id + "';";
			ResultSet r = s.executeQuery(sql);
			r.next();
			while(r.next()) {
				String DATE = r.getString(1);
				if(DATE.equals(date)) {
					return true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return false;
	}
}
	
