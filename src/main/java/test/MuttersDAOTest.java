package test;

import java.util.List;

import model.GetMutter;
import model.GetMutterListLogic;

public class MuttersDAOTest {
  public static void main(String[] args) {
	//	PostMutterLogicのテスト
//	PostMutter testmutter = new PostMutter("admin", "テスト");
//	PostMutterLogic postMutterLogic = new PostMutterLogic();
//	postMutterLogic.execute(testmutter);
	  
	GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
	List<GetMutter> mutterList = getMutterListLogic.execute("home", "admin");
	System.out.println(mutterList.get(0).getImage());
	
	// AccountsDAOのテスト
//    testFindMutters();
  }
  public static void testFindMutters() {
//    HomeMuttersDAO dao = new HomeMuttersDAO();
//    List<GetMutter> mutterList = dao.findAll("admin");
//    System.out.println(mutterList.get(0).getId());
//    System.out.println(mutterList.get(0).getUserid());
//    System.out.println(mutterList.get(0).getDatetime());
//    System.out.println(mutterList.get(0).getText());
  }
}