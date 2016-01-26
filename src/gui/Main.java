package gui;

import algorithm.StreamingMST;
import linkcuttrees.Algorithm;

public class Main {

	public static void main(String[] args) {
		StreamingMST algorithm = new Algorithm();
		MainWindow win = new MainWindow("Streaming MST", algorithm);
	}

}
