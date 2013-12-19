package advandb;

public class Word {
	private String wordName;
	private float wordPercentage;
	private float frequency;
	private String classification = "";
	
	public Word(){
		wordName = "";
		wordPercentage=0;
		setFrequency(0);
		classification = "";
	}

	public String getWordName() {
		return wordName;
	}

	public float getWordPercentage() {
		return wordPercentage;
	}

	public float getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public void setWordPercentage(float wordPercentage) {
		this.wordPercentage = wordPercentage;
	}
	
	public void addFrequency(){
		frequency++;
	}
}
