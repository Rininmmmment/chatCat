package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.GetMutter;
import model.PostMutter;

public class MuttersDAO {
	// データベース接続に使用する情報
	//  private final String JDBC_URL = "jdbc:h2:tcp://my-h2/my-db-name";
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/chatCat";
	private final String DB_USER = "";
	private final String DB_PASS = "";

	public List<GetMutter> findAll(String user_account, String mutter_list) {
	    List<GetMutter> MutterList = new ArrayList<GetMutter>();
	    // JDBCドライバを読み込む
	    try {
	        Class.forName("org.h2.Driver");
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	    }
	    // データベース接続
	    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	
	      // SELECT文の準備
	      String sql = "SELECT ID, "+ mutter_list + ".USER_ID, DATETIME, TEXT, IMAGE "
	      		+ "FROM " + mutter_list + " "
	      		+ "LEFT OUTER JOIN ACCOUNTS ON " + mutter_list + ".USER_ID = ACCOUNTS.USER_ID "
	      		+ "WHERE USER_ACCOUNT = '" + user_account + "' "
	      		+ "ORDER BY ID DESC;";
	      PreparedStatement pStmt = conn.prepareStatement(sql);
	
	      // SELECTを実行
	      ResultSet rs = pStmt.executeQuery();
	
	      // SELECT文の結果をArrayListに格納
	      while (rs.next()) {
	    	int id = rs.getInt("ID");
	        String userId = rs.getString("USER_ID");
	        Timestamp datetime = rs.getTimestamp("DATETIME");
	        String text = rs.getString("TEXT");
	        String image = rs.getString("IMAGE");
	        GetMutter Mutter = new GetMutter(id, userId, datetime, text, image);
	        MutterList.add(Mutter);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }
	    return MutterList;
  }
  
  public boolean create(PostMutter mutter, String user_account, String mutter_list) {
	  // JDBCドライバを読み込む
	  try {
		  Class.forName("org.h2.Driver");
	  } catch(ClassNotFoundException e) {
//	      throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		  e.printStackTrace();
	  }
    // データベース接続
    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

      // INSERT文の準備(idは自動連番なので指定しなくてよい）
      String sql = "INSERT INTO " + mutter_list + "(USER_ID, TEXT, USER_ACCOUNT) VALUES(?, ?, ?)";
      PreparedStatement pStmt = conn.prepareStatement(sql);
      
      // INSERT文中の「?」に使用する値を設定しSQLを完成
      pStmt.setString(1, mutter.getUserid());
      pStmt.setString(2, mutter.getText());
      pStmt.setString(3, user_account);

      // INSERT文を実行（resultには追加された行数が代入される）
      int result = pStmt.executeUpdate();
      if (result != 1) {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}