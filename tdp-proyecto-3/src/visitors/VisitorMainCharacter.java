package visitors;

import java.lang.Thread.State;

import entities.Doorway;
import entities.Enemy;
import entities.Fruit;
import entities.MainCharacter;
import entities.Potion;
import entities.PowerTypeA;
import entities.PowerTypeB;
import entities.PoweredDot;
import entities.RegularDot;
import entities.Wall;
import logic.Direction;
import logic.Game;
import timeHandlers.RespawnTimer;

public class VisitorMainCharacter implements Visitor {

	private MainCharacter player;
	private int size = 36;
	
	public VisitorMainCharacter(MainCharacter player) {
		
		this.player = player;
	}
	
	@Override
	public void visitWall(Wall w) {
	
		player.setDirection(Direction.STILL);

	}

	@Override
	public void visitMainCharacter(MainCharacter m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFruitTypeA(Fruit f) {

		Game myGame = f.getGame();
		myGame.destroyEntity(f);
		modifyScore(100);
		myGame.checkIfWin();
		
	}

	@Override
	public void visitFruitTypeB(Fruit f) {
		
			
		
	}

	@Override
	public void visitFruitTypeC(Fruit f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPotionTypeA(Potion p) {
		
		Game myGame = p.getGame();
		player.setPotionTypeA(true);
		myGame.destroyEntity(p);
	}

	@Override
	public void visitPotionTypeB(Potion p) {
		
		if (p.getXValue() == player.getXValue() && p.getYValue()== player.getYValue()) {
			Game myGame = p.getGame();
			myGame.destroyEntity(p);
			player.setDirection(Direction.STILL);
			player.setNextDirection(Direction.STILL);
			player.setSpeed(3);
			new Thread() {
				public void run() {
					try {
						Thread.sleep(10000);
						player.setSpeed(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
		}

		
	}

	@Override
	public void visitPoweredDot(PoweredDot p) {
		
		Game myGame = p.getGame();
		
		myGame.enableFrightenedMode();
		myGame.destroyEntity(p);
		modifyScore(50);
		myGame.checkIfWin();
		
	}

	@Override
	public void visitRegulardDot(RegularDot p) {
		
		Game myGame = p.getGame();
		
		myGame.destroyEntity(p);
		modifyScore(10);
		myGame.checkIfWin();
		
	}

	@Override
	public void visitActivePotionTypeA(PowerTypeA a) {		
	
	}

	@Override
	public void visitDoorway(Doorway doorway) {
		int size = 36;
		float xPosition = player.getXValue();
		if (xPosition < 0) {
			player.setXValue(26 * size);
		}
		if( xPosition > 26 * size) {
			
			player.setXValue(0);
		}
		
	}
	
	private void modifyScore(int plus) {
		int newScore = player.getGame().getScore() + plus;
		player.getGame().setScore(newScore);
	}


	@Override
	public void visitEnemy(Enemy enemy) {
		
		if (Math.abs(enemy.getXValue() - player.getXValue()) < 18 && Math.abs(enemy.getYValue() - player.getYValue()) < 18)
			
		
			if (enemy.getState() == entities.Enemy.State.FRIGHTENED) {
				
			
				enemy.setXValue(enemy.getInitialXValue());
				enemy.setYValue(enemy.getInitialYValue());
				enemy.disableFrightenedMode();
				enemy.enableRespawnMode();
				enemy.setDirection(Direction.STILL);
				enemy.setNextDirection(Direction.STILL);			
				
				new RespawnTimer(enemy,15000).start();
					

			    modifyScore(200);
			}
			
			else if (enemy.getState() != entities.Enemy.State.RESPAWNING){
				player.getGame().gameOver();
			}	
		

		
	}

	@Override
	public void visitActivePotionTypeB(PowerTypeB a) {
		// TODO Auto-generated method stub
		
	}

}
