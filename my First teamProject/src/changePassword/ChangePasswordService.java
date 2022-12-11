package changePassword;

import javafx.scene.control.PasswordField;
import login.LoginDTO;
import main.CommonService;

public class ChangePasswordService {
	
	private ChangePasswordDAO dao;
	
	public ChangePasswordService() {
		dao = new ChangePasswordDAO();
	}
	

	public int check(LoginDTO loginDto, PasswordField currentPw,PasswordField changePw) {
		int check = 0;
		if(currentPw.getText().isEmpty()||changePw.getText().isEmpty()) {
			CommonService.msg("공백을 확인해주세요");
			check += 1;
			return check;
		}
		
		
		// 현재 비밀번호가 입력한 현재 비밀번호와 같은지 확인
		if(loginDto.getPw().equals(currentPw.getText())==false) {
			CommonService.msg("현재 비밀번호가 잘못 입력되었습니다.\n확인 후 다시 입력하세요");
			check += 1;
			return check;
		}
		
		// 현재 비밀번호와 바꾸려는 비밀번호가 같지 않은지 확인
		if(currentPw.getText().equals(changePw.getText())) {
			CommonService.msg("새로운 비밀번호는 이전 비밀번호와 달라야합니다. \n확인 후 다시 입력하세요");
			check += 1;
			return check;
		}
		
		
		return check;
		
	}

	public int checkWithCurrentPw(PasswordField currentPw, PasswordField changePw) {
		int check = 0;

		return check;
	}

	public void change(LoginDTO loginDto, PasswordField changePw) {
		dao.changePw(loginDto.getId(), changePw);
		CommonService.msg("성공적으로 비밀번호가 변경되었습니다. \n다시 로그인 해주세요");
		
	}

}
