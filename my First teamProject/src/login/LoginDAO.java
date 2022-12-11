package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.LoginDTO;
import main.Open;

public class LoginDAO {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private Open open;
	
	public void setOpen(Open open) {
		this.open = open;
	}
	
	public LoginDAO() {
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
	
	public LoginDTO selectId(String id) {
		String sql = "SELECT * FROM damo_member WHERE id=?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs =  ps.executeQuery();
			if(rs.next()) {
				LoginDTO login = new LoginDTO();
				login.setId(rs.getString("id"));
				login.setPw(rs.getString("pw"));
				login.setLogin(rs.getString("login"));
				login.setNotify(rs.getString("notify"));
				login.setReportCount(rs.getInt("report"));
				return login;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void setLogin(LoginDTO dto) {
		String sql = "UPDATE damo_member SET login=? WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "y");
			ps.setString(2, dto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setLogout(String id) {
		String sql = "UPDATE damo_member SET login=? WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "n");
			ps.setString(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnection() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public void setNotify(LoginDTO loginDto) {
		String sql = "UPDATE damo_member SET notify='y' WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, loginDto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
		
		
		
	

}
