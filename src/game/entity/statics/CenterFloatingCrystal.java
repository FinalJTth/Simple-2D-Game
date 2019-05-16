package game.entity.statics;

import java.awt.Color;
import java.awt.Graphics2D;

import game.engine.GameThread;
import game.graphics.Assets;

public class CenterFloatingCrystal extends FloatingCrystal {

	private final int maxHealth;
	private int health;
	private float defense;
	private boolean isAlive;

	public CenterFloatingCrystal(GameThread gameThread, float xPos, float yPos, int width, int height) {
		super(gameThread, xPos, yPos, width, height);
		health = 10000;
		defense = 0;	// as % of damageTaken
		maxHealth = health;
		bounds.height = 0;
		bounds.width = 0;
		isAlive = true;
	}

	public void hurt(int damageTaken) {
		health -= damageTaken - defense * damageTaken;
		if (health <= 0) {
			isAlive = false;
		}
	}
	
	public void upgradeDefense() {
		defense += 0.1;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(getCurrentAnimationFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);
		g2d.drawImage(Assets.crystal_shadow, (int) (xPos - gameThread.getGameCamera().getxOffset() - 10),
				(int) (yPos - gameThread.getGameCamera().getyOffset()) + 320, 120, 90, null);
		g2d.setColor(Color.red);
//		g2d.drawRect((int) (xPos - gameThread.getGameCamera().getxOffset()),
//				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height);
//		// draw collision checking (bounding) box
//		g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
//				(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
//		g2d.drawOval((int) (xPos + width / 2 - gameThread.getGameCamera().getxOffset()),
//				(int) (yPos + height / 2 - gameThread.getGameCamera().getyOffset()), 10, 10);
		// draw healthBar
		g2d.fillRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()) - 20,
				(int) (yPos + bounds.y - 120 - gameThread.getGameCamera().getyOffset()), (int) getHealthBarWidth(), 8);
	}

	public float getHealthBarWidth() {
		// health, maxHealth, bounds.width is int, if dont cast float will calculate as
		// int
		return (float) health / maxHealth * 80;
	}

	public float getCenterX() {
		return (xPos + width / 2);
	}

	public float getCenterY() {
		return (yPos + height / 2);
	}
	
	public boolean isAlive() {
		return isAlive;
	}
}
