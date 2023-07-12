package model;
import java.io.Serializable;

public class User implements Serializable {
  private String userid; // ユーザー名
  private String pass; // パスワード

  public User() { }
  public User(String userid, String pass) {
    this.userid = userid;
    this.pass = pass;
  }
  public String getUserid() { return userid; }
  public String getPass() { return pass; }
}