package game.soundFX;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.engine.GameThread;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class SoundPlayer {

	protected final GameThread gameThread;

	protected MediaPlayer mediaPlayer;
	protected boolean isPlaying;

	public SoundPlayer(GameThread gameThread, String path) {
		this.gameThread = gameThread;

		isPlaying = false;
		Media hit = new Media(new File("res/" + path).toURI().toString());
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mediaPlayer = new MediaPlayer(hit);
			}
		});
	}

	public void playSound() {
		mediaPlayer.play();

//		if (!isPlaying) {
//			mediaPlayer.play();
//			isPlaying = true;
//		} else if (mediaPlayer.getStatus() == Status.STOPPED) {
//			isPlaying = false;
//			mediaPlayer.seek(mediaPlayer.getStartTime());
//		}

	}
}
