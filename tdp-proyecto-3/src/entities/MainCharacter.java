package entities;

import java.awt.Rectangle;

import visitors.Visitor;
import visitors.VisitorMainCharacter;

public class MainCharacter extends Entity {

	public MainCharacter(int xValue, int yValue, String imageRoute) {
	
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
		this.xVelocity = 0;
		this.yVelocity = 0;
		
		this.width = 36;
		this.height = 36;
		
		visitor = new VisitorMainCharacter(this);
		this.moving = false;
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitMainCharacter(this);
	}	


}
