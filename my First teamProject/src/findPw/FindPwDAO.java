package findPw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.scene.control.TextField;
import regstration.RegstrationDTO;

public class FindPwDAO {

	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
	public FindPwDAO() {
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
	public RegstrationDTO check(TextField id) {
		RegstrationDTO dto = new RegstrationDTO();
		String sql = "SELECT * FROM damo_member WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id.getText());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setPhoneNumber(rs.getString("phonNum"));
				dto.setPw(rs.getString("pw"));
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 전화번호 체크하여 정렬하는 메서드
	public String organizeNumber(TextField phoneNumber) {
		
		String organizedNum = phoneNumber.getText();
		if(organizedNum.contains("-")) {
			String[] tmps = organizedNum.split("-");
			organizedNum = tmps[0] + tmps[1] + tmps[2];
		}
		if(organizedNum.length()==9) {
			String tmp1 = organizedNum.substring(0, 2);
			String tmp2 = organizedNum.substring(2, 5);
			String tmp3 = organizedNum.substring(5, 9);
			organizedNum = tmp1 + "-" + tmp2 + "-" + tmp3;
		}
		if(organizedNum.length()==10) {
			String tmp1 = organizedNum.substring(0, 3);
			String tmp2 = organizedNum.substring(3, 6);
			String tmp3 = organizedNum.substring(6, 10);
			organizedNum = tmp1 + "-" + tmp2 + "-" + tmp3;
		}
		if(organizedNum.length()==11) {
			String tmp1 = organizedNum.substring(0, 3);
			String tmp2 = organizedNum.substring(3, 7);
			String tmp3 = organizedNum.substring(7, 11);
			organizedNum = tmp1 + "-" + tmp2 + "-" + tmp3;
		}
		return organizedNum;
	}
	
	public void disconnection() {
		try {
			if(ps != null)ps.close();
			if(rs != null)rs.close();
			if(con != null)con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
