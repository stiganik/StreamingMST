package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import algorithm.StreamingMST;

public class MainWindow {
	private JFrame frame;
	private StreamingMST algorithm;
	private static int WIDTH = 800;
	private static int HEIGHT = 600;

	public MainWindow(String name, StreamingMST algorithm){
		this.algorithm = algorithm;
		initialize(name);
	}
	
	private void initialize(String name) {
		frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		
		Container content = frame.getContentPane();
		content.setLayout(new GridBagLayout());
		
		JPanel toolbar = new JPanel(new GridBagLayout());
		JPanel graphics = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		toolbar.setBackground(new Color(180, 180, 180));
		graphics.setBackground(new Color(255, 255, 255));
		
		c.weighty = 0.05;
		content.add(toolbar,c);
		c.gridy += 1;
		c.weighty = 0.9;
		content.add(graphics,c);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
