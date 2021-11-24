package logic;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {

		private String domainRoute;
		private Clip clip = null;
		private boolean sound = true;
		
		public SoundHandler(String domainRoute) {
			
			this.domainRoute = domainRoute;
			playBackgroundMusic();	
		}
	
		/*
		 * Reproduce un sonido especifico
		 * @param soundRoute ruta del sonido  
		 */
		private void playBackgroundMusic()  {
			
			AudioInputStream audioInputStream;
			java.net.URL url = Game.class.getResource(domainRoute + "back.wav");
			
			try {
				audioInputStream = AudioSystem.getAudioInputStream(url);
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
			} 
			
			catch (UnsupportedAudioFileException |IOException | LineUnavailableException e) {
				e.printStackTrace();
			} 
			
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		
		/*
		 * Reproduce un sonido especifico
		 * @param soundRoute ruta del sonido  
		 */
		public void playSpecificSound(String soundRoute)  {
			
			AudioInputStream audioInputStream;
			java.net.URL url = Game.class.getResource(soundRoute);
			
			try {
				audioInputStream = AudioSystem.getAudioInputStream(url);
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
			} 
			
			catch (UnsupportedAudioFileException |IOException | LineUnavailableException e) {
				e.printStackTrace();
			} 
			
	
			clip.start();
		}
	
		/**
		 * 
		 * @return retorna si el sonido esta activado o no
		 */
		public boolean isSound() {
			return sound;
		}
		
		/**
		 * Modifica la variable de sonido
		 * @param sound
		 */
		public void setSound(boolean sound) {
			this.sound = sound;
		}
		
		/**
		 * Cambia estado del sonido
		 */
		public void changeSound() {
			
			if (sound) {
				turnOffAudio();
				sound = false;
			}
			
			else {
				clip.start();
				sound = true;
			}
		}
		
		/**
		 * Desactiva el sonido
		 */
		void turnOffAudio() {
			clip.stop();
		}
	
	
}
