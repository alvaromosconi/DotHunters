package entities;

import java.util.HashMap;

import visitors.Visitor;
import visitors.VisitorMainCharacter;

public class MainCharacter extends Entity {

	public MainCharacter(int xValue, int yValue, String imageRoute) {
	
		this.imageRoute = imageRoute;
		this.xValue = xValue;
		this.yValue = yValue;
	
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.nextXVelocity = 0;
		this.nextYVelocity = 0;
		this.currentDirection = Direction.STILL;
		this.nextDirection = Direction.STILL;
		this.sprites = new HashMap<Direction, String>();
		this.width = 36;
		this.height = 36;
		
		visitor = new VisitorMainCharacter(this);
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitMainCharacter(this);
	}	
	


}
