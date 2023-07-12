package model;
import java.io.Serializable;

public class Account implements Serializable {
	private String userid;
	private String pass;
	
	public Account() { }
	public Account(String userid, String pass) {
		this.userid = userid;
		this.pass = pass;
	}
	
	public String getUserid() { return userid; }
	public void setUserid(String userid) { this.userid = userid; }
	public String getPass() { return pass; }
	public void setPass(String pass) { this.pass = pass; }
}