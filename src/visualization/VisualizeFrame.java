package visualization;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class VisualizeFrame extends JFrame{
	private String[] food = {"Choose Food","apple", "banana", "bacon"};
	public VisualizeFrame(){
		super("Vizualization");
		this.setSize(800,500);
		this.getContentPane().setLayout(null);
		this.initComponents();
		this.setVisible(true);
		this.setResizable(false);
	}

	private void initComponents() {
		JComboBox<String> comboBox = new JComboBox<String>(food);
		//JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(615, 31, 144, 32);
		getContentPane().add(comboBox);
		
		JPanel barGraphPanel = new JPanel();
		barGraphPanel.setBounds(35, 74, 724, 369);
		barGraphPanel.setBackground(Color.WHITE);
		getContentPane().add(barGraphPanel);
		
		JLabel lblVisualization = new JLabel("Visualization");
		lblVisualization.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVisualization.setBounds(35, 25, 175, 39);
		getContentPane().add(lblVisualization);
	}
}
