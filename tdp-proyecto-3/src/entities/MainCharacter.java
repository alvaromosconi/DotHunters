package entities;

import java.awt.Rectangle;

import visitors.Visitor;
import visitors.VisitorMainCharacter;

public class MainCharacter extends Entity {

	public MainCharacter(int xValue, int yValue, String imageRoute) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
		this.xVelocity = 2;
		this.yVelocity = 2;
		
		visitor = new VisitorMainCharacter(this);
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitMainCharacter(this);
	}	


}
