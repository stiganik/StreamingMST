package gui;

import algorithm.StreamingMST;

public class Main {

	public static void main(String[] args) {
		StreamingMST algorithm = new StreamingMST();
		MainWindow win = new MainWindow("Streaming MST", algorithm);
	}

}
