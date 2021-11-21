package visitors;
import entities.PowerTypeA;
import entities.PowerTypeB;
import entities.Doorway;
import entities.Enemy;
import entities.Fruit;
import entities.MainCharacter;
import entities.Potion;
import entities.PoweredDot;
import entities.RegularDot;
import entities.Wall;

public interface Visitor {
	public void visitMainCharacter(MainCharacter m);
	
	public void visitEnemy(Enemy enemy);
	
	public void visitFruitTypeA(Fruit f);
	
	public void visitFruitTypeB(Fruit f);
	
	public void visitFruitTypeC(Fruit f);
	
	public void visitPotionTypeA(Potion p);
	
	public void visitPotionTypeB(Potion p);
	
	public void visitPoweredDot(PoweredDot p);
	
	public void visitRegulardDot(RegularDot p);
	
	public void visitWall(Wall w);
	
	public void visitActivePotionTypeA(PowerTypeA a);
	
	public void visitActivePotionTypeB(PowerTypeB a);

	public void visitDoorway(Doorway doorway);

	
}
