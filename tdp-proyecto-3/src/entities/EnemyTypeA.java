package entities;
import visitors.Visitor;
import visitors.VisitorEnemyTypeA;
import visitors.VisitorMainCharacter;

public class EnemyTypeA extends Enemy {
	private int verticalMovements;
	private int horizontalMovements;
	
	public EnemyTypeA(int xValue, int yValue, String imageRoute) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		
		
		this.currentDirection = Direction.STILL;
		this.imageRoute = imageRoute;
		visitor = new VisitorEnemyTypeA(this);
		System.out.println(necessaryVerticalMovements(36)+","+necessaryHorizontalMovements(900));
		
		
		
	}
	
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void chase() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void scatter() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void frightened() {
		// TODO Auto-generated method stub
		
	}
	public int necessaryVerticalMovements(int y) {

        int difference = y - this.yValue;

        return difference / 36;

    }

    public int necessaryHorizontalMovements(int x) {

        int difference = x - this.xValue;

        return difference / 36;
    }

}
