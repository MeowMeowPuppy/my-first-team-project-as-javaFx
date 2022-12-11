package report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.scene.control.TextField;

public class ReportDAO {

	private PreparedStatement ps;
	private ResultSet rs;
	private Connection con;

	public ReportDAO() {
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

	public boolean check(TextField reportId) {
		String sql = "SELECT * FROM damo_member WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, reportId.getText());
			rs = ps.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	public void report(TextField reportId) {
		String sql = "UPDATE damo_member SET report=report+1, notify='n' WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, reportId.getText());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
