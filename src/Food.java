import java.util.ArrayList;


public class Food {
	private ArrayList<String> foodName;
	private ArrayList<String> frequency;
	
	public Food() {
		foodName = new ArrayList<String>();
		frequency = new ArrayList<String>();
	}
	public void addFoodName(String name){
		foodName.add(name);
	}
	public ArrayList<String> getFoodName() {
		return foodName;
	}

	public void setFoodName(ArrayList<String> foodName) {
		this.foodName = foodName;
	}

	public ArrayList<String> getFrequency() {
		return frequency;
	}

	public void setFrequency(ArrayList<String> frequency) {
		this.frequency = frequency;
	}
	public void addFoodFrequency(String string) {
		frequency.add(string);
	}
}
