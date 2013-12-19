package Managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import advandb.Craving;
import advandb.Word;

import db.DBConnectionFactory;


public class WordManager {
	public void loadWords(ArrayList<Word> words){
		String selectQuery = "SELECT word_name, frequency, word_percentage, word_classification FROM food_words";
		try {
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
	        
	        PreparedStatement pstmt = myConnection.prepareStatement(selectQuery);
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
            myConnection.close();
		} catch (SQLException ex) {
			Logger.getLogger(Craving.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void computePercentage(ArrayList<Word> words){
		int total = 0;
		for(int i = 0; i<words.size();i++){
			total += words.get(i).getFrequency();
		}
		
		for(int i = 0; i<words.size();i++){
			words.get(i).setWordPercentage(words.get(i).getFrequency()/total);
			updatePercentage(words.get(i).getWordPercentage(), i+1);
		}
	}

	private void updatePercentage(float percentage, int id) {
		String updateQuery = "UPDATE food_words SET word_percentage ='"+percentage+"'" +
				"WHERE idwords = "+id+"";
		try { 
			DBConnectionFactory myFactory = DBConnectionFactory.getInstace();
	        Connection myConnection = myFactory.getConnection();
			PreparedStatement pstmt = myConnection.prepareStatement(updateQuery);
			
		    pstmt.executeUpdate();

	        myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
