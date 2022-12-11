package managementLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ManagementLoginDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ManagementLoginDAO() {
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
	public ManagementLoginDTO check(TextField id, PasswordField pw) {
		ManagementLoginDTO dto = new ManagementLoginDTO();
		String sql = "SELECT * FROM manager WHERE id=? and pw=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id.getText());
			ps.setString(2, pw.getText());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
