package game.soundFX;

import game.engine.GameThread;

public class RepeatingSoundPlayer extends SoundPlayer {

	public RepeatingSoundPlayer(GameThread gameThread, String path) {
		super(gameThread, path);
	}
	
	@Override
	public void playSound() {
		mediaPlayer.play();
		mediaPlayer.seek(mediaPlayer.getStartTime());
	}

}
