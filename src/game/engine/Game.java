package game.engine;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import game.listener.KeyboardListener;
import game.listener.MousepadListener;

public class Game {

	private final JFrame window;
	private Canvas canvas;
	
	private final ScreenFactory screenFactory;
	private final GameThread gameThread;
	private final KeyboardListener keyboardListener;
	private final MousepadListener mousepadListener;

	public Game(int window_width, int window_height, String title) {
		window = new JFrame(title);
		window.setSize(window_width, window_width);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close JFrame and process
		window.setFocusable(false);
		window.setLocationRelativeTo(null); // Open window in the center of the screen
		window.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(window_width, window_height));
		canvas.setMaximumSize(new Dimension(window_width, window_height));
		canvas.setMinimumSize(new Dimension(window_width, window_height));
		canvas.setFocusable(false);
		
		window.add(canvas);
		window.pack();
		
		this.screenFactory = new ScreenFactory(this);
		this.gameThread = new GameThread(this);
		this.keyboardListener = new KeyboardListener();
		this.mousepadListener = new MousepadListener();

		window.addKeyListener(keyboardListener);
		window.addMouseListener(mousepadListener);

		new Thread(gameThread).start();
		;
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public MousepadListener getMouseListener() {
		return mousepadListener;
	}

	public KeyboardListener getKeyboardListener() {
		return keyboardListener;
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
