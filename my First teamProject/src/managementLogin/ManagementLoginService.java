package managementLogin;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.CommonService;

public class ManagementLoginService {

	private ManagementLoginDAO dao;
	
	public ManagementLoginService() {
		dao = new ManagementLoginDAO();
	}
	public boolean login(TextField id, PasswordField pw) {
		ManagementLoginDTO dto = dao.check(id,pw);
		if(dto==null) {
			CommonService.msg("존재하지 않는 계정입니다.");
			return true;
		}
		return false;
	}

}
