package game.soundFX;

import game.engine.GameThread;

public class RepeatingSoundPlayer extends SoundPlayer {

	public RepeatingSoundPlayer(String path) {
		super(path);
	}
	
	@Override
	public void playSound() {
		if (SoundPlayer.isSoundOFF) {
			mediaPlayer.play();
			mediaPlayer.seek(mediaPlayer.getStartTime());
		}
		
	}

}
