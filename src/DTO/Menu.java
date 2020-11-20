package DTO;

import java.util.ArrayList;

public class Menu {
	private int no;
	private String name;
	private int type;
	private int available;
	private int price;
	private String info;
	private ArrayList<MenuDetail> menuDetailList = null;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public ArrayList<MenuDetail> getMenuDetailList() {
		return menuDetailList;
	}
	public void setMenuDetailList(ArrayList<MenuDetail> menuDetailList) {
		this.menuDetailList = menuDetailList;
	}
}
