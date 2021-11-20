package visitors;

import entities.*;

import logic.Direction;
import logic.Game;
import timeHandlers.PowerTimer;
import timeHandlers.RespawnTimer;

public class VisitorEnemy implements Visitor {

	
	private Enemy enemy;
	
	public VisitorEnemy(Enemy enemy) {
		
		this.enemy = enemy;
	}
	
	@Override
	public void visitWall(Wall w) {
	
		enemy.setDirection(Direction.STILL);
		

	}

	@Override
	public void visitMainCharacter(MainCharacter m) {

	}

	
	@Override
	public void visitFruitTypeA(Fruit f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFruitTypeB(Fruit f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFruitTypeC(Fruit f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPotionTypeA(Potion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPotionTypeB(Potion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPoweredDot(PoweredDot p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visitRegulardDot(RegularDot p) {
	
		
	}

	@Override
	public void visitActivePotionTypeA(PowerTypeA a) {
		
		Game myGame = enemy.getGame();
		a.setImageRoute("/assets/MarioAssets/explosion.gif");
		myGame.getGUI().refreshImage(a);
		//myGame.getGUI().deleteEntity(a);
		new PowerTimer(a,1000).start();
		enemy.setDirection(Direction.STILL);
		enemy.setNextDirection(Direction.STILL);
		
		if (enemy.getState() == entities.Enemy.State.FRIGHTENED)
			enemy.disableFrightenedMode();		
		enemy.enableRespawnMode();
		new RespawnTimer(enemy, 15000).start();
		enemy.setXValue(enemy.getInitialXValue());
		enemy.setYValue(enemy.getInitialYValue());
	}

	@Override
	public void visitDoorway(Doorway doorway) {
	
		int size = 36;
		float xPosition = enemy.getXValue();
		if (xPosition < 0) {
			enemy.setXValue(26 * size);
		}
		if ( xPosition > 26 * size) {
			
			enemy.setXValue(0);
		}
		
	}

	@Override
	public void visitEnemy(Enemy enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitActivePotionTypeB(PowerTypeB a) {
		enemy.setDirection(Direction.STILL);
		enemy.disableFrightenedMode();
		enemy.setXValue(enemy.getInitialXValue());
		enemy.setYValue(enemy.getInitialYValue());	
	}


}
