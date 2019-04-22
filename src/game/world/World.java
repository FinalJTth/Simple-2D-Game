package game.world;

import java.awt.Graphics2D;

import game.engine.GameThread;
import game.tile.Tile;
import game.utils.Utils;

public class World {

	private GameThread gameThread;
	private int width, height;
	private int playerSpawnX, playerSpawnY;
	private int[][] tiles;

	public World(GameThread gameThread, String path) {
		loadWorld(path);
		this.gameThread = gameThread;
	}

	public void update() {

	}

	public void render(Graphics2D g2d) {
		// render only tiles that player can see (on camera)
		int rowStart = (int) Math.max(0, gameThread.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int rowEnd = (int) Math.min(width, (gameThread.getGameCamera().getxOffset() + gameThread.getGame().getWindow().getWidth()) / Tile.TILE_WIDTH + 1);
		int colStart = (int) Math.max(0, gameThread.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int colEnd = (int) Math.min(height, (gameThread.getGameCamera().getyOffset() + gameThread.getGame().getWindow().getHeight()) / Tile.TILE_HEIGHT + 1);

		for (int row = rowStart; row < rowEnd; row++) {
			for (int col = colStart; col < colEnd; col++) {
				getTile(row, col).render(g2d, (int) (row * Tile.TILE_WIDTH - gameThread.getGameCamera().getxOffset()),
						(int) (col * Tile.TILE_HEIGHT - gameThread.getGameCamera().getyOffset()));
			}
		}
	}

	public Tile getTile(int row, int col) {
		// return grassTile if out of world
		if(row < 0 || col < 0 || row >= width || col >= height) {
			return Tile.grassTile;
		}
		
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
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
