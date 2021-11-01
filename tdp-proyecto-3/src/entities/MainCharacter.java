package entities;

import visitors.Visitor;
import visitors.VisitorMainCharacter;

public class MainCharacter extends Character {

	public MainCharacter(int xValue, int yValue, int direction, String imageRoute) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.direction = direction;
		this.imageRoute = imageRoute;
		
		visitor = new VisitorMainCharacter(this);
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitMainCharacter(this);
		
	}

	@Override
	public void moveUp() {
		
	//	direction = 2;
		yValue -= 5;	
	}


	@Override
	public void moveDown() {
		
		yValue += 5;	
	}


	@Override
	public void moveLeft() {
		
		if (canMove && direction == 1)
			xValue -= 5;	
	}


	@Override
	public void moveRight() {
		
		xValue += 5;	
	}


}
