package timeHandlers;

import logic.Game;

public class Time extends Thread {

	private Game game;
	private int step;
	
	public Time(Game game, int step) {
		
		this.game = game;
		this.step = step;
	}
	
	
	@Override
	public void run() {
		
		try {
			
			Thread.sleep(step);		
			game.disableFrightenedMode();
	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setStep(int step) {
		
		this.step = step;
	}


}