package game.display;

import game.engine.Game;

public class MyGame {

	public MyGame() {
		new Game(800, 600, "My Game");
	}

	public static void main(String[] args) {
		new MyGame();
	}

}