package DTO;

import java.util.ArrayList;

public class Order {
	private int no;
	private String name;
	private String mobile;
	private String address;
	private int totalPrice;	
	private String cardNum;
	private int isDiscounted;
	private String dateTime;
	private String deliverydateTime;
	private int status;
	private String memberID;
	private String info;
	private ArrayList<OrderedMenu> cart = null;
	
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public int getIsDiscounted() {
		return isDiscounted;
	}
	public void setIsDiscounted(int isDiscounted) {
		this.isDiscounted = isDiscounted;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getDeliverydateTime() {
		return deliverydateTime;
	}
	public void setDeliverydateTime(String deliverydateTime) {
		this.deliverydateTime = deliverydateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public ArrayList<OrderedMenu> getCart() {
		return cart;
	}
	public void setCart(ArrayList<OrderedMenu> cart) {
		this.cart = cart;
	}
	
	
	
}
