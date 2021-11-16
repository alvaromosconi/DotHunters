package visitors;

import entities.ActivePotionTypeA;
import entities.Doorway;
import entities.EnemyTypeA;
import entities.Entity;
import entities.Entity.Direction;
import entities.Fruit;
import entities.MainCharacter;
import entities.Potion;
import entities.PoweredDot;
import entities.RegularDot;
import entities.Wall;
import logic.Game;

public class VisitorMainCharacter implements Visitor {

	private MainCharacter player;
	
	public VisitorMainCharacter(MainCharacter player) {
		
		this.player = player;
	}
	
	@Override
	public void visitWall(Wall w) {
	
		player.setDirection(Direction.STILL);
		player.setVelocity(0, 0);

	}

	@Override
	public void visitMainCharacter(MainCharacter m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitEnemyTypeA(EnemyTypeA e) {		
		if (e.getFrightenedMode()) {
			e.disableFrightenedMode();
			e.setXValue(12 * 36);
			e.setYValue(5 * 36);
			e.setDirection(Direction.LEFT);
			e.setNextDirection(Direction.LEFT);
		}
		else {
			//player.getGame().gameOver();
		}		
	}

	@Override
	public void visitFruitTypeA(Fruit f) {

		f.setXValue(-36);
		f.setYValue(-36);
		f.getGame().activeFrightenedMode();
		
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
		
		p.setXValue(0);
		p.setYValue(0);	
		player.setPotionTypeA(true);		
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
		
		p.setXValue(0);
		p.setYValue(0);
		
		
	}

	@Override
	public void visitActivePotionTypeA(ActivePotionTypeA a) {		
		
	}

	@Override
	public void visitDoorway(Doorway doorway) {
		int size = 36;
		int xPosition = player.getXValue();
		if (xPosition < 0) {
			player.setXValue(26 * size);
		}
		if( xPosition > 26 * size) {
			
			player.setXValue(0);
		}
		
	}

}
