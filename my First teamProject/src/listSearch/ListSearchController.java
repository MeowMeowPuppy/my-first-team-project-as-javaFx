package listSearch;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import listCategory.ListCategoryDTO;
import main.Open;

public class ListSearchController implements Initializable {
	
	private ListSearchDAO listSearchDao;
	private Open open;
	private String category;
	@FXML
    private TableView<ListCategoryDTO> searchTableView;

    @FXML
    private TableColumn<ListCategoryDTO, Integer> groupNum;

    @FXML
    private TableColumn<ListCategoryDTO, String> groupName;

    @FXML
    private TableColumn<ListCategoryDTO, String> leaderId;

    @FXML
    private TableColumn<ListCategoryDTO, Integer> currentMember;

    @FXML
    private TableColumn<ListCategoryDTO, Integer> groupMember;
	
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setSearchText(String searchText) {
		listSearchDao.setCategory(category);
		listSearchDao.setSearchText(searchText);
		
		try {
			ObservableList<ListCategoryDTO> list = listSearchDao.searchSelect();
			searchTableView.setItems(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		groupNum.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, Integer>("groupNum"));
		groupName.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, String>("groupName"));
		leaderId.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, String>("leaderId"));
		currentMember.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, Integer>("currentMember"));
		groupMember.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, Integer>("groupMember"));
		
	}	

	public void listSearchCliked() {
		ListCategoryDTO clickedList = searchTableView.getSelectionModel().getSelectedItem();
		if(clickedList==null) {
			return;
		}
		open.openGroup(clickedList);
	}
	
	public void setOpen(Open open) {
		this.open = open;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listSearchDao = new ListSearchDAO();

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

}
