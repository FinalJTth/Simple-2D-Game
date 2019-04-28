package game.entity.creature.minion;

import java.awt.Color;
import java.awt.Graphics2D;

import game.engine.GameThread;
import game.entity.creature.Player;
import game.graphics.Animation;
import game.graphics.Assets;

public class BigBlob extends Minion {

	private Animation animationAttack, animationWalk, animationIdle;

	public BigBlob(GameThread gameThread, float xPos, float yPos, int width, int height) {
		super(gameThread, xPos, yPos, width, height);

		health = 300;
		speed = 3.0f;
		// System.out.println(String.format("x : %d, y : %d", bounds.x, bounds.y));
		// System.out.println(String.format("w : %d, h : %d", bounds.width, bounds.height));
		
		bounds.x = width / 4;
		bounds.y = height / 2 + 10;
		bounds.width = width / 2 - 10;
		bounds.height = height / 2 - 10;
		
		animationAttack = new Animation(100, Assets.big_blob_attack);
		animationIdle = new Animation(100, Assets.big_blob_idle);
		animationWalk = new Animation(100, Assets.big_blob_walk);
	}

	@Override
	public void update() {
		animationIdle.timerCounter();
		moveRandomly();
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(animationIdle.getCurrentFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);
		g2d.setColor(Color.red);
		// draw image box
//		g2d.drawRect((int) (xPos - gameThread.getGameCamera().getxOffset()),
//				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height);
		// draw collision checking (bounding) box	
		g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
				(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
		g2d.setColor(Color.blue);
	}

	@Override
	public void attack(Player player) {

	}

}
