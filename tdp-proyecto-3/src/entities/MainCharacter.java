package entities;

import logic.Game;
import visitors.Visitor;
import visitors.VisitorMainCharacter;

import java.util.HashMap;

import logic.Direction;

public class MainCharacter extends Character {
	
	private boolean potionTypeA;

	public MainCharacter(int xValue, int yValue, String imageRoute, int speed, Game game) {
	
		super(xValue, yValue, imageRoute, speed, game);
	
		this.currentDirection = Direction.STILL;
		this.nextDirection = Direction.STILL;
		this.width = 36;
		this.height = 36;
		
		this.potionTypeA = false;
		
		visitor = new VisitorMainCharacter(this);
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitMainCharacter(this);
	}	
	
	public void setPotionTypeA(boolean p) {
		potionTypeA = p;
	}
	
	public boolean getPotionTypeA() {
		return potionTypeA;
	}
	
	public void move() {
		
		if (nextDirection != Direction.STILL ) 
			
			if (!game.collideWithWall(nextDirection, this)) {
				setDirection(nextDirection);
				setNextDirection(Direction.STILL);
			}
		
		super.move();
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}


}
