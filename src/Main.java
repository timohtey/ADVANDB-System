import java.util.ArrayList;

import visualization.VisualizeFrame;

public class Main {
	public static void main(String[] args){
		NaiveBayesFoodClassifier naiveBayesFoodClassifier = new NaiveBayesFoodClassifier();
		String classified = naiveBayesFoodClassifier.classify("I want some food!");
		ArrayList<Tweet> rawTweets = new ArrayList<Tweet>();
		ArrayList<Tweet> acceptedTweets = new ArrayList<Tweet>();
		new TweetManager(rawTweets, "raw");
		new TweetManager(acceptedTweets, "accepted");
		//Food foods = new Food();
		new VisualizeFrame();
		System.out.println(classified);
	}
}
