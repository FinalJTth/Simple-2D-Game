package game.tile;

import java.awt.image.BufferedImage;

import game.graphics.Assets;

public class SolidTile extends Tile{

	public SolidTile(BufferedImage[] texture, int id) {
		super(texture, id);
	}
	
	// player CAN'T walk over
	@Override
	public boolean isSolid() {
		return false;
	}
	
}
