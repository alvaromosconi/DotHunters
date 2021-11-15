package entities;
import logic.Game;
import visitors.Visitor;

public class EnemyTypeD extends Enemy {

	public EnemyTypeD(int xValue, int yValue, String imageRoute, Game game) {
		super(game);
		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
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


}
