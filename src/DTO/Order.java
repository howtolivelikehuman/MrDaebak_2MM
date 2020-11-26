package DTO;

import java.util.ArrayList;

public class Order {
	private int no; //DB에넣을때자동 (필요없음)
	private String name; //주문자이름
	private String mobile; //
	private String address;
	private int totalPrice;	//(cart의 개별 가격의 합) if (단골 = 세일)
	private String cardNum;
	private boolean isDiscounted;
	private String deliverydateTime; //원하는 예약 날짜, 시간
	private int status; //상태(얘는 필요없음) 무조건 처음 주문때는 0
	private int memberNo; //세션 (얘는 필요없음)
	private String memberID; //세션 (얘는 필요없음)
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
	public boolean getIsDiscounted() {
		return isDiscounted;
	}
	public void setIsDiscounted(boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
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
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
