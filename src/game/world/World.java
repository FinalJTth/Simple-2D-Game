package game.world;

import java.awt.Graphics2D;

import game.engine.GameThread;
import game.entity.EntityManager;
import game.entity.creature.Player;
import game.tile.CliffTile;
import game.tile.Tile;
import game.utils.Utils;

public class World {

	private GameThread gameThread;
	private int width, height;
	private int playerSpawnX, playerSpawnY;
	private int[][] tiles, randomedTiles;
	private EntityManager entityManager;

	public World(GameThread gameThread, String path) {
		this.gameThread = gameThread;

		loadWorld(path);

		entityManager = new EntityManager(gameThread, new Player(gameThread, 100, 100));
		entityManager.getPlayer().setxPos(playerSpawnX);
		entityManager.getPlayer().setyPos(playerSpawnY);
	}

	public void update() {
		entityManager.update();
	}

	public void render(Graphics2D g2d) {
		// render only tiles that player can see (on camera)
		int rowStart = (int) Math.max(0, gameThread.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int rowEnd = (int) Math.min(width,
				(gameThread.getGameCamera().getxOffset() + gameThread.getGame().getWindow().getWidth())
						/ Tile.TILE_WIDTH + 1);
		int colStart = (int) Math.max(0, gameThread.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int colEnd = (int) Math.min(height,
				(gameThread.getGameCamera().getyOffset() + gameThread.getGame().getWindow().getHeight())
						/ Tile.TILE_HEIGHT + 1);

		for (int row = rowStart; row < rowEnd; row++) {
			for (int col = colStart; col < colEnd; col++) {
				if (tiles[row][col] < 256) {
					getTile(row, col).render(g2d,
							(int) (row * Tile.TILE_WIDTH - gameThread.getGameCamera().getxOffset()),
							(int) (col * Tile.TILE_HEIGHT - gameThread.getGameCamera().getyOffset()),
							randomedTiles[row][col]);
				} else if (tiles[row][col] > 255) {
					if (getCliffTile(row, col).isCorner() == true && tiles[row][col] < 280) {
						Tile.spaceTiles.render(g2d,
								(int) (row * Tile.TILE_WIDTH - gameThread.getGameCamera().getxOffset()),
								(int) (col * Tile.TILE_HEIGHT - gameThread.getGameCamera().getyOffset()),
								randomedTiles[row][col]);
					}
					getCliffTile(row, col).render(g2d,
							(int) (row * Tile.TILE_WIDTH - gameThread.getGameCamera().getxOffset()),
							(int) (col * Tile.TILE_HEIGHT - gameThread.getGameCamera().getyOffset()));
				}
			}
		}
	}

	public int[][] getRawTile() {
		return tiles;
	}

	public CliffTile getCliffTile(int row, int col) {
		// return spaceTiles if out of world
		CliffTile t = CliffTile.cliffTiles[tiles[row][col] - 256]; // get Tile from tile id
		return t;
	}

	public Tile getTile(int row, int col) {
		// return spaceTiles if out of world
		if (row < 0 || col < 0 || row >= width || col >= height) {
			return Tile.spaceTiles;
		}

		Tile t = Tile.tiles[tiles[row][col]]; // get Tile from tile id
		if (t == null) {
			return Tile.dirtTiles; // return default tile
		}
		return t;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tmp = file.split("\\s+");
		width = Utils.parseInt(tmp[0]);
		height = Utils.parseInt(tmp[1]);
		playerSpawnX = Utils.parseInt(tmp[2]);
		playerSpawnY = Utils.parseInt(tmp[3]);

		tiles = new int[width][height];
		randomedTiles = new int[width][height];
		for (int col = 0; col < height; col++) {
			for (int row = 0; row < width; row++) {
				tiles[row][col] = Utils.parseInt(tmp[row + col * width + 4]); // turn 1D array to 2D
				if (tiles[row][col] < 256) {
					randomedTiles[row][col] = (int) (Math.random() * Tile.tiles[tiles[row][col]].getTileVariation());
				} else if (tiles[row][col] < 280) {
					randomedTiles[row][col] = (int) (Math.random() * Tile.dirtTiles.getTileVariation());
				} else {
					randomedTiles[row][col] = -1;
				}
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
