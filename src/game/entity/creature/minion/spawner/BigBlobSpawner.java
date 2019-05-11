package game.entity.creature.minion.spawner;

import java.awt.Graphics2D;

import game.engine.GameThread;
import game.entity.creature.minion.BigBlob;

public class BigBlobSpawner extends MinionSpawner {

	protected static final int DELAY = 10000;

	public BigBlobSpawner(GameThread gameThread, int xPos, int yPos) {
		super(gameThread, xPos, yPos);
	}

	@Override
	public void spawnMinion() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > DELAY) {
			timer = 0;
			gameThread.getWorld().getEntityManager().addEntity(new BigBlob(gameThread, xPos, yPos));
		}
	}

	public void render(Graphics2D g2d) {
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), 200, 200, null);
	}
}
