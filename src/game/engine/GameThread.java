package game.engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import game.graphics.Assets;
import game.graphics.GameCamera;
import game.listener.KeyManager;
import game.listener.MouseManager;
import game.soundFX.SoundPlayer;
import game.state.GameState;
import game.state.MenuState;
import game.state.State;
import game.utils.Utils;
import game.world.World;

public class GameThread implements Runnable {

	private static final double ONE_BILLION = 1000000000;
	
	private final JFrame window;
	private final  Canvas canvas;

	private int currentFPS;
	private boolean isPaused;
	
	private Thread initThread;

	// States
	private GameState gameState;
	private MenuState menuState;

	// Canvas
	private BufferStrategy bs;
	private Graphics2D g2d;

	// Camera
	private GameCamera gameCamera;

	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	public GameThread() {
		System.out.println("Thread started");

		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		window = new JFrame();
		canvas = new Canvas();
	}

	@Override
	public void run() {
		init();
		
		try {
			initThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int fps = 60;
		double timePerTick = ONE_BILLION / fps;
		double delta = 0;
		long currentTime;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		while (true) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / timePerTick;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}

			if (timer >= ONE_BILLION) {
				// System.out.println("FPS = " + ticks);
				this.currentFPS = ticks;
				ticks = 0;
				timer = 0;
			}
		}
	}

	public void init() {
		initThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Assets.init();
				SoundPlayer.initGameSound();
				
				initScreen("JobJob's Adventure", 800, 600);
			}
		});
		initThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gameCamera = new GameCamera(this, 0, 0);
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}
	
	private void initScreen(String title, int window_width, int window_height) {
		window.setTitle(title);
		window.setSize(window_width, window_height);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setLocationRelativeTo(null); // Open window in the center of the screen
		window.setVisible(true);
		// System.out.println(String.format("H : %d, W : %d", window.getHeight(), window.getWidth()));

		canvas.setPreferredSize(new Dimension(window_width, window_height));
		canvas.setMaximumSize(new Dimension(window_width, window_height));
		canvas.setMinimumSize(new Dimension(window_width, window_height));
		canvas.setFocusable(false);
		
		window.add(canvas);
		window.pack();
		
		this.keyManager = new KeyManager();
		this.mouseManager = new MouseManager();

		window.addKeyListener(keyManager);
		window.addMouseListener(mouseManager);
		window.addMouseMotionListener(mouseManager);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
	}

	public void update() {
		if (!isPaused)
			State.getState().update();
	}

	public void render() {
		bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		g2d = (Graphics2D) bs.getDrawGraphics();
		g2d.clearRect(0, 0, window.getWidth(), window.getHeight());
		// draw here
		if (State.getState() == gameState && !isPaused) {
			State.getState().render(g2d);
			// draw player's HUD
			getWorld().getEntityManager().getPlayer().drawPlayerHUD(g2d);
		} else if (State.getState() == menuState) {
			menuState.render(g2d);
		}
		// draw guide line
		g2d.drawLine(window.getWidth() / 2, 0, window.getWidth() / 2, window.getHeight());
		g2d.drawLine(0, window.getHeight() / 2, window.getWidth(),
				window.getHeight() / 2);

		g2d.drawString(Integer.toString(currentFPS), 10, 10);

		// for debugging
		for (Shape s : Utils.shapeToRender) {
			g2d.draw(s);
		}
		int x = 0, y = 0;
		for (BufferedImage img : Utils.imageToRender) {
			g2d.drawImage(img, x, y, null);
			x += img.getWidth();
			if (x >= window.getContentPane().getSize().width) {
				y += img.getHeight();
				x = 0;
			}
		}
		// end debugging
		
		// end drawing
		bs.show();
		g2d.dispose();

	}

	public void setGameState() {
		State.setState(gameState);
	}

	public void setMenuState() {
		State.setState(menuState);
		System.out.println("Menu");
	}

	public void togglePauseGame() {
		isPaused = !isPaused;
		System.out.println("Paused");
	}

	public int getCurrentFPS() {
		return currentFPS;
	}

	public GameState getGameState() {
		return gameState;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public World getWorld() {
		return gameState.getWorld();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public JFrame getWindow() {
		return window;
	}
}
