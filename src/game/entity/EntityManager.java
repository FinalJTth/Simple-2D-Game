package game.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.entity.creature.Player;
import game.entity.creature.minion.BigBlob;
import game.entity.creature.minion.EvilSorcerer;
import game.entity.creature.minion.SmallBlob;
import game.entity.creature.minion.spawner.BigBlobSpawner;
import game.entity.creature.minion.spawner.MinionSpawner;
import game.entity.creature.minion.spawner.WaveManager;
import game.entity.statics.CenterFloatingCrystal;
import game.entity.statics.FloatingCrystal;
import game.entity.statics.Tree;

public class EntityManager {

	private GameThread gameThread;
	private Player player;
	private ArrayList<Entity> entities;
	private ArrayList<Creatures> waveCreatures;
	private MinionSpawner bigBlobSpawner;
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
		centerCrystal = new CenterFloatingCrystal(gameThread, 600, 600, 100, 300);
		addEntity(centerCrystal);
		addEntity(new Tree(gameThread, 550, 200, 60, 75));
		addEntity(new BigBlob(gameThread, 100, 400));
		addEntity(new BigBlob(gameThread, 800, 400));
		// addEntity(new EvilSorcerer(gameThread, 650, 800, 1000));
		addEntity(new SmallBlob(gameThread, 200, 600));
		addEntity(new SmallBlob(gameThread, 100, 700));
		addEntity(new SmallBlob(gameThread, 800, 700));
//		addEntity(new SmallBlob(gameThread, 300, 500));
		bigBlobSpawner = new BigBlobSpawner(gameThread, 800, 800);
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
		}
		entities.sort(renderSorter);
		bigBlobSpawner.update();
		waveManager.update();
	}

	public void render(Graphics2D g2d) {
		bigBlobSpawner.render(g2d);
		for (Entity e : entities) {
			e.render(g2d);
		}
	}

	// Getters & Setters
	public void addWaveCreature(Creatures e) {
		waveCreatures.add(e);
	}

	public ArrayList<Creatures> getWaveCreature() {
		return waveCreatures;
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

}
