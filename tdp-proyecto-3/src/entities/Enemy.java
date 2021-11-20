package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.Game;
import logic.Direction;

public abstract class Enemy extends Character {
	
//	protected boolean frightenedMode;
//	protected boolean isInsideHouse;
//	protected boolean respawnMode;
	
	protected State state;
	
	protected int initialXValue;
	protected int initialYValue;
	protected int chaseSpeed;
	protected final static int frightenedSpeed = 1;
	
	public Enemy(int xValue, int yValue, String imageRoute, int speed, Game game) {
	
		super(xValue, yValue, imageRoute, speed, game);
//		frightenedMode = false;
//    	respawnMode = false;
		chaseSpeed = speed;
		state = State.LEAVINGHOUSE;
		
	}
	
	public enum State {
		FRIGHTENED,CHASING,RESPAWNING,LEAVINGHOUSE;
	}
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
 
    public void enableFrightenedMode() {
    		state = State.FRIGHTENED;
    		speed = frightenedSpeed;
    		loadSprites("/assets/MarioAssets/" + "frightenedFront.gif", "/assets/MarioAssets/" + "frightenedBack.gif", "/assets/MarioAssets/" + "frightenedFront.gif", "/assets/MarioAssets/" + "frightenedFront.gif");

    }
    
    public abstract void disableFrightenedMode();
	 
    public void executeCurrentBehaviour() {
    	
    	/*if (isInsideHouse)
			exitHouse();
		
    	else if (frightenedMode)
			frightened();
		
		else if (!respawnMode)
			chase();
    	*/
    	switch(state) {
    		case FRIGHTENED:{
    			frightened();
    			break;
    		}	
    		case LEAVINGHOUSE:{
    			exitHouse();
    			break;
    		}	
    		case CHASING:{
    			chase();
    			break;
    		}
    		case RESPAWNING:{
    			break;
    		}
    		default:
    			break;
    	}
    	
		setDirection(nextDirection);	
		game.move(this);
    }	
    
    protected void frightened() {


    	Random rnd = new Random();
    	List<Direction> possibleDirections = getPossibleDirections(getOppositeDirection());
    	
    	if (xValue % 36 == 0 && yValue % 36 == 0 ) {
    	
    		if (possibleDirections.isEmpty()) 
    			setNextDirection(getOppositeDirection());
    		else if (possibleDirections.size() > 1 )
    			setNextDirection(possibleDirections.get(rnd.nextInt(possibleDirections.size())));
    		else 
    			setNextDirection(possibleDirections.get(0));
    			
    	}
    	
    }
    
	protected abstract void chase();
	
	protected abstract void exitHouse();
    
	public void enableRespawnMode() {
			state = State.RESPAWNING;
			loadSprites("/assets/MarioAssets/" + "respawnMode.gif", "/assets/MarioAssets/" + "respawnMode.gif", "/assets/MarioAssets/" + "respawnMode.gif", "/assets/MarioAssets/" + "respawnMode.gif");
	}

	public abstract void disableRespawnMode();
	
//    public boolean isInFrightenedMode() {
//    	
//    	return frightenedMode;
//    }
//    
//    public boolean isInRespawnMode() {
//    	
//    	return respawnMode;
//    }
//    
//    public boolean IsInsideHouse() {
//    	
//    	return isInsideHouse;
//    }
//    
//    public void IsInsideHouse(boolean b) {
//    	
//    	isInsideHouse = b;
//    }
    
    public int getInitialXValue() {
    	
    	return initialXValue;
    }
    
    public int getInitialYValue() {
    	
    	return initialYValue;
    }
    
    protected List<Direction> getPossibleDirections(Direction oppositeDirection) {
        
    	List<Direction> possibleDirections = new ArrayList<Direction>();
    	
    	if (!game.collideWithWall(Direction.LEFT, this) && oppositeDirection != Direction.LEFT) 
    		possibleDirections.add(Direction.LEFT);
       	if (!game.collideWithWall(Direction.RIGHT, this) && oppositeDirection != Direction.RIGHT) 
    		possibleDirections.add(Direction.RIGHT);
       	if (!game.collideWithWall(Direction.UP, this) && oppositeDirection != Direction.UP) 
    		possibleDirections.add(Direction.UP);
       	if (!game.collideWithWall(Direction.DOWN, this) && oppositeDirection != Direction.DOWN) 
    		possibleDirections.add(Direction.DOWN);
		
       	
       	return possibleDirections;
    }
}
