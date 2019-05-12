package game.entity.creature.minion.spawner;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.entity.creature.minion.BigBlob;

public class WaveSpawner extends MinionSpawner {

	private static final int DELAY = 5000;
	
	private ArrayList<Creatures> minionToSpawn;
	private int minionIndex;
	private final float xPos1 = 200, yPos1 = 200, xPos2 = 1000, yPos2 = 200, xPos3 = 200, yPos3 = 1000, xPos4 = 1000, yPos4 = 1000;

	public WaveSpawner(GameThread gameThread, ArrayList<Creatures> minionToSpawn) {
		super(gameThread, 0, 0);
		this.minionToSpawn = minionToSpawn;
		minionIndex = 0;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos1 - gameThread.getGameCamera().getxOffset()),
				(int) (yPos1 - gameThread.getGameCamera().getyOffset()), 200, 200, null);
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos2 - gameThread.getGameCamera().getxOffset()),
				(int) (yPos2 - gameThread.getGameCamera().getyOffset()), 200, 200, null);
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos3 - gameThread.getGameCamera().getxOffset()),
				(int) (yPos3 - gameThread.getGameCamera().getyOffset()), 200, 200, null);
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos4 - gameThread.getGameCamera().getxOffset()),
				(int) (yPos4 - gameThread.getGameCamera().getyOffset()), 200, 200, null);
	}

	@Override
	public void spawnMinion() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > DELAY) {
			timer = 0;
			if (minionIndex < minionToSpawn.size()) {
				for (int i = 0; i<4 ;i++) {
					gameThread.getWorld().getEntityManager().addEntity(minionToSpawn.get(minionIndex));
					minionIndex++;
					if (minionIndex >= minionToSpawn.size())
						break;
				}
			}
			
		}
	}

}
