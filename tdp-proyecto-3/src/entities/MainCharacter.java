package entities;

import visitors.Visitor;
import visitors.VisitorMainCharacter;

public class MainCharacter extends Entity {

	public MainCharacter(int xValue, int yValue, String imageRoute) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
		this.canMove = true;
		
		visitor = new VisitorMainCharacter(this);
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitMainCharacter(this);
		
	}

	@Override
	public void moveUp() {
		
		yValue -= 3;	
	}


	@Override
	public void moveDown() {
		
		yValue += 3;	
	}


	@Override
	public void moveLeft() {
		
		if (canMove)
			xValue -= 3;	
	}


	@Override
	public void moveRight() {
		
		xValue += 3;	
	}


}
