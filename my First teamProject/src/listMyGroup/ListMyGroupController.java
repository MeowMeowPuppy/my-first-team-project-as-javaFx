package listMyGroup;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import login.LoginDTO;
import main.Open;

public class ListMyGroupController implements Initializable {
	private Open open;

	public void setOpen(Open open) {
		this.open = open;
	}

	public void openMain() {
		open.openMain();
	}

	public void openSearch() {
		open.openSearch();
	}

	public void openMyGroupList() {
		open.openListMyGroup();
	}

	public void openMore() {
		open.openMore();
	}

	@FXML
	private TableView<ListMyGroupDTO> CreateTable;

	@FXML
	private TableColumn<ListMyGroupDTO, Integer> groupNum;

	@FXML
	private TableColumn<ListMyGroupDTO, String> groupName;

	@FXML
	private TableColumn<ListMyGroupDTO, String> leaderId;

	@FXML
	private TableColumn<ListMyGroupDTO, Integer> currentMember;

	@FXML
	private TableColumn<ListMyGroupDTO, Integer> groupMember;

	@FXML
	private TableView<ListMyGroupDTO> JoinTable;

	@FXML
	private TableColumn<ListMyGroupDTO, Integer> groupNum1;

	@FXML
	private TableColumn<ListMyGroupDTO, String> groupName1;

	@FXML
	private TableColumn<ListMyGroupDTO, String> leaderId1;

	@FXML
	private TableColumn<ListMyGroupDTO, Integer> currentMember1;

	@FXML
	private TableColumn<ListMyGroupDTO, Integer> groupMember1;

	private ListMyGroupService listMyGroupService;

	public ListMyGroupController() {
		new ListMyGroupDAO();
		listMyGroupService = new ListMyGroupService();
	}

	public void setLoginDto(LoginDTO loginDto) {
		listMyGroupService.setLoginDto(loginDto);
		try {
			ObservableList<ListMyGroupDTO> CreateTablelist = listMyGroupService.CreateTableSelect(loginDto);
			CreateTable.setItems(CreateTablelist);

			ObservableList<ListMyGroupDTO> JoinTablelist = listMyGroupService.JoinTableSelect(loginDto);
			JoinTable.setItems(JoinTablelist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		groupNum.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, Integer>("groupNum"));
		groupName.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, String>("groupName"));
		leaderId.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, String>("leaderId"));
		currentMember.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, Integer>("currentMember"));
		groupMember.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, Integer>("groupMember"));

		groupNum1.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, Integer>("groupNum"));
		groupName1.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, String>("groupName"));
		leaderId1.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, String>("leaderId"));
		currentMember1.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, Integer>("currentMember"));
		groupMember1.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, Integer>("groupMember"));

	}

	// TableView 실행과 동시에 게시물이 나오게 한다.
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	// CreateTable 클릭 시
	public void CreateListCliked() {
		ListMyGroupDTO clickedList = CreateTable.getSelectionModel().getSelectedItem();
		if (clickedList == null) {
			return;
		}
		open.openMyGroup(clickedList);

	}

	// JoinTable 클릭 시
	public void JoinListCliked() {
		ListMyGroupDTO clickedList = JoinTable.getSelectionModel().getSelectedItem();
		if (clickedList == null) {
			return;
		}
		open.openMyJoinGroup(clickedList);
	}

}
