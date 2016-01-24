package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
		
		JMenuBar menuBar = createMenuBar();
		frame.setJMenuBar(menuBar);
		
		JToolBar editToolBar = createEditToolBar();
		JToolBar resultToolBar = createResultToolBar();
		JPanel edit = new JPanel(new GridBagLayout());
		JPanel result = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		
		c.weighty = 0.02;
		content.add(editToolBar,c);
		c.gridx += 1;
		content.add(resultToolBar, c);
		c.gridx -= 1;
		c.gridy += 1;
		c.weighty = 1 - c.weighty;
		edit.setBackground(new Color(200, 200, 200));
		content.add(edit,c);
		c.gridx += 1;
		result.setBackground(new Color(255, 255, 255));
		content.add(result,c);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private JToolBar createResultToolBar() {
		JToolBar toolBar = new JToolBar();
		
		toolBar.addSeparator();
		
		JButton button = new JButton("Restart");
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		toolBar.add(button);
		
		toolBar.addSeparator();
		
		button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		button.setFocusPainted(false);
		toolBar.add(button);
		
		toolBar.setFloatable(false);
		return toolBar;
	}

	private JToolBar createEditToolBar() {
		JToolBar toolBar = new JToolBar();
		
		toolBar.addSeparator();
		
		JButton button = new JButton("Add Edge");
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		toolBar.add(button);
		
		toolBar.addSeparator();
		
		button = new JButton("Move Node");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		button.setFocusPainted(false);
		toolBar.add(button);
		
		toolBar.setFloatable(false);
		return toolBar;
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		
		JMenuItem menuItem = new JMenuItem("New Graph");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Graph");
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open Graph");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Open Graph");
			}
		});
		menu.add(menuItem);
		
		menuBar.add(menu);
		return menuBar;
	}
}
