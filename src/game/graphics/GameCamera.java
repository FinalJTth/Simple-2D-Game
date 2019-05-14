package game.graphics;

import game.engine.GameThread;
import game.entity.Entity;
import game.tile.Tile;

public class GameCamera {

	private float xOffset, yOffset;
	private final GameThread gameThread;

	public GameCamera(GameThread gameThread, float xOffset, float yOffset) {
		this.gameThread = gameThread;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	// set camera to follow entity
	public void focusCameraOnEntity(Entity e) {
		xOffset = e.getxPos() - gameThread .getWindow().getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getyPos() - gameThread .getWindow().getHeight() / 2 + e.getHeight() / 2;

		checkBlankSpace();
	}

	// hide out of world area
	public void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > gameThread.getGameState().getWorld().getWidth() * Tile.TILE_WIDTH
				- gameThread .getWindow().getWidth()) {
			xOffset = gameThread.getGameState().getWorld().getWidth() * Tile.TILE_WIDTH
					- gameThread .getWindow().getWidth();
		}
		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > gameThread.getGameState().getWorld().getHeight() * Tile.TILE_HEIGHT
				- gameThread .getWindow().getHeight()) {
			yOffset = gameThread.getGameState().getWorld().getHeight() * Tile.TILE_HEIGHT
					- gameThread.getWindow().getHeight();
		}
	}

	// move camera
	public void move(float xAmount, float yAmount) {
		xOffset += xAmount;
		yOffset += yAmount;

		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
