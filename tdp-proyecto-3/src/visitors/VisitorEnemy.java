package visitors;

import entities.*;
import logic.Direction;

public class VisitorEnemy implements Visitor {

	
	private Enemy entity;
	
	public VisitorEnemy(Enemy enemy) {
		
		this.entity = enemy;
	}
	
	@Override
	public void visitWall(Wall w) {
	
		entity.setDirection(Direction.STILL);
		

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
	public void visitActivePotionTypeA(ActivePotionTypeA a) {
		
		entity.setDirection(Direction.STILL);
		a.getGame().getGUI().deleteEntity(a);
		entity.disableFrightenedMode();
		entity.setXValue(12 * 36);
		entity.setYValue(5 * 36);
		entity.setDirection(Direction.LEFT);
		entity.setNextDirection(Direction.LEFT);
		a.setImageRoute("/assets/MarioAssets/explosion.gif");
		
		a.getGame().getGUI().refreshImage(a);
		


	}

	@Override
	public void visitDoorway(Doorway doorway) {
	
		int size = 36;
		float xPosition = entity.getXValue();
		if (xPosition < 0) {
			entity.setXValue(26 * size);
		}
		if ( xPosition > 26 * size) {
			
			entity.setXValue(0);
		}
		
	}

	@Override
	public void visitEnemy(Enemy enemy) {
		// TODO Auto-generated method stub
		
	}


}
