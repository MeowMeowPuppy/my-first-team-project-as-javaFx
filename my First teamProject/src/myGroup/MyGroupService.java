package myGroup;

import javafx.collections.ObservableList;
import listCategory.ListCategoryDTO;
import listMyGroup.ListMyGroupDTO;
import login.LoginDTO;
import main.CommonService;

public class MyGroupService {
	
	
	private MyGroupDAO myGroupDao;
	
	public MyGroupService() {
		myGroupDao = new MyGroupDAO();
	}

	public boolean drop(LoginDTO loginDto, ListMyGroupDTO listMyGroupDto) {
		boolean check = false;
		// 모임 생성자인지 확인
		if(loginDto.getId().equals(listMyGroupDto.getLeaderId())) {
			CommonService.msg("모임 생성자는 탈퇴할 수 없습니다.");
			check = true;
			return check;
		}
		
		myGroupDao.drop(loginDto, listMyGroupDto);
		myGroupDao.exist(loginDto, listMyGroupDto);
		CommonService.msg("성공적으로 탈퇴 되었습니다.");
		return check;
	}

	public boolean delete(LoginDTO loginDto, ListMyGroupDTO listMyGroupDto) {
		boolean check = false;
		if(loginDto.getId().equals(listMyGroupDto.getLeaderId())==false) {
			CommonService.msg("모임 생성자만 수정/삭제가 가능합니다");
			check = true;
			return check;
		}
		myGroupDao.deleteJoin(loginDto, listMyGroupDto);
		myGroupDao.deleteGroup(listMyGroupDto);
		CommonService.msg("성공적으로 삭제되었습니다");
		return check;
	}

	public MyGroupDTO selectAll(ListMyGroupDTO listMyGroupDto) {
		MyGroupDTO listCategoryDto = myGroupDao.selectAll(listMyGroupDto);
		return listCategoryDto;
	}

	public String getMemberId(int groupNum) {
		String memberId = myGroupDao.getMemberId(groupNum);
		return memberId;
	}
}
