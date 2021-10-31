import Visitors.Visitor;

public class MainCharacter extends Character {

	public MainCharacter(int xValue, int yValue, int direction, String imageRoute) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.direction = direction;
		this.imageRoute = imageRoute;
		
	}
	
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveUp() {
		
		yValue -= 5;

		
	}


	@Override
	public void moveDown() {
		
		yValue += 5;

		
	}


	@Override
	public void moveLeft() {
		xValue -= 5;

		
	}


	@Override
	public void moveRight() {
		
		xValue += 5;

		
	}

}
