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
	private int size = 36;
	
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
			e.setXValue(12 * size);
			e.setYValue(5 * size);
			e.setDirection(Direction.LEFT);
			e.setNextDirection(Direction.LEFT);
		}
		else {
			//player.getGame().gameOver();
		}		
	}

	@Override
	public void visitFruitTypeA(Fruit f) {

		f.setXValue(-size);
		f.setYValue(-size);
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
		
		p.setXValue(-size);
		p.setYValue(-size);	
		player.setPotionTypeA(true);		
	}

	@Override
	public void visitPotionTypeB(Potion p) {
		p.setXValue(-size);
		p.setYValue(-size);
		
	}

	@Override
	public void visitPoweredDot(PoweredDot p) {
		p.setXValue(-size);
		p.setYValue(-size);
		
	}

	@Override
	public void visitRegulardDot(RegularDot p) {
		
		p.setXValue(-size);
		p.setYValue(-size);
		
		
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
