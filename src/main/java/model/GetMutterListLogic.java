package model;

import java.util.ArrayList;
import java.util.List;

import dao.MuttersDAO;

public class GetMutterListLogic {
	// bbs_typeに応じた投稿リストを取得
	public List<GetMutter> execute(String bbs_type, String user_account) {
		List<GetMutter> mutterList = new ArrayList<GetMutter>();
		MuttersDAO mutters_dao = new MuttersDAO();
		mutterList = mutters_dao.findAll(user_account, bbs_type+"_MUTTERS");
//		switch(bbs_type){
//			case "home":
//				HomeMuttersDAO home_dao = new HomeMuttersDAO();
//				mutterList = home_dao.findAll(user_account);
//				break;
//			case "matatabi":
//				MatatabiMuttersDAO matatabi_dao = new MatatabiMuttersDAO();
//			    mutterList = matatabi_dao.findAll(user_account);
//			    break;
//			case "clinic":
//				ClinicMuttersDAO clinic_dao = new ClinicMuttersDAO();
//			    mutterList = clinic_dao.findAll(user_account);
//			    break;
//			case "restaurant":
//				RestaurantMuttersDAO restaurant_dao = new RestaurantMuttersDAO();
//			    mutterList = restaurant_dao.findAll(user_account);
//			    break;
//			case "school":
//				SchoolMuttersDAO school_dao = new SchoolMuttersDAO();
//			    mutterList = school_dao.findAll(user_account);
//			    break;
//		}
		return mutterList;
	}
	
//	public static void main(String[] args) {
//		System.out.println(execute("home").get(0).getText());
//	}
}