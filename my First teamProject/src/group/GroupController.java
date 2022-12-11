package group;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import listCategory.ListCategoryDTO;
import login.LoginDTO;
import main.Open;

public class GroupController implements Initializable {
	private ListCategoryDTO listcategoryDto;

	@FXML
	private TextField number;

	@FXML
	private TextField id;

	@FXML
	private TextField name;

	@FXML
	private TextField category;

	@FXML
	private TextArea content;

	@FXML
	private TextField current;

	@FXML
	private TextField group;

	@FXML
	private Label yesbutton;

	@FXML
	private Label nobutton;
	
	@FXML
	private TextArea memberId;

	private Open open;

	private LoginDTO loginDto;
	
	private GroupService groupService;

	public void setLoginDto(LoginDTO loginDto) {
		this.loginDto = loginDto;
	}

	public void setOpen(Open open) {
		this.open = open;
	}

	public void initialize(URL location, ResourceBundle resources) {
		groupService = new GroupService();
	}

	public void setListcategoryDto(ListCategoryDTO listcategoryDto) {
		this.listcategoryDto = listcategoryDto;
		ListCategoryDTO groupDto = groupService.selectAll(listcategoryDto);
		this.listcategoryDto = groupDto;
		
		number.setText(Integer.toString(groupDto.getGroupNum()));
		id.setText(groupDto.getLeaderId());
		name.setText(groupDto.getGroupName());
		category.setText(groupDto.getCategory());
		content.setText(groupDto.getContent());
		current.setText(Integer.toString(groupDto.getCurrentMember()));
		group.setText(Integer.toString(groupDto.getGroupMember()));
		
		String id = groupService.getMemberId(listcategoryDto.getGroupNum());
		memberId.setText(id);
	}

	public void nobutton(MouseEvent event) {
		open.openBackStage();
	}

	public void yesbutton(MouseEvent event) {
		GroupService groupService = new GroupService();
		int groupNum = listcategoryDto.getGroupNum();
		groupService.setGroupNum(groupNum);
		groupService.insert(loginDto, listcategoryDto);
		open.openGroup(listcategoryDto);
	}

}
