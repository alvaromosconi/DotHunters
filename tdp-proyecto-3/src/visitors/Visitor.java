package visitors;
import entities.ActivePotionTypeA;
import entities.ActivePotionTypeB;
import entities.Doorway;
import entities.Enemy;
import entities.EnemyTypeA;
import entities.EnemyTypeB;
import entities.EnemyTypeC;
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
	
	public void visitActivePotionTypeA(ActivePotionTypeA a);
	
	public void visitActivePotionTypeB(ActivePotionTypeB a);

	public void visitDoorway(Doorway doorway);

	
}
