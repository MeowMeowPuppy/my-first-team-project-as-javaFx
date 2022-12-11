package main;

import java.io.IOException;

import breakAway.BreakAwayController;
import breakAway.BreakAwayDAO;
import changeGroup.ChangeGroupController;
import changeGroup.ChangeGroupDAO;
import changePassword.ChangePasswordController;
import changePassword.ChangePasswordDAO;
import createGroup.CreateGroupController;
import createGroup.CreateGroupDAO;
import findId.FindIdController;
import findId.FindIdDAO;
import findPw.FindPwController;
import findPw.FindPwDAO;
import group.GroupController;
import group.GroupDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import listCategory.ListCategoryController;
import listCategory.ListCategoryDAO;
import listCategory.ListCategoryDTO;
import listMyGroup.ListMyGroupController;
import listMyGroup.ListMyGroupDAO;
import listMyGroup.ListMyGroupDTO;
import listSearch.ListSearchController;
import listSearch.ListSearchDAO;
import listSearch.ListSearchDTO;
import login.LoginController;
import login.LoginDAO;
import login.LoginDTO;
import management.ManagementController;
import managementLogin.ManagementLoginController;
import managementLogin.ManagementLoginDAO;
import more.MoreController;
import myGroup.MyGroupController;
import myGroup.MyGroupDAO;
import myJoinGroup.MyJoinGroupController;
import regstration.RegstrationController;
import regstration.RegstrationDAO;
import report.ReportController;
import search.SearchController;
import showExitReason.ShowExitReasonController;
import showExitReason.ShowExitReasonDAO;
import showMember.ShowMemberController;

public class Open {
	private Stage primaryStage;
	private Parent backForm;
	private LoginDTO loginDto;
	private String title;
	private ListMyGroupDTO myGroupDto;
	private BreakAwayDAO breakAwayDao;
	private ChangeGroupDAO changeGroupDao;
	private ChangePasswordDAO changePasswordDao;
	private CreateGroupDAO createGroupDao;
	private GroupDAO groupDao;
	private ListCategoryDAO listcategoryDao;
	private ListMyGroupDAO listMyGroupDao;
	private ListSearchDAO listSearchDao;
	private LoginDAO loginDao;
	private MyGroupDAO myGroupDao;
	private RegstrationDAO regstrationDao;
	private FindIdDAO findIdDao;
	private FindPwDAO findPwDao;
	private ManagementLoginDAO managementLoginDao;
	private ShowExitReasonDAO showExitReasonDao;
	private ListSearchDTO listSearchDto;
	private String value;
	private String tmp;

	public Open() {
		breakAwayDao = new BreakAwayDAO();
		changeGroupDao = new ChangeGroupDAO();
		changePasswordDao = new ChangePasswordDAO();
		createGroupDao = new CreateGroupDAO();
		groupDao = new GroupDAO();
		listcategoryDao = new ListCategoryDAO();
		listMyGroupDao = new ListMyGroupDAO();
		listSearchDao = new ListSearchDAO();
		loginDao = new LoginDAO();
		myGroupDao = new MyGroupDAO();
		regstrationDao = new RegstrationDAO();
		findIdDao = new FindIdDAO();
		findPwDao = new FindPwDAO();
		managementLoginDao = new ManagementLoginDAO();
		showExitReasonDao = new ShowExitReasonDAO();
		listSearchDto = new ListSearchDTO();

	}

	public void setLoginDto(LoginDTO loginDto) {
		this.loginDto = loginDto;
	}

	public LoginDTO getLoginDto() {
		return loginDto;
	}

	public void setForm(Parent form) {
		this.backForm = form;
	}

	public void setPrimaryStage(Stage primaryForm) {
		this.primaryStage = primaryForm;
	}

