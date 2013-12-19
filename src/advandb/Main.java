package advandb;

import java.util.ArrayList;


import Managers.FoodManager;
import Managers.TweetManager;
import Managers.WordManager;


import visualization.VisualizeFrame;

public class Main {
	public static void main(String[] args){
		NaiveBayesFoodClassifier naiveBayesFoodClassifier = new NaiveBayesFoodClassifier();
		
		ArrayList<Tweet> rawTweets = new ArrayList<Tweet>();
		ArrayList<Tweet> acceptedTweets = new ArrayList<Tweet>();
		VisualizeFrame vm = new VisualizeFrame();
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
		WordManager wm = new WordManager();
		ArrayList<Food> foods = new ArrayList<Food>();
		ArrayList<Word> words = new ArrayList<Word>();
		tm.populateTweetAsRaw(rawTweets);
		tm.populateTweet(acceptedTweets);
		wm.loadWords(words);
		wm.computePercentage(words);
		fm.getFood(foods);
		tm.checkIfFood(foods, rawTweets, words);
		float total = 0;
		for(int i = 0; i<words.size();i++){
			total += words.get(i).getFrequency();
		}
		
		for(int i = 0; i<acceptedTweets.size(); i++){
			String classified = naiveBayesFoodClassifier.classify(acceptedTweets.get(i).getTweet(),total);
			tm.updateTweetClassification(classified, i+1);
		}
	}
}
