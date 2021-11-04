package entities;
import visitors.Visitor;

public class EnemyTypeB extends Enemy {

	public EnemyTypeB(int xValue, int yValue, String imageRoute) {
		

		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
		
		
	}
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
