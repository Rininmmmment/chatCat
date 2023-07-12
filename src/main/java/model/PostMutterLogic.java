package model;

import dao.MuttersDAO;


public class PostMutterLogic {
    public void execute(PostMutter mutter, String user_account, String bbs_type) { 
	    MuttersDAO mutters_dao = new MuttersDAO();
	    mutters_dao.create(mutter, user_account, bbs_type+"_MUTTERS");
    }
}