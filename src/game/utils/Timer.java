package game.utils;

public class Timer {

	public static long currentTime;

	public static void newGameTimer() {
		Thread thread = new Thread(new Runnable() {
			long lastTime = System.currentTimeMillis();

			@Override
			public void run() {
				while (true) {
					currentTime = System.currentTimeMillis() - lastTime;
				}
			}
		});
		thread.start();
	}

	public static long getCurrentTime() {
		return currentTime;
	}
}
