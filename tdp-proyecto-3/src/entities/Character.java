package entities;

import java.awt.Rectangle;
import java.util.HashMap;

import logic.Direction;
import logic.Game;

public abstract class Character extends Entity {
	
	protected int xVelocity;		// Vector velocidad en x.  xVelocity = vector direccion * cte velocidad 
	protected int yVelocity;		// Vector velocidad en y.  yVelocity = vector direccion * cte velocidad 
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
		
		// Mapeo entre direcciones y sprites
		this.sprites = new HashMap<Direction, String>();
	}	
	
	/*
	 * Se establece vector velocidad en base al vector direccion y la velocidad
	 * @param direction vector direccion
	 */
	public void setVelocity(Direction direction) {
		
		this.xVelocity = direction.getXVelocity() * speed;
		this.yVelocity = direction.getYVelocity() * speed;
	}
	
	/*
	 * Se establecen el siguiente vector velocidad en base al vector direccion y la velocidad
	 * @param direction vector Direccion
	 */
	public void setNextVelocity (Direction direction) {
		
		this.nextXVelocity = direction.getXVelocity() * speed;
		this.nextYVelocity = direction.getYVelocity() * speed;
	}
	
	/*
	 * Realiza el movimiento estableciendo las nuevas posiciones en (X,Y) y cambia las imagenes en base a la direccion
	 */
	public void move() {
	
		xValue += xVelocity;
		yValue += yVelocity;
		// Cambio de imagen a la correspondiente con la direccion actual
		this.imageRoute = sprites.get(currentDirection);
	}
	
	/*
	 * Establece la nueva direccion
	 * @param direction nueva direccion
	 */
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
	
	/*
	 * Establece la siguiente direccion
	 * @param direction nueva direccion
	 */
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
	
	
	/*
	 * Retorna la direccion opuesta
	 * @return retorna la direccion opuesta
	 */
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
	
	/*
	 * Mapea la direccion con su sprite correspondiente
	 */
	public void loadSprites(String upSprite, String downSprite, String rightSprite, String leftSprite) {
		
		sprites.put(Direction.UP, upSprite);
		sprites.put(Direction.DOWN, downSprite);
		sprites.put(Direction.RIGHT, rightSprite);
		sprites.put(Direction.LEFT, leftSprite);
		sprites.put(Direction.STILL, downSprite);
	}
	
	
	/*
	 * Genera un rectangulo para predecir colisiones
	 * @ return rectangulo con offset para predecir colisiones
	 */
	public Rectangle getOffsetBounds() {
	    
		return new Rectangle(xValue + xVelocity, yValue + yVelocity, width, height);
	}

	/*
	 * Retorna la velocidad actual
	 * @return speed velocidad actual
	 */
	public int getSpeed() {
		
		return speed;
	}	
}
