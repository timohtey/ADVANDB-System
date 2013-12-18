import java.util.ArrayList;


public class NaiveBayesFoodClassifier{
	protected String classified = "";
	
	protected String classify(String tweet){
		ArrayList<String> tokenList = new ArrayList<String>();
		tokenList = tokenize(tweet);
		if(computeHunger(tokenList) > computeCraving(tokenList)){
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
	
	protected float computeCraving(ArrayList<String> tokenList) {
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
		computation*=craving.getPercentage();
		return computation;
	}

	protected float computeHunger(ArrayList<String> tokenList) {
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
		computation*=hunger.getPercentage();
		return computation;
	}

}
