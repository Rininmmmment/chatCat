package model;
import java.io.Serializable;
import java.sql.Timestamp;

public class GetMutter implements Serializable {
	private int id;
	private String userid;
	private Timestamp datetime;
	private String text;
	private String image;
	
	public GetMutter() { }
	public GetMutter(int id, String userid, Timestamp datetime, String text, String image) {
		this.id = id;
		this.userid = userid;
		this.datetime = datetime;
		this.text = text;
		this.image = image;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getUserid() { return userid; }
	public void setUserid(String userid) { this.userid = userid; }
	public Timestamp getDatetime() { return datetime; }
	public void setDatetime(Timestamp datetime) { this.datetime = datetime; }
	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
	public String getImage() { return image; }
	public void setImage(String image) { this.image = image; }
}