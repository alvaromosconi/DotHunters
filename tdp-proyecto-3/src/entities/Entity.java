package entities;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import entities.Entity.Direction;
import logic.Game;
import visitors.Visitor;

public abstract class Entity {

	protected Game game;
	
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
	
	public Entity(Game game) {
		this.sprites = new HashMap<Direction, String>();
		this.game = game;
	}
	
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
		// Cambio de imagen a la correspondiente con la direccion actual
		this.imageRoute = sprites.get(currentDirection);
	}

	/*
	 * Genera un rectangulo para predecir colisiones
	 */
	public Rectangle getOffsetBounds() {
	    
		return new Rectangle(xValue + xVelocity, yValue + yVelocity, width, height);
	}	
	
	public Rectangle getRectangle() {
		
		return new Rectangle(xValue, yValue, width, height);
	}

	public void setDirection(Direction direction) {

		this.currentDirection = direction;
		
		switch (direction) {
			
			case UP: {
				this.xVelocity = 0;
				this.yVelocity = -2;
				break;
			}
			
			case DOWN: {
				this.xVelocity = 0;
				this.yVelocity = 2;
				break;
			}
			
			case RIGHT: {
				this.xVelocity = 2;
				this.yVelocity = 0;
				break;
			}
			
			case LEFT: {
				this.xVelocity = -2;
				this.yVelocity = 0;
				break;
			}
			
			case STILL: {
				this.xVelocity = 0;
				this.yVelocity = 0;
				break;
			}		
		}
	}
	
	public void setNextDirection(Direction direction) {
		
		this.nextDirection = direction;
		
		switch (direction) {
		
		case UP: {
			this.nextXVelocity = 0;
			this.nextYVelocity = -2;
			break;
		}
		
		case DOWN: {
			this.nextXVelocity = 0;
			this.nextYVelocity = 2;
			break;
		}
		
		case RIGHT: {
			this.nextXVelocity = 2;
			this.nextYVelocity = 0;
			break;
		}
		
		case LEFT: {
			this.nextXVelocity = -2;
			this.nextYVelocity = 0;
			break;
		}
		
		case STILL: {
			this.nextXVelocity = 0;
			this.nextYVelocity = 0;
			break;
		}		
	}
		
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

	public Game getGame() {
		return game;
	}
}
