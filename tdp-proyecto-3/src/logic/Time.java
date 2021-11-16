package logic;

import entities.Entity;
import entities.Entity.Direction;

public class Time extends Thread {

	private int milliseconds;
	private int seconds;
	private int secondsSpeed;
	private int minutes;
	private long startTime;
	private long stopTime;
	private boolean running;
	private Game game;
	private int step;
	private Entity player;
	
	public Time(Game game, int step, Entity player) {
	
		this.milliseconds = 0;
		this.seconds = 0;
		this.minutes = 0;
		this.stopTime = 0;
		this.game = game;
		this.running = true;
		this.step = step;
		this.secondsSpeed = seconds + 10;
		this.startTime = System.currentTimeMillis();
		
		this.player = player;
	}
	
	
	public void stopTime() {
		
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}
	
	@Override
	public void run() {
		

		while (this.running) {
				
			try {
						
				//System.out.println(game.getZones(player).get(0).getBottomLeftVertex().getX());
				Thread.sleep(step);
						
				game.move(player);
				
				if (player.getNextDirection() != Direction.STILL ) 
				
					if (!game.collideWithWall(player.getNextXVelocity(), player.getNextYVelocity(), player)) {
						player.setDirection(player.getNextDirection());
						player.setNextDirection(Direction.STILL);
					}
				
			}

						
			 catch (InterruptedException e) {
				
					e.printStackTrace();
			  }

				


			
		
		}
	}

	public void setStep(int step) {
		
		this.step = step;
	}

	public long getElapsedTime() {
		long elapsed;
		
		if (running) {
	         elapsed = (System.currentTimeMillis() - startTime);
		}
	    else 
	        elapsed = (stopTime - startTime);
	    
	    return elapsed;
	}
	
	private String getElapsedTimeString() {
		
		
		milliseconds = (int) (getElapsedTime());
		seconds = (int) (milliseconds / 1000) % 60 ;
		minutes = (int) ((milliseconds / (1000*60)) % 60);
		increment();
		
		String toReturn = minutes + ":" + seconds;
		
		if (seconds < 10) 
			toReturn = minutes + ":" + "0" + seconds;
	
		return toReturn;
	}
	
	private void increment() {
		
		if (seconds == secondsSpeed) {
			secondsSpeed = seconds + 10;
		}
		
		if (seconds == 50) 
			secondsSpeed = 0;
	}	

}