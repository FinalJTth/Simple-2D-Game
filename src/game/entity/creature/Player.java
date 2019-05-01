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
import game.listener.MouseManager;
import game.tile.Tile;

public class Player extends Creatures {

	private static final int ANIMATION_SPEED = 200, DEFAULT_MANA = 100;

	private final int maxMana;

	private Game game;
	private Animation animationDown, animationUp, animationLeft, animationRight;
	private String currentAttack;
	private int attackCoolDown, mana;
	private long lastTimeCoolDown, attackCoolDownTimer, knockBackTimer, lastTimeKnockBack, chargeManaTimer, lastTimeChargeMana;
	private boolean isBeingKnockedBack;

	public Player(GameThread gameThread, float x, float y) {
		super(gameThread, x, y, Creatures.DEFAULT_CREATURE_WIDTH, Creatures.DEFAULT_CREATURE_HEIGHT, 1000, 6.0f);
		this.game = gameThread.getGame();
		this.currentAttack = "ICE";
		this.mana = DEFAULT_MANA;
		maxMana = DEFAULT_MANA;
		isBeingKnockedBack = false;
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
		if (!isCastingAttack) {
			isCastingAttack = true;
			if (currentAttack == "ICE") {
				ProjectileAttacks atk = ProjectileAttacks.attackList.get(0);
				if (atk.getManaCost() <= mana) {
					attackCoolDown = atk.getCoolDown();
					atk.fire();
					decreaseMana(atk.getManaCost());
				}
			}
		}

	}

	public void decreaseMana(int manaCost) {
		mana -= manaCost;
	}
	
	public void chargeMana() {
		chargeManaTimer += System.currentTimeMillis() - lastTimeChargeMana;
		lastTimeChargeMana = System.currentTimeMillis();
		if (chargeManaTimer > 500) {
			mana += 10;
			chargeManaTimer = 0;
		}
	}

	public void knockBack(int damageReceived, String enemyFacingDirection) {
		float knockBackSpeed = damageReceived;
		isBeingKnockedBack = true;

		if (enemyFacingDirection == "LEFT") { // meaning enemy is on player's right
			xMove = -knockBackSpeed;
			facingDirection = "RIGHT";
		} else if (enemyFacingDirection == "RIGHT") {
			xMove = knockBackSpeed;
			facingDirection = "LEFT";
		} else if (enemyFacingDirection == "DOWN") {
			yMove = knockBackSpeed;
			facingDirection = "UP";
		} else if (enemyFacingDirection == "UP") {
			yMove = -knockBackSpeed;
			facingDirection = "DOWN";
		}
	}

	@Override
	public void update() {
		animationDown.timerCounter();
		animationLeft.timerCounter();
		animationRight.timerCounter();
		animationUp.timerCounter();

		attackCooldownTimer();

		ProjectileAttacks.updateAttacks();

		if (isBeingKnockedBack) {
			moveWithFixedDirection();
			knockbackTimer();
		} else {
			getKeyboardInput();
			getMouseInput();
			getFacingDirectionFromMouse();
			// move();
			moveWithFixedDirection();
		}
		gameThread.getGameCamera().focusCameraOnEntity(this);
	}

	private void attackCooldownTimer() {
		attackCoolDownTimer += System.currentTimeMillis() - lastTimeCoolDown;
		lastTimeCoolDown = System.currentTimeMillis();
		if (attackCoolDownTimer > attackCoolDown) {
			attackCoolDown = 0;
			attackCoolDownTimer = 0;
		}
	}

	private void knockbackTimer() {
		knockBackTimer += System.currentTimeMillis() - lastTimeKnockBack;
		lastTimeKnockBack = System.currentTimeMillis();
		if (knockBackTimer > 200) {
			knockBackTimer = 0;
			isBeingKnockedBack = false;
		}
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
			chargeMana();
			
		}
	}

	public void getMouseInput() {
		MouseManager mouse = game.getMouseManager();
		if (mouse.isLeftPressed()) {
			attack();
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
		// draw collision checking (bounding) box
		g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
				(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);

		/*
		 * g2d.fillRect((int) (xPos + bounds.x -
		 * gameThread.getGameCamera().getxOffset()), (int) (yPos + bounds.y -
		 * gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
		 */
	}

	public void drawPlayerHUD(Graphics2D g2d) {
		Color defaultColor = g2d.getColor();
		g2d.setColor(Color.red);
		g2d.fillRect(40, 530, (int) gameThread.getWorld().getEntityManager().getPlayer().getHealthBarWidth() * 18, 20);
		g2d.setColor(Color.blue);
		g2d.fillRect(40, 550, (int) getManaBarWidth() * 18, 20);
		g2d.setColor(defaultColor);
	}

	private float getManaBarWidth() {
		return (float) mana / maxMana * bounds.width;
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

	public void getFacingDirectionFromMouse() {
		MouseManager mouse = gameThread.getGame().getMouseManager();
		float mouseX = mouse.getMouseX();
		float mouseY = mouse.getMouseY();
		// if we use getWindow().getWidth() will return window's width + border size
		float windowW = gameThread.getGame().getWindow().getContentPane().getSize().width;
		float windowH = gameThread.getGame().getWindow().getContentPane().getSize().height;
		if ((mouseY > (-windowH / windowW) * mouseX + windowH) && (mouseY < windowH / windowW * mouseX)) {
			facingDirection = "RIGHT";
		} else if ((mouseY > (-windowH / windowW) * mouseX + windowH) && (mouseY > windowH / windowW * mouseX)) {
			facingDirection = "DOWN";
		} else if ((mouseY < (-windowH / windowW) * mouseX + windowH) && (mouseY < windowH / windowW * mouseX)) {
			facingDirection = "UP";
		} else if ((mouseY < (-windowH / windowW) * mouseX + windowH) && (mouseY > windowH / windowW * mouseX)) {
			facingDirection = "LEFT";
		}

	}
}
