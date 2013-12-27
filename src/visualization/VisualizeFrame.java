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
import java.util.ArrayList;

import advandb.Food;

public class VisualizeFrame extends JFrame{
	
	private JPanel barGraphPanel;
	private ArrayList<Food> foods;
	private String[] food = {"Choose Food","apple", "banana", "bacon"};
	
	public VisualizeFrame(ArrayList<Food> foods){
		super("Vizualization");
		
		this.foods = foods;
		
		this.setSize(800,500);
		this.getContentPane().setLayout(null);
		this.initComponents();
		this.setVisible(true);
		this.setResizable(false);
		
		JFreeChart chart = ChartFactory.createBarChart("Food Frequencies", 
				 "Food", "Frequency", createDataset(), PlotOrientation.VERTICAL, 
				 false, true, false);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	    barGraphPanel.add(chartPanel);
	    repaint();
	    revalidate();
	    System.out.println("Built chart");
	}
	
	private DefaultCategoryDataset createDataset() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				
		for (int i = 0; i < 5; i++)
			dataset.setValue(foods.get(i).getFrequency(), "Frequency", foods.get(i).getFoodName());
		
		return dataset;
	}

	private void initComponents() {
		JComboBox<String> comboBox = new JComboBox<String>(food);
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
	}
}
