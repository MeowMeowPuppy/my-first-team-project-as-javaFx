package createGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateGroupDAO {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	public CreateGroupDAO() {
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
	
	public int damoGroupInsert(CreateGroupDTO createGroup) {
		String sql = "INSERT INTO damo_group VALUES (group_seq.nextval, ?, ?, ?, ?, ?, 1 )";
		// group_seq.nextval이 group_seq로 들어가서 오류
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, createGroup.getLeaderId());			
			ps.setString(2, createGroup.getCategory());
			ps.setString(3, createGroup.getGroupName());	
			ps.setInt(4, createGroup.getGroupMember());					
			ps.setString(5, createGroup.getContent());				
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int damoGroupSelect() {
		String sql = "SELECT max(group_num) from damo_group";
		int num = 0; 
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
					num = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	public int joinGroupInsert(CreateGroupDTO createGroup) {
		String sql = "INSERT INTO join_group VALUES (?, ?, ?)";

		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, createGroup.getLeaderId());			
			ps.setInt(2, createGroup.getGroupNum());
			ps.setString(3, createGroup.getLeaderId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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




}
