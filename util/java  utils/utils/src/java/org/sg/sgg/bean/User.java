package org.sg.sgg.bean;

import javax.print.DocFlavor.STRING;

public class User {
	private int id;
	private String name;
	private int age;
	private String sex;
	private String phone;
	
	
	public User(){}
	
	public User(int id){
		this.id = id;
	}
	public User(int id,String name){
		this.id = id;
		this.name = name;
	}
	public User(int id,String name,String sex){
		this.id = id;
		this.name = name;
		this.sex = sex;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String address;
}
