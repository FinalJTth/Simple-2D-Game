package game.entity.creature;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.engine.Game;
import game.engine.GameThread;
import game.entity.creature.attacks.IceShardSpell;
import game.entity.creature.attacks.ProjectileAttacks;
import game.graphics.Animation;
import game.graphics.Assets;

public class Player extends Creatures {

	private static final int ANIMATION_SPEED = 200;

	private Game game;
	private Animation animationDown, animationUp, animationLeft, animationRight;
	private String currentAttack;
	private int attackCoolDown;
	private long lastTime, timer;

	public Player(GameThread gameThread, float x, float y) {
		super(gameThread, x, y, Creatures.DEFAULT_CREATURE_WIDTH, Creatures.DEFAULT_CREATURE_HEIGHT);
		this.game = gameThread.getGame();
		this.currentAttack = "ICE";
		System.out.println("Player init");

		// overrides super constructor bounding box set
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 40;
		bounds.height = 30;

		animationDown = new Animation(ANIMATION_SPEED, Assets.player_wizard_down);
		animationUp = new Animation(ANIMATION_SPEED, Assets.player_wizard_up);
		animationLeft = new Animation(ANIMATION_SPEED, Assets.player_wizard_left);
		animationRight = new Animation(ANIMATION_SPEED, Assets.player_wizard_right);
		
		ProjectileAttacks.addAttack(new IceShardSpell(gameThread, this));
		
	}
	
	public void attack() {
		isCastingAttack = true;
		if (currentAttack == "ICE") {
			ProjectileAttacks atk = ProjectileAttacks.attackList.get(0);
			attackCoolDown = atk.getCoolDown();
			atk.fire();
		}
	}

	@Override
	public void update() {
		animationDown.timerCounter();
		animationLeft.timerCounter();
		animationRight.timerCounter();
		animationUp.timerCounter();
		
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if (timer > attackCoolDown) {
			attackCoolDown = 0;
			timer = 0;
		}
		ProjectileAttacks.updateAttacks();

		getKeyboardInput();
		move();
		gameThread.getGameCamera().focusCameraOnEntity(this);
	}

	public void getKeyboardInput() {
		xMove = 0;
		yMove = 0;

		if (game.getKeyManager().isKeyPressed(KeyEvent.VK_W)) {
			yMove = -speed;
		}
		if (game.getKeyManager().isKeyPressed(KeyEvent.VK_S)) {
			yMove = speed;
		}
		if (game.getKeyManager().isKeyPressed(KeyEvent.VK_A)) {
			xMove = -speed;
		}
		if (game.getKeyManager().isKeyPressed(KeyEvent.VK_D)) {
			xMove = speed;
		}
		if (game.getKeyManager().isKeyPressed(KeyEvent.VK_SPACE) && attackCoolDown == 0) {
			attack();
			System.out.println("FIRE");
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		ProjectileAttacks.renderAttacks(g2d);
		g2d.drawImage(getCurrentAnimationFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);

		g2d.setColor(Color.red);
		// draw image box
		g2d.drawRect((int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height);
		//draw collision checking (bounding) box
		g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
				(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
		
		
		/*
		 * g2d.fillRect((int) (xPos + bounds.x -
		 * gameThread.getGameCamera().getxOffset()), (int) (yPos + bounds.y -
		 * gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
		 */
	}

	protected BufferedImage getCurrentAnimationFrame() {
		if (facingDirection == "LEFT") {
			return animationLeft.getCurrentFrame();
		} else if (facingDirection == "RIGHT") {
			return animationRight.getCurrentFrame();
		} else if (facingDirection == "UP") {
			return animationUp.getCurrentFrame();
		} else {
			return animationDown.getCurrentFrame();
		}
	}

	@Override
	public void hurt(int damage) {
		// TODO Auto-generated method stub
		
	}

	
}
