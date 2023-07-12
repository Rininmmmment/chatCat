package test;

import java.util.List;

import dao.AccountsDAO;
import model.Account;
import model.LoginLogic;
import model.User;

public class AccountsDAOTest {
  public static void main(String[] args) {
	// LoginLogicのテスト
	User testuser = new User("admin", "1234");
	LoginLogic loginLogic = new LoginLogic();
	boolean isLogin = loginLogic.execute(testuser);
	System.out.println(isLogin);
	
	// AccountsDAOのテスト
    testFindAccount();
  }
  public static void testFindAccount() {
    AccountsDAO dao = new AccountsDAO();
    List<Account> accountList = dao.findAll();
    System.out.println(accountList.get(0).getUserid());
    System.out.println(accountList.get(0).getPass());
  }
}