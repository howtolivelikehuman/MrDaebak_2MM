package DTO;

public class Customer extends Member{
	private boolean isVip;
	private int type = 0;

	public boolean isVip() {
		return isVip;
	}

	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}
}
