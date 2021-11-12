package visitors;

import entities.*;
import entities.Entity.Direction;


public class VisitorEnemyTypeA implements Visitor {

	
	private Entity entity;
	
	public VisitorEnemyTypeA(Entity entity) {
		
		this.entity = entity;
	}
	
	@Override
	public void visitWall(Wall w) {
	
		entity.setDirection(Direction.STILL);

	}

	@Override
	public void visitMainCharacter(MainCharacter m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitEnemyTypeA(EnemyTypeA e) {
		// TODO Auto-generated method stub
		
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

}
