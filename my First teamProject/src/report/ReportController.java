package report;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import main.CommonService;
import main.Open;

public class ReportController implements Initializable{

	@FXML private TextField reportId;
	@FXML private Button report;
	@FXML private Button cancel;
	@FXML private ComboBox<String> reason;
	
	private Open open;
	private ReportService reportService;

	public void setOpen(Open open) {
		this.open = open;
	}
	
	public void report() {
		if(reportId.getText().equals(open.getLoginDto().getId())) {
			CommonService.msg("자기 자신을 신고할 수는 없습니다.");
			return;
		}
		reportService.report(reportId);
	}
	public void cancel() {
		open.openMore();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reportService = new ReportService();
		
		report.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				report();
			}
		});
		cancel.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				cancel();
			}
		});
		
		report.setOnMouseEntered(event -> {
			report.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		report.setOnMouseExited(event -> {
			report.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		cancel.setOnMouseEntered(event -> {
			cancel.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		cancel.setOnMouseExited(event -> {
			cancel.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		
		
		
		
	}

}
