package createGroup;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import main.CommonService;

public class CreateGroupService {
	private CreateGroupDAO createGroupDao;
	
	
	public CreateGroupService() {
		createGroupDao = new CreateGroupDAO();
	}
	
	public boolean createProc(String id, TextField groupName, TextField groupMember, ComboBox<String> category,
			TextArea content) {
		boolean check = false;
		// 빈 데이터
		if(groupName.getText().isEmpty() || groupMember.getText().isEmpty() || category.getValue() == null || content.getText().isEmpty() )  {
			CommonService.msg("전체 입력 해주세요.");
			check = true;
			return check;
		}
		
		// 구룹 생성 성공!!
		
		CreateGroupDTO createGroupDto = new CreateGroupDTO();
		createGroupDto.setLeaderId(id);
		createGroupDto.setGroupName(groupName.getText());
		createGroupDto.setCategory(category.getValue());
		createGroupDto.setGroupMember(Integer.parseInt(groupMember.getText()));
		createGroupDto.setContent(content.getText());		
		int result = createGroupDao.damoGroupInsert(createGroupDto);
		if (result == 1)
			CommonService.msg("그룹 생성 완료 되었습니다!");
			int groupNum = createGroupDao.damoGroupSelect();
			createGroupDto.setGroupNum(groupNum);
			createGroupDao.joinGroupInsert(createGroupDto);
			return check;
	}

	

}
