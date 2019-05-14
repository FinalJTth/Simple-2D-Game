package game.display;

import game.engine.GameThread;

public class MyGame {
	
	public MyGame() {
		new Thread(new GameThread()).start();
	}

	public static void main(String[] args) {
		new MyGame();
	}

}