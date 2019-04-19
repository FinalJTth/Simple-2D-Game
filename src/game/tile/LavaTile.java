package game.tile;

import game.graphics.Assets;

public class LavaTile extends Tile{

	public LavaTile(int id) {
		super(Assets.lava, id);
	}
	
	// player CAN'T walk over
	@Override
	public boolean isSolid() {
		return false;
	}
	
}
