package more;

import login.LoginDAO;
import login.LoginDTO;
import main.CommonService;
import main.Open;

public class MoreController {
	private Open open;
	private LoginDTO loginDto;

	public void setLoginDto(LoginDTO loginDto) {
		this.loginDto = loginDto;
	}

	public void setOpen(Open open) {
		this.open = open;
	}

	// 회원 탈퇴 버튼 클릭시 실행
	public void breakAway() {
		open.openBreakAway();
	}

	// 비밀번호 변경 클릭시 실행
	public void changePw() {
		open.openChangePassword();
	}

	// 로그아웃 클릭시 실행
	public void logout() {
		LoginDAO loginDao = new LoginDAO();
		loginDao.setLogout(loginDto.getId());
		CommonService.msg("로그아웃 되었습니다.");
		open.openLogin();
	}

	// Main 클릭시 실행되는 메서드 (메인화면)
	public void openMain() {
		open.openMain();
	}

	// Search 클릭시 실행되는 메서드
	public void openSearch() {
		open.openSearch();
	}

	// 내 모임 클릭시 실행되는 메서드.
	public void openMyGroupList() {
		open.openListMyGroup();
	}

	// 더보기 눌렀을때 실행되는 메서드
	public void openMore() {
		open.openMore();
	}

	public void openReport() {
		open.openReport();
	}

}
