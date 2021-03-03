

public class PetType {
	
	private int petid;
	private int levels;
	private int price;
	private String description;
	
	public PetType() {
		super();
	}

	public PetType(int id, int levels, int price) {
		super();
		petid = id;
		this.levels = levels;
		this.price = price;
	}

	public int getID() {
		return petid;
	}

	public void setID(int iD) {
		petid = iD;
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

}
