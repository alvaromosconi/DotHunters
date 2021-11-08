package logic;

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
	
	public Time(Game game, int step) {
	
		this.milliseconds = 0;
		this.seconds = 0;
		this.minutes = 0;
		this.stopTime = 0;
		this.game = game;
		this.running = true;
		this.step = step;
		this.secondsSpeed = seconds + 10;
		this.startTime = System.currentTimeMillis();
	}
	
	
	public void stopTime() {
		
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}
	
	@Override
	public void run() {
		

		while (this.running) {


					
						
					try {
						if (game.getPlayer().getXValue() % 36 != 0 || game.getPlayer().getYValue() % 36 != 0) 
							game.move();
						Thread.sleep(step);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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