package model;
public class Ticket {
	public String id;
	public String name;
	public String sex;
	public String Originating_City;
	public String End_City;
	public String idcard;
	
	public Ticket(){}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getOriginating_City() {
		return Originating_City;
	}
	public void setOriginating_City(String originating_City) {
		Originating_City = originating_City;
	}
	public String getEnd_City() {
		return End_City;
	}
	public void setEnd_City(String end_City) {
		End_City = end_City;
	}


	
}
