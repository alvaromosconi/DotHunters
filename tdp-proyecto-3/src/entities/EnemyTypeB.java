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
	public void chase() {
		
		Enemy enemyA = game.getEnemyTypeA();
		
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
		
		if (getPossibleDirections().size() == 1)
			setNextDirection(getPossibleDirections().get(0));
		
//		if (game.collideWithWall(nextDirection, this))
//			if (!game.collideWithWall(Direction.UP, this))
//				setNextDirection(Direction.UP);
//			else
//				if (!game.collideWithWall(Direction.LEFT, this))
//					setNextDirection(Direction.LEFT);
//			else
//				if (!game.collideWithWall(Direction.RIGHT, this))
//					setNextDirection(Direction.DOWN);
//					
		
		
		setDirection(nextDirection);
		
		game.move(this);
	}
	
    private List<Direction> getPossibleDirections() {
        
    	List<Direction> possibleDirections = new ArrayList<Direction>();
    	
    	if (!game.collideWithWall(Direction.LEFT, this)) 
    		possibleDirections.add(Direction.LEFT);
       	if (!game.collideWithWall(Direction.RIGHT, this)) 
    		possibleDirections.add(Direction.RIGHT);
       	if (!game.collideWithWall(Direction.UP, this)) 
    		possibleDirections.add(Direction.UP);
       	if (!game.collideWithWall(Direction.DOWN, this))
    		possibleDirections.add(Direction.DOWN);
		
       	
       	return possibleDirections;
    }
		

	

	@Override
	public void scatter() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void exitHouse() {
	
		if (!game.collideWithWall(Direction.UP, this))
			setNextDirection(Direction.UP);
		else setNextDirection(Direction.RIGHT);
		
		
		setDirection(nextDirection);
		
		
		game.move(this);
		
		if (yValue < 7 * 36 )
			isInsideHouse = false;
 		
	}
	
    public void disableFrightenedMode() {
    	speed = chaseSpeed;
    	loadSprites("/assets/MarioAssets/" + "EnemyTypeB.gif", "/assets/MarioAssets/" + "EnemyTypeB.gif", "/assets/MarioAssets/" + "EnemyTypeB.gif", "/assets/MarioAssets/"+ "EnemyTypeB.gif");
    	frightenedMode = false;
    }

}
