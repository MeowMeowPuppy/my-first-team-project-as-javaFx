package listMyGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import listCategory.ListCategoryDTO;
import login.LoginDTO;

public class ListMyGroupDAO {

	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
	
	public ListMyGroupDAO(){
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "oracle";
	String password = "oracle";
	
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		con = DriverManager.getConnection(url, user, password);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("FAIL");
	}
	}
	
	public ObservableList<ListMyGroupDTO> selectAll(Integer num) {
			String sql = "SELECT * FROM damo_group WHERE group_num=?";
			ObservableList<ListMyGroupDTO> lists = FXCollections.observableArrayList();
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, num);
				rs = ps.executeQuery();
				while(rs.next()) {
					ListMyGroupDTO list = new ListMyGroupDTO();
					list.setGroupNum(rs.getInt("group_num"));
					list.setGroupName(rs.getString("group_name"));
					list.setLeaderId(rs.getString("leader_id"));
					list.setCategory(rs.getString("category"));
					list.setContent(rs.getString("content"));
					list.setCurrentMember(rs.getInt("current_member"));
					list.setGroupMember(rs.getInt("group_member"));
					lists.add(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lists;
		}	
	
	// 이미 가입한 모임인지 확인하는 메서드
	public ArrayList<Integer> regedCheck(LoginDTO loginDto) {
		String sql = "SELECT group_num FROM join_group WHERE id=?";
		ArrayList<Integer> groups = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, loginDto.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				int group = rs.getInt("group_num");
				groups.add(group);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groups;
	}
	public ArrayList<Integer> joinGroupSelect(LoginDTO loginDto) {
		String sql = "SELECT GROUP_NUM FROM join_group WHERE leader_check=?";
		ArrayList<Integer> leader_check = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, loginDto.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				int group = rs.getInt("group_num");
				if(leader_check.contains(group)==false) {
					leader_check.add(group);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leader_check;
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
}