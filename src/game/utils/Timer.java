package game.utils;

import java.util.ArrayList;

public class Timer {

	public static ArrayList<Thread> threadList = new ArrayList<Thread>();
	
	public static long currentTime;

	public static int newTimer(long time) {
		Thread tmp = new Thread(new Runnable() {
			long timePast = 0;

			@Override
			public void run() {
				while (true) {
					timePast += 10;
					if (timePast >= time) {
						timePast = 0;
						return;
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});
		threadList.add(tmp);
		tmp.start();
		return threadList.indexOf(tmp);
	}

	public int kuy = 0;
	
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
