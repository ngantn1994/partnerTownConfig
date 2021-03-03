

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPetDAO {
	
	public static final String STOCKID = "StockID";
	public static final String VISIBLE = "Visible";
	public static final String USERNAME = "UserName";
	public static final String PETNAME = "PetName";
	public static final String PETID = "PetID";
	public static final String CURRENTLV = "CurrentLv";
	public static final String MAXLV = "MaxLv";
	public static final String HP = "HP";
	public static final String SP = "SP";
	public static final String HAPPY = "Happy";
	public static final String WC = "WC";
	
	private static final String	SELECTALL	= "SELECT * FROM UserPet WHERE UserName = ?";
	private static final String	UPDATE_VISIBLE = "UPDATE UserPet SET Visible = ? WHERE StockID = ?";
	private static final String	DELETE	= "DELETE FROM UserPet WHERE StockID = ?";
	private static final String	DELETE2	= "DELETE FROM UserPet WHERE UserName = ?";
	
	public static final String[] DEFAULT_NAME = {"Toto", "Titi", "Tata", "Tutu"};
	
	private Connection connection;
	
	private static UserPetDAO dao = new UserPetDAO();
	
	public static UserPetDAO getInstance() {
		return dao;
	}
	
	private UserPetDAO () {
		connection = SQLConnection.getInstance();
	}
	
	public List<UserPet> getAllUserPet(String username) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(SELECTALL);
				statement.setString(1, username.toLowerCase());

				ResultSet resultSet = statement.executeQuery();

				List<UserPet> pets = new ArrayList<UserPet>();
				
				while (resultSet.next()) {
					UserPet pet = new UserPet();
					pet.setStockID(resultSet.getInt(STOCKID));
					pet.setVisible(resultSet.getBoolean(VISIBLE));
					pet.setUserName(resultSet.getString(USERNAME));
					pet.setPetName(resultSet.getString(PETNAME));
					pet.setPetID(resultSet.getInt(PETID));
					pet.setCurrentLv(resultSet.getInt(CURRENTLV));
					pet.setMaxLv(resultSet.getInt(MAXLV));
					pet.setHP(resultSet.getInt(HP));
					pet.setSP(resultSet.getInt(SP));
					pet.setHappy(resultSet.getInt(HAPPY));
					pet.setWC(resultSet.getInt(WC));
					
					pets.add(pet);
				}
				return pets;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can not Connect to SQL Server.");
		}
		return null;
	}
	
	public void updateVisible(UserPet pet){
		if(connection != null && pet != null){
			try {
				PreparedStatement statement = connection.prepareStatement(UPDATE_VISIBLE);

				statement.setBoolean(1, pet.getVisible());
				
				statement.setInt(2, pet.getStockID());
				
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(UserPet pet) {
		if (connection != null && pet != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(DELETE);
				
				statement.setInt(1, pet.getStockID());
				
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete2(String username) {
		if (connection != null && username != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(DELETE2);
				
				statement.setString(1, username);
				
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
