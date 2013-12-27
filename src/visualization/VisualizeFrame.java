package visualization;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import advandb.Food;

public class VisualizeFrame extends JFrame{
	
	private JPanel barGraphPanel;
	JComboBox<String> comboBox; 
	private ArrayList<Food> foods;
	private ArrayList<ArrayList<Food>> foods_;
	private String[] food = {"apple", "banana", "bacon"};
	
	public VisualizeFrame(ArrayList<Food> foods){
		super("Vizualization");
		
		this.foods = foods;
		
		this.setSize(800,500);
		this.getContentPane().setLayout(null);
		this.initComponents();
		this.setVisible(true);
		this.setResizable(false);
		
		/*
		JFreeChart chart = ChartFactory.createBarChart("Food Frequencies", 
				 "Food", "Frequency", createDataset1(), PlotOrientation.VERTICAL, 
				 false, true, false);
		*/
		
		foods_ = new ArrayList<ArrayList<Food>>();
		ArrayList<Food> fa = new ArrayList<Food>();
		fa.add(new Food("apple", 5));
		fa.add(new Food("bacon", 2));
		for (int i = 0; i < 12; i++)
			foods_.add(fa);
			
		refresh();
		
	}
	
	public DefaultCategoryDataset createDataset1() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				
		for (int i = 0; i < foods.size(); i++) {
			if (foods.get(i).getFrequency() > 0)
				dataset.setValue(foods.get(i).getFrequency(), "Frequency", foods.get(i).getFoodName());
		}
		return dataset;
	}
	
	
	public DefaultCategoryDataset createDataset2() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (int i = 0; i < foods_.size(); i++) {
			int j = 0;
			while (j < foods_.get(i).size()) {
				if (foods_.get(i).get(j).getFoodName().equals((String)comboBox.getSelectedItem()))
					break;
				j++;
			}
				
			if (j != foods_.get(i).size()) 
				dataset.setValue(foods_.get(i).get(j).getFrequency(), "Frequency", i + ":00");
		}
		return dataset;
		
	}
	
	public void refresh() {
		
		JFreeChart chart = ChartFactory.createBarChart("Frequency in Relation to Time", 
				 "Time", "Frequency", createDataset2(), PlotOrientation.VERTICAL, 
				 false, true, false);
		
		barGraphPanel.removeAll();
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(724, 369));
	    barGraphPanel.add(chartPanel);
	    repaint();
	    revalidate();
	    System.out.println("Built chart");
		
	}

	private void initComponents() {
		
		comboBox = new JComboBox<String>(food);
		//JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(615, 31, 144, 32);
		getContentPane().add(comboBox);
		
		barGraphPanel = new JPanel();
		barGraphPanel.setBounds(35, 74, 724, 369);
		barGraphPanel.setBackground(Color.WHITE);
		getContentPane().add(barGraphPanel);
		
		JLabel lblVisualization = new JLabel("Visualization");
		lblVisualization.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVisualization.setBounds(35, 25, 175, 39);
		getContentPane().add(lblVisualization);
		
		comboBox.addItemListener(new ItemListener() {
	        @Override
			public void itemStateChanged(ItemEvent e) {
				refresh();
			}
	    });
	}
}
