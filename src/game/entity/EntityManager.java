package game.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

import game.engine.GameThread;
import game.entity.creature.Player;
import game.entity.creature.minion.BigBlob;

public class EntityManager {

	private GameThread gameThread;
	private Player player;
	private ArrayList<Entity> entities;
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getyPos() + a.getHeight() < b.getyPos() + b.getHeight())
				return -1;
			return 1;
		};
	};

	public EntityManager(GameThread gameThread, Player player) {
		this.gameThread = gameThread;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
		addEntity(new BigBlob(gameThread, 800, 800, 200, 200));
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
		}
		entities.sort(renderSorter);

	}

	public void render(Graphics2D g2d) {
		for (Entity e : entities) {
			e.render(g2d);
		}
	}

	// Getters & Setters

	public void addEntity(Entity e) {
		entities.add(e);
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

}
