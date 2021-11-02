package visitors;

import entities.EnemyTypeA;
import entities.Entity;
import entities.MainCharacter;
import entities.Wall;

public class VisitorWall implements Visitor{

	private Entity wall;
	
	public VisitorWall(Wall wall) {
		
		this.wall = wall;
	}
	
	@Override
	public void visitWall(Wall w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitMainCharacter(MainCharacter m) {
		
		m.blockMove();
		
	}

	@Override
	public void visitorEnemyTypeA(EnemyTypeA e) {
		// TODO Auto-generated method stub
		
	}

}
