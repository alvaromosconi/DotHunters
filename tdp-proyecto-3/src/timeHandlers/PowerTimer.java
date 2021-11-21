package timeHandlers;

import entities.PowerTypeA;

public class PowerTimer extends Thread {
	private int step;
	private PowerTypeA power;

	public PowerTimer(PowerTypeA power, int step) {

		this.power = power;
		this.step = step;
	}

	public void run() {

		try {
			Thread.sleep(step);
			power.getGame().destroyEntity(power);
		} 
		
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
