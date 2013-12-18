import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DBConnectionFactory;


public class Hunger {
	private ArrayList<Word> words;
	private float percentage;
	private String wordsQuery = "SELECT word_name, word_percentage FROM food_words WHERE word_classification = \"hunger\"";
	private String percentageQuery = "SELECT classification_percentage FROM food_classification WHERE classification_name = \"hunger\"";
	public Hunger(){
		words = new ArrayList<Word>();
		try {
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
	        
	        PreparedStatement pstmt = myConnection.prepareStatement(wordsQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
            	try{
            		addWord(rs.getString("word_name"), 
            				Float.parseFloat(rs.getString("word_percentage")));
            	} catch(Exception e){
            		
            	}
            }
            
            pstmt = myConnection.prepareStatement(percentageQuery);
            rs = pstmt.executeQuery();
            try{
                this.setPercentage(Float.parseFloat(rs.getString("classification_percentage")));
        	} catch(Exception e){
        		
        	}
        
	        myConnection.close();
	    } catch (SQLException ex) {
	        Logger.getLogger(Hunger.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	public void addWord(String wordName, float wordPercentage){
		words.add(new Word(wordName, wordPercentage));
	}
	
	public ArrayList<Word> getWords() {
		return words;
	}

	public float getPercentage() {
		return percentage;
	}
	
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
}
