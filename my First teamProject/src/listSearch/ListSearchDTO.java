package listSearch;


public class ListSearchDTO {
	private int groupNum;
	private String groupName;
	private String leaderId;
	private	String content;
	private String category;
	private int currentMember;
	private int groupMember;
	//작성자 아이디(leaderId;)// 모임 이름(groupName)// 모집 인원(groupMember)// 현재 인원.(currentMembe)

	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCurrentMember() {
		return currentMember;
	}

	public void setCurrentMember(int currentMember) {
		this.currentMember = currentMember;
	}

	public int getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(int groupMember) {
		this.groupMember = groupMember;
	}

	
	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	
}
