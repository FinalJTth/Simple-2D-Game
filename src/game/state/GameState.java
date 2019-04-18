package game.state;

import java.awt.Graphics2D;

import game.engine.GameThread;
import game.entity.creature.Player;
import game.tile.Tile;

public class GameState extends State {

	private Player player;

	public GameState(GameThread gameThread) {
		super(gameThread);
		player = new Player(gameThread, 100, 100);
		System.out.println("GameState init");
	}

	@Override
	public void update() {
		gameThread.getGame().getKeyboardListener().update();
		player.update();
	}

	@Override
	public void render(Graphics2D g2d) {
		player.render(g2d);
		Tile.tiles[0].render(g2d, 0, 0);
	}

	public Player getPlayer() {
		return this.player;
	}

}
