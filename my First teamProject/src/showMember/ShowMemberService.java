package showMember;

import javafx.collections.ObservableList;
import main.CommonService;
import regstration.RegstrationDTO;

public class ShowMemberService {

	private ShowMemberDAO dao;
	
	public ShowMemberService() {
		dao = new ShowMemberDAO();
		
	}
	public ObservableList<RegstrationDTO> getMember() {
		ObservableList<RegstrationDTO> dtos = dao.getMember();
		return dtos;
	}
	public void block(RegstrationDTO clickedList) {
		dao.block(clickedList);
		CommonService.msg("삭제되었습니다.");
		
	}

}
