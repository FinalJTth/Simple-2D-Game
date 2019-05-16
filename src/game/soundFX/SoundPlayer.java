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
	public static SoundPlayer pewpew;
	public static SoundPlayer bgm, menuBgm;
	
	protected static boolean isSoundOFF = false;
	
	public static void initGameSound() {
		small_explosion = new SoundPlayer("sound/smallBomb.mp3");
		pewpew = new SoundPlayer("sound/pew.wav");
		bgm = new SoundPlayer("sound/bgm.mp3");
	}
	
	public static void initMenuSound() {
		menuBgm = new SoundPlayer("sound/menubgm.mp3");
	}
	
	public static void toggleSound() {
		isSoundOFF = !isSoundOFF;
		if (menuBgm.getMediaPlayer().getStatus() == Status.PLAYING) {
			menuBgm.stopPlaying();
		} else {
			menuBgm.playNonStop();
		}
	}

	protected MediaPlayer mediaPlayer;

	public SoundPlayer(String path) {

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
		if (!isSoundOFF) {
			mediaPlayer.play();
			mediaPlayer.seek(mediaPlayer.getStartTime());
		}

	}
	
	public void setVolume(double volume) {
		mediaPlayer.setVolume(volume);
	}
	
	public void playNonStop() {
		if (!isSoundOFF) {
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.play();
		}
		
	}
	
	public void stopPlaying() {
		mediaPlayer.stop();
	}
	
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
}
