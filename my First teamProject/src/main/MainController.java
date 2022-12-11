package main;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainController {
	@FXML
	private ImageView sport;
	@FXML
	private ImageView art;
	@FXML
	private ImageView game;
	@FXML
	private ImageView study;
	@FXML
	private ImageView relationship;
	@FXML
	private ImageView voluteer;
	@FXML
	private ImageView cook;
	@FXML
	private ImageView camp;
	@FXML
	private ImageView free;

	private Open open;

	public void setOpen(Open open) {
		this.open = open;
	}
	
	// 검색창 여는 메서드
	public void openSearch() {
		open.openSearch();
	}
	
	// 내모임창 여는 메서드
	public void openMyGroupList() {
		open.openListMyGroup();
	}
	
	// 더보기창 여는 메서드
	public void openMore() {
		open.openMore();
	}
	
	// 운동 버튼 클릭시
	public void openListCategorySport() {
		open.openListCategory("운동");
	}
	
	// 예술 버튼 클릭시
	public void openListCategoryArt() {
		open.openListCategory("예술");
	}
	
	// 오락 버튼 클릭시
	public void openListCategoryGame() {
		open.openListCategory("오락");
	}
	
	// 공부/어학 버튼 클릭시
	public void openListCategoryStudy() {
		open.openListCategory("공부/어학");
	}
	
	// 인맥 버튼 클릭시
	public void openListCategoryRelationship() {
		open.openListCategory("인맥");
	}
	
	// 봉사 버튼 클릭시
	public void openListCategoryVoluteer() {
		open.openListCategory("봉사");
	}
	
	// 요리 버튼 클릭시
	public void openListCategoryCook() {
		open.openListCategory("요리");
	}
	
	// 캠핑/차
	public void openListCategoryCamp() {
		open.openListCategory("캠핑/차");
	}
	public void openListCategoryFree() {
		open.openListCategory("자유 주제");
	}
	public void openCreateGroup() {
		open.openCreateGroup();
	}
}
