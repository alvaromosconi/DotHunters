package entities;
import visitors.Visitor;
import visitors.VisitorEnemyTypeA;
import visitors.VisitorMainCharacter;

public class EnemyTypeA extends Enemy {
	
	public EnemyTypeA(int xValue, int yValue, String imageRoute) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
		
		visitor = new VisitorEnemyTypeA(this);
	}
	
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}


}
