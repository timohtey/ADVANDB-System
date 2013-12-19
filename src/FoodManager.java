import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DBConnectionFactory;


public class FoodManager {
	public void getFood(Food food){
		String selectQuery = "SELECT food_name, food_frequency FROM food_list";
	
	    try {
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
	        
	        PreparedStatement pstmt = myConnection.prepareStatement(selectQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
            	food.addFoodName(rs.getString("food_name"));
            	food.addFoodFrequency(rs.getString("food_frequency"));
            }
            myConnection.close();
	    } catch (SQLException ex) {
	    	Logger.getLogger(Hunger.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	public void loadFood(Food food){
		String insertQuery = "INSERT INTO food_list(food_name, food_frequency) values (?,?)";
		Scanner scanner;
		try {
			scanner = new Scanner(new File("fooddictionary.csv"));
			scanner.useDelimiter(",");

			while (scanner.hasNext()) 
			{
				DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
		        Connection myConnection = myFactory.getConnection();
		        
				try { 
					PreparedStatement pstmt = myConnection.prepareStatement(insertQuery);
					String temp = scanner.next();
					if(temp != null){
						food.addFoodName(temp);
				        pstmt.setString(1, temp);
				        pstmt.setInt(2, 0);
				        pstmt.executeUpdate();
					}
			        myConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
