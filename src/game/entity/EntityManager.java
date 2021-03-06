package game.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.entity.creature.Player;
import game.entity.creature.minion.spawner.WaveManager;
import game.entity.statics.CenterFloatingCrystal;

public class EntityManager {

	private GameThread gameThread;
	private Player player;
	private ArrayList<Entity> entities;
	private CenterFloatingCrystal centerCrystal;
	private WaveManager waveManager;

	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) {
			if (a.getyPos() + a.getHeight() < b.getyPos() + b.getHeight())
				return -1;
			return 1;
		};
	};

	public EntityManager(GameThread gameThread, Player player) {
		this.gameThread = gameThread;
		this.player = player;

		waveManager = new WaveManager(gameThread);

		entities = new ArrayList<Entity>();
		addEntity(player);
		centerCrystal = new CenterFloatingCrystal(gameThread, 950, 800, 100, 300);
		addEntity(centerCrystal);
//		addEntity(new Tree(gameThread, 550, 200, 60, 75));
//		addEntity(new BigBlob(gameThread, 100, 400));
//		addEntity(new BigBlob(gameThread, 800, 400));
//		addEntity(new SmallBlob(gameThread, 200, 600));
//		addEntity(new SmallBlob(gameThread, 100, 700));
//		addEntity(new SmallBlob(gameThread, 800, 700));
//		addEntity(new SmallBlob(gameThread, 300, 500));
		// addEntity(new EvilSorcerer(gameThread, 650, 800, 1000));
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
		}
		entities.sort(renderSorter);
		waveManager.update();
	}

	public void render(Graphics2D g2d) {
		for (Entity e : entities) {
			e.render(g2d);
		}
		waveManager.render(g2d);
		if (!isAnyCreatureAlive()) {
			Color defaultColor = g2d.getColor();
			g2d.setColor(Color.red);
		    g2d.setFont(new Font("Sans Serif", Font.PLAIN, 48));
			g2d.drawString(String.format("Wave : %d", waveManager.getWaveNo()), 300, 200);
			g2d.setColor(defaultColor);
		}
	}

	// Getters & Setters

	public boolean isAnyCreatureAlive() {
		for (Entity e : entities) {
			if (e instanceof Creatures && !(e instanceof Player)) {
				return true;
			}
		}
		return false;
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public GameThread getGameThread() {
		return gameThread;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public CenterFloatingCrystal getCenterCrystal() {
		return centerCrystal;
	}

	public WaveManager getWaveManager() {
		return waveManager;
	}
}
