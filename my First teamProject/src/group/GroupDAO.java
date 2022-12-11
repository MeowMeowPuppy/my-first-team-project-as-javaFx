package group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import listCategory.ListCategoryDTO;
import login.LoginDTO;

public class GroupDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public GroupDAO() {
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

	// 정원 확인 하는 메서드
	public boolean maxCheck(LoginDTO loginDto, ListCategoryDTO listcategoryDto) {
		boolean check = false;
		int tmp = listcategoryDto.getGroupNum();
		int currentMember = 0;
		int maxMember = 0;
		String groupNum = tmp + "";
		String sql = "SELECT * FROM damo_group WHERE group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, groupNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				currentMember += rs.getInt("current_member");
				maxMember += rs.getInt(("group_member"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (currentMember == maxMember) {
			check = true;
			return check;
		}
		return check;
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
	
	// 모임에 가입시켜주는 메서드
	public void insert(LoginDTO loginDto, ListCategoryDTO listcategoryDto, int groupNum) {
		String sql = "INSERT INTO join_group VALUES(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, loginDto.getId());
			ps.setInt(2, groupNum);
			ps.setString(3, listcategoryDto.getLeaderId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void damoGroupUpdate(int groupNum) {
		String sql = "UPDATE damo_group SET current_member=current_member+1 WHERE group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, groupNum);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public ListCategoryDTO selectAll(ListCategoryDTO listcategoryDto) {
		ListCategoryDTO groupDto = new ListCategoryDTO();
		String sql = "SELECT * FROM damo_group WHERE group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, listcategoryDto.getGroupNum());
			rs = ps.executeQuery();
			if(rs.next()) {
				groupDto.setCategory(rs.getString("Category"));
				groupDto.setContent(rs.getString("content"));
				groupDto.setCurrentMember(rs.getInt("current_member"));
				groupDto.setGroupMember(rs.getInt("group_member"));
				groupDto.setGroupName(rs.getString("group_name"));
				groupDto.setGroupNum(rs.getInt("group_num"));
				groupDto.setLeaderId(rs.getString("leader_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupDto;
	}

	public String getMemberId(int groupNum) {
		String id = "";
		String sql = "SELECT id FROM join_group WHERE group_num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, groupNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				id += " "+rs.getString("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
