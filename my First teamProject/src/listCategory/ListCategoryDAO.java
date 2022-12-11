package listCategory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListCategoryDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String category;
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public ListCategoryDAO() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String password = "oracle";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("연결되지 않음.");
			e.printStackTrace();
		}
	}

	//모든 게시물(모임글)을 가져오기 위한 쿼리문
	//DB연동은 전에 배운 내용과 거의 동일 하지만
	//배운 내용에서 ArrayList를 사용했던 것을
	//ObservableList<ListCategoryDTO>로 바꿔사용해줘야 함.
	//ObservableList<ListCategoryDTO> lists = FXCollections.observableArrayList(); << 이것도 비슷한 유형
	public ObservableList<ListCategoryDTO> selectAll() {
		String sql = "SELECT * FROM damo_group WHERE category=? ORDER BY group_num";
		ObservableList<ListCategoryDTO> lists = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, category);
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
