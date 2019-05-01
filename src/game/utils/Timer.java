package game.utils;

import java.util.ArrayList;

public class Timer {

	public static ArrayList<Thread> threadList = new ArrayList<Thread>();

	public static int newTimer(long time) {
		Thread tmp = new Thread(new Runnable() {
			long timePast = 0;

			@Override
			public void run() {
				while (true) {
					timePast += 10;
					System.out.println(timePast);
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
}
