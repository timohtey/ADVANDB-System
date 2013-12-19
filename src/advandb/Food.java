package advandb;

public class Food {
	private String foodName;
	private int frequency;
	
	public Food() {
		foodName = "";
		frequency = 0;
	}
	public String getFoodName(){
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public void addCountFrequency(){
		frequency++;
	}
}
