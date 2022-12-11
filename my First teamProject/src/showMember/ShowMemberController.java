package showMember;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Open;
import regstration.RegstrationDTO;

public class ShowMemberController implements Initializable {

	@FXML
	private TableView<RegstrationDTO> table;

	@FXML
	private TableColumn<RegstrationDTO, String> id;

	@FXML
	private TableColumn<RegstrationDTO, String> pw;

	@FXML
	private TableColumn<RegstrationDTO, String> name;

	@FXML
	private TableColumn<RegstrationDTO, String> gender;

	@FXML
	private TableColumn<RegstrationDTO, String> phoneNumber;

	@FXML
	private TableColumn<RegstrationDTO, Integer> report;

	private Open open;

	private ShowMemberService service;

	public void setOpen(Open open) {
		this.open = open;
	}

	public void block() {
		RegstrationDTO clickedList = table.getSelectionModel().getSelectedItem();
		if(clickedList == null) {
			return;
		}
		service.block(clickedList);
		open.openShowMember();
	}

	public void cancel() {
		open.openManagement();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = new ShowMemberService();

		try {
			ObservableList<RegstrationDTO> dtos = service.getMember();
			table.setItems(dtos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		id.setCellValueFactory((new PropertyValueFactory<RegstrationDTO, String>("id")));
		pw.setCellValueFactory((new PropertyValueFactory<RegstrationDTO, String>("pw")));
		name.setCellValueFactory((new PropertyValueFactory<RegstrationDTO, String>("name")));
		gender.setCellValueFactory((new PropertyValueFactory<RegstrationDTO, String>("gender")));
		phoneNumber.setCellValueFactory((new PropertyValueFactory<RegstrationDTO, String>("phoneNumber")));
		report.setCellValueFactory((new PropertyValueFactory<RegstrationDTO, Integer>("report")));

	}
	

}
