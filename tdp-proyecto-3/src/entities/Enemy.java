package entities;

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

		if (!game.collideWithWall(-2, 0, this) && this.getDirection() != Direction.RIGHT) 	    				
    		this.setNextDirection(Direction.LEFT);
    	
		if (!game.collideWithWall(2, 0, this) && this.getDirection() != Direction.LEFT) 
			this.setNextDirection(Direction.RIGHT);
			
		if (!game.collideWithWall(0, -2, this) && this.getDirection() != Direction.DOWN)
    			this.setNextDirection(Direction.UP);

		if (!game.collideWithWall(0, 2, this) && this.getDirection() != Direction.UP)
    			this.setNextDirection(Direction.DOWN);
		
		this.setDirection(nextDirection);
		game.move(this);
		
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
