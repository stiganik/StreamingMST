package gui;

import algorithm.StreamingMST;
import linkCutTrees.Algorithm;

public class Main {

	public static void main(String[] args) {
		StreamingMST algorithm = new Algorithm();
		MainWindow win = new MainWindow("Streaming MST", algorithm);
	}

}
