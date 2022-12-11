package group;

import java.util.ArrayList;

import listCategory.ListCategoryDTO;
import login.LoginDTO;
import main.CommonService;

public class GroupService {
	
	private int groupNum;
	
	private GroupDAO groupDao;
	
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	};
	
	public GroupService() {
		groupDao = new GroupDAO();
	}
	
	public void insert(LoginDTO loginDto, ListCategoryDTO listcategoryDto) {
		// 정원초과 확인하는 부분
		GroupDAO groupDao = new GroupDAO();
		boolean check = groupDao.maxCheck(loginDto, listcategoryDto);
		if(check) {
			CommonService.msg("정원이 초과되어 참석 할 수 없습니다.");
			return;
		}
		
		// 가입한 모임인지 확인하는 부분
		ArrayList<Integer> num = groupDao.regedCheck(loginDto);
		if(num.contains(groupNum)) {
			CommonService.msg("이미 참여한 모임입니다.");
			return;
		}
		
		groupDao.insert(loginDto, listcategoryDto, groupNum);
		CommonService.msg("성공적으로 가입되었습니다.");
		int currentMember = listcategoryDto.getCurrentMember() + 1;
		listcategoryDto.setCurrentMember(currentMember);
		
		groupDao.damoGroupUpdate(groupNum);
	}

	public ListCategoryDTO selectAll(ListCategoryDTO listcategoryDto) {
		ListCategoryDTO groupDto = groupDao.selectAll(listcategoryDto);
		return groupDto;
		// TODO Auto-generated method stub
		
	}

	public String getMemberId(int groupNum) {
		String id = groupDao.getMemberId(groupNum);
		return id;
	}
}
