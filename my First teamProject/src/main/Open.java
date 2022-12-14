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

	// ??????????????? ?????? ?????????
	public void openMain() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/MainForm.fxml"));
		try {
			Parent form = loader.load();
			this.backForm = form;
			MainController con = loader.getController();
			con.setOpen(this);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			this.tmp = "?????? ??????";
			primaryStage.setTitle("?????? ??????");

			Font.loadFont(getClass().getResource("./a?????????OL.ttf").toString(), 16);
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

	// More ??? ?????? ????????? (?????????)
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
			primaryStage.setTitle("????????? ??????");
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

	// Search ?????? ????????? (??????)
	public void openSearch() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/search/SearchForm.fxml"));
		try {
			Parent form = loader.load();
			this.backForm = form;
			SearchController con = loader.getController();

			@SuppressWarnings("unchecked")
			ComboBox<String> categoryBox = (ComboBox<String>) form.lookup("#category");
			categoryBox.getItems().addAll("??????", "??????", "??????", "??????", "??????/??????", "??????", "??????", "??????", "??????/???", "?????? ??????");
			con.setOpen(this);

			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("?????? ??????");
			this.tmp = "?????? ??????";

			scene.getStylesheets().add("main/application.css");
			primaryStage.getIcons().add(new Image("/more/img/DAMO__3_-removebg-preview (1).png"));
			this.title = "????????????";
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

	// listMyGroup ?????? ????????? (??? ??????)
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
			primaryStage.setTitle("??? ?????? ??????");
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

	// ?????? 4??? ???????????? ?????? ????????? ???????????? ???????????? ????????? ?????????.
	// ????????? ???????????? ??? ??????????????? ?????? ???????????? ???????????? ???????????? ????????????.

	// MyGroup?????? ????????? (???????????????)
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
			tmp = "??? ?????? ?????? ??????";
			primaryStage.setTitle("??? ?????? ?????? ??????");
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

// ListCategory ?????? ?????????
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
			primaryStage.setTitle("???????????? ??????");
			tmp = "???????????? ??????";
			scene.getStylesheets().add("main/application.css");
			this.title = "???????????? ??????";
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

	// group ?????? ?????????
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
			primaryStage.setTitle("?????? ??????");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	 ListSearch?????? ????????? (???????????? ??????)	
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
			primaryStage.setTitle("?????? ?????? ??????");
			tmp = "?????? ?????? ??????";
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

	// ChangeGroup ?????? ????????? (?????? ??????)
	public void openChangeGroup(ListMyGroupDTO listMyGroupDto) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/changeGroup/ChangeGroupForm.fxml"));
		try {
			Parent form = loader.load();
			ChangeGroupController con = loader.getController();
			con.setOpen(this);
			con.setListMyGroupDto(listMyGroupDto);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("?????? ?????? ??????");

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

	// BreakAway ?????? ????????? (????????????)
	public void openBreakAway() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/breakAway/BreakAwayForm.fxml"));
		try {
			Parent form = loader.load();
			BreakAwayController con = loader.getController();
			con.setOpen(this);

			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("?????? ??????");

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

	// ChangePassword ?????? ????????? (???????????? ??????)
	public void openChangePassword() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/changePassword/ChangePasswordForm.fxml"));
		try {
			Parent form = loader.load();
			ChangePasswordController con = loader.getController();
			con.setOpen(this);
			con.setLoginDto(loginDto);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("???????????? ?????? ??????");

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

	// Login ?????? ????????? (???????????????)
	public void openLogin() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/LoginForm.fxml"));
		try {
			Parent form = loader.load();
			LoginController con = loader.getController();
			con.setOpen(this);
			Scene scene = new Scene(form);
			primaryStage.setScene(scene);
			primaryStage.setTitle("????????? ??????");

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

	// Regstration ?????? ????????? (????????????)
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
			primaryStage.setTitle("?????? ?????? ??????");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// MyJoinGroup?????? ????????? (???????????????)
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

			primaryStage.setTitle("??? ?????? ?????? ?????? ??????");
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

	// CreateGroup?????? ????????? (???????????? ??????)
	public void openCreateGroup() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/createGroup/CreateGroupForm.fxml"));
		try {
			Parent form = loader.load();
			CreateGroupController con = loader.getController();
			ComboBox<String> categoryBox = (ComboBox<String>) form.lookup("#category");
			categoryBox.getItems().addAll("??????", "??????", "??????", "??????/??????", "??????", "??????", "??????", "??????/???", "?????? ??????");

			con.setOpen(this);

			con.setLoginDto(loginDto);
			Scene scene = new Scene(form);
			primaryStage.setOnCloseRequest(event -> {
				loginDao.setLogout(loginDto.getId());
				CommonService.windowsClose(form);
			});

			scene.getStylesheets().add("main/application.css");

			primaryStage.setTitle("?????? ?????? ??????");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ????????? ?????? ?????? ?????? ?????????
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
			primaryStage.setTitle("????????? ??????");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ???????????? ?????? ?????? ?????? ?????????
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
			primaryStage.setTitle("???????????? ??????");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ???????????? ?????? ?????????
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
			primaryStage.setTitle("????????????");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ??????????????? ????????? ??????
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
			primaryStage.setTitle("????????? ?????????");
			primaryStage.setX(460);
			primaryStage.setY(240);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ??????????????? ??????
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
			primaryStage.setTitle("????????? ??????");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ?????? ?????? ??????
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
			primaryStage.setTitle("?????? ?????? ??????");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ?????? ?????? ??????
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
			primaryStage.setTitle("?????? ?????? ??????");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ????????? ????????? ?????? ???????????? ?????????
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

	// ?????? ???????????? ?????? ?????????
	public void openBackStage() {
		// ?????? ???????????? form??? ????????? Scene??? ?????? ?????? primaryStage??? ????????? ??????
		if (this.tmp.equals("???????????????")) {
			openListCategory(value);
			return;
		}
		if (this.tmp.equals("?????? ??????")) {
			openMain();
			return;
		}
		if (this.tmp.equals("??? ?????? ?????? ??????")) {
			openMyGroup(myGroupDto);
			return;
		}
		if (this.tmp.equals("?????? ??????")) {
			openSearch();
			return;
		}
		if (tmp.equals("?????? ?????? ??????")) {
			openListSearch(listSearchDto.getCategory(), listSearchDto.getGroupName());
			return;
		}
		if (tmp.equals("???????????? ??????")) {
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
