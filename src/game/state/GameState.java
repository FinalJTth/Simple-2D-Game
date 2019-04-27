package game.state;

import java.awt.Graphics2D;

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
		gameThread.getGame().getKeyboardListener().update();
		world.update();
	}

	@Override
	public void render(Graphics2D g2d) {
		world.render(g2d);
	}
	
	public World getWorld() {
		return world;
	}

}
