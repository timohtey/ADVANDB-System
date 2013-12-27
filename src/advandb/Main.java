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

		
		FoodManager fm = new FoodManager();
		TweetManager tm = new TweetManager();
		WordManager wm = new WordManager();
		ArrayList<Food> foods = new ArrayList<Food>();
		ArrayList<Word> words = new ArrayList<Word>();
		tm.populateTweetAsRaw(rawTweets);
		tm.checkIfFood(foods, rawTweets, words);
		tm.populateTweet(acceptedTweets);
		wm.loadWords(words);
		wm.computePercentage(words);
		fm.getFood(foods);
		float total = 0;
		for(int i = 0; i<words.size();i++){
			total += words.get(i).getFrequency();
		}
		
		for(int i = 0; i<acceptedTweets.size(); i++){
			String classified = naiveBayesFoodClassifier.classify(acceptedTweets.get(i).getTweet(),total);
			tm.updateTweetClassification(classified, i+1);
		}
		
		VisualizeFrame vm = new VisualizeFrame(foods);
	}
}
