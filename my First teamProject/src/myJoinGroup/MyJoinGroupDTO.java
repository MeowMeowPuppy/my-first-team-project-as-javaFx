package myJoinGroup;

import javafx.fxml.FXML;

public class MyJoinGroupDTO {

	private int groupNum;
	private String groupName;
	private String leaderId;
	private int currentMember;
	private int groupMember;
	private String category;
	private	String content;
	
	
	public MyJoinGroupDTO(int groupNum, String groupName, String leaderId, String content, String category, int currentMember, int groupMember) {
		this.groupNum = groupNum;
		this.groupName = groupName;
		this.leaderId = leaderId;
		this.content = content;
		this.category = category;
		this.currentMember = currentMember;
		this.groupMember = groupMember;

	}


	public int getGroupNum() {
		return groupNum;
	}


	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
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


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
}


