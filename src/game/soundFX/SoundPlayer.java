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
	
	public static SoundPlayer small_explosion;
	public static RepeatingSoundPlayer pewpew;
	public static SoundPlayer bgm;
	
	public static void initSound() {
		small_explosion = new SoundPlayer("sound/smallBomb.mp3");
		pewpew = new RepeatingSoundPlayer("sound/pew.wav");
		bgm = new RepeatingSoundPlayer("sound/bgm.mp3");
			
	}

	protected MediaPlayer mediaPlayer;
	protected boolean isPlaying;

	public SoundPlayer(String path) {

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
		mediaPlayer.seek(mediaPlayer.getStartTime());
//		if (!isPlaying) {
//			mediaPlayer.play();
//			isPlaying = true;
//		} else if (mediaPlayer.getStatus() == Status.STOPPED) {
//			isPlaying = false;
//			mediaPlayer.seek(mediaPlayer.getStartTime());
//		}

	}
	
	public void playNonStop() {
		mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
}
