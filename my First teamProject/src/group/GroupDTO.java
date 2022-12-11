package group;

public class GroupDTO {

	private int groupNum;
	private String groupName;
	private String leaderId;
	private	String content;
	private String cartegory;
	private int currentMember;
	private int groupMember;
	

	public String getCategory() {
		return cartegory;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

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

	public int getCurrentMember() {
		return currentMember;
	}

	public void setCurrentMember(int currentMember) {
		this.currentMember = currentMember;
	}

	public int getGroupMember() {
		return groupMember;
	}
	
	public void setCategory(String cartegory) {
		this.cartegory = cartegory;
	}

	public void setGroupMember(int groupMember) {
		this.groupMember = groupMember;
	}
	
	

}
