import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import db.DBConnectionFactory;


public class Food {
	private ArrayList<String> foodName = new ArrayList<String>();
	private ArrayList<String> frequency = new ArrayList<String>();
	private String insertQuery = "INSERT INTO food_list(food_name, food_frequency) values (?,?)";
	public Food() {
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
						foodName.add(temp);
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
