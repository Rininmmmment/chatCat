package model;
import java.io.Serializable;

public class PostMutter implements Serializable {
	private String userid;
	private String text;
	
	public PostMutter() { }
	public PostMutter(String userid, String text) {
		this.userid = userid;
		this.text = text;
	}

	public String getUserid() { return userid; }
	public void setUserid(String userid) { this.userid = userid; }
	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
}