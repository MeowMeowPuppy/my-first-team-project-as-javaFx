package myGroup;

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

public class MyGroupController implements Initializable {
	private ListMyGroupDTO listMyGroupDto;

	private Open open;

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

	private MyGroupService myGroupService;

	public void setLoginDto(LoginDTO loginDto) {
		this.loginDto = loginDto;
	}

	public void setListMyGroupDto(ListMyGroupDTO listMyGroupDto) {
		this.listMyGroupDto = listMyGroupDto;
		MyGroupDTO list = myGroupService.selectAll(listMyGroupDto);

		myGroupService = new MyGroupService();
		number.setText(Integer.toString(list.getGroupNum()));
		id.setText(list.getLeaderId());
		name.setText(list.getGroupName());
		category.setText(list.getCategory());
		content.setText(list.getContent());
		current.setText(Integer.toString(list.getCurrentMember()));
		group.setText(Integer.toString(list.getGroupMember()));

		String memberId = myGroupService.getMemberId(list.getGroupNum());
		this.memberId.setText(memberId);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myGroupService = new MyGroupService();
	}

	@FXML
	void backProc(MouseEvent event) {
		open.openListMyGroup();
	}

	@FXML
	void changeProc(MouseEvent event) {
		String loginId = loginDto.getId();

		String leaderId = listMyGroupDto.getLeaderId();
		if (loginId.equals(leaderId)) {
			open.openChangeGroup(listMyGroupDto);
		} else {
			CommonService.msg("수정 권한이 없습니다.");
		}
	}

	@FXML
	void deleteProc(MouseEvent event) {
		boolean check = myGroupService.delete(loginDto, listMyGroupDto);
		if (check == false) {
			open.openListMyGroup();
		}

	}

}
