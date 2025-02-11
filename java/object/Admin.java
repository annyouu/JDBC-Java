package object;

public class Admin {
	 private int admin_id;           // 管理者ID
	 private String name;            // 管理者名
	 private String password;        // パスワード
	 private boolean login_flag;
	 
//	 ゲッターとセッター
	 
	 public int getId() {
		 return admin_id;
	 }
	 
	 public void setId(int admin_id) {
		 this.admin_id = admin_id;
	 }
	 
	 public String getName() {
		 return name;
	 }
	 
	 public void setName(String name) {
		 this.name = name;
	 }
	 
	 public String getPassword() {
		 return password;
	 }
	 
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 
	 public boolean getLogin_flag() {
		 return login_flag;
	 }
	 
	 public void setLogin_flag(boolean login_flag) {
		 this.login_flag = login_flag;
	 }
}



