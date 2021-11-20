package entities;
import java.util.ArrayList;
import java.util.List;

import logic.Direction;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;

public class EnemyTypeB extends Enemy {

	public EnemyTypeB(int xValue, int yValue, String imageRoute, int speed, Game game) {
		
		super(xValue, yValue, imageRoute, speed, game);
		
	    initialXValue = xValue;
		initialYValue = yValue;
		
		visitor = new VisitorEnemy(this);
		isInsideHouse = true;
	}
	
	@Override
	public void accept(Visitor v) {
	
		v.visitEnemy(this);
	}

	@Override
	protected void chase() {

		Enemy enemyA = game.getEnemyTypeA();
		
		if (getPossibleDirections(getOppositeDirection()).size() == 1)
			setNextDirection(getPossibleDirections(getOppositeDirection()).get(0));
		
		else
		
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
		
		
		if (yValue < 7 * 36 )
			isInsideHouse = false;	
	}
	
	public void disableRespawnMode() {
		
		loadSprites("/assets/MarioAssets/" + "EnemyTypeB.gif", "/assets/MarioAssets/" + "EnemyTypeB.gif", "/assets/MarioAssets/" + "EnemyTypeB.gif", "/assets/MarioAssets/"+ "EnemyTypeB.gif");
		respawnMode = true;
	}
	
    public void disableFrightenedMode() {
    	speed = chaseSpeed;
    	loadSprites("/assets/MarioAssets/" + "EnemyTypeB.gif", "/assets/MarioAssets/" + "EnemyTypeB.gif", "/assets/MarioAssets/" + "EnemyTypeB.gif", "/assets/MarioAssets/"+ "EnemyTypeB.gif");
    	frightenedMode = false;
    }

}
