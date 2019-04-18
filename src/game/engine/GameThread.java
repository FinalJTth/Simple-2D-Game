package game.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import game.graphics.Assets;
import game.state.GameState;
import game.state.MenuState;
import game.state.State;

public class GameThread extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final double ONE_BILLION = 1000000000;
	private static final long serialVersionUID = 1L;
	private final Game game;
	private double old_time;

	private int currentFPS;

	private GameState gameState;
	private MenuState menuState;

	public GameThread(Game game) {
		System.out.println("Thread started");
		this.game = game;
		setFocusable(true); // Let JPanel get input from the keyboard
		Assets.init();
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}

	@Override
	public void run() {
		int fps = 60;
		double timePerTick = ONE_BILLION / fps;
		double delta = 0;
		long currentTime;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		while (true) {
			Screen screen = game.getScreenFactory().getCurrentScreen();
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / timePerTick;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			if (delta >= 1) {
				try {
					if (game.getScreenFactory().getCurrentScreen() != null) { // Check if the screen is set
						screen.onUpdate(); // TODO rename onUpdate to render? avoiding confuse with this.update()
					}
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ticks++;
				delta--;
			}

			if (timer >= ONE_BILLION) {
				System.out.println("FPS = " + ticks);
				this.currentFPS = ticks;
				ticks = 0;
				timer = 0;
			}
			try {
				if (game.getScreenFactory().getCurrentScreen() != null) { // Check if the screen is set
					game.getScreenFactory().getCurrentScreen().onUpdate(); // Update screen
				}
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		gameState.update();
	}

	public double getFPS() {
		double new_time = System.nanoTime();
		double delta = new_time - this.old_time;
		double fps = 1 / (delta * 1000);
		this.old_time = new_time;
		return fps;
	};

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g; // Graphics2D is more powerful than Graphics
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Keep game from
																									// lagging
		if (game.getScreenFactory().getCurrentScreen() != null) {
			game.getScreenFactory().getCurrentScreen().onDraw(g2d);
		}
		repaint();
	}

	public int getCurrentFPS() {
		return currentFPS;
	}

	public Game getGame() {
		return game;
	}

	public GameState getGameState() {
		return gameState;
	}
}
