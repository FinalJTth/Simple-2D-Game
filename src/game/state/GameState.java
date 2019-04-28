package game.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.engine.GameThread;
import game.entity.creature.Player;
import game.entity.statics.Tree;
import game.tile.Tile;
import game.world.World;

public class GameState extends State {

	private World world;

	public GameState(GameThread gameThread) {
		super(gameThread);
		world = new World(gameThread, "res/worlds/world1.txt");
		System.out.println("GameState init");
	}

	@Override
	public void update() {
		gameThread.getGame().getKeyManager().update();
		world.update();
		if (gameThread.getGame().getKeyManager().isKeyPressed(KeyEvent.VK_ESCAPE)) {
			gameThread.togglePauseGame();
			gameThread.setMenuState();
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		world.render(g2d);
	}
	
	public World getWorld() {
		return world;
	}

}
