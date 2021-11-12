package entities;
import java.awt.Rectangle;
import java.util.Map;

import entities.Entity.Direction;
import visitors.Visitor;

public abstract class Entity {

	protected int width;
	protected int height;
	protected int xValue;
	protected int yValue;

	protected int xVelocity;
	protected int yVelocity;
	protected int nextXVelocity;
	protected int nextYVelocity;
	protected Direction currentDirection;
	protected Direction nextDirection;

	protected Visitor visitor;
	protected String imageRoute;
	protected Map<Direction, String> sprites;
	
	public enum Direction {
		UP,
		DOWN,
		RIGHT,
		LEFT,
		STILL;	
	}
	
	public void setVelocity(int xVelocity, int yVelocity) {
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	
	public void setNextVelocity (int nextXVelocity, int nextYVelocity) {
		
		this.nextXVelocity = nextXVelocity;
		this.nextYVelocity = nextYVelocity;
	}
	
	public void setXValue(int xValue) {
		
		this.xValue = xValue;
	}
	
	public void setYValue(int yValue) {
		
		this.yValue = yValue;
	}
	
	public void setVisitor(Visitor visitor) {
		
		this.visitor = visitor;
	}
	
	public void setXVelocity(int xVelocity) {
		
		this.xVelocity = xVelocity;
	}
	
	public void setYVelocity(int yVelocity) {
		
		this.yVelocity = yVelocity;
	}
	
	public void setNextXVelocity(int nextXVelocity) {
		
		this.nextXVelocity = nextXVelocity;
	}
	
	public void setNextYVelocity(int nextYVelocity) {
		
		this.nextYVelocity = nextYVelocity;
	}
	
	public int getXValue() {
		
		return xValue;
	}
	
	public int getYValue() {
		
		return yValue;
	}
	
	public void setWidth(int width) {
		
		this.width = width;
	}
	
	public void setHeight(int height) {
		
		this.height = height;
	}
	
	public String getImageRoute() {
		
		return imageRoute;
	}
	
	public Visitor getVisitor() {
		
		return visitor;
	}
	
	public abstract void accept(Visitor v);


	public int getWidth() {
			
		return width;	
	}

	public int getHeight() {
		
		return height;	
	}
	
	public int getXVelocity() {
		
		return yVelocity;
	}
	
	public int getYVelocity() {
		
		return xVelocity;
	}
	
	public int getNextXVelocity() {
		
		return nextXVelocity;
	}
	
	public int getNextYVelocity() {
		
		return nextYVelocity;
	}
	
	public void move() {
	
		xValue += xVelocity;
		yValue += yVelocity;
		this.imageRoute = sprites.get(currentDirection);
	}

	public Rectangle getOffsetBounds() {
	    return new Rectangle(xValue + xVelocity, yValue + yVelocity, width, height);
	}	
	
	public Rectangle getRectangle() {
		
		return new Rectangle(xValue, yValue, width, height);
	}


	public void setDirection(Direction direction) {

		this.currentDirection = direction;
	}
	
	public void setNextDirection(Direction direction) {
		
		this.nextDirection = direction;
		
	}

	public Direction getDirection() {
		
		return currentDirection;
	}
	
	public Direction getNextDirection() {
		
		return nextDirection;
	}	
	
	public void loadSprites(String upSprite, String downSprite, String rightSprite, String leftSprite) {
		
		sprites.put(Direction.UP, upSprite);
		sprites.put(Direction.DOWN, downSprite);
		sprites.put(Direction.RIGHT, rightSprite);
		sprites.put(Direction.LEFT, leftSprite);
		sprites.put(Direction.STILL, downSprite);
	}

}
