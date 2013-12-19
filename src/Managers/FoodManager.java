package Managers;

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

import advandb.Food;
import advandb.Hunger;

import db.DBConnectionFactory;


public class FoodManager {
	public void getFood(ArrayList<Food> foods){
		String selectQuery = "SELECT food_name, food_frequency FROM food_list";
	
	    try {
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
	        
	        PreparedStatement pstmt = myConnection.prepareStatement(selectQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
            	Food food = new Food();
            	food.setFoodName(rs.getString("food_name"));
            	food.setFrequency(Integer.parseInt(rs.getString("food_frequency")));
            	foods.add(food);
            }
            myConnection.close();
	    } catch (SQLException ex) {
	    	Logger.getLogger(Hunger.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	public void loadFood(ArrayList<Food> foods){
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
						Food food = new Food();
						food.setFoodName(temp);
						foods.add(food);
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

	public void updateCravingPercentage(float percentage) {
		String updateQuery = "UPDATE food_classification SET classification_percentage = "+percentage+"WHERE classifcation_name = \"craving\"";
		try { 
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
			PreparedStatement pstmt = myConnection.prepareStatement(updateQuery);
			
		    pstmt.executeUpdate();

	        myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateHungerPercentage(float percentage) {
		String updateQuery = "UPDATE food_classification SET classification_percentage = "+percentage+"WHERE classifcation_name = \"hunger\"";
		try { 
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
			PreparedStatement pstmt = myConnection.prepareStatement(updateQuery);
			
		    pstmt.executeUpdate();

	        myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
