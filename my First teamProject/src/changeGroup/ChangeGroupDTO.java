package changeGroup;

public class ChangeGroupDTO {

	private int groupNum; //모입 번호
	private String leaderId; //작성자 아이디
	private String category; // 카테고리
	private String groupName; // 모임 이름
	private int groupMember;  // 모집 인원
	private String content; // 상세 내용
	private int currentMember; // 현재 인원
	
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getGroupMember() {
		return groupMember;
	}
	public void setGroupMember(int groupMember) {
		this.groupMember = groupMember;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCurrentMember() {
		return currentMember;
	}
	public void setCurrentMember(int currentMember) {
		this.currentMember = currentMember;
	}

}
