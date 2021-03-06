package advandb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DBConnectionFactory;


public class Craving {
	private ArrayList<Word> words;
	private float percentage;
	private String wordsQuery = "SELECT word_name, frequency, word_percentage, word_classification FROM food_words WHERE word_classification = \"craving\"";
	private String percentageQuery = "SELECT classification_percentage FROM food_classification WHERE classification_name = \"craving\"";
	
	public Craving(){
		words = new ArrayList<Word>();
		try {
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
	        
	        PreparedStatement pstmt = myConnection.prepareStatement(wordsQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
            	try{
            		Word word = new Word();
            		word.setWordName(rs.getString("word_name"));
            		word.setWordPercentage(Float.parseFloat(rs.getString("word_percentage")));
            		word.setFrequency(Integer.parseInt(rs.getString("frequency")));
            		word.setClassification(rs.getString("word_classification"));
            		words.add(word);
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
	        Logger.getLogger(Craving.class.getName()).log(Level.SEVERE, null, ex);
	    }
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
