package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import algorithm.StreamingMST;
import animation.Animation;
import dataTypes.Edge;
import linkCutTrees.Algorithm;
import linkCutTrees.Vertex;
import utils.CSVParser;

public class MainWindow {
	private JFrame frame;
	private StreamingMST algorithm;
	private GraphPanel edit;
	private GraphPanel result;
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	private static final int time = 1200;	// Animation time
	
	private ArrayList<Edge> edges = null;
	private Timer timer;

	public MainWindow(String name){
		this.timer = null;
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
		
		JButton button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(edges != null && timer == null) {
					algorithm = new Algorithm();
					startAnimation();
				}
			}
		});
		button.setFocusPainted(false);
		toolBar.add(button);
		
		toolBar.addSeparator();
		
		button = new JButton("Stop");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(edges != null && timer != null) {
					stopAnimation();
				}
			}
		});
		button.setFocusPainted(false);
		toolBar.add(button);
		
		toolBar.setFloatable(false);
		return toolBar;
	}

	protected void stopAnimation() {
		timer.cancel();
		timer = null;
	}

	protected void startAnimation() {
		clearScreen();
		timer = new Timer(); // Instantiate Timer Object
		Animation a = new Animation(this); // Instantiate SheduledTask class
		timer.schedule(a, 0, time); // Create Repetitively task for every 1 secs
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		
		JMenuItem menuItem = new JMenuItem("New Graph");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearScreen();
				int nodes = getValue();
				if (nodes != 0) {
					ArrayList<Vertex> points = createNodes(nodes);
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
				clearScreen();
				File file = getFile();
				if(file != null) {
					CSVParser par = new CSVParser();
					if(par.parse(file)){
						ArrayList<Vertex> nodes = par.getNodes();
						edges = par.getEdges();
						edit.addPoints(nodes);
						result.addPoints(nodes);
						frame.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Failed to parse file", "Error",
			                    JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		menu.add(menuItem);
		
		menuBar.add(menu);
		return menuBar;
	}

	protected ArrayList<Vertex> createNodes(int nodes) {
		ArrayList<Vertex> arr = new ArrayList<Vertex>();
		for(int i = 0; i < nodes; ++i) {
			arr.add(new Vertex(String.valueOf(i), 0, 0));
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

	public void animate(int p) {
		if(p < 0)
			return;
		if(p >= edges.size()){
			this.timer.cancel();
			this.timer = null;
			return;
		}
		
		Edge e = edges.get(p);
		algorithm.newEdge(e);
		
		edit.addEdge(e);
		result.addEdges(algorithm.getEdges());
		
		frame.repaint();
	}

	private void clearScreen() {
		edit.clearEdges();
		result.clearEdges();
		frame.repaint();
	}
}