	// 메인화면을 여는 메서드
	public void openMain() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/MainForm.fxml"));
		try {
			Parent form = loader.load();
			this.backForm = form;
			MainController con = loader.getController();
			con.setOpen(this);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			this.tmp = "메인 화면";
			primaryStage.setTitle("메인 화면");

			Font.loadFont(getClass().getResource("./a오동통OL.ttf").toString(), 16);
			scene.getStylesheets().add("main/application.css");
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// More 창 여는 메서드 (더보기)
	public void openMore() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/more/MoreForm.fxml"));
		try {
			Parent form = loader.load();
			this.backForm = form;
			MoreController con = loader.getController();
			con.setLoginDto(loginDto);
			con.setOpen(this);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);

			scene.getStylesheets().add("main/application.css");
			primaryStage.setTitle("더보기 화면");
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Search 여는 메서드 (검색)
	public void openSearch() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/search/SearchForm.fxml"));
		try {
			Parent form = loader.load();
			this.backForm = form;
			SearchController con = loader.getController();

			@SuppressWarnings("unchecked")
			ComboBox<String> categoryBox = (ComboBox<String>) form.lookup("#category");
			categoryBox.getItems().addAll("전체", "운동", "예술", "오락", "공부/어학", "인맥", "봉사", "요리", "캠핑/차", "자유 주제");
			con.setOpen(this);

			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("검색 화면");
			this.tmp = "검색 화면";

			scene.getStylesheets().add("main/application.css");
			primaryStage.getIcons().add(new Image("/more/img/DAMO__3_-removebg-preview (1).png"));
			this.title = "검색화면";
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// listMyGroup 여는 메서드 (내 모임)
	public void openListMyGroup() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/listMyGroup/ListMyGroupForm.fxml"));
		try {
			Parent form = loader.load();
			ListMyGroupController con = loader.getController();
			con.setLoginDto(loginDto);
			con.setOpen(this);
			this.backForm = form;
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);

			scene.getStylesheets().add("main/application.css");
			primaryStage.setTitle("내 모임 화면");
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 위의 4개 메서드는 화면 하단에 배치되는 기본적인 메서드 버튼임.
	// 하단의 메서드는 각 페이지에서 다른 페이지로 이동할때 사용되는 메서드임.

	// MyGroup여는 메서드 (내모임상세)
	public void openMyGroup(ListMyGroupDTO myGroupDto) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/myGroup/MyGroupForm.fxml"));
		try {
			Parent form = loader.load();
			MyGroupController con = loader.getController();
			con.setOpen(this);
			con.setLoginDto(loginDto);
			con.setListMyGroupDto(myGroupDto);
			this.myGroupDto = myGroupDto;
			Scene scene = new Scene(form);
			this.backForm = form;
			primaryStage.setScene(scene);

			scene.getStylesheets().add("main/application.css");
			tmp = "내 모임 상세 화면";
			primaryStage.setTitle("내 모임 상세 화면");
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

// ListCategory 여는 메서드
	public void openListCategory(String value) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/listCategory/ListCategoryForm.fxml"));
		try {
			Parent form = loader.load();
			ListCategoryController con = loader.getController();
			con.setCategory(value);
			con.setOpen(this);
			this.backForm = form;
			this.value = value;
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("카테고리 화면");
			tmp = "카테고리 화면";
			scene.getStylesheets().add("main/application.css");
			this.title = "카테고리 화면";
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// group 여는 메서드
	public void openGroup(ListCategoryDTO listCategoryDto) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/group/GroupForm.fxml"));

		try {
			Parent form = loader.load();
			GroupController con = loader.getController();
			con.setListcategoryDto(listCategoryDto);
			con.setLoginDto(loginDto);
			con.setOpen(this);
			Scene scene = new Scene(form);

			scene.getStylesheets().add("main/application.css");
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.setTitle("모임 정보");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	 ListSearch여는 메서드 (검색결과 화면)	
	public void openListSearch(String category, String searchText) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/listSearch/ListSearchForm.fxml"));
		try {
			Parent form = loader.load();
			ListSearchController con = loader.getController();
			con.setCategory(category);
			con.setSearchText(searchText);

			con.setOpen(this);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("검색 결과 화면");
			tmp = "검색 결과 화면";
			listSearchDto.setCategory(category);
			listSearchDto.setGroupName(searchText);
			scene.getStylesheets().add("main/application.css");
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ChangeGroup 여는 메서드 (모임 수정)
	public void openChangeGroup(ListMyGroupDTO listMyGroupDto) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/changeGroup/ChangeGroupForm.fxml"));
		try {
			Parent form = loader.load();
			ChangeGroupController con = loader.getController();
			con.setOpen(this);
			con.setListMyGroupDto(listMyGroupDto);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("모임 수정 화면");

			scene.getStylesheets().add("main/application.css");

			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// BreakAway 여는 메서드 (회원탈퇴)
	public void openBreakAway() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/breakAway/BreakAwayForm.fxml"));
		try {
			Parent form = loader.load();
			BreakAwayController con = loader.getController();
			con.setOpen(this);

			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("탈퇴 화면");

			scene.getStylesheets().add("main/application.css");

			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ChangePassword 여는 메서드 (비밀번호 수정)
	public void openChangePassword() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/changePassword/ChangePasswordForm.fxml"));
		try {
			Parent form = loader.load();
			ChangePasswordController con = loader.getController();
			con.setOpen(this);
			con.setLoginDto(loginDto);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("비밀번호 변경 화면");

			scene.getStylesheets().add("main/application.css");

			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Login 여는 메서드 (로그인화면)
	public void openLogin() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/LoginForm.fxml"));
		try {
			Parent form = loader.load();
			LoginController con = loader.getController();
			con.setOpen(this);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("로그인 화면");

			scene.getStylesheets().add("main/application.css");

			primaryStage.setOnCloseRequest(event -> {
				disconnection();
				CommonService.windowsClose(form);
			});

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Regstration 여는 메서드 (회원가입)
	public void openRegstration() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/regstration/RegstrationForm.fxml"));
		try {
			Parent form = loader.load();
			RegstrationController con = loader.getController();
			con.setOpen(this);
			Scene scene = new Scene(form);

			scene.getStylesheets().add("main/application.css");

			primaryStage.setOnCloseRequest(event -> {
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.setScene(scene);
			primaryStage.setTitle("회원 가입 화면");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// MyJoinGroup여는 메서드 (내모임상세)
	public void openMyJoinGroup(ListMyGroupDTO myGroupDto) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/myJoinGroup/MyJoinGroupForm.fxml"));
		try {
			Parent form = loader.load();
			MyJoinGroupController con = loader.getController();
			con.setOpen(this);
			con.setLoginDto(loginDto);
			con.setListMyGroupDto(myGroupDto);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);

			scene.getStylesheets().add("main/application.css");

			primaryStage.setTitle("내 참여 모임 상세 화면");
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// CreateGroup여눈 메서드 (모임생성 화면)
	public void openCreateGroup() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/createGroup/CreateGroupForm.fxml"));
		try {
			Parent form = loader.load();
			CreateGroupController con = loader.getController();
			ComboBox<String> categoryBox = (ComboBox<String>) form.lookup("#category");
			categoryBox.getItems().addAll("운동", "예술", "오락", "공부/어학", "인맥", "봉사", "요리", "캠핑/차", "자유 주제");

			con.setOpen(this);

			con.setLoginDto(loginDto);
			Scene scene = new Scene(form);
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				CommonService.windowsClose(form);
			});

			scene.getStylesheets().add("main/application.css");

			primaryStage.setTitle("모임 생성 화면");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 아이디 찾기 화면 여는 메서드
	public void openFindId() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/findId/FindIdForm.fxml"));
		try {
			Parent form = loader.load();
			FindIdController con = loader.getController();
			con.setOpen(this);

			Scene scene = new Scene(form);

			scene.getStylesheets().add("main/application.css");

			primaryStage.setOnCloseRequest(event -> {
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.setTitle("아이디 찾기");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 비밀번호 찾기 화면 여는 메서드
	public void openFindPw() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/findPw/FindPwForm.fxml"));
		try {
			Parent form = loader.load();
			FindPwController con = loader.getController();
			con.setOpen(this);

			Scene scene = new Scene(form);

			scene.getStylesheets().add("main/application.css");

			primaryStage.setOnCloseRequest(event -> {
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.setTitle("비밀번호 찾기");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 신고하기 여는 메서드
	public void openReport() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/report/ReportForm.fxml"));
		try {
			Parent form = loader.load();
			ReportController con = loader.getController();
			con.setOpen(this);

			Scene scene = new Scene(form);

			scene.getStylesheets().add("main/application.css");

			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				disconnection();
				CommonService.windowsClose(form);
			});
			primaryStage.setTitle("신고하기");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 관리자모드 로그인 화면
	public void openManagementLogin() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/managementLogin/ManagementLoginForm.fxml"));
		try {
			Parent form = loader.load();
			ManagementLoginController con = loader.getController();
			con.setOpen(this);

			Scene scene = new Scene(form);

			if ((loginDto == null) == false) {
				primaryStage.setOnCloseRequest(event -> {
					disconnection();
					CommonService.windowsClose(form);
				});
			}
			primaryStage.setTitle("관리자 로그인");
			primaryStage.setX(460);
			primaryStage.setY(240);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 관리자모드 화면
	public void openManagement() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/ManagementForm.fxml"));
		try {
			Parent form = loader.load();
			ManagementController con = loader.getController();
			con.setOpen(this);

			Scene scene = new Scene(form);

			if ((loginDto == null) == false) {
				primaryStage.setOnCloseRequest(event -> {
					disconnection();
					CommonService.windowsClose(form);
				});
			}
			primaryStage.setTitle("관리자 모드");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 정보 조회
	public void openShowMember() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/showMember/ShowMemberForm.fxml"));
		try {
			Parent form = loader.load();
			ShowMemberController con = loader.getController();
			con.setOpen(this);
			Scene scene = new Scene(form);

			if ((loginDto == null) == false) {
				primaryStage.setOnCloseRequest(event -> {
					disconnection();
					CommonService.windowsClose(form);
				});
			}
			primaryStage.setTitle("회원 정보 조회");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 탈퇴 사유 조회
	public void openExitReason() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/showExitReason/ShowExitReason.fxml"));
		try {
			Parent form = loader.load();
			ShowExitReasonController con = loader.getController();
			con.setOpen(this);
			Scene scene = new Scene(form);

			if ((loginDto == null) == false) {
				primaryStage.setOnCloseRequest(event -> {
					disconnection();
					CommonService.windowsClose(form);
				});
			}
			primaryStage.setTitle("탈퇴 사유 조회");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 데이터 베이스 전체 종료하는 메서드
	public void disconnection() {
		listcategoryDao.disconnection();
		breakAwayDao.disconnection();
		changeGroupDao.disconnection();
		changePasswordDao.disconnection();
		createGroupDao.disconnection();
		groupDao.disconnection();
		listMyGroupDao.disconnection();
		listSearchDao.disconnection();
		loginDao.disconnection();
		myGroupDao.disconnection();
		regstrationDao.disconnection();
		findIdDao.disconnectrion();
		findPwDao.disconnection();
		managementLoginDao.disconnection();
		showExitReasonDao.disconnection();
	}

	// 이전 페이지를 여는 메서드
	public void openBackStage() {
		// 이전 페이지의 form을 받아서 Scene에 넣고 이걸 primaryStage에 넣어서 출력
		if (this.tmp.equals("카테고리창")) {
			openListCategory(value);
			return;
		}
		if (this.tmp.equals("메인 화면")) {
			openMain();
			return;
		}
		if (this.tmp.equals("내 모임 상세 화면")) {
			openMyGroup(myGroupDto);
			return;
		}
		if (this.tmp.equals("검색 화면")) {
			openSearch();
			return;
		}
		if (tmp.equals("검색 결과 화면")) {
			openListSearch(listSearchDto.getCategory(), listSearchDto.getGroupName());
			return;
		}
		if (tmp.equals("카테고리 화면")) {
			openListCategory(value);
			return;
		}
		

		Scene scene = backForm.getScene();
		primaryStage.setScene(scene);
		primaryStage.setTitle(title);
		primaryStage.setOnCloseRequest(event -> {
			loginDao.setLogout(loginDto.getId());
			CommonService.windowsClose(backForm);
		});
		primaryStage.show();
	}
}
