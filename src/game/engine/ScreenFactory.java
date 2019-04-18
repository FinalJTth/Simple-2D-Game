package game.engine;
// Handle Screen, such as update create and so on
public class ScreenFactory {
	
	private final Game game;
	private Screen screen;	// Screen currently showing
	
	public ScreenFactory(Game game) {
		this.game = game;
	}
	
	public void showScreen(Screen screen) {
		this.screen = screen;
		this.screen.onCreate();
	}
	
	public Screen getCurrentScreen() {
		return this.screen;
	}
	
	public Game getGame() {
		return game;
	}
}
