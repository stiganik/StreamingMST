package gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import dataTypes.Point;

public class GraphPanel extends JPanel {
	private String name;
	private ArrayList<Point> nodes = new ArrayList<Point>();
	private static final int RADIUS = 30;
	private static final int PADDING = 20;
	
	public GraphPanel(String name) {
		 this.setBorder(BorderFactory.createLineBorder(Color.black));
		 this.name = name;
		 this.setBackground(new Color(255, 255, 255));
	}
	
	public void addPoints(ArrayList<Point> arr) {
		this.nodes = arr;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Draw custom stuff
		g2.drawString(name, 10, 20);
		
		// Draw points with label
		if(nodes.size() != 0) {
			int turn = 360 / nodes.size();
			int rotation = -90;
			int x = this.getWidth() - PADDING - RADIUS/2;
			int y = this.getHeight() / 2;
			FontMetrics metrics = g2.getFontMetrics();
			int halfHeight = (metrics.getAscent() - metrics.getDescent()) / 2;
			for(Point v : nodes){
				int[] p = rotate_point(this.getWidth()/2, this.getHeight()/2, rotation, x, y);
				int xp = p[0];
				int yp = p[1];
				
				g2.setColor(Color.BLACK);
				g2.fillOval(xp - (RADIUS/2), yp - (RADIUS/2), RADIUS, RADIUS);
				g2.setColor(Color.WHITE);
				int halfWidth = metrics.stringWidth(v.getLabel()) / 2;
				g2.drawString(v.getLabel(), xp - halfWidth, yp + halfHeight);
				
				rotation += turn;
			}
		}
	}
	private int[] rotate_point(float cx,float cy,float angle, int x, int y)
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
