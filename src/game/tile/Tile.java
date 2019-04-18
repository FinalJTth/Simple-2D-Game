package game.tile;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {

	// Static stuff
	
	public static Tile[] tiles = new Tile[256];	// can access all tiles instances through Tile class
	public static Tile grassTile = new GrassTile(0);	// set id of grassTile = 0
	public static Tile lavaTile = new LavaTile(1);
	
	// Class stuff
	
	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		// add each tile to array with id as index
		tiles[id] = this;
	}

	public void update() {

	}
	
	// Check if creatures able to walk over tile
	public boolean isWalkable() {
		return true;
	}

	public void render(Graphics2D g2d, int x, int y) {
		g2d.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	public int getId() {
		return id;
	}
}
