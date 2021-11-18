package visitors;

import entities.ActivePotionTypeA;
import entities.Doorway;
import entities.Enemy;
import entities.Fruit;
import entities.MainCharacter;
import entities.Potion;
import entities.PoweredDot;
import entities.RegularDot;
import entities.Wall;
import logic.Direction;
import logic.Game;

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
		
		Game myGame = p.getGame();
		player.setPotionTypeA(true);
		myGame.destroyEntity(p);
	}

	@Override
	public void visitPotionTypeB(Potion p) {
		
		Game myGame = p.getGame();
		myGame.destroyEntity(p);
		myGame.destroyEntity(p);
		
	}

	@Override
	public void visitPoweredDot(PoweredDot p) {
		
		Game myGame = p.getGame();
		
		myGame.enableFrightenedMode();
		myGame.destroyEntity(p);
		
		modifyScore(50);
		
	}

	@Override
	public void visitRegulardDot(RegularDot p) {
		
		Game myGame = p.getGame();
		
		myGame.destroyEntity(p);
		

		modifyScore(10);
		
		
	}

	@Override
	public void visitActivePotionTypeA(ActivePotionTypeA a) {		
		
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
		
		if (enemy.getFrightenedMode()) {
			enemy.disableFrightenedMode();
			enemy.IsInsideHouse(true);
			enemy.setXValue(enemy.getInitialXValue());
			enemy.setYValue(enemy.getInitialYValue());
			modifyScore(200);
		}
		else {
			player.getGame().gameOver();
		}	
		
	}

}
