import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DBConnectionFactory;


public class TweetManager {
	
	public void populateTweet(ArrayList<Tweet> tweets) {
		String getValuesQuery1 = "SELECT username, timestamp, location, tweet, tweet_classification FROM accepted_tweets";
		try {
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
	        
	        PreparedStatement pstmt = myConnection.prepareStatement(getValuesQuery1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
            	try{
            		tweets.add(new Tweet());
            		tweets.get(tweets.size()-1).setUsername(rs.getString("username"));
            		tweets.get(tweets.size()-1).setTimestamp(rs.getString("timestamp"));
            		tweets.get(tweets.size()-1).setLocation(rs.getString("location"));
            		tweets.get(tweets.size()-1).setTweet(rs.getString("tweet"));
            		tweets.get(tweets.size()-1).setTweet_classification(rs.getString("tweet_classification"));
            	} catch(Exception e){
            		
            	}
            }
                  
	        myConnection.close();
	    } catch (SQLException ex) {
	        Logger.getLogger(Hunger.class.getName()).log(Level.SEVERE, null, ex);
	    }
	
		
	}

	public void populateTweetAsRaw(ArrayList<Tweet> tweets) {
		String getValuesQuery = "SELECT username, timestamp, location, tweet, accepted FROM tweets";
		try {
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
	        
	        PreparedStatement pstmt = myConnection.prepareStatement(getValuesQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
            	try{
            		tweets.add(new Tweet());
            		tweets.get(tweets.size()-1).setUsername(rs.getString("username"));
            		tweets.get(tweets.size()-1).setTimestamp(rs.getString("timestamp"));
            		tweets.get(tweets.size()-1).setLocation(rs.getString("location"));
            		tweets.get(tweets.size()-1).setTweet(rs.getString("tweet"));
            		tweets.get(tweets.size()-1).setTweet_classification(rs.getString("accepted"));
            	} catch(Exception e){
            		
            	}
            }
                  
	        myConnection.close();
	    } catch (SQLException ex) {
	        Logger.getLogger(Hunger.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	public void checkIfFood(Food food, ArrayList<Tweet> tweets){
		boolean isFood = false;
		for(int i = 0; i<tweets.size();i++){
			isFood = false;
			System.out.println(i +" = "+tweets.size());
			ArrayList<String> tokenList = new ArrayList<String>();
			tokenList = tokenize(tweets.get(i).getTweet());
			for(int j = 0; j<tokenList.size()&& isFood == false;j++){
				for(int k = 0; k<food.getFoodName().size()&& isFood == false;k++){
					if(tokenList.get(j).contains(food.getFoodName().get(k))){
						// add food frequency here
						isFood = true;
						Tweet tweet = new Tweet();
						tweet.setUsername(tweets.get(i).getUsername());
						tweet.setTimestamp(tweets.get(i).getTimestamp());
						tweet.setLocation(tweets.get(i).getLocation());
						tweet.setTweet(tweets.get(i).getTweet());
						insertAcceptedTweet(tweet);
						updateIsAccepted("Yes", i+1);
					}
				}
			}
			if(isFood == false){
				updateIsAccepted("No", i+1);
			}
		}
	}
	
	private void updateIsAccepted(String string, int id) {
		String updateQuery = "";
		switch(string){
		case "Yes":updateQuery = "UPDATE tweets SET accepted = \"Yes\" WHERE tweetID = "+id+"";
		break;
		default: updateQuery = "UPDATE tweets SET accepted = \"No\" WHERE tweetID = "+id+"";
		break;
		}
		
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

	private void insertAcceptedTweet(Tweet tweet) {
		String insertQuery = "INSERT INTO accepted_tweets(username,timestamp,location,tweet) values(?,?,?,?)";
		
		try { 
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
			PreparedStatement pstmt = myConnection.prepareStatement(insertQuery);
			
		    pstmt.setString(1, tweet.getUsername());
		    pstmt.setString(2, tweet.getTimestamp());
		    pstmt.setString(3, tweet.getLocation());
		    pstmt.setString(4, tweet.getTweet());
		    pstmt.executeUpdate();

	        myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	protected ArrayList<String> tokenize(String tweet){
		ArrayList<String> tokenList = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i<tweet.length(); i++){
			if(tweet.charAt(i) == ' ' || tweet.charAt(i) == '!' || tweet.charAt(i) == ',' ||
					tweet.charAt(i) == '?' || tweet.charAt(i) == ';' || tweet.charAt(i) == '.'){
				tokenList.add(temp.toLowerCase());
				temp = "";
			}
			else {
				temp += tweet.charAt(i);
			}
		}
		return tokenList;
	}
}
