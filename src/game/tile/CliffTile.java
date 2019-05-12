package game.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.graphics.Assets;

public class CliffTile {

	public static CliffTile[] cliffTiles = new CliffTile[256];
	public static CliffTile dirtCliffTilesNW = new CliffTile(Assets.dirt_cliff_tile[0], 256, true);
	public static CliffTile dirtCliffTilesN = new CliffTile(Assets.dirt_cliff_tile[1], 257, false);
	public static CliffTile dirtCliffTilesNE = new CliffTile(Assets.dirt_cliff_tile[2], 258, true);
	public static CliffTile dirtCliffTilesW = new CliffTile(Assets.dirt_cliff_tile[3], 259, false);
	public static CliffTile dirtCliffTilesM = new CliffTile(Assets.dirt_cliff_tile[4], 260, false);
	public static CliffTile dirtCliffTilesE = new CliffTile(Assets.dirt_cliff_tile[5], 261, false);
	public static CliffTile dirtCliffTilesSW = new CliffTile(Assets.dirt_cliff_tile[6], 262, false);
	public static CliffTile dirtCliffTilesS = new CliffTile(Assets.dirt_cliff_tile[7], 263, false);
	public static CliffTile dirtCliffTilesSE = new CliffTile(Assets.dirt_cliff_tile[8], 264, false);
	public static CliffTile dirtTowerTilesLeft = new CliffTile(Assets.dirt_cliff_tile[9], 265, false);
	public static CliffTile dirtTowerTilesMiddle = new CliffTile(Assets.dirt_cliff_tile[10], 266, false);
	public static CliffTile dirtTowerBaseTilesRight = new CliffTile(Assets.dirt_cliff_tile[11], 267, false);
	public static CliffTile dirtCliffDirtBaseTilesLeft = new CliffTile(Assets.dirt_cliff_tile[12], 268, true);
	public static CliffTile dirtCliffDirtBaseTilesMiddle = new CliffTile(Assets.dirt_cliff_tile[13], 269, false);
	public static CliffTile dirtCliffDirtBaseTilesRight = new CliffTile(Assets.dirt_cliff_tile[14], 270, false);
	public static CliffTile dirtCliffStoneBaseTilesLeft = new CliffTile(Assets.dirt_cliff_tile[15], 271, false);
	public static CliffTile dirtCliffStoneBaseTilesMiddle = new CliffTile(Assets.dirt_cliff_tile[16], 272, false);
	public static CliffTile dirtCliffStoneBaseTilesRight = new CliffTile(Assets.dirt_cliff_tile[17], 273, false);
	public static CliffTile dirtCliffVoidBaseTilesLeft = new CliffTile(Assets.dirt_cliff_tile[18], 274, true);
	public static CliffTile dirtCliffVoidBaseTilesMiddle = new CliffTile(Assets.dirt_cliff_tile[19], 275, false);
	public static CliffTile dirtCliffVoidBaseTilesRight = new CliffTile(Assets.dirt_cliff_tile[20], 276, true);
	public static CliffTile dirtCliffSpaceBaseTilesLeft = new CliffTile(Assets.dirt_cliff_tile[21], 277, true);
	public static CliffTile dirtCliffSpaceBaseTilesMiddle = new CliffTile(Assets.dirt_cliff_tile[22], 278, false);
	public static CliffTile dirtCliffSpaceBaseTilesRight = new CliffTile(Assets.dirt_cliff_tile[23], 279, true);

	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;

	protected BufferedImage texture;
	protected final int id;
	protected boolean isCorner;

	public CliffTile(BufferedImage texture, int id, boolean isCorner) {
		this.texture = texture;
		this.id = id - 256;
		this.isCorner = isCorner;
		cliffTiles[this.id] = this;
	}

	public boolean isSolid() {
		return false;
	}

	public boolean isCorner() {
		return isCorner;
	}

	public void render(Graphics2D g2d, int x, int y) {
		g2d.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	public int getId() {
		return id + 256;
	}

	public BufferedImage getTexture() {
		return texture;
	}
}
