package game.soundFX;

import java.io.File;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class SoundPlayer {

	public static SoundPlayer small_explosion;
	public static SoundPlayer pewpew;
	public static SoundPlayer bgm, menuBgm;

	protected MediaPlayer mediaPlayer;

	protected static boolean isSoundOFF = false;

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
