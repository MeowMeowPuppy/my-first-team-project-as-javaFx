package changeGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import listMyGroup.ListMyGroupDTO;

public class ChangeGroupDAO {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	public ChangeGroupDAO() {
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

	public void update(ChangeGroupDTO changeGroupDto) {
		String sql = "UPDATE damo_group SET group_name=?, group_member=?, content=? WHERE  group_num=? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, changeGroupDto.getGroupName());
			ps.setInt(2, changeGroupDto.getGroupMember());
			ps.setString(3, changeGroupDto.getContent());
			ps.setInt(4, changeGroupDto.getGroupNum());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ChangeGroupDTO selectAll(ListMyGroupDTO listMyGroupDto) {
		ChangeGroupDTO changeGroupDto = new ChangeGroupDTO();
		
		String sql = "SELECT * FROM damo_group WHERE group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, listMyGroupDto.getGroupNum());
			rs = ps.executeQuery();
			if(rs.next()) {
				changeGroupDto.setCategory(rs.getString("category"));
				changeGroupDto.setContent(rs.getString("content"));
				changeGroupDto.setCurrentMember(rs.getInt("current_member"));
				changeGroupDto.setGroupMember(rs.getInt("group_member"));
				changeGroupDto.setGroupName(rs.getString("group_name"));
				changeGroupDto.setGroupNum(rs.getInt("group_num"));
				changeGroupDto.setLeaderId(rs.getString("leader_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return changeGroupDto;
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
