package findId;

import javafx.scene.control.TextField;
import main.CommonService;
import regstration.RegstrationDTO;

public class FindIdService {

	private FindIdDAO findIdDao;

	public FindIdService() {
		findIdDao = new FindIdDAO();
	}

	public RegstrationDTO find(TextField name, TextField phoneNumber) {
		if(name.getText().isEmpty()||phoneNumber.getText().isEmpty()) {
			CommonService.msg("공백을 확인해주세요");
			return null;
		}
		RegstrationDTO check = findIdDao.checkName(name);
		if(check==null) {
			CommonService.msg("가입되지 않은 유저입니다.\n이름을 다시 확인하세요");
			return null;
		}
		String strPhoneNumber = findIdDao.organizeNumber(phoneNumber);
		if(check.getPhoneNumber().equals(strPhoneNumber)==false) {
			CommonService.msg("전화번호가 다릅니다.\n확인 후 다시 입력하세요");
			return null;
		}
		
		CommonService.msg("아이디는 " + check.getId() + " 입니다.");
		return check;
	}
}
