package game.state;

import java.awt.Graphics;
import java.awt.Graphics2D;

import game.engine.GameThread;

public abstract class State {

	// Static stuff

	private static State currentState = null;

	public static void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}

	// Class stuff

	protected GameThread gameThread;

	public State(GameThread gameThread) {
		this.gameThread = gameThread;
	}

	public abstract void update();

	public abstract void render(Graphics2D g2d);

}
