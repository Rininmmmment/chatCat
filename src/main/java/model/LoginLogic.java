package model;

import java.util.List;

import dao.AccountsDAO;

public class LoginLogic {
	public boolean execute(User user) {
		// 登録されているアカウント情報を取得
	    AccountsDAO dao = new AccountsDAO();
	    List<Account> accountList = dao.findAll();
	    
	    // Userのuseridを持ったアカウントとpassが一致していればTrueを返す
	    for (int i = 0; i < accountList.size(); i++) {
			if (accountList.get(i).getUserid().equals(user.getUserid()))
				if (user.getPass().equals(accountList.get(i).getPass())) { return true; }
		}
	    return false;
	}
}