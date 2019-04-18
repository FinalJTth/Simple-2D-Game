package game.state;

import java.awt.Graphics2D;

import game.engine.GameThread;
import game.entity.creature.Player;
import game.tile.Tile;
import game.world.World;

public class GameState extends State {

	private Player player;
	private World world;

	public GameState(GameThread gameThread) {
		super(gameThread);
		player = new Player(gameThread, 100, 100);
		world = new World("res/worlds/world1.txt");
		System.out.println("GameState init");
	}

	@Override
	public void update() {
		gameThread.getGame().getKeyboardListener().update();
		player.update();
	}

	@Override
	public void render(Graphics2D g2d) {
		world.render(g2d);
		player.render(g2d);
	}

	public Player getPlayer() {
		return this.player;
	}

}
