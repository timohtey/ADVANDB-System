import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import db.DBConnectionFactory;

import visualization.VisualizeFrame;

public class Main {
	public static void main(String[] args){
		NaiveBayesFoodClassifier naiveBayesFoodClassifier = new NaiveBayesFoodClassifier();
		
		ArrayList<Tweet> rawTweets = new ArrayList<Tweet>();
		ArrayList<Tweet> acceptedTweets = new ArrayList<Tweet>();
//		Scanner scanner;
//		String insertQuery = "INSERT INTO tweets (username, timestamp, location, tweet) values (?,?,?,?)";
//		try {
//			scanner = new Scanner(new File("tweets.csv"));
//			scanner.useDelimiter(",");
//
//			Tweet tweet = new Tweet();
//			int i = 0;
//			while (scanner.hasNext()) 
//			{
//				DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
//		        Connection myConnection = myFactory.getConnection();
//				try { 
//					PreparedStatement pstmt = myConnection.prepareStatement(insertQuery);
//					String temp = scanner.next();
//					System.out.println(temp);
//					if(temp != null){
//						if(i == 1){
//							tweet.setUsername(temp);
//						}else if(i==2){
//							tweet.setTimestamp(temp);
//						} else if(i==3){
//							tweet.setLocation(temp);
//						}else if(i==4){
//							tweet.setTweet(temp);
//							pstmt.setString(1, tweet.getUsername());
//						    pstmt.setString(2, tweet.getTimestamp());
//						    pstmt.setString(3, tweet.getLocation());
//						    pstmt.setString(4, tweet.getTweet());
//						    pstmt.executeUpdate();
//						    i=0;
//						}
//						i++;
//					}
//			        myConnection.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//			scanner.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		FoodManager fm = new FoodManager();
		TweetManager tm = new TweetManager();
		Food foods = new Food();
		tm.populateTweetAsRaw(rawTweets);
		tm.populateTweet(acceptedTweets);
		fm.getFood(foods);
		tm.checkIfFood(foods, rawTweets);
		for(int i = 0; i<acceptedTweets.size(); i++){
			String classified = naiveBayesFoodClassifier.classify(acceptedTweets.get(i).getTweet());
			System.out.println(classified);
		}
		new VisualizeFrame();
	}
}
