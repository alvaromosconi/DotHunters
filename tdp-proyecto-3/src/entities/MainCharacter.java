package entities;

import java.util.HashMap;

import visitors.Visitor;
import visitors.VisitorMainCharacter;

public class MainCharacter extends Entity {
	
	private boolean potionTypeA;
	private boolean potionTypeB;

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
		this.width = 36;
		this.height = 36;
		
		this.potionTypeA = false;
		this.potionTypeB = false;
		visitor = new VisitorMainCharacter(this);
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitMainCharacter(this);
	}	
	
	public void setPotionTypeA(boolean p) {
		potionTypeA = p;
	}
	
	public void setPotionTypeB(boolean p) {
		potionTypeB = p;
	}

	public boolean getPotionTypeA() {
		return potionTypeA;
	}
	
	public boolean getPotionTypeB() {
		return potionTypeB;
	}
}
