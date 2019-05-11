package game.entity.creature.minion.spawner;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.entity.creature.minion.BigBlob;
import game.entity.creature.minion.SmallBlob;
import game.utils.Timer;

public class WaveManager {
	
	private final GameThread gameThread;
	
	private long currentTime, lastTime;
	private LinkedList<ArrayList<Integer>> waveCreatures;
	private WaveSpawner spawner;

	public WaveManager(GameThread gameThread) {
		this.gameThread = gameThread;
		lastTime = System.currentTimeMillis();
		
		Timer.newGameTimer();
		waveCreatures = new LinkedList<ArrayList<Integer>>();
		// wave 0
		waveCreatures.add(new ArrayList<Integer> (Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)));
		// wave 1
		waveCreatures.add(new ArrayList<Integer> (Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)));
		// wave 2
		waveCreatures.add(new ArrayList<Integer> (Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)));
		// wave 3
		waveCreatures.add(new ArrayList<Integer> (Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)));
		// wave 4
		waveCreatures.add(new ArrayList<Integer> (Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)));
	}
	
	public void update() {
		handleWaveChange();
		spawner.update();
	}
	
	public void render(Graphics2D g2d) {
		spawner.render(g2d);
	}
	
	private void handleWaveChange() {
		if (gameThread.getWorld().getEntityManager().getWaveCreature().isEmpty()) {
			spawner = new WaveSpawner(gameThread, createCreatureListFromNumber());
		}
	}
	
	private ArrayList<Creatures> createCreatureListFromNumber() {
		ArrayList<Integer> list = waveCreatures.peek();
		ArrayList<Creatures> out = new ArrayList<Creatures>();
		float xPos = 0, yPos = 0;
		for (int i = 0 ;i<list.size();i++) {
			int creatureID = list.get(i);
			switch (i % 4 + 1) {	// split creature to 4 portal
			case 1:	// upper left
				xPos = 200;
				yPos = 200;
				break;
			case 2: // upper right
				xPos = 1000;
				yPos = 200;
				break;
			case 3: // lower left
				xPos = 200;
				yPos = 1000;
				break;
			case 4: // lower right
				xPos = 1000;
				yPos = 1000;
				break;
			}
			switch (creatureID) {
			case 0:
				out.add(new SmallBlob(gameThread, xPos, yPos));
				break;
			case 1:
				out.add(new BigBlob(gameThread, xPos, yPos));
				break;
			}
		}
		waveCreatures.remove(0);
		System.out.println(out);
		return out;
	}
}
