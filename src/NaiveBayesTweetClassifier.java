import java.util.ArrayList;


public class NaiveBayesTweetClassifier {
	private String classified = "";
	
	public String classify(ArrayList<Tweet> tweets){
		ArrayList<String> tokenList = new ArrayList<String>();
		for(int i = 0; i< tweets.size(); i++){
			tokenList = tokenize(tweets.get(i).getTweet());
		}
		if(computeNotFood(tokenList) > computeIsFood(tokenList)){
			classified = "no";
		}
		else classified = "yes";
		
		return classified;
	}

	public ArrayList<String> tokenize(String tweet){
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
	
	public float computeIsFood(ArrayList<String> tokenList) {
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

	public float computeNotFood(ArrayList<String> tokenList) {
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
