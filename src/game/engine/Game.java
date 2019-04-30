package game.engine;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import game.listener.KeyManager;
import game.listener.MouseManager;

public class Game {

	private final JFrame window;
	private Canvas canvas;
	
	private final ScreenFactory screenFactory;
	private final GameThread gameThread;
	private final KeyManager keyManager;
	private final MouseManager mouseManager;

	public Game(int window_width, int window_height, String title) {
		window = new JFrame(title);
		window.setSize(window_width, window_height);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setLocationRelativeTo(null); // Open window in the center of the screen
		window.setVisible(true);
		// System.out.println(String.format("H : %d, W : %d", window.getHeight(), window.getWidth()));
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(window_width, window_height));
		canvas.setMaximumSize(new Dimension(window_width, window_height));
		canvas.setMinimumSize(new Dimension(window_width, window_height));
		canvas.setFocusable(false);
		
		window.add(canvas);
		window.pack();
		
		this.screenFactory = new ScreenFactory(this);
		this.gameThread = new GameThread(this);
		this.keyManager = new KeyManager();
		this.mouseManager = new MouseManager();

		window.addKeyListener(keyManager);
		window.addMouseListener(mouseManager);
		window.addMouseMotionListener(mouseManager);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);

		new Thread(gameThread).start();
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

	public ScreenFactory getScreenFactory() {
		return this.screenFactory;
	}

	public GameThread getGameThread() {
		return this.gameThread;
	}

	public JFrame getWindow() {
		return window;
	}
}
