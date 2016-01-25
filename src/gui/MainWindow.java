package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import algorithm.StreamingMST;

public class MainWindow {
	private JFrame frame;
	private StreamingMST algorithm;
	private GraphPanel edit;
	private GraphPanel result;
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
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		JMenuBar menuBar = createMenuBar();
		frame.setJMenuBar(menuBar);
		
		JToolBar toolBar = createToolBar();
		
		edit = new GraphPanel();
		edit.setBackground(new Color(200, 200, 200));
		result = new GraphPanel();
		result.setBackground(new Color(255, 255, 255));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 0.02;
		
		content.add(toolBar, c);
		
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridy = 1;
		c.weighty = 0.98;
		
		content.add(edit, c);
		c.gridx = 1;
		content.add(result, c);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		
		toolBar.addSeparator();
		
		JButton button = new JButton("Reset");
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Reset");
			}
		});
		toolBar.add(button);
		
		toolBar.addSeparator();
		
		button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start");
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
				int nodes = getValue();
				if (nodes != 0) {
					System.out.println(nodes);
				}
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

	protected int getValue() {
		String input = JOptionPane.showInputDialog("Amount of nodes", 1);
		int nodes = 0;
		if(input == null){
			return nodes;
		}
		try {
			nodes = Integer.parseInt(input);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid number", "Error",
                    JOptionPane.ERROR_MESSAGE);
		}
		return nodes;
		
	}
}
