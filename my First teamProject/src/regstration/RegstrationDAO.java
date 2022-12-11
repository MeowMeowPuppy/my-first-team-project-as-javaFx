package regstration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.TextField;

public class RegstrationDAO {
	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;

	// DB설정
	public RegstrationDAO() {
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

	// DB에 저장하는 메서드
	public void insert(RegstrationDTO regstrationDto) {
		String id = regstrationDto.getId();
		String pw = regstrationDto.getPw();
		String phoneNumber = regstrationDto.getPhoneNumber();
		String name = regstrationDto.getName();
		String gender = regstrationDto.getGender();

		String sql = "INSERT INTO damo_member VALUES(?,?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setString(4, phoneNumber);
			ps.setString(5, gender);
			ps.setString(6, "n");
			ps.setInt(7, 0);
			ps.setString(8, "y");
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 아이디 중복을 확인하는 메서드
	public int check(TextField id) {
		String sql = "SELECT count(id) FROM damo_member WHERE id=?";
		int count = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id.getText());
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 전화번호 체크하여 정렬하는 메서드
	public String organizeNumber(TextField phoneNumber) {

		String organizedNum = phoneNumber.getText();
		String removed;
		if (organizedNum.contains("-")) {
			String[] tmps = organizedNum.split("-");
			removed = organizedNum.replace("-", "");
			organizedNum = tmps[0] + tmps[1] + tmps[2];
		}
		removed = organizedNum;
		if (removed.length() < 9 || removed.length() > 11) {
			return null;
		}

		for (int i = 0; i < removed.length(); i++) {
			if ((48<=removed.charAt(i)&&removed.charAt(i)<=57)==false) {
				return null;
			}
		}

		if (organizedNum.length() == 9) {
			String tmp1 = organizedNum.substring(0, 2);
			String tmp2 = organizedNum.substring(2, 5);
			String tmp3 = organizedNum.substring(5, 9);
			organizedNum = tmp1 + "-" + tmp2 + "-" + tmp3;
		}
		if (organizedNum.length() == 10) {
			String tmp1 = organizedNum.substring(0, 3);
			String tmp2 = organizedNum.substring(3, 6);
			String tmp3 = organizedNum.substring(6, 10);
			organizedNum = tmp1 + "-" + tmp2 + "-" + tmp3;
		}
		if (organizedNum.length() == 11) {
			String tmp1 = organizedNum.substring(0, 3);
			String tmp2 = organizedNum.substring(3, 7);
			String tmp3 = organizedNum.substring(7, 11);
			organizedNum = tmp1 + "-" + tmp2 + "-" + tmp3;
		}
		return organizedNum;
	}

	public void disconnection() {
		try {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
