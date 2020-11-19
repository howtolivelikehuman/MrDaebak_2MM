package DTO;

public class Customer extends Member{
	private boolean vip;
	private int type = 0;

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean isVip) {
		this.vip = isVip;
	}
}
