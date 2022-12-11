package myJoinGroup;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import listCategory.ListCategoryDTO;
import listMyGroup.ListMyGroupDTO;
import login.LoginDTO;
import main.CommonService;
import main.Open;

public class MyJoinGroupController implements Initializable {
	// import MyGroupListDTO
	private ListMyGroupDTO listMygroupDto;

	private Open open;
	private ListMyGroupDTO listMyGroupDto;

	public void setOpen(Open open) {
		this.open = open;
	}

	public void mygrouplist() {
		open.openListMyGroup();
	}

	@FXML
	private TextField number;

	@FXML
	private TextField id;

	@FXML
	private TextField category;

	@FXML
	private TextField name;

	@FXML
	private TextArea content;

	@FXML
	private TextArea member;

	@FXML
	private TextField current;

	@FXML
	private TextField group;

	@FXML
	private Label changebutton;

	@FXML
	private Label deletebutton;

	@FXML
	private Label breakawaybutton;

	@FXML
	private Label nobutton;

	@FXML
	private TextArea memberId;

	private LoginDTO loginDto;

	public void setLoginDto(LoginDTO loginDto) {
		this.loginDto = loginDto;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myGroupService = new MyJoinGroupService();
	}

	private MyJoinGroupService myGroupService;

	public void setListMyGroupDto(ListMyGroupDTO listMyGroupDto) {
		this.listMyGroupDto = listMyGroupDto;
		number.setText(Integer.toString(listMyGroupDto.getGroupNum()));
		id.setText(listMyGroupDto.getLeaderId());
		name.setText(listMyGroupDto.getGroupName());
		category.setText(listMyGroupDto.getCategory());
		content.setText(listMyGroupDto.getContent());
		current.setText(Integer.toString(listMyGroupDto.getCurrentMember()));
		group.setText(Integer.toString(listMyGroupDto.getGroupMember()));

		String memberId = myGroupService.getMemberId(listMyGroupDto.getGroupNum());
		this.memberId.setText(memberId);
	}

	@FXML
	void backProc(MouseEvent event) {
		open.openListMyGroup();
	}

	@FXML
	void dropProc(MouseEvent event) {
		boolean check = myGroupService.drop(loginDto, listMyGroupDto);
		if (check == false) {
			open.openListMyGroup();
		}
	}

}
