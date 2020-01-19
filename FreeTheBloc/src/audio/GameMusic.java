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
	
	public void playMusic(String musicLocation) {

		
		try {

			File musicPath = new File(musicLocation);

			if (musicPath.exists()) {

				audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();//eric
				clip.loop(Clip.LOOP_CONTINUOUSLY);

			} else {
				System.out.println("Cant find file");// eric
			}

		} catch (Exception ex) {
			ex.printStackTrace();// eric
		}

	}
	
	public void stop() {
		clip.stop();
	}
	

}
