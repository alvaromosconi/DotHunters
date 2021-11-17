package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enemies.ChaseBehaviour;
import enemies.FrightenedBehaviour;
import enemies.ScatterBehaviour;
import entities.Entity.Direction;
import logic.Game;

public abstract class Enemy extends Entity implements ChaseBehaviour, ScatterBehaviour, FrightenedBehaviour {
	
	protected boolean frightenedMode;
	protected boolean isInsideHouse;
	
	protected int initialXValue;
	protected int initialYValue;
	
	public Enemy(Game game) {
	
		super(game);
		frightenedMode = false;
		this.width = 36;
		this.height = 36;
	}
	
	
	public int necessaryVerticalMovements(int y) {

        int difference = y - this.yValue;

        return difference / 36;

    }

    public int necessaryHorizontalMovements(int x) {

        int difference = x - this.xValue;

        return difference / 36;
    }
    
    public boolean getFrightenedMode() {
    	
    	return frightenedMode;
    }
    
    public void enableFrightenedMode() {
    	
     	frightenedMode = true;
    	loadSprites("/assets/MarioAssets/" + "frightenedFront.gif", "/assets/MarioAssets/" + "frightenedBack.gif", "/assets/MarioAssets/" + "frightenedFront.gif", "/assets/MarioAssets/" + "frightenedFront.gif");
   
    }
    
    public void disableFrightenedMode() {
    	loadSprites("/assets/MarioAssets/" + "EnemyTypeA.gif", "/assets/MarioAssets/" + "EnemyTypeA.gif", "/assets/MarioAssets/" + "EnemyTypeA.gif", "/assets/MarioAssets/"+ "EnemyTypeA.gif");
    	frightenedMode = false;
    }
    
	
    public void frightened() {

    
    	Random rnd = new Random();
    	Direction aux = null;
    	
    	List<Direction> possibleDirections = getPossibleDirections(getOppositeDirection());
    	
    	if (xValue % 36 == 0 && yValue % 36 == 0 ) {
    	
    		if (possibleDirections.size() == 0) 
    			setNextDirection(getOppositeDirection());
    		else if (possibleDirections.size() > 1 )
    			setNextDirection(possibleDirections.get(rnd.nextInt(possibleDirections.size())));
    		else {
    			setNextDirection(possibleDirections.get(0));
    		}
    		
    	}
    	
    	this.setDirection(nextDirection);
    	game.move(this);
		
    }
    
    private List<Direction> getPossibleDirections(Direction oppositeDirection) {
    
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
    
    
	public abstract void exitHouse();
	
    
    public boolean IsInsideHouse() {
    	
    	return isInsideHouse;
    }
    
    public void IsInsideHouse(boolean b) {
    	
    	isInsideHouse = b;
    }
    
    public int getInitialXValue() {
    	
    	return initialXValue;
    }
    
    public int getInitialYValue() {
    	
    	return initialYValue;
    }

}
