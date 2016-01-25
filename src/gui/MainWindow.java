package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import algorithm.StreamingMST;
import dataTypes.Point;

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
		
		edit = new GraphPanel("EDIT");
		result = new GraphPanel("RESULT");
		
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
					ArrayList<Point> points = createNodes(nodes);
					edit.addPoints(points);
					result.addPoints(points);
					frame.repaint();
				}
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open Graph");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = getFile();
				if(file != null) {
					// implement something useful
					System.out.println(file.getAbsolutePath());
				}
			}
		});
		menu.add(menuItem);
		
		menuBar.add(menu);
		return menuBar;
	}

	protected ArrayList<Point> createNodes(int nodes) {
		ArrayList<Point> arr = new ArrayList<Point>();
		for(int i = 0; i < nodes; ++i) {
			arr.add(new Point(String.valueOf(i)));
		}
		return arr;
	}

	protected File getFile() {
		JFileChooser fc = new JFileChooser();
		FileFilter filter = new FileFilter() {
			@Override
			public String getDescription() {
				return "Comma Separated Values (.csv)";
			}
			@Override
			public boolean accept(File f) {
				int i = f.getAbsolutePath().lastIndexOf('.');
				if (i > 0) {
				    if(f.getAbsolutePath().substring(i+1).equalsIgnoreCase("csv")){
				    	return true;
				    }
				}
				return false;
			}
		};
		fc.removeChoosableFileFilter(
		fc.getFileFilter() );
		fc.addChoosableFileFilter(filter);
		fc.showOpenDialog(null);
		File f = fc.getSelectedFile();
		return f;
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
