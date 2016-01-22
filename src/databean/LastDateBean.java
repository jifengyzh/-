package databean;

import java.util.Date;

import org.genericdao.PrimaryKey;
@PrimaryKey("name")
public class LastDateBean {
	String name;
	Date date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
