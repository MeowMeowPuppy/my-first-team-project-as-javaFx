package report;

import javafx.scene.control.TextField;
import main.CommonService;

public class ReportService {

	private ReportDAO reportDao;
	
	public ReportService() {
		reportDao = new ReportDAO();
	}
	public void report(TextField reportId) {
		if(reportDao.check(reportId)) {
			CommonService.msg("없는 ID입니다.\n확인 후 다시 입력하세요");
			return;
		}
		reportDao.report(reportId);
		CommonService.msg("신고되었습니다.");
		reportId.clear();
	}

}
