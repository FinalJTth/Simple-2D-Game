package game.entity.creature.minion.spawner;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.sun.glass.events.KeyEvent;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.entity.creature.minion.BigBlob;
import game.entity.creature.minion.SmallBlob;
import game.exception.OutOfWaveException;
import game.utils.Timer;

public class WaveManager {

	private final long DELAY_WAVE = 7000;
	private final GameThread gameThread;

	private LinkedList<ArrayList<Integer>> waveCreatures;
	private WaveSpawner spawner;
	private long timer;
	private boolean isTimerStarted, isUpgraded;
	private int waveNo;

	public WaveManager(GameThread gameThread) {
		this.gameThread = gameThread;
		Timer.newGameTimer();
		waveNo = 2;
		isUpgraded = false;

		waveCreatures = new LinkedList<ArrayList<Integer>>();
		// wave 0
		waveCreatures
				.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
		// wave 1
		waveCreatures.add(new ArrayList<Integer>(
				Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
		// wave 2
		waveCreatures.add(
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)));
		// wave 3
		waveCreatures.add(new ArrayList<Integer>(
				Arrays.asList(0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0)));
		// wave 4
		waveCreatures
				.add(new ArrayList<Integer>(Arrays.asList(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1)));
		// wave 5
		waveCreatures.add(new ArrayList<Integer>(
				Arrays.asList(1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0)));
		// wave 6
		waveCreatures.add(new ArrayList<Integer>(
				Arrays.asList(1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)));
		// wave 7
		waveCreatures.add(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1)));
		// wave 8
		waveCreatures
				.add(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)));
		// wave 9
		waveCreatures.add(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1,
				0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0)));

		try {
			spawner = new WaveSpawner(gameThread, createCreatureListFromNumber());
		} catch (OutOfWaveException e) {
			System.out.println("out of waves");
		}
	}

	public void update() {
		spawner.update();
		handleWaveChange();

//		System.out.println(Timer.getCurrentTime());
	}

	public void render(Graphics2D g2d) {
		if (!gameThread.getWorld().getEntityManager().isAnyCreatureAlive() && !isUpgraded) {
			g2d.setFont(new Font("Sans Serif", Font.PLAIN, 24));
			g2d.drawString("press up arrow to upgrade attack", 240, 300);
			g2d.drawString("press down arrow to increase mana", 240, 340);
			g2d.drawString("press left arrow to upgrade crystal's defense", 240, 380);
		}
		spawner.render(g2d);
	}

	private void handleWaveChange() {
		if (!gameThread.getWorld().getEntityManager().isAnyCreatureAlive()) {
			if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_DOWN) && !isUpgraded) {
				gameThread.getWorld().getEntityManager().getPlayer().upgradeMana();
				isUpgraded = true;
			} else if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_UP) && !isUpgraded) {
				gameThread.getWorld().getEntityManager().getPlayer().upgradeAttack();
				isUpgraded = true;
			} else if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_LEFT) && !isUpgraded) {
				gameThread.getWorld().getEntityManager().getCenterCrystal().upgradeDefense();
				isUpgraded = true;
			}
			if (!isTimerStarted) {
				timer = Timer.getCurrentTime();
				isTimerStarted = true;
			}
			if (Timer.getCurrentTime() - timer > DELAY_WAVE) {
				waveNo++;
				isUpgraded = false;
				isTimerStarted = false;
				try {
					spawner = new WaveSpawner(gameThread, createCreatureListFromNumber());
				} catch (OutOfWaveException e) {
					System.out.println("out of waves");
				}
			}

		}
	}

	private ArrayList<Creatures> createCreatureListFromNumber() throws OutOfWaveException {
		if (waveCreatures.size() == 0)
			throw new OutOfWaveException();
		ArrayList<Integer> list = waveCreatures.peek();
		ArrayList<Creatures> out = new ArrayList<Creatures>();
		float xPos = 0, yPos = 0;
		for (int i = 0; i < list.size(); i++) {
			int creatureID = list.get(i);
			switch (i % 4 + 1) { // split creature to 4 portal
			case 1: // upper left
				xPos = 250;
				yPos = 230;
				break;
			case 2: // upper right
				xPos = 1630;
				yPos = 250;
				break;
			case 3: // lower left
				xPos = 250;
				yPos = 1620;
				break;
			case 4: // lower right
				xPos = 1630;
				yPos = 1590;
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
		return out;
	}

	public int getWaveNo() {
		return waveNo;
	}
}
