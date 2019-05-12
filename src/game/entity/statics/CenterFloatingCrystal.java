package game.entity.statics;

import java.awt.Color;
import java.awt.Graphics2D;

import game.engine.GameThread;

public class CenterFloatingCrystal extends FloatingCrystal {

	private final int maxHealth;
	private int health;

	public CenterFloatingCrystal(GameThread gameThread, float xPos, float yPos, int width, int height) {
		super(gameThread, xPos, yPos, width, height);
		health = 1000000000;
		maxHealth = health;
	}

	public void hurt(int damageTaken) {
		health -= damageTaken;
		if (health <= 0) {
			System.out.println("FUKING DEAD");
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(getCurrentAnimationFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);
		g2d.setColor(Color.red);
		g2d.drawRect((int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height);
		// draw collision checking (bounding) box
		g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
				(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
		g2d.drawOval((int) (xPos + width / 2 - gameThread.getGameCamera().getxOffset()),
				(int) (yPos + height / 2 - gameThread.getGameCamera().getyOffset()), 10, 10);
		// draw healthBar
		g2d.fillRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()) - 20,
				(int) (yPos + bounds.y - 120 - gameThread.getGameCamera().getyOffset()), (int) getHealthBarWidth(), 8);
	}

	public float getHealthBarWidth() {
		// health, maxHealth, bounds.width is int, if dont cast float will calculate as
		// int
		return (float) health / maxHealth * 70;
	}

	public float getCenterX() {
		return (xPos + width / 2);
	}

	public float getCenterY() {
		return (yPos + height / 2);
	}
}
