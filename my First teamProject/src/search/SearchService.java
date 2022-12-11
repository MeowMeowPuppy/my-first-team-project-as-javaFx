package search;

import group.GroupDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import listCategory.ListCategoryDTO;
import listMyGroup.ListMyGroupDTO;
import listSearch.ListSearchDAO;
import main.CommonService;
import main.Open;

public class SearchService {
	private ListSearchDAO searchDao;
	private Open open;
	
	public SearchService() {
		searchDao = new ListSearchDAO();
	}
	public void setOpen(Open open) {
		this.open = open;
	}
	
	public TextField searchProc(String category, TextField search) {
		if(search.getText().isEmpty() || category == null) {
			CommonService.msg("검색어를 입력 해주세요.");	
			search.requestFocus();
			return null;
		}
		return search;
	}
	public ObservableList<ListCategoryDTO> selectAll(){
		ObservableList<ListCategoryDTO> result = FXCollections.observableArrayList();
		result = searchDao.selectAll();
		return result;
		
	} 
}
