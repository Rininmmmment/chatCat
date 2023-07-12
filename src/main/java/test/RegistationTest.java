package test;

import model.Account;
import model.RegistationLogic;

public class RegistationTest {
  public static void main(String[] args) {
	// AccountsDAOに問題はなし
	Account testaccount = new Account("test2", "1234");
//	AccountsDAO dao = new AccountsDAO();
//	System.out.println(dao.create(testaccount));
	
	// RegistationLogicに問題はなし
	RegistationLogic registationLogic = new RegistationLogic();
	registationLogic.execute(testaccount);

  }
//  public static void testFindAccount() {
//    AccountsDAO dao = new AccountsDAO();
//    List<Account> accountList = dao.findAll();
//    System.out.println(accountList.get(0).getUserid());
//    System.out.println(accountList.get(0).getPass());
//  }
}