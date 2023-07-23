package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.GetMutter;
import model.User;

public class AccountsDAO {
  // データベース接続に使用する情報
//  private final String JDBC_URL = "jdbc:h2:tcp://my-h2/my-db-name";
  private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/chatCat";
  private final String DB_USER = "";
  private final String DB_PASS = "";

  public List<Account> findAll() {
    List<Account> accountList = new ArrayList<Account>();
    // JDBCドライバを読み込む
    try {
        Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    }
    // データベース接続
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

      // SELECT文の準備
      String sql = "SELECT USER_ID,PASS FROM ACCOUNTS ORDER BY USER_ID DESC";
      PreparedStatement pStmt = conn.prepareStatement(sql);

      // SELECTを実行
      ResultSet rs = pStmt.executeQuery();

      // SELECT文の結果をArrayListに格納
      while (rs.next()) {
        String userId = rs.getString("USER_ID");
        String pass = rs.getString("PASS");
        Account account = new Account(userId, pass);
        accountList.add(account);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return accountList;
  }
  
  public String findPostedUser(GetMutter mutter) {
	    String postedUserImage = "";
	    // JDBCドライバを読み込む
	    try {
	        Class.forName("org.h2.Driver");
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	    }
	    // データベース接続
	    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

	      // SELECT文の準備
	      String sql = "SELECT * FROM ACCOUNTS WHERE USER_ID = ?";
	      PreparedStatement pStmt = conn.prepareStatement(sql);
	      pStmt.setString(1, mutter.getUserid());

	      // SELECTを実行
	      ResultSet rs = pStmt.executeQuery();

	      // SELECT文の結果をArrayListに格納
	      while (rs.next()) {
	        postedUserImage = rs.getString("IMAGE");
	      }
	      
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }
	    return postedUserImage;
	  }
  
  public boolean create(Account account) {
	  // JDBCドライバを読み込む
	  try {
		  Class.forName("org.h2.Driver");
	  } catch(ClassNotFoundException e) {
	      throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	  }
    // データベース接続
    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

      // INSERT文の準備
      String sql = "INSERT INTO ACCOUNTS(USER_ID, PASS) VALUES (?, ?);";
      PreparedStatement pStmt = conn.prepareStatement(sql);
      
      // INSERT文中の「?」に使用する値を設定しSQLを完成
      pStmt.setString(1, account.getUserid());
      pStmt.setString(2, account.getPass());

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
  
  public boolean addEmail(User login_user, String email) {
	  // JDBCドライバを読み込む
	  try {
		  Class.forName("org.h2.Driver");
	  } catch(ClassNotFoundException e) {
	      throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	  }
    // データベース接続
    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

      // INSERT文の準備
      String sql = "UPDATE ACCOUNTS SET EMAIL = ? WHERE USER_ID = ?;";
      PreparedStatement pStmt = conn.prepareStatement(sql);
      
      // INSERT文中の「?」に使用する値を設定しSQLを完成
      pStmt.setString(1, email);
      pStmt.setString(2, login_user.getUserid());

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
  
  public boolean alterMailDeliveryPermission(User login_user, String whether_permit) {
	  // JDBCドライバを読み込む
	  try {
		  Class.forName("org.h2.Driver");
	  } catch(ClassNotFoundException e) {
	      throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	  }
    // データベース接続
    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

      // INSERT文の準備
      String sql = "UPDATE ACCOUNTS SET DELIVERY_EMAIL = ? WHERE USER_ID = ?";
      PreparedStatement pStmt = conn.prepareStatement(sql);
      
      // INSERT文中の「?」に使用する値を設定しSQLを完成
      pStmt.setString(1, whether_permit);
      pStmt.setString(2, login_user.getUserid());

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
  
  public boolean deleteAccount(User login_user) {
	  // JDBCドライバを読み込む
	  try {
		  Class.forName("org.h2.Driver");
	  } catch(ClassNotFoundException e) {
	      throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	  }
    // データベース接続
    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

      // INSERT文の準備
      String sql = "DELETE FROM ACCOUNTS WHERE USER_ID = ?";
      PreparedStatement pStmt = conn.prepareStatement(sql);
      
      // INSERT文中の「?」に使用する値を設定しSQLを完成
      pStmt.setString(1, login_user.getUserid());

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