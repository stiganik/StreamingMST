package gui;

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
import dataTypes.Edge;
import linkCutTrees.Vertex;
import utils.CSVParser;

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
					ArrayList<Vertex> points = createNodes(nodes);
					edit.addPoints(points);
					result.addPoints(points);
					/*edit.addEdge("20", "8", 3);
					edit.addEdge("20", "11", 2);
					edit.addEdge("20", "15", 7);
					edit.addEdge("11", "8", 5);
					edit.addEdge("7", "8", 1);
					edit.addEdge("15", "7", 10);
					
					result.addEdge("20", "11", 2);
					result.addEdge("20", "15", 7);
					result.addEdge("20", "8", 3);
					result.addEdge("8", "7", 1);*/
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
					CSVParser par = new CSVParser();
					if(par.parse(file)){
						ArrayList<Vertex> nodes = par.getNodes();
						ArrayList<Edge> edges = par.getEdges();
						edit.addPoints(nodes);
						result.addPoints(nodes);
						for(Edge edge : edges){
							result.addEdge(edge);
						}
						
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
}
