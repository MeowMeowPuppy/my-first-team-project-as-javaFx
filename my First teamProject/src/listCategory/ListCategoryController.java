package listCategory;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Open;



public class ListCategoryController implements Initializable{

private ListCategoryDAO categoryDao;
private Open open;

public ListCategoryController() {
	categoryDao = new ListCategoryDAO();
}

public void setOpen(Open open) {
	this.open = open;
}

	

    @FXML
    private TableView<ListCategoryDTO> tableView1;

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
    
    @FXML
    private TextField number;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextArea content;
    @FXML
    private TextField current;
    @FXML
    private TextField group;
    
    private String category;
    
    public void setCategory(String category) {
		this.category = category;
		
		categoryDao.setCategory(category);
		
		try {
			ObservableList<ListCategoryDTO> list = categoryDao.selectAll();
			tableView(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		groupNum.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, Integer>("groupNum"));
		groupName.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, String>("groupName"));
		leaderId.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, String>("leaderId"));
		currentMember.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, Integer>("currentMember"));
		groupMember.setCellValueFactory(new PropertyValueFactory<ListCategoryDTO, Integer>("groupMember"));
		
	}
    
    @FXML
    private void tableView (ObservableList<ListCategoryDTO> list) throws ClassNotFoundException{
    	tableView1.setItems(list);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void listCliked(MouseEvent event) {
		
		ListCategoryDTO clickedList = tableView1.getSelectionModel().getSelectedItem();
		categoryDao.setCategory(category);
		if(clickedList==null) {
			return;
		}
		open.openGroup(clickedList);
	}
	
	public void openMain() {
		open.openMain();
	}
	public void openSearch() {
		open.openSearch();
	}
	public void openMore() {
		open.openMore();
	}
	public void openMyGroupList() {
		open.openListMyGroup();
	}
	
	public void openCreateGroup() {
		open.openCreateGroup();
	}
	

}
