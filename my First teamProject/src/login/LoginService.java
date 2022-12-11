package login;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.CommonService;
import main.Open;

public class LoginService {
	private LoginDAO loginDao;
	
	public LoginService() {
		loginDao = new LoginDAO();	
	}
	
//	public String getLogin(String id) {
//		return loginDao.getLogin(id); // fx_tmp - login - value
//	}
	
	public void disconnection() {
		loginDao.disconnection();
	}
	
	
	public LoginDTO loginProc(TextField id, PasswordField pw) {
		//빈 데이터
		if(id.getText().isEmpty() || pw.getText().isEmpty()) {
			CommonService.msg("아이디 또는 비밀번호를 입력하세요.");
			return null;
		}
		//테이블 데이터 조회(아이디, 비밀번호)
		LoginDTO loginDto = loginDao.selectId(id.getText());
		if(loginDto == null){
			CommonService.msg("가입되지 않는 계정입니다.\n확인 후 다시 입력하세요.");
			return null;
		}
		
		// 비밀번호 비교
		else if (loginDto.getPw().equals(pw.getText()) == false) {
			CommonService.msg("비밀번호 확인 후 다시 입력하세요.");
			return null;
		} 
		// 현재 로그인 상태 여부 확인
		if (loginDao.selectId(id.getText()).getLogin().equals("y")){
			CommonService.msg("이미 로그인 되어있는 계정입니다.\n확인 후 다시 입력하세요");
			id.clear(); pw.clear();
			return null;
		}
		
		//로그인 성공
		loginDao.setLogin(loginDto);
		if(loginDto.getNotify().equals("n")) {
			CommonService.msg("다른 회원이 회원님을 신고 했습니다.\n"+loginDto.getReportCount()+" 번 신고당하셨습니다.\n10회 누적시 임의로 탈퇴당할 수 있습니다.");
			loginDao.setNotify(loginDto);
		}
		return loginDto;
	}

}
