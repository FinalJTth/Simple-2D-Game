package game.world;

import java.awt.Graphics2D;

import game.tile.Tile;
import game.utils.Utils;

public class World {

	private int width, height;
	private int playerSpawnX, playerSpawnY;
	private int[][] tiles;

	public World(String path) {
		loadWorld(path);
	}

	public void update() {

	}

	public void render(Graphics2D g2d) {
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				getTile(row, col).render(g2d, row * Tile.TILE_WIDTH, col * Tile.TILE_HEIGHT);
			}
		}
	}

	public Tile getTile(int row, int col) {
		Tile t = Tile.tiles[tiles[row][col]]; // get Tile from tile id
		if (t == null) {
			return Tile.grassTile; // return default tile
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
		for (int col = 0; col < height; col++) {
			for (int row = 0; row < width; row++) {
				tiles[row][col] = Utils.parseInt(tmp[row + col * width + 4]); // turn 1D array to 2D
			}
		}
	}

}
