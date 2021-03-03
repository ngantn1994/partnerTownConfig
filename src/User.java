

public class User {
	
	private String username;
	private String password;
	private String email;
	private String address;
	private boolean sex;
	private int numOfPet;
	private int money;
	private int energy;
	
	private static final String MALE = "Nam";
	private static final String FEMALE = "N\u1eef";

	public User() {
		super();
	}

	public User(String username, String password, String email, String address,
			boolean sex, int numOfPet, int money, int energy) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.sex = sex;
		this.numOfPet = numOfPet;
		this.money = money;
		this.energy = energy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	public boolean setSexFromString(String sex){
		if(sex.equalsIgnoreCase(MALE)){
			return true;
		} else {
			return false;
		}
	}
	
	public String getSex(){
		if(sex){
			return MALE;
		} else {
			return FEMALE;
		}
	}

	public int getNumOfPet() {
		return numOfPet;
	}

	public void setNumOfPet(int numOfPet) {
		this.numOfPet = numOfPet;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void decMoney(int money){
		this.money = this.money - money;
	}
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
