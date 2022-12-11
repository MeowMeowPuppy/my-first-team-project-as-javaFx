package changeGroup;

import java.net.URL;
import java.util.ResourceBundle;

import createGroup.CreateGroupService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import listMyGroup.ListMyGroupDTO;
import main.CommonService;
import main.Open;

public class ChangeGroupController implements Initializable {
	private Open open;
	private ListMyGroupDTO listMyGroupDto;
	private ChangeGroupDTO changeGroupDto;
	private ChangeGroupDAO changeGroupDao;
	public void setOpen(Open open) {
		this.open = open;
	}
    
	@FXML
	private TextField number;

	@FXML
	private TextField id;

	@FXML
	private TextField category;

	@FXML
	private TextField name;//제목

	@FXML
	private TextArea content;//상세 정보

	@FXML
	private TextArea member;

	@FXML
	private TextField current;

	@FXML
	private TextField group; //모집 인원

	@FXML
	private Label changebutton;

	@FXML
	private Label nobutton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		changeGroupDao = new ChangeGroupDAO();
		changeGroupDto = new ChangeGroupDTO();
		
	}
	
	
	@FXML
	void cancleProc(MouseEvent event) {
		open.openBackStage();
	}

	@FXML
	void changeProc(MouseEvent event) {
		String newTitle = name.getText();
		String newContent = content.getText();
	
		int newGroup = Integer.parseInt(group.getText());
		
		changeGroupDto.setGroupNum(listMyGroupDto.getGroupNum());
		changeGroupDto.setGroupName(newTitle);
		changeGroupDto.setContent(newContent);
		changeGroupDto.setGroupMember(newGroup);
		changeGroupDao.update(changeGroupDto);
		CommonService.msg("수정 되었습니다.");
		open.openBackStage();

	}
	public void setListMyGroupDto(ListMyGroupDTO listMyGroupDto) {
		this.listMyGroupDto = listMyGroupDto;
		ChangeGroupDTO changeGroupDto = changeGroupDao.selectAll(listMyGroupDto);
		
		number.setText(Integer.toString(changeGroupDto.getGroupNum()));
		id.setText(changeGroupDto.getLeaderId());
		name.setText(changeGroupDto.getGroupName());
		category.setText(changeGroupDto.getCategory());
		content.setText(changeGroupDto.getContent());
		current.setText(Integer.toString(changeGroupDto.getCurrentMember()));
		group.setText(Integer.toString(changeGroupDto.getGroupMember()));
	}

	

}
