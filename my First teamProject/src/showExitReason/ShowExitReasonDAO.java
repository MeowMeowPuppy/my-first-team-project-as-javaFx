package showExitReason;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import breakAway.BreakAwayDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShowExitReasonDAO {
	
	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
	public ShowExitReasonDAO() {
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
	
	public ObservableList<BreakAwayDTO> selectAll(){
		ObservableList<BreakAwayDTO> dtos = FXCollections.observableArrayList();
		String sql = "SELECT * FROM exit";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BreakAwayDTO dto = new BreakAwayDTO();
				dto.setId(rs.getString("id"));
				dto.setDate(rs.getString("exitdate"));
				dto.setReason(rs.getString("reason"));
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
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

	public void delete(BreakAwayDTO clickedList) {
		String sql = "DELETE FROM exit WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, clickedList.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
