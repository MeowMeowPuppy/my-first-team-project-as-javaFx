package myJoinGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import listCategory.ListCategoryDTO;
import listMyGroup.ListMyGroupDTO;
import login.LoginDTO;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyJoinGroupDAO {

	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
	
	public MyJoinGroupDAO(){
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
	
	public ObservableList<MyJoinGroupDTO> selectAll() {
			String sql = "SELECT group_num, group_name, leader_id, category, content, current_member, group_member FROM damo_group ORDER BY group_num";
			ObservableList<MyJoinGroupDTO> lists = FXCollections.observableArrayList();
			//lists = tableview
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					MyJoinGroupDTO list = new MyJoinGroupDTO(rs.getInt("group_num"), rs.getString("group_name"), rs.getString("leader_id"), rs.getString("category"),
							rs.getString("content"), rs.getInt("current_member"), rs.getInt("group_member"));
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
	
	public void disconnection() {
		try {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// join_group 에서 삭제
	public void drop(LoginDTO loginDto, ListMyGroupDTO listMyGroupDto) {
		String sql = "DELETE FROM join_group WHERE id=? and group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, loginDto.getId());
			ps.setInt(2, listMyGroupDto.getGroupNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// damo_group에서 현재인원 감소
	public void exist(LoginDTO loginDto, ListMyGroupDTO listMyGroupDto) {
		String sql = "UPDATE damo_group SET current_member=current_member-1 WHERE group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, listMyGroupDto.getGroupNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteJoin(LoginDTO loginDto, ListMyGroupDTO listMyGroupDto) {
		String sql = "DELETE FROM join_group WHERE id=? and group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, loginDto.getId());
			ps.setInt(2, listMyGroupDto.getGroupNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteGroup(ListMyGroupDTO listMyGroupDto) {
		String sql = "DELETE FROM damo_group WHERE group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, listMyGroupDto.getGroupNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMemberId(int groupNum) {
		String id = "";
		String sql = "SELECT id from join_group WHERE group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, groupNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				id += " " + rs.getString("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}

