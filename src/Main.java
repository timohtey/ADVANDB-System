import visualization.VisualizeFrame;

public class Main {
	public static void main(String[] args){
		NaiveBayesClassifier naiveBayesClassifier = new NaiveBayesClassifier();
		String classified = naiveBayesClassifier.classify("I want some food!");
		Food foods = new Food();
		new VisualizeFrame();
		System.out.println(classified);
	}
}
