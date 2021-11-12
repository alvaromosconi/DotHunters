package entities;
import visitors.Visitor;
import visitors.VisitorEnemyTypeA;
import visitors.VisitorMainCharacter;

public class EnemyTypeA extends Enemy {
	
	public EnemyTypeA(int xValue, int yValue, String imageRoute) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		
		
		this.currentDirection = Direction.LEFT;
		this.nextDirection = Direction.LEFT;
		this.imageRoute = imageRoute;
		visitor = new VisitorEnemyTypeA(this);
		System.out.println(necessaryVerticalMovements(36)+","+necessaryHorizontalMovements(900));
		
	}
	
	
	@Override
	public void accept(Visitor v) {
		
		v.visitEnemyTypeA(this);
	}


	@Override
	public void chase() {
		
		
		
	}


	@Override
	public void scatter() {
		
		
		
		
	}


	@Override
	public void frightened() {
		// TODO Auto-generated method stub
		
	}


}
