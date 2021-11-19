package visitors;

import entities.ActivePotionTypeA;
import entities.ActivePotionTypeB;
import entities.Doorway;
import entities.Enemy;
import entities.EnemyTypeA;
import entities.EnemyTypeB;
import entities.EnemyTypeC;
import entities.Entity;
import entities.Fruit;
import entities.MainCharacter;
import entities.Potion;
import entities.PoweredDot;
import entities.RegularDot;
import entities.Wall;

public class VisitorActivePotionTypeA implements Visitor {
	
	private Entity activePotionTypeA;
	
	public VisitorActivePotionTypeA (ActivePotionTypeA a) {
		this.activePotionTypeA = a;
	}


	@Override
	public void visitMainCharacter(MainCharacter m) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitWall(Wall w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitActivePotionTypeA(ActivePotionTypeA a) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visitDoorway(Doorway doorway) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visitEnemy(Enemy enemy) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visitActivePotionTypeB(ActivePotionTypeB a) {
		// TODO Auto-generated method stub
		
	}

}
