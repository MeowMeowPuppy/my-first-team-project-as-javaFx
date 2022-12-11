package breakAway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BreakAwayDAO {

	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;

	public BreakAwayDAO() {
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

	public int check(String id, PasswordField pw) {
		int count = 0;

		String sql = "SELECT pw FROM damo_member WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("pw").equals(pw.getText())) {
					count += 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public void delete(String id) {
		String sql = "DELETE FROM damo_member WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "DELETE FROM damo_group WHERE leader_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "DELETE FROM join_group WHERE leader_check=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void disconnection() {
		try {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveReason(String name, TextArea reason) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String today = format.format(date);
		
		
		String sql = "INSERT INTO exit VALUES(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, reason.getText());
			ps.setString(3, today);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
