package game.graphics;

import game.engine.GameThread;
import game.entity.Entity;

public class GameCamera {

	private float xOffset, yOffset;
	private GameThread gameThread;

	public GameCamera(GameThread gameThread, float xOffset, float yOffset) {
		this.gameThread = gameThread;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	// set camera to follow entity
	public void focusCameraOnEntity(Entity e) {
		xOffset = e.getxPos() - gameThread.getGame().getWindow().getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getyPos() - gameThread.getGame().getWindow().getHeight() / 2 + e.getHeight() / 2;
 	}

	// move camera
	public void move(float xAmount, float yAmount) {
		xOffset += xAmount;
		yOffset = yAmount;
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
