import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DBConnectionFactory;


public class TweetManager {
	private String getValuesQuery = "SELECT username, timestamp, location, tweet, accepted FROM tweets";
	private String getValuesQuery1 = "SELECT username, timestamp, location, tweet, tweet_classification FROM accepted_tweets";
	
	public TweetManager(ArrayList<Tweet> tweets, String mode){
		switch(mode){
			case "raw": populateTweetAsRaw(tweets);
						break;
			case "accepted": populateTweet(tweets);
							break;
		}
	}

	private void populateTweet(ArrayList<Tweet> tweets) {
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

	private void populateTweetAsRaw(ArrayList<Tweet> tweets) {
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
}
