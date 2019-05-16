package game.entity.creature.minion;

import game.engine.GameThread;

public abstract class CrystalAttackingMinion extends Minion {

	public CrystalAttackingMinion(GameThread gameThread, float xPos, float yPos, int width, int height, int health,
			float speed, int attackRange, int attackDamage) {
		super(gameThread, xPos, yPos, width, height, health, speed, attackRange, attackDamage);
		// TODO Auto-generated constructor stub
	}

	public abstract void attackCrystal();

	public float getDistanceToCrystal() {
		float crystalX = gameThread.getWorld().getEntityManager().getCenterCrystal().getCenterX();
		float crystalY = gameThread.getWorld().getEntityManager().getCenterCrystal().getCenterY();

		return (float) (Math
				.sqrt(Math.pow(xPos + width / 2 - crystalX, 2) + Math.pow(yPos + height / 2 - crystalY, 2)));
	}

	public void moveToCrystal() {
		float crystalX = gameThread.getWorld().getEntityManager().getCenterCrystal().getCenterX();
		float crystalY = gameThread.getWorld().getEntityManager().getCenterCrystal().getCenterY();
		isWalking = true;

		if (xPos + bounds.x < crystalX) {
			xMove = speed;
		} else if (xPos + bounds.x > crystalX) {
			xMove = -speed;
		}
		if (yPos + bounds.y + bounds.height < crystalY) {
			yMove = speed;
		} else if (yPos + bounds.y + bounds.height > crystalY) {
			yMove = -speed;
		} else if (yPos + bounds.y + bounds.height == crystalY) {
			yMove = 0;
			// System.out.println("y = 0");
		}
		move();
	}

	public void setFacingDirectionFromCrystalPos() {
		float crystalX = gameThread.getWorld().getEntityManager().getCenterCrystal().getxPos();
		if (crystalX > xPos) {
			facingDirection = "RIGHT";
		}

		else
			facingDirection = "LEFT";
	}

}
