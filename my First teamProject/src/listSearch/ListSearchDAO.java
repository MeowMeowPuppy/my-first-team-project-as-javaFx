package listSearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import group.GroupDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import listCategory.ListCategoryDTO;
import listMyGroup.ListMyGroupDTO;

public class ListSearchDAO {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private String category;
	private String searchText;

	public void setCategory(String category) {
		this.category = category;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public ListSearchDAO() {
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
	
	public ObservableList<ListCategoryDTO> selectAll(){
		ObservableList<ListCategoryDTO> lists = FXCollections.observableArrayList();
		String sql = "SELECT * FROM damo_group";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ListCategoryDTO list = new ListCategoryDTO();
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

	public ObservableList<ListCategoryDTO> searchSelect() {
		ObservableList<ListCategoryDTO> lists = FXCollections.observableArrayList();
		if (category == "전체") {
			String sql = "SELECT * FROM damo_group WHERE  GROUP_NAME LIKE ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, "%" + searchText + "%");
				rs = ps.executeQuery();
				while(rs.next()) {
					ListCategoryDTO list = new ListCategoryDTO();
					list.setGroupNum(rs.getInt("group_num"));
					list.setGroupName(rs.getString("group_name"));
					list.setLeaderId(rs.getString("leader_id"));
					list.setCategory(rs.getString("category"));
					list.setContent(rs.getString("content"));
					list.setCurrentMember(rs.getInt("current_member"));
					list.setGroupMember(rs.getInt("group_member"));
					lists.add(list);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			String sql = "SELECT * FROM damo_group WHERE category=? and GROUP_NAME LIKE ?";

			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, category);
				ps.setString(2, "%" + searchText + "%");
				rs = ps.executeQuery();
				while(rs.next()) {
					ListCategoryDTO list = new ListCategoryDTO();
					list.setGroupNum(rs.getInt("group_num"));
					list.setGroupName(rs.getString("group_name"));
					list.setLeaderId(rs.getString("leader_id"));
					list.setCategory(rs.getString("category"));
					list.setContent(rs.getString("content"));
					list.setCurrentMember(rs.getInt("current_member"));
					list.setGroupMember(rs.getInt("group_member"));
					ListCategoryDTO categoryList = new ListCategoryDTO();
					
					lists.add(list);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
		
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

}
