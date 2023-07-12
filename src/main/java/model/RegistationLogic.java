package model;

import dao.AccountsDAO;

public class RegistationLogic {
	 public void execute(Account account) { 
		 AccountsDAO dao = new AccountsDAO();
		 dao.create(account);
	 }
}
