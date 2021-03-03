

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	public static final String USERNAME = "UserName";
	public static final String PASSWORD = "Password";
	public static final String EMAIL = "Email";
	public static final String ADDRESS = "Address";
	public static final String SEX = "Sex";
	public static final String NUMOFPET = "NumOfPet";
	public static final String MONEY = "Money";
	public static final String ENERGY = "Energy";
	
	public static final String FOOD = "Food";
	public static final String ENERGY_1 = "Energy_1";
	public static final String ENERGY_5 = "Energy_5";
	
	private static final String	SELECT_ALL	= "SELECT * FROM UserInformation";
	public static final String	SELECT_USERNAME = "SELECT UserName FROM UserInformation";
	private static final String	SELECT	= "SELECT * FROM UserInformation WHERE UserName = ?";
	private static final String	INSERT = "INSERT INTO UserInformation VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String  UPDATE_INFORMATION = "UPDATE UserInformation SET Password = ?, Email = ?, Address = ?, "
			+ "Sex = ?, Money = ?, Energy = ? WHERE UserName = ?";
	private static final String  UPDATE_NUMOFPET = "UPDATE UserInformation SET NumOfPet = ? WHERE UserName = ?";
	private static final String	SELECT_ALLSTOCK	= "SELECT * FROM UserStock";
	private static final String	SELECT_STOCK = "SELECT * FROM UserStock WHERE UserName = ?";
	private static final String	INSERT_STOCK = "INSERT INTO UserStock VALUES (?, ?, ?, ?)";
	private static final String  UPDATE_STOCK = "UPDATE UserStock SET Food = ?, Energy_1 = ?, Energy_5 = ? WHERE UserName = ?";
	private static final String  DELETE_USER = "DELETE FROM UserInformation WHERE UserName = ?";
	private static final String  DELETE_STOCK = "DELETE FROM UserStock WHERE UserName = ?";
	
	private Connection connection;
	
	private static UserDAO dao = new UserDAO();
	
	public static UserDAO getInstance() {
		return dao;
	}
	
	private UserDAO () {
		connection = SQLConnection.getInstance();
	}
	
	public User getUser(String username) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(SELECT);
				statement.setString(1, username.toLowerCase());

				ResultSet resultSet = statement.executeQuery();
				
				if(resultSet.isBeforeFirst()){
					resultSet.next();
				}
				
				User user = new User();
				user.setUsername(resultSet.getString(USERNAME));
				user.setPassword(resultSet.getString(PASSWORD));
				user.setEmail(resultSet.getString(EMAIL));
				user.setAddress(resultSet.getString(ADDRESS));
				user.setSex(resultSet.getBoolean(SEX));
				user.setNumOfPet(resultSet.getInt(NUMOFPET));
				user.setMoney(resultSet.getInt(MONEY));
				user.setEnergy(resultSet.getInt(ENERGY));
				
				resultSet.close();
	            statement.close();
				
				return user;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can not Connect to SQL Server.");
		}
		return null;
	}
	
	public List<User> getAllUsers(){
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL);

				ResultSet resultSet = statement.executeQuery();

				List<User> users = new ArrayList<User>();
				
				while (resultSet.next()) {
					User user = new User();
					user.setUsername(resultSet.getString(USERNAME));
					user.setPassword(resultSet.getString(PASSWORD));
					user.setEmail(resultSet.getString(EMAIL));
					user.setAddress(resultSet.getString(ADDRESS));
					user.setSex(resultSet.getBoolean(SEX));
					user.setNumOfPet(resultSet.getInt(NUMOFPET));
					user.setMoney(resultSet.getInt(MONEY));
					user.setEnergy(resultSet.getInt(ENERGY));
					
					users.add(user);
				}
				return users;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can not Connect to SQL Server.");
		}
		return null;
	}
	
	public List<UserStock> getAllUserStocks(){
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(SELECT_ALLSTOCK);

				ResultSet resultSet = statement.executeQuery();

				List<UserStock> stocks = new ArrayList<UserStock>();
				
				while (resultSet.next()) {
					UserStock stock = new UserStock();
					stock.setUserName(resultSet.getString(USERNAME));
					stock.setMilk(resultSet.getInt(FOOD));
					stock.setGrape(resultSet.getInt(ENERGY_1));
					stock.setMango(resultSet.getInt(ENERGY_5));
					
					stocks.add(stock);
				}
				return stocks;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can not Connect to SQL Server.");
		}
		return null;
	}
	
	public void create(User user){
		if(connection != null && user != null){
			try {
				PreparedStatement statement = connection.prepareStatement(INSERT);
				
				statement.setString(1, user.getUsername().toLowerCase());
				statement.setString(2, user.getPassword());
				statement.setString(3, user.getEmail());
				statement.setString(4, user.getAddress());
				statement.setBoolean(5, user.isSex());
				statement.setInt(6, user.getNumOfPet());
				statement.setInt(7, user.getMoney());
				statement.setInt(8, user.getEnergy());
				
				statement.execute();

	            statement.close();
	            
	            createNewStock(user);
	            
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createNewStock(User user){
		if(connection != null && user != null){
			try {
				PreparedStatement statement = connection.prepareStatement(INSERT_STOCK);
				
				statement.setString(1, user.getUsername().toLowerCase());
				statement.setInt(2, 0);
				statement.setInt(3, 0);
				statement.setInt(4, 0);
				
				statement.execute();

	            statement.close();
	            
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateInformation(User user){
		if(connection != null && user != null){
			try {
				PreparedStatement statement = connection.prepareStatement(UPDATE_INFORMATION);

				statement.setString(1, user.getPassword());
				statement.setString(2, user.getEmail());
				statement.setString(3, user.getAddress());
				statement.setBoolean(4, user.isSex());
				statement.setInt(5, user.getMoney());
				statement.setInt(6, user.getEnergy());
				
				statement.setString(7, user.getUsername());
				
				statement.execute();
				
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public UserStock getStock(String username){
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(SELECT_STOCK);
				statement.setString(1, username);

				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next()){
				
					UserStock stock = new UserStock();
					stock.setUserName(resultSet.getString(USERNAME));
					stock.setMilk(resultSet.getInt(FOOD));
					stock.setGrape(resultSet.getInt(ENERGY_1));
					stock.setMango(resultSet.getInt(ENERGY_5));
					return stock;
				}
				resultSet.close();
	            statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can not Connect to SQL Server.");
		}
		return null;
	}
	
	public void updateStock(UserStock stock){
		if(connection != null && stock != null){
			try {
				PreparedStatement statement = connection.prepareStatement(UPDATE_STOCK);
				
				statement.setInt(1, stock.getMilk());
				statement.setInt(2, stock.getGrape());
				statement.setInt(3, stock.getMango());
				
				statement.setString(4, stock.getUserName());
				
				statement.execute();
				
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateNumOfPet(User user){
		if(connection != null && user != null){
			try {
				PreparedStatement statement = connection.prepareStatement(UPDATE_NUMOFPET);

				statement.setInt(1, user.getNumOfPet());
				
				statement.setString(2, user.getUsername());
				
				statement.execute();
				
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkAvaible(String username){
		if(connection != null){
			try {
				PreparedStatement statement = connection.prepareStatement(SELECT_USERNAME);

				ResultSet resultSet = statement.executeQuery();
				
				if(!resultSet.isBeforeFirst()){
					resultSet.close();
		            statement.close();
					return false;
				}
				
				while(resultSet.next()){
					if(username.equalsIgnoreCase(resultSet.getString(USERNAME))){
						resultSet.close();
			            statement.close();
						return true;
					}
				}
				resultSet.close();
	            statement.close();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return false;
		}
		return false;
	}
	
	public void delete(User user){
		if (connection != null && user != null) {
			try {
				deleteStock(user);
				UserPetDAO.getInstance().delete2(user.getUsername());
				
				PreparedStatement statement = connection.prepareStatement(DELETE_USER);
				
				statement.setString(1, user.getUsername());
				
				statement.execute();
				
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteStock(User user){
		if (connection != null && user != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(DELETE_STOCK);
				
				statement.setString(1, user.getUsername());
				
				statement.execute();
				
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
