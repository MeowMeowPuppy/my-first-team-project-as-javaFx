package listMyGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import login.LoginDTO;

public class ListMyGroupService {

	private LoginDTO loginDto;
	private ListMyGroupDAO listMyGroupDao;

	public void setLoginDto(LoginDTO loginDto) {
		this.loginDto = loginDto;
	}

	public ListMyGroupService() {
		listMyGroupDao = new ListMyGroupDAO();
	}

	public ObservableList<ListMyGroupDTO> CreateTableSelect(LoginDTO loginDto) {
		// 가입한 모임의 고유번호를 불러오는 메서드
		ArrayList<Integer> num = listMyGroupDao.joinGroupSelect(loginDto);

		// ObervableList<> 는 생성할때에 new가 아니라 FXCollections.observableArrayList(); 을 사용한다!
		ObservableList<ListMyGroupDTO> result = FXCollections.observableArrayList();
		
		// 가입 모임의 고유번호 갯수만큼 반복
		for (int i = 0; i < num.size(); i++) {
			result.addAll(listMyGroupDao.selectAll(num.get(i)));
		}
		return result;
	}
	
	public ObservableList<ListMyGroupDTO> JoinTableSelect(LoginDTO loginDto) {
		// 가입한 모임의 고유번호를 불러오는 메서드
		ArrayList<Integer> num = listMyGroupDao.regedCheck(loginDto);
		ArrayList<Integer> num1 = listMyGroupDao.joinGroupSelect(loginDto);		
		for(int i=0; i<num1.size();i++) {
			num.remove(num1.get(i));		
		}

		// ObervableList<> 는 생성할때에 new가 아니라 FXCollections.observableArrayList(); 을 사용한다!
		ObservableList<ListMyGroupDTO> result = FXCollections.observableArrayList();
		
		// 가입 모임의 고유번호 갯수만큼 반복
		for (int i = 0; i < num.size(); i++) {
			result.addAll(listMyGroupDao.selectAll(num.get(i)));
		}
		
		return result;
	}
}
