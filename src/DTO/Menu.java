package DTO;

import java.util.ArrayList;

public class Menu {
	
	private int no;
	private String name;
	private int available; //만약 얘가 속한 재고가 0이면 주문 못하게.
	private int price;
	private String info;
	private ArrayList<Integer> availableStyle; //1,2,3
	private ArrayList<MenuDetail> menuDetailList = null;
	private ArrayList<MenuDetail> extraDetailList = null;
	
	public ArrayList<MenuDetail> getExtraDetailList() {
		return extraDetailList;
	}
	public void setExtraDetailList(ArrayList<MenuDetail> extraDetailList) {
		this.extraDetailList = extraDetailList;
	}
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
	public ArrayList<Integer> getAvailableStyle() {
		return availableStyle;
	}
	public void setAvailableStyle(ArrayList<Integer> availableStyle) {
		this.availableStyle = availableStyle;
	}
}
