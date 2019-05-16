package game.entity.creature;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.creature.attacks.FireBallSpell;
import game.entity.creature.attacks.IceShardSpell;
import game.entity.creature.attacks.NormalBlast;
import game.entity.creature.attacks.ProjectileAttacks;
import game.graphics.Animation;
import game.graphics.Assets;
import game.graphics.TemporaryAnimation;
import game.listener.MouseManager;

public class Player extends Creatures {

	private static final int ANIMATION_SPEED = 200, DEFAULT_MANA = 100, CHARGE_MANA_TIME = 500,
			MANA_GAINED_FROM_CHARGING = 10;

	private int maxMana;
	private final Animation animationDown, animationUp, animationLeft, animationRight;
	private final Animation animationDeadDown, animationDeadUp, animationDeadLeft, animationDeadRight;
	private String currentAttack; // ICE NORMAL
	private int attackCoolDown, mana;
	private long chargeManaTimer, lastTimeChargeMana;
	
	private AlphaComposite ac1, ac2, ac3;

	private ArrayList<ProjectileAttacks> attackList = new ArrayList<ProjectileAttacks>();

	public Player(GameThread gameThread, float x, float y) {
		super(gameThread, x, y, Creatures.DEFAULT_CREATURE_WIDTH, Creatures.DEFAULT_CREATURE_HEIGHT, 1000, 6.0f);
		this.currentAttack = "NORMAL";
		this.mana = DEFAULT_MANA;
		maxMana = DEFAULT_MANA;
//		isBeingKnockedBack = false;
		isAlive = true;

		// overrides super constructor bounding box set
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 40;
		bounds.height = 30;

		animationDown = new Animation(ANIMATION_SPEED, Assets.player_wizard_down);
		animationUp = new Animation(ANIMATION_SPEED, Assets.player_wizard_up);
		animationLeft = new Animation(ANIMATION_SPEED, Assets.player_wizard_left);
		animationRight = new Animation(ANIMATION_SPEED, Assets.player_wizard_right);

		animationDeadDown = new TemporaryAnimation(ANIMATION_SPEED, Assets.player_wizard_dead_down);
		animationDeadUp = new TemporaryAnimation(ANIMATION_SPEED, Assets.player_wizard_dead_up);
		animationDeadLeft = new TemporaryAnimation(ANIMATION_SPEED, Assets.player_wizard_dead_left);
		animationDeadRight = new TemporaryAnimation(ANIMATION_SPEED, Assets.player_wizard_dead_right);

		attackList.add(new IceShardSpell(gameThread, this));
		attackList.add(new NormalBlast(gameThread, this));
		attackList.add(new FireBallSpell(gameThread, this));
	}

	public void attack() {
		if (!isCastingAttack && attackCoolDown == 0) {
			if (currentAttack == "ICE") {
				ProjectileAttacks atk = attackList.get(0);
				if (atk.getManaCost() <= mana) {
					isCastingAttack = true;
					atk.fire();
					decreaseMana(atk.getManaCost());
				}
			} else if (currentAttack == "NORMAL") {
				ProjectileAttacks atk = attackList.get(1);
				if (atk.getManaCost() <= mana) {
					isCastingAttack = true;
					atk.fire();
					decreaseMana(atk.getManaCost());
				}
			} else if (currentAttack == "FIRE") {
				ProjectileAttacks atk = attackList.get(2);
				if (atk.getManaCost() <= mana) {
					isCastingAttack = true;
					atk.fire();
					decreaseMana(atk.getManaCost());
				}
			}
		}
	}

	private void updateAttack() {
		for (ProjectileAttacks atk : attackList) {
			atk.update();
		}
	}

	public void decreaseMana(int manaCost) {
		mana -= manaCost;
	}

	public void chargeMana() {
		chargeManaTimer += System.currentTimeMillis() - lastTimeChargeMana;
		lastTimeChargeMana = System.currentTimeMillis();
		if (chargeManaTimer > CHARGE_MANA_TIME && mana < maxMana) {
			if (mana + MANA_GAINED_FROM_CHARGING >= maxMana) {
				mana = maxMana;
			} else {
				mana += MANA_GAINED_FROM_CHARGING;
			}
			chargeManaTimer = 0;
		}
	}
	
	// Upgrade player
	
	public void addMaxMana(int mana) {
		this.maxMana += mana;
		this.mana = maxMana;
	}
	
	public void upgradeAttack() {
		for (ProjectileAttacks atk : attackList) {
			atk.setDamage((int) (atk.getDamage() + atk.getDamage() * 0.12));
		}
	}

//	private boolean isBeingKnockedBack;
//	private long knockBackTimer, lastTimeKnockBack;

//	public void knockBack(int damageReceived, String enemyFacingDirection) {
//		float knockBackSpeed = damageReceived;
//		isBeingKnockedBack = true;
//
//		if (enemyFacingDirection == "LEFT") { // meaning enemy is on player's right
//			xMove = -knockBackSpeed;
//			facingDirection = "RIGHT";
//		} else if (enemyFacingDirection == "RIGHT") {
//			xMove = knockBackSpeed;
//			facingDirection = "LEFT";
//		} else if (enemyFacingDirection == "DOWN") {
//			yMove = knockBackSpeed;
//			facingDirection = "UP";
//		} else if (enemyFacingDirection == "UP") {
//			yMove = -knockBackSpeed;
//			facingDirection = "DOWN";
//		}
//	}

