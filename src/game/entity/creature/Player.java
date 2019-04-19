package game.entity.creature;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.engine.Game;
import game.engine.GameThread;
import game.graphics.Assets;

public class Player extends Creatures {

	private Game game;

	public Player(GameThread gameThread, float x, float y) {
		super(gameThread, x, y, Creatures.DEFAULT_CREATURE_WIDTH, Creatures.DEFAULT_CREATURE_HEIGHT);
		this.game = gameThread.getGame();
		System.out.println("Player init");
	}

	@Override
	public void update() {
		getKeyboardInput();
		move();
		gameThread.getGameCamera().focusCameraOnEntity(this);
	}

	public void getKeyboardInput() {
		xMove = 0;
		yMove = 0;

		if (game.getKeyboardListener().isKeyPressed(KeyEvent.VK_W)) {
			yMove = -speed;
		}
		if (game.getKeyboardListener().isKeyPressed(KeyEvent.VK_S)) {
			yMove = speed;
		}
		if (game.getKeyboardListener().isKeyPressed(KeyEvent.VK_A)) {
			xMove = -speed;
		}
		if (game.getKeyboardListener().isKeyPressed(KeyEvent.VK_D)) {
			xMove = speed;
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(Assets.player, (int) (xPos - gameThread.getGameCamera().getxOffset()), (int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);
	}

}
