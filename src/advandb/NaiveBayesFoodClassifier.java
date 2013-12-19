package advandb;
import java.util.ArrayList;

import Managers.FoodManager;


public class NaiveBayesFoodClassifier{
	protected String classified = "";
	
	protected String classify(String tweet, float total){
		ArrayList<String> tokenList = new ArrayList<String>();
		tokenList = tokenize(tweet);
		if(computeHunger(tokenList, total) > computeCraving(tokenList,total)){
			classified = "hunger";
		}
		else classified = "craving";
		
		return classified;
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
	
	protected float computeCraving(ArrayList<String> tokenList, float total) {
		Craving craving = new Craving();
		ArrayList<Word> cravingWords = craving.getWords();
		float computation = 1;
		for(int i = 0; i<tokenList.size();i++){
			for(int j = 0; j<cravingWords.size(); j++){
				if(tokenList.get(i).equals(cravingWords.get(j).getWordName())){
					computation*=cravingWords.get(j).getWordPercentage();
				}
			}
		}
		float cravingTotal = 0;
		for(int i = 0; i<cravingWords.size();i++){
			cravingTotal+=cravingWords.get(i).getFrequency();
		}
		
		craving.setPercentage(cravingTotal/total);
		FoodManager fm = new FoodManager();
		fm.updateCravingPercentage(craving.getPercentage());
		computation*=craving.getPercentage();
		return computation;
	}

	protected float computeHunger(ArrayList<String> tokenList, float total) {
		Hunger hunger = new Hunger();
		ArrayList<Word> hungerWords = hunger.getWords();
		float computation = 1;
		for(int i = 0; i<tokenList.size();i++){
			for(int j = 0; j<hungerWords.size(); j++){
				if(tokenList.get(i).equals(hungerWords.get(j).getWordName())){
					computation*=hungerWords.get(j).getWordPercentage();
				}
			}
		}
		float hungerTotal = 0;
		for(int i = 0; i<hungerWords.size();i++){
			hungerTotal+=hungerWords.get(i).getFrequency();
		}
		hunger.setPercentage(hungerTotal/total);
		FoodManager fm = new FoodManager();
		fm.updateHungerPercentage(hunger.getPercentage());
		computation*=hunger.getPercentage();
		return computation;
	}

}
