package DTO;

public class OrderedMenu {
	private int orderNo;
	private String menu;
	private String style;
	private String orderedDetailList; //이거는 전에 말했듯이, 스끼다시 그냥 한줄로 쭉 합쳐서
	private int price;
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getOrderedDetailList() {
		return orderedDetailList;
	}
	public void setOrderedDetailList(String orderedDetailList) {
		this.orderedDetailList = orderedDetailList;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
