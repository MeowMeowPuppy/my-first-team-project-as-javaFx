package breakAway;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.CommonService;

public class BreakAwayService {
	
	private BreakAwayDAO dao;
	
	public BreakAwayService() {
		dao  = new BreakAwayDAO();
	}
	public int breakAway(String id ,PasswordField pw) {
		int check = 0;
		if(pw.getText().isEmpty()) {
			CommonService.msg("비밀번호가 입력되지 않았습니다.\n확인 후 다시 입력하세요");
			check += 1;
			return check;
		}
		
		int count = dao.check(id, pw);
		if (count == 0) {
			CommonService.msg("비밀번호가 다릅니다. \n확인 후 다시 입력하세요");
			check += 1;
			return check;
		}
		
		dao.delete(id);
		CommonService.msg("성공적으로 탈퇴되었습니다.");
		return check;
	}

	public void saveReason(TextField name, TextArea reason) {
		String strName = "";
		if(name.getText().isEmpty()) {
			strName = "Anonymous";
		} else {
			strName = name.getText();
		}
		dao.saveReason(strName,reason);
		
	}
}
