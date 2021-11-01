package visitors;
import entities.EnemyTypeA;
import entities.MainCharacter;
import entities.Wall;


public interface Visitor {

	public void visitWall(Wall w);
	
	public void visitMainCharacter(MainCharacter m);
	
	public void visitorEnemyTypeA(EnemyTypeA e);
}
