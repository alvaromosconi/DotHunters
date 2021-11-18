package entities;

import java.awt.Rectangle;
import java.util.HashMap;

import logic.Direction;
import logic.Game;

public abstract class Character extends Entity {
	
	protected int xVelocity;
	protected int yVelocity;
	protected int nextXVelocity;
	protected int nextYVelocity;
	protected int speed;
	
	protected Direction currentDirection;
	protected Direction nextDirection;

	public Character(int xValue, int yValue, String imageRoute, int speed, Game game) {
	
		super(xValue, yValue, imageRoute, game);
		
		this.speed = speed;	
		this.width = 36;
		this.height = 36;
		this.sprites = new HashMap<Direction, String>();
	}	
	
	public void setVelocity(Direction direction) {
		
		this.xVelocity = direction.getXVelocity() * speed;
		this.yVelocity = direction.getYVelocity() * speed;
	}
	
	public void setNextVelocity (Direction direction) {
		
		this.nextXVelocity = direction.getXVelocity() * speed;
		this.nextYVelocity = direction.getYVelocity() * speed;
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
	
	public void setDirection(Direction direction) {

		this.currentDirection = direction;
		
		switch (direction) {
			
			case UP: {
				setVelocity(Direction.UP);
				break;
			}
			
			case DOWN: {
				setVelocity(Direction.DOWN);
				break;
			}
			
			case RIGHT: {
				setVelocity(Direction.RIGHT);
				break;
			}
			
			case LEFT: {
				setVelocity(Direction.LEFT);
				break;
			}
			
			case STILL: {
				setVelocity(Direction.STILL);
				break;
			}		
		}
	}
	
	public void setNextDirection(Direction direction) {
		
		this.nextDirection = direction;
		
		switch (direction) {
		
			case UP: {
				setNextVelocity(Direction.UP);
				break;
			}
			
			case DOWN: {
				setNextVelocity(Direction.DOWN);
				break;
			}
			
			case RIGHT: {
				setNextVelocity(Direction.RIGHT);
				break;
			}
			
			case LEFT: {
				setNextVelocity(Direction.LEFT);
				break;
			}
			
			case STILL: {
				setNextVelocity(Direction.STILL);
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
	
	public Direction getOppositeDirection() {
		
		Direction toReturn = null;
		
		switch (currentDirection) {
			
			case UP: {
				toReturn = Direction.DOWN;
				break;
			}
			
			case DOWN: {
				toReturn = Direction.UP;
				break;
			}
			
			case RIGHT: {
				toReturn = Direction.LEFT;
				break;
			}
			
			case LEFT: {
				toReturn = Direction.RIGHT;
				break;
			}
			
			case STILL: {
				toReturn = Direction.LEFT;
				break;
			}		
		}
		
		return toReturn;
	}
	
	public void loadSprites(String upSprite, String downSprite, String rightSprite, String leftSprite) {
		
		sprites.put(Direction.UP, upSprite);
		sprites.put(Direction.DOWN, downSprite);
		sprites.put(Direction.RIGHT, rightSprite);
		sprites.put(Direction.LEFT, leftSprite);
		sprites.put(Direction.STILL, downSprite);
	}
	
	
	/*
	 * Genera un rectangulo para predecir colisiones
	 */
	public Rectangle getOffsetBounds() {
	    
		return new Rectangle(xValue + xVelocity, yValue + yVelocity, width, height);
	}

	public int getSpeed() {
		
		return speed;
	}	
}
