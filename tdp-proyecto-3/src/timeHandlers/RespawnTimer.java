package timeHandlers;

import entities.Enemy;

public class RespawnTimer extends Thread {
	private int step;
	private Enemy enemy;
	
	public RespawnTimer(Enemy enemy, int step) {
		this.enemy = enemy;
		this.step = step;
		
	}
	public void run() {
		try {
			Thread.sleep(step);
			enemy.setState(entities.Enemy.State.LEAVINGHOUSE);
			enemy.disableRespawnMode();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
