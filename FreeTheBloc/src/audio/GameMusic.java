package audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameMusic {
	
	AudioInputStream audioInput;
	Clip clip;
	
	public GameMusic() {
		
	}
	
	// Music playing method
	public void playMusic(String musicLocation) {

		try {

			File musicPath = new File(musicLocation);

			if (musicPath.exists()) {

				//playing music
				audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);

			} 
		} catch (Exception ex) {
		}

	}
	
	//Stopping music
	public void stop() {
		clip.stop();
	}
	

}
