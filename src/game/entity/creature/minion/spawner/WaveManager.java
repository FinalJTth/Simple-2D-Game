package game.entity.creature.minion.spawner;

import java.util.ArrayList;
import java.util.Arrays;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.utils.Timer;

public class WaveManager {
	
	private final GameThread gameThread;
	
	private long currentTime, lastTime;
	private ArrayList<ArrayList<Integer>> waveCreatures;

	public WaveManager(GameThread gameThread) {
		this.gameThread = gameThread;
		lastTime = System.currentTimeMillis();
		
		Timer.newGameTimer();
		waveCreatures = new ArrayList<ArrayList<Integer>>();
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
		
	}
	
	private void handleWaveChange() {
		if (gameThread.getWorld().getEntityManager().getWaveCreature().isEmpty()) {
			
		}
	}
	
	private ArrayList<Creatures> createCreatureListFromNumber() {
		return null;
	}
	
}
