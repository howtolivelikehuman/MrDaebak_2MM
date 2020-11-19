package DTO;

public class Stock {
	private int no;
	private String name;
	private int amount;
	private String nextSupplyDate;
	private int price;
	
	
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getNextSupplyDate() {
		return nextSupplyDate;
	}
	public void setNextSupplyDate(String nextSupplyDate) {
		this.nextSupplyDate = nextSupplyDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