	@Override
	public void update() {
		if (isAlive) {
			animationDown.timerCounter();
			animationLeft.timerCounter();
			animationRight.timerCounter();
			animationUp.timerCounter();

			updateAttack();

			getKeyboardInput();
			getMouseInput();
			getFacingDirectionFromMouse();
			move();

			gameThread.getGameCamera().focusCameraOnEntity(this);
		} else {
			animationDeadDown.timerCounter();
			animationDeadLeft.timerCounter();
			animationDeadRight.timerCounter();
			animationDeadUp.timerCounter();
		}
	}

//	private void knockbackTimer() {
//		knockBackTimer += System.currentTimeMillis() - lastTimeKnockBack;
//		lastTimeKnockBack = System.currentTimeMillis();
//		if (knockBackTimer > 200) {
//			knockBackTimer = 0;
//			isBeingKnockedBack = false;
//		}
//	}
//
//	@Override
//	public void hurt(int damage) {
//		health -= damage;
//		if (health <= 0) {
//			health = 0;
//			isAlive = false;
//		}
//	}

	public void getKeyboardInput() {
		xMove = 0;
		yMove = 0;

		if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_W)) {
			yMove = -speed;
		}
		if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_S)) {
			yMove = speed;
		}
		if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_A)) {
			xMove = -speed;
		}
		if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_D)) {
			xMove = speed;
		}
		// prevent player from charging mana while attacking
		if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_SPACE)
				&& !gameThread.getMouseManager().isLeftPressed()) {
			chargeMana();
		}
		// switching attack
		if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_1)) {
			currentAttack = "NORMAL";
		}
		if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_2)) {
			currentAttack = "ICE";
		}
		if (gameThread.getKeyManager().isKeyPressed(KeyEvent.VK_3)) {
			currentAttack = "FIRE";
		}
	}

	public void getMouseInput() {
		MouseManager mouse = gameThread.getMouseManager();
		if (mouse.isLeftPressed()) {
			attack();
		}
		if (mouse.isRightPressed()) {
			
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		for (ProjectileAttacks atk : attackList) {
			atk.render(g2d);
		}
		g2d.drawImage(getCurrentAnimationFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);

		g2d.setColor(Color.red);
		// draw image box
//		g2d.drawRect((int) (xPos - gameThread.getGameCamera().getxOffset()),
//				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height);
//		// draw collision checking (bounding) box
//		g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
//				(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);

		/*
		 * g2d.fillRect((int) (xPos + bounds.x -
		 * gameThread.getGameCamera().getxOffset()), (int) (yPos + bounds.y -
		 * gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
		 */
	}

	
			
	// this method is called at gameThread because it would get render at top
	public void drawPlayerHUD(Graphics2D g2d) {
		Color defaultColor = g2d.getColor();
		g2d.setColor(Color.red);
		g2d.fillRect(40, 530, (int) gameThread.getWorld().getEntityManager().getCenterCrystal().getHealthBarWidth() * 9,
				20);
		g2d.setColor(Color.blue);
		g2d.fillRect(40, 550, (int) getManaBarWidth() * 18, 20);
		
		handleAlphaChangeToCurrentAttack();
		g2d.setComposite(ac1);
		g2d.drawImage(Assets.blast_bullet, 40, 480, 60, 60, null);
		g2d.setComposite(ac2);
		g2d.drawImage(Assets.iceShardSpell_bullet, 75, 452, 100, 100, null);
		g2d.setComposite(ac3);
		g2d.drawImage(Assets.fireball_bullet[2], 155, 480, 50, 50, null);
	
		g2d.setColor(defaultColor);
	}

	
	private void handleAlphaChangeToCurrentAttack() {
		float alpha;
		if (currentAttack == "NORMAL") {
			alpha = 1.0f;
			ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
			alpha = 0.5f;
			ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
			ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
		} else if (currentAttack == "ICE") {
			alpha = 1.0f;
			ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
			alpha = 0.5f;
			ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
			ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
		} else if (currentAttack == "FIRE") {
			alpha = 0.5f;
			ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
			ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
			alpha = 1.0f;
			ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
		}
	}
	
	private float getManaBarWidth() {
		return (float) mana / maxMana * bounds.width;
	}

	@Override
	protected BufferedImage getCurrentAnimationFrame() {
		if (isAlive) {
			if (facingDirection == "LEFT") {
				return animationLeft.getCurrentFrame();
			} else if (facingDirection == "RIGHT") {
				return animationRight.getCurrentFrame();
			} else if (facingDirection == "UP") {
				return animationUp.getCurrentFrame();
			} else {
				return animationDown.getCurrentFrame();
			}
		} else {
			if (facingDirection == "LEFT") {
				return animationDeadLeft.getCurrentFrame();
			} else if (facingDirection == "RIGHT") {
				return animationDeadRight.getCurrentFrame();
			} else if (facingDirection == "UP") {
				return animationDeadUp.getCurrentFrame();
			} else {
				return animationDeadDown.getCurrentFrame();
			}
		}

	}

	public void getFacingDirectionFromMouse() {
		MouseManager mouse = gameThread.getMouseManager();
		float mouseX = mouse.getMouseX();
		float mouseY = mouse.getMouseY();
		// if we use getWindow().getWidth() will return window's width + border size
		float windowW = gameThread.getWindow().getContentPane().getSize().width;
		float windowH = gameThread.getWindow().getContentPane().getSize().height;
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
	
	public int getMaxMana() {
		return maxMana;
	}
}
