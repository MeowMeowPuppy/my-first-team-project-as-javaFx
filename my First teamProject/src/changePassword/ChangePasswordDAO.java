package changePassword;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.scene.control.PasswordField;

public class ChangePasswordDAO {

	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
	public ChangePasswordDAO() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String password = "oracle";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String check(String id) {
		String sql = "SELECT * FROM damo_member WHERE id=?";
		String check = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				check = rs.getString("pw");
				return check;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void changePw(String id , PasswordField changePw) {
		String sql = "UPDATE damo_member SET pw=? WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, changePw.getText());
			ps.setString(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void disconnection() {
		try {
			if(ps != null) ps.close();
			if(rs != null) rs.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
