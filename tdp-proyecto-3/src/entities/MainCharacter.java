package entities;

import logic.Game;
import visitors.Visitor;
import visitors.VisitorMainCharacter;

import logic.Direction;

public class MainCharacter extends Character {
	
	private boolean havePotionTypeA;

	public MainCharacter(int xValue, int yValue, String imageRoute, int speed, Game game) {
	
		super(xValue, yValue, imageRoute, speed, game);
	
		this.currentDirection = Direction.STILL;
		this.nextDirection = Direction.STILL;
		this.width = 36;
		this.height = 36;
		
		this.havePotionTypeA = false;
		
		visitor = new VisitorMainCharacter(this);
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitMainCharacter(this);
	}	
	
	/*
	 * Ejecuta el movimiento semiautomatico que permite que pacman guarde posiciones tentativas y no colisione.
	 */
	public void move() {
		
		if (nextDirection != Direction.STILL ) 
			
			if (!game.collideWithWall(nextDirection, this)) {
				setDirection(nextDirection);
				setNextDirection(Direction.STILL);
			}
		
		// Delega en Entidad para ejecutar el movimiento.
		super.move();
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void havePotionTypeA(boolean p) {
		havePotionTypeA = p;
	}
	
	public boolean havePotionTypeA() {
		return havePotionTypeA;
	}


}
