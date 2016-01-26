package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import dataTypes.Edge;
import linkCutTrees.Vertex;

public class GraphPanel extends JPanel {
	private String name;
	private ArrayList<Vertex> nodes = new ArrayList<Vertex>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private static final int RADIUS = 30;
	private static final int WEIGHT_RADIUS = 18;
	private static final int PADDING = 20;
	
	public GraphPanel(String name) {
		 this.setBorder(BorderFactory.createLineBorder(Color.black));
		 this.name = name;
		 this.setBackground(new Color(255, 255, 255));
	}
	
	public void addPoints(ArrayList<Vertex> arr) {
		this.nodes = arr;
		double turn = 360.0 / nodes.size();
		double rotation = -90;
		int x = this.getWidth() - PADDING - RADIUS/2;
		int y = this.getHeight() / 2;
		for(Vertex v : nodes){
			int[] p = rotate_point(this.getWidth()/2, this.getHeight()/2, rotation, x, y);
			v.setX(p[0]);
			v.setY(p[1]);
			rotation += turn;
		}
	}
	
	public void addEdge(String p1, String p2, int weight){
		Edge e = new Edge();
		for(Vertex v : nodes) {
			if(v.getLabel().equalsIgnoreCase(p1)){
				e.setVertex1(v);
			}
			if(v.getLabel().equalsIgnoreCase(p2)){
				e.setVertex2(v);
			}
		}
		e.setWeight(weight);
		edges.add(e);
	} 
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Draw custom stuff
		g2.drawString(name, 10, 20);
		
		// Draw lines before points
		if(edges.size() != 0) {
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(6));
			for(Edge e : edges){
				g2.drawLine(
						e.getVertex1().getX(),
						e.getVertex1().getY(),
						e.getVertex2().getX(),
						e.getVertex2().getY());
			}
		}
		
		// Draw points with label
		if(nodes.size() != 0) {
			FontMetrics metrics = g2.getFontMetrics();
			int halfHeight = (metrics.getAscent() - metrics.getDescent()) / 2;
			for(Vertex v : nodes){
				g2.setColor(Color.BLACK);
				g2.fillOval(v.getX() - (RADIUS/2), v.getY() - (RADIUS/2), RADIUS, RADIUS);
				g2.setColor(Color.WHITE);
				int halfWidth = metrics.stringWidth(v.getLabel()) / 2;
				g2.drawString(v.getLabel(), v.getX() - halfWidth, v.getY() + halfHeight);
			}
		}
		
		// Draw weights
		if(edges.size() != 0) {
			FontMetrics metrics = g2.getFontMetrics();
			int halfHeight = (metrics.getAscent() - metrics.getDescent()) / 2;
			for(Edge e : edges){
				int x = 0;
				int y = 0;
				x = e.getVertex1().getX() - ((e.getVertex1().getX() - e.getVertex2().getX()) / 2);
				y = e.getVertex1().getY() - ((e.getVertex1().getY() - e.getVertex2().getY()) / 2);
				
				g2.setColor(Color.BLUE);
				g2.fillOval(x - (WEIGHT_RADIUS/2), y - (WEIGHT_RADIUS/2), WEIGHT_RADIUS, WEIGHT_RADIUS);
				g2.setColor(Color.WHITE);
				int halfWidth = metrics.stringWidth(String.valueOf(e.getWeight())) / 2;
				g2.drawString(String.valueOf(e.getWeight()), x - halfWidth, y + halfHeight);
			}
		}
		
	}
	private int[] rotate_point(float cx,float cy,double angle, int x, int y)
	{
	  float s = (float) Math.sin(Math.toRadians(angle));
	  float c = (float) Math.cos(Math.toRadians(angle));

	  // translate point back to origin:
	  x -= cx;
	  y -= cy;

	  // rotate point
	  float xnew = x * c - y * s;
	  float ynew = x * s + y * c;

	  // translate point back:
	  x = (int) (xnew + cx);
	  y = (int) (ynew + cy);
	  int[] ret = {x,y};
	  return ret;
	}
}
