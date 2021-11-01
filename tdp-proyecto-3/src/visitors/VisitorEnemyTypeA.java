package visitors;

import entities.EnemyTypeA;
import entities.Entity;
import entities.MainCharacter;
import entities.Wall;

public class VisitorEnemyTypeA implements Visitor {

	
	private Entity entity;
	
	public VisitorEnemyTypeA(Entity entity) {
		
		this.entity = entity;
	}
	
	@Override
	public void visitWall(Wall w) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitMainCharacter(MainCharacter m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitorEnemyTypeA(EnemyTypeA e) {
		// TODO Auto-generated method stub
		
	}

}
