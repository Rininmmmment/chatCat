package test;

import dao.AccountsDAO;
import model.User;


public class AccountsDAOTest {
  public static void main(String[] args) {
	  AccountsDAO dao = new AccountsDAO();
	  User user = new User("user_cat01", "1234");
	  dao.addEmail(user, "bbb@gmail.com");
	  
	// LoginLogicのテスト
//	User testuser = new User("admin", "1234");
//	LoginLogic loginLogic = new LoginLogic();
//	boolean isLogin = loginLogic.execute(testuser);
//	System.out.println(isLogin);
	
	// AccountsDAOのテスト
//    testFindAccount();
  }
//  public static void testFindAccount() {
//    AccountsDAO dao = new AccountsDAO();
//    List<Account> accountList = dao.findAll();
//    System.out.println(accountList.get(0).getUserid());
//    System.out.println(accountList.get(0).getPass());
//  }
}