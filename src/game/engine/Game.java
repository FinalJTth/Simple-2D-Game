package game.engine;

import javax.swing.JFrame;

import game.listener.KeyboardListener;
import game.listener.MousepadListener;

public class Game {

	private final JFrame window = new JFrame();
	private final ScreenFactory screenFactory;
	private final GameThread gameThread;
	private final KeyboardListener keyboardListener;
	private final MousepadListener mousepadListener;

	public Game(int window_x, int window_y, String title) {
		window.setSize(window_x, window_x);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close JFrame and process
		window.setFocusable(true);
		window.setLocationRelativeTo(null); // Open window in the center of the screen
		window.setTitle(title);
		window.setVisible(true);
		this.screenFactory = new ScreenFactory(this);
		this.gameThread = new GameThread(this);
		this.keyboardListener = new KeyboardListener();
		this.mousepadListener = new MousepadListener();

		window.add(gameThread); // Mean adding JPanel to window
		window.addKeyListener(keyboardListener);
		window.addMouseListener(mousepadListener);

		new Thread(gameThread).start();
		;
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

	public JFrame getJFrame() {
		return this.window;
	}

	public JFrame getWindow() {
		return window;
	}
}
