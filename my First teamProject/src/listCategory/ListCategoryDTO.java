package listCategory;

public class ListCategoryDTO {
// 추후 모임생성 DTO에서 상속을 받아 카테고리와 모임상세정보DTO를
// 다시 작성할 필요가 있음.
	private int groupNum;
	private String groupName;
	private String leaderId;
	private	String content;
	private String category;
	private int currentMember;
	private int groupMember;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	
	
	public void setContent(String content) {
		this.content = content;
	}

	public void setCurrentMember(int currentMember) {
		this.currentMember = currentMember;
	}

	public void setGroupMember(int groupMember) {
		this.groupMember = groupMember;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getLeaderId() {
		return leaderId;
	}
	
	public String getContent() {
		return content;
	}

	public int getCurrentMember() {
		return currentMember;
	}

	public int getGroupMember() {
		return groupMember;
	}

//	@Override
//	public String toString() {
//		return "ListCategoryDTO{" + 
//				", groupNum=" + groupNum + '\'' +
//				", groupName=" + groupName + '\'' + 
//				", leaderId=" + leaderId + '\'' + 
//				", currentMember=" + currentMember + '\'' + 
//				", groupMember=" + groupMember + '\'' +
//				'}';
//	}

}
