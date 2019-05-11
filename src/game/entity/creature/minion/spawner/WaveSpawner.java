package game.entity.creature.minion.spawner;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.entity.creature.minion.BigBlob;

public class WaveSpawner extends MinionSpawner {
	
	private ArrayList<Creatures> minionToSpawn;
	private int minionIndex;

	public WaveSpawner(GameThread gameThread, int xPos, int yPos, ArrayList<Creatures> minionToSpawn) {
		super(gameThread, xPos, yPos);
		this.minionToSpawn = minionToSpawn;
		minionIndex = 0;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), 200, 200, null);
	}

	@Override
	public void spawnMinion() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > DELAY) {
			timer = 0;
			if (minionIndex < minionToSpawn.size())
				gameThread.getWorld().getEntityManager().addWaveCreature(minionToSpawn.get(minionIndex));
			minionIndex++;
		}
	}

}
