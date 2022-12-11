package login;

/*
 * SQL> CREATE TABLE damo_member(
  2  id varchar2(20),
  3  pw varchar2(20),
  4  name varchar2(15),
  5  phonNum varchar2(30),
  6  gender varchar2(30)
  7  );

Table created.

SQL> SELECT * FROM damo_member;
 */
public class LoginDTO {
	private String id;
	private String pw;
	private String login;
	private String notify;
	private int reportCount;

	int getReportCount() {
		return reportCount;
	}

	void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
