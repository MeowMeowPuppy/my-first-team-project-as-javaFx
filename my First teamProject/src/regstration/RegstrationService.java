package regstration;

import breakAway.BreakAwayService;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.CommonService;

public class RegstrationService {

	private RegstrationDAO regstrationDao;

	public RegstrationService() {
		regstrationDao = new RegstrationDAO();
//		BreakAwayService breakAwayService = new BreakAwayService(regstrationDao);
	}

	
	// 입력받은 전화번호를 정리
	public String organizedNum(TextField phoneNumber) {
		String organizedNum = regstrationDao.organizeNumber(phoneNumber);
		return organizedNum;
		
	}
	// 비밀번호 체크여부 확인, 중복 아이디 여부 확인, 빈 데이터 여부 확인
	public boolean check(TextField id, PasswordField pw, PasswordField confirm, TextField name, TextField phoneNumber,
			RadioButton male, RadioButton female) {
		if(id.getText().isEmpty()||pw.getText().isEmpty()||confirm.getText().isEmpty()||name.getText().isEmpty()||phoneNumber.getText().isEmpty()) {
			CommonService.msg("공백을 확인해주세요");
			return true;
		}
		boolean check = false;

		String gender = null;
		if (male.isSelected()) {
			gender = "남자";
		} else if (female.isSelected()) {
			gender = "여자";
		}
		
		// 아이디 길이 체크
		if(id.getText().length()<4) {
			CommonService.msg("아이디 길이는 3자 이하로 할 수 없습니다.");
			check = true;
			return check;
		}
		
		// 비밀번호 길이 체크
		if(pw.getText().length()<4) {
			CommonService.msg("비밀번호 길이는 3자 이하로 할 수 없습니다");
			check = true;
			return check;
		}
		
		
		// 빈 데이터 여부 확인
		if (id.getText().isEmpty() || pw.getText().isEmpty() || phoneNumber.getText().isEmpty()
				|| name.getText().isEmpty() || confirm.getText().isEmpty()) {
			CommonService.msg("빈 입력창을 확인해주세요");
			check = true;
			return check;
		}

		// 성별 체크 여부 확인
		if (gender == null) {
			CommonService.msg("성별을 체크해주세요");
			check = true;
			return check;
		}

		// 비밀번호 확인
		if (pw.getText().equals(confirm.getText()) == false) {
			CommonService.msg("비밀번호 확인이 잘못되었습니다. \n확인 후 다시 입력하세요");
			check = true;
			return check;
		}
		
		// 전화번호 정렬
		String organizedNum = regstrationDao.organizeNumber(phoneNumber);
		if(organizedNum==null) {
			CommonService.msg("전화번호 형식을 확인해주세요");
			check = true;
			return check;
		}

		// 아이디 중복 여부 확인
		int count = regstrationDao.check(id);
		if (count == 1) {
			CommonService.msg("이미 등록된 아이디입니다.");
			check = true;
			return check;
		}
		
		
		
		

		return check;
	}

	// DB에 저장하는 메서드
	public void insert(TextField idTf, PasswordField pwTf, PasswordField confirmTf, TextField nameTf,
			TextField phoneNumberTf, RadioButton maleTf, RadioButton femaleTf) {
		String id = idTf.getText();
		String pw = pwTf.getText();
		String confirm = confirmTf.getText();
		String name = nameTf.getText();
		String PhoneNumber = phoneNumberTf.getText();

		String gender = null;
		if (maleTf.isSelected()) {
			gender = "남자";
		} else if (femaleTf.isSelected()) {
			gender = "여자";
		}

		RegstrationDTO regstrationDto = new RegstrationDTO();
		regstrationDto.setId(id);
		regstrationDto.setPw(pw);
		regstrationDto.setName(name);
		regstrationDto.setPhoneNumber(PhoneNumber);
		regstrationDto.setGender(gender);

		regstrationDao.insert(regstrationDto);
		CommonService.msg("성공적으로 가입 되었습니다!");
		idTf.clear();
		confirmTf.clear();
		nameTf.clear();
		maleTf.setSelected(false);
		femaleTf.setSelected(false);
		pwTf.clear();
		phoneNumberTf.clear();

	}

}
