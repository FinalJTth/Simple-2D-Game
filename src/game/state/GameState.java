package game.state;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;

import game.engine.GameThread;
import game.entity.creature.Player;
import game.graphics.Assets;
import game.soundFX.SoundPlayer;
import game.tile.Tile;
import game.world.World;

public class GameState extends State {

	private World world;

	public GameState(GameThread gameThread) {
		super(gameThread);
		world = new World(gameThread, "res/worlds/world1.txt");
		System.out.println("GameState init");
		SoundPlayer.bgm.setVolume(0.5);
		SoundPlayer.bgm.playNonStop();
	}

	@Override
	public void update() {
		gameThread.getKeyManager().update();
		world.update();
		if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_ESCAPE)) {
			gameThread.togglePauseGame();
			gameThread.setMenuState();
		}
		
	}

	@Override
	public void render(Graphics2D g2d) {
		world.render(g2d);
		drawCrosshair(g2d);
	}

	private void drawCrosshair(Graphics2D g2d) {
		Stroke defaultStroke = g2d.getStroke();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
		g2d.setStroke(dashed);
		g2d.drawLine((int) (world.getEntityManager().getPlayer().getxPos()

				+ world.getEntityManager().getPlayer().getWidth() / 2 - gameThread.getGameCamera().getxOffset()),
				(int) (world.getEntityManager().getPlayer().getyPos()
						+ world.getEntityManager().getPlayer().getHeight() / 2
						- gameThread.getGameCamera().getyOffset()),
				gameThread.getMouseManager().getMouseX(), gameThread.getMouseManager().getMouseY());
		g2d.setStroke(defaultStroke);
		g2d.drawImage(Assets.crosshair, gameThread.getMouseManager().getMouseX() - 25,
				gameThread.getMouseManager().getMouseY() - 25, 50, 50, null);
	}

	public World getWorld() {
		return world;
	}

}
