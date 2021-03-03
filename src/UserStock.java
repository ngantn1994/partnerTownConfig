

public class UserStock {

	private String username;
	private int milk;
	private int grape;
	private int mango;
	public UserStock() {
		super();
	}
	public UserStock(String username, int milk, int grape, int mango) {
		super();
		this.username = username;
		this.milk = milk;
		this.grape = grape;
		this.mango = mango;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	
	public int getMilk() {
		return milk;
	}
	public void setMilk(int milk) {
		this.milk = milk;
	}
	public int getGrape() {
		return grape;
	}
	public void setGrape(int grape) {
		this.grape = grape;
	}
	public int getMango() {
		return mango;
	}
	public void setMango(int mango) {
		this.mango = mango;
	}

}
