package findPw;

import javafx.scene.control.TextField;
import main.CommonService;
import regstration.RegstrationDTO;

public class FindPwService {

	private FindPwDAO findPwDao;
	
	public FindPwService() {
		findPwDao = new FindPwDAO();
	}
	public RegstrationDTO check(TextField name, TextField id, TextField phoneNumber) {
		if(name.getText().isEmpty()||id.getText().isEmpty()||phoneNumber.getText().isEmpty()){
			CommonService.msg("공백을 확인해주세요");
			return null;
		}
		RegstrationDTO dto = findPwDao.check(id);
		if(dto==null) {
			CommonService.msg("없는 아이디 입니다.\n확인 후 다시 입력하세요");
			return null;
		}
		if(dto.getName().equals(name.getText())==false) {
			CommonService.msg("회원 정보와 이름이 일치하지 않습니다.\n확인 후 다시 입력하세요");
			return null;
		}
		String strPhoneNumber = findPwDao.organizeNumber(phoneNumber);
		
		if(dto.getPhoneNumber().equals(strPhoneNumber)==false) {
			CommonService.msg("회원 정보와 전화번호가 일치하지 않습니다.\n확인 후 다시 입력하세요");
			return null;
		}
		
		CommonService.msg("비밀번호는 " + dto.getPw() + " 입니다.");
		return dto;
		
		
	}

}
