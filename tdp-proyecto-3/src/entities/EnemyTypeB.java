package entities;
import java.util.List;

import logic.Direction;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;

public class EnemyTypeB extends Enemy {

	public EnemyTypeB(int xValue, int yValue, String imageRoute, int speed, Game game) {
		
		super(xValue, yValue, imageRoute, speed, game);
		visitor = new VisitorEnemy(this);
	}
	
	@Override
	public void accept(Visitor v) {
	
		v.visitEnemy(this);
	}

	@Override
	protected void chase() {

		Enemy enemyA = game.getEnemyTypeA();
		
		// Si hay 2 direcciones po
		
		List<Direction> possibleDirections = getPossibleDirections(getOppositeDirection());
		
		if (possibleDirections.size() == 1)		// Si hay solo una direccion posible (No contando la opuesta)
			setNextDirection(getPossibleDirections(getOppositeDirection()).get(0)); 	// Usar tal direccion
		
		else
		
			// Establece la direccion que va a ejecutar el EnemyTypeA y la gira 180°
			switch(enemyA.nextDirection) {
			
				case UP: {
					
					if (!game.collideWithWall(Direction.DOWN, this) && currentDirection != Direction.UP)
						setNextDirection(Direction.DOWN);
				
					break;
				}
					
				case DOWN: {
					
					if (!game.collideWithWall(Direction.UP, this) && currentDirection != Direction.DOWN )
						setNextDirection(Direction.UP);
				
					break;
				}
				
				case LEFT: {
					
					if (!game.collideWithWall(Direction.RIGHT, this) && currentDirection != Direction.LEFT) 
						setNextDirection(Direction.RIGHT);
				
					break;
				}
				
				case RIGHT: {
					
					if (!game.collideWithWall(Direction.LEFT, this) && currentDirection != Direction.RIGHT) 
						setNextDirection(Direction.LEFT);
	
					break;
				}
			
				default:
					
					break;
				
			}			
		
	}

	@Override
	protected void exitHouse() {
	
		if (!game.collideWithWall(Direction.UP, this))
			setNextDirection(Direction.UP);
		else setNextDirection(Direction.RIGHT);
		
		if (yValue < initialYValue - 36)
			state = State.CHASING;	
	}
	
	public void disableRespawnMode() {
		state = State.CHASING;
		loadSprites(domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute+ "EnemyTypeB.gif");
	}
	
    public void disableFrightenedMode() {
    	speed = chaseSpeed;
    	loadSprites(domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute+ "EnemyTypeB.gif");
    	
    }

}
