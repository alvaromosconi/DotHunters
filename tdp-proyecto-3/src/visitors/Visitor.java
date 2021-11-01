package visitors;
import entities.MainCharacter;
import entities.Wall;


public interface Visitor {

	public void visitWall(Wall w);
	
	public void visitMainCharacter(MainCharacter m);
}
