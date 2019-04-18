package game.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import game.display.MyScreen;
import game.graphics.Assets;
import game.state.GameState;
import game.state.MenuState;
import game.state.State;

public class GameThread implements Runnable {

	/**
	 * 
	 */
	private static final double ONE_BILLION = 1000000000;
	private static final long serialVersionUID = 1L;
	private final Game game;
	private double old_time;

	private int currentFPS;

	// States
	private GameState gameState;
	private MenuState menuState;
	
	// Canvas
	private BufferStrategy bs;
	private Graphics2D g2d;

	public GameThread(Game game) {
		System.out.println("Thread started");
		this.game = game;
	}

	@Override
	public void run() {
		init();

		int fps = 60;
		double timePerTick = ONE_BILLION / fps;
		double delta = 0;
		long currentTime;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		Screen screen = game.getScreenFactory().getCurrentScreen();
		while (true) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / timePerTick;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				render();
//				try {
//					if (game.getScreenFactory().getCurrentScreen() != null) { // Check if the screen is set
//						screen.onUpdate(); // TODO rename onUpdate to render? avoiding confuse with this.update()
//					}
//					Thread.sleep(10);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				ticks++;
				delta--;
			}

			if (timer >= ONE_BILLION) {
				System.out.println("FPS = " + ticks);
				this.currentFPS = ticks;
				ticks = 0;
				timer = 0;
			}
		}
	}

	public void init() {
		Assets.init();
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}

	public void update() {
		State.getState().update();
	}
	
	public void render() {
		bs = game.getCanvas().getBufferStrategy();
		if (bs == null) {
			game.getCanvas().createBufferStrategy(3);
			return;
		}
		g2d = (Graphics2D) bs.getDrawGraphics();
		g2d.clearRect(0, 0, game.getWindow().getWidth(), game.getWindow().getHeight());
		// draw here
		if (State.getState() != null) {
			State.getState().render(g2d);
		}
		
		// end drawing
		bs.show();
		g2d.dispose();
	}

	/*
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
	*/

	// replace getFPS with this one. currentFPS was set in thread loop
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
