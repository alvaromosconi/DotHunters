package entities;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;

public class EnemyTypeD extends Enemy {

	public EnemyTypeD(int xValue, int yValue, String imageRoute, int speed, Game game) {
	
		super(xValue, yValue, imageRoute, speed, game);
		
	    initialXValue = xValue;
		initialYValue = yValue;
		
		this.imageRoute = imageRoute;

		visitor = new VisitorEnemy(this);
		isInsideHouse = true;
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

	@Override
	public void exitHouse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableFrightenedMode() {
		// TODO Auto-generated method stub
		
	}


}
