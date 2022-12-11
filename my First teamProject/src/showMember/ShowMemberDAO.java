package showMember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import regstration.RegstrationDTO;

public class ShowMemberDAO {

	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;

	public ShowMemberDAO() {
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

	public ObservableList<RegstrationDTO> getMember() {
		ObservableList<RegstrationDTO> dtos = FXCollections.observableArrayList();
		String sql = "SELECT * FROM damo_member";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				RegstrationDTO dto = new RegstrationDTO();
//				System.out.println(rs.getInt("report"));
//				System.out.println(rs.getString("phonNum"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setPhoneNumber(rs.getString("phonNum"));
				dto.setReport(rs.getInt("report"));
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	public void block(RegstrationDTO clickedList) {
		String sql = "DELETE FROM damo_member WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, clickedList.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "DELETE FROM damo_group WHERE leader_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, clickedList.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "DELETE FROM join_group WHERE leader_check=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, clickedList.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
