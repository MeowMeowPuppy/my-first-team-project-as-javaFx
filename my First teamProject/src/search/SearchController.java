package search;

import java.net.URL;
import java.util.ResourceBundle;

import group.GroupDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import listCategory.ListCategoryDTO;
import listMyGroup.ListMyGroupDTO;
import main.Open;

public class SearchController implements Initializable {

	@FXML
	private ComboBox<String> category;

	@FXML
	private TextField search;

	@FXML
	private TableView<ListCategoryDTO> tableView2;

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
	private Button find;

	private SearchService searchSevice;
	private Open open;

	public void setOpen(Open open) {
		this.open = open;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchSevice = new SearchService();
		try {
			ObservableList<ListCategoryDTO> list = searchSevice.selectAll();
			tableView(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		groupNum.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, Integer>("groupNum"));
		groupName.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, String>("groupName"));
		leaderId.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, String>("leaderId"));
		currentMember.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, Integer>("currentMember"));
		groupMember.setCellValueFactory(new PropertyValueFactory<ListMyGroupDTO, Integer>("groupMember"));

		find.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				searchProc();
			}
		});
		
		search.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				searchProc();
			}
		});
		
		category.setOnMouseEntered(event -> {
			category.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		category.setOnMouseExited(event -> {
			category.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		
		find.setOnMouseEntered(event -> {
			find.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		find.setOnMouseExited(event -> {
			find.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});


	}

	@FXML
	private void tableView(ObservableList<ListCategoryDTO> list) throws ClassNotFoundException {
		tableView2.setItems(list);
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

	public void listCliked() {
		ListCategoryDTO clickedList = tableView2.getSelectionModel().getSelectedItem();
		if (clickedList == null) {
			return;
		}
		open.openGroup(clickedList);

	}

	// 검색 버튼 클릭시 동작하는 메서드
	public void searchProc() {
		String selectedCategory = (String) category.getValue();
		String searchText = search.getText();
		if (searchSevice.searchProc(selectedCategory, search) == null) {
			return;
		}

		open.openListSearch(selectedCategory, searchText);
	}
}
