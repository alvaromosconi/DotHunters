package visitors;
import entities.ActivePotionTypeA;
import entities.EnemyTypeA;
import entities.Fruit;
import entities.MainCharacter;
import entities.Potion;
import entities.PoweredDot;
import entities.RegularDot;
import entities.Wall;


public interface Visitor {
	public void visitMainCharacter(MainCharacter m);
	
	public void visitEnemyTypeA(EnemyTypeA e);
	
	public void visitFruitTypeA(Fruit f);
	
	public void visitFruitTypeB(Fruit f);
	
	public void visitFruitTypeC(Fruit f);
	
	public void visitPotionTypeA(Potion p);
	
	public void visitPotionTypeB(Potion p);
	
	public void visitPoweredDot(PoweredDot p);
	
	public void visitRegulardDot(RegularDot p);
	
	public void visitWall(Wall w);
	
	public void visitActivePotionTypeA(ActivePotionTypeA a);
	
}
