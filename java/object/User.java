package object;
public class User {
	 private int user_id;           // 管理者ID
	 private String name;            // 管理者名
	 private String password;        // パスワード
	 private boolean login_flag;
	 
//	 ゲッターとセッター
	 
	 public int getId() {
		 return user_id;
	 }
	 
	 public void setId(int user_id) {
		 this.user_id = user_id;
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
