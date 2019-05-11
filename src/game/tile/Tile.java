package game.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.graphics.Assets;

public class Tile {

	// Static stuff

	public static Tile[] tiles = new Tile[256]; // can access all tiles instances through Tile class
	public static Tile dirtTiles = new WalkableTile(Assets.dirt_tile, 0);
	public static Tile rockTiles = new WalkableTile(Assets.rock_tile, 1);
	public static Tile spaceTiles = new SolidTile(Assets.space_tile, 2);

	// Class stuff

	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;

	protected BufferedImage[] texture;
	protected final int id;

	public Tile(BufferedImage[] texture, int id) {
		this.texture = texture;
		this.id = id;

		// add each tile to array with id as index
		tiles[id] = this;
	}

	public void update() {

	}

	// Check if creatures able to walk over tile
	public boolean isSolid() {
		return true;
	}

	public void render(Graphics2D g2d, int x, int y, int randInt) {
		g2d.drawImage(texture[randInt], x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	public int getId() {
		return id;
	}

	public int getTileVariation() {
		return texture.length;
	}

	public BufferedImage getRandomTexture() {
		return texture[(int) (Math.random() * texture.length)];
	}
}
