package test;
import java.sql.*;
public class UserDAO {
	public boolean validate(String uname, String pass) {
		try {
			Connection con = DriverConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from usertab17 where uname=\'"+uname+"\' and pword=\'"+pass+"\'");
			return rs.next();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
