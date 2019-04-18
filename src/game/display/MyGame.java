package game.display;

import game.engine.Game;

public class MyGame {

	private Game game;

	public MyGame() {
		this.game = new Game(800, 600, "My Game");
		this.game.getScreenFactory().showScreen(new MyScreen(game.getScreenFactory()));
	}

	public static void main(String[] args) {
		new MyGame();
	}

}