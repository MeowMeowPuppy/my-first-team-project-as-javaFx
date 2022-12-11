package showExitReason;

import java.net.URL;
import java.util.ResourceBundle;

import breakAway.BreakAwayDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.CommonService;
import main.Open;

public class ShowExitReasonController implements Initializable {

	@FXML
	private TableView<BreakAwayDTO> table;

	@FXML
	private TableColumn<BreakAwayDTO, String> id;

	@FXML
	private TableColumn<BreakAwayDTO, String> reason;

	@FXML
	private TableColumn<BreakAwayDTO, String> date;

	private Open open;
	private ShowExitReasonDAO dao;

	public void setOpen(Open open) {
		this.open = open;
	}

	public void delete() {
		BreakAwayDTO clickedList = table.getSelectionModel().getSelectedItem();
		dao.delete(clickedList);
		CommonService.msg("삭제되었습니다.");
		open.openExitReason();
	}

	public void cancel() {
		open.openManagement();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new ShowExitReasonDAO();
		ObservableList<BreakAwayDTO> dtos = dao.selectAll();
		table.setItems(dtos);

		id.setCellValueFactory(new PropertyValueFactory<BreakAwayDTO, String>("id"));
		reason.setCellValueFactory(new PropertyValueFactory<BreakAwayDTO, String>("reason"));
		date.setCellValueFactory(new PropertyValueFactory<BreakAwayDTO, String>("date"));

	}

}
