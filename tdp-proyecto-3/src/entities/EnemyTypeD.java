package entities;
import logic.Direction;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;

public class EnemyTypeD extends Enemy {

	public EnemyTypeD(int xValue, int yValue, String imageRoute, int speed, Game game) {
	
		super(xValue, yValue, imageRoute, speed, game);
		
	    initialXValue = xValue;
		initialYValue = yValue;
		
		this.imageRoute = imageRoute;

		visitor = new VisitorEnemy(this);
		isInsideHouse = true;
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitEnemy(this);
	}

	@Override
	public void chase() {

		MainCharacter player = game.getPlayer();
		float min = Float.MAX_VALUE;
		
		
		
		if (!game.collideWithWall(Direction.LEFT, this) && currentDirection != Direction.RIGHT) 
    		if (game.distance(xValue + (Direction.LEFT.getXVelocity() * speed), player.getXValue(), yValue, player.getYValue()) < min ) {
    				min = game.distance(xValue + (Direction.LEFT.getXVelocity() * speed), player.getXValue(), yValue, player.getYValue());
    				setNextDirection(Direction.LEFT);
    		}
    		
		if (!game.collideWithWall(Direction.RIGHT, this) && currentDirection != Direction.LEFT) 
			if (game.distance(xValue + (Direction.RIGHT.getXVelocity() * speed), player.getXValue(), yValue, player.getYValue()) < min) {
					min = game.distance(xValue + (Direction.RIGHT.getXVelocity() * speed), player.getXValue(), yValue, player.getYValue());
					setNextDirection(Direction.RIGHT);
			}
			
		if (!game.collideWithWall(Direction.UP, this) && currentDirection != Direction.DOWN )
			if (game.distance(xValue, player.getXValue(), yValue + (Direction.UP.getYVelocity() * speed), player.getYValue()) < min) {
					min = game.distance(xValue, player.getXValue(), yValue + (Direction.UP.getYVelocity() * speed) , player.getYValue());
					setNextDirection(Direction.UP);
			}
		
		if (!game.collideWithWall(Direction.DOWN, this) && currentDirection != Direction.UP)
			if (game.distance(xValue, player.getXValue(), yValue + (Direction.DOWN.getYVelocity() * speed), player.getYValue()) < min) {
					min = game.distance(xValue, player.getXValue(), yValue + (Direction.DOWN.getYVelocity() * speed), player.getYValue());
					setNextDirection(Direction.DOWN);
			}
		
		if (game.distance(xValue, player.getXValue(), yValue, player.getYValue()) <= 8 * 36)
			goToCorner();
				
	}

	private void goToCorner() {
		

		int xDestiny = 36,
		     yDestiny = 17 * 36;
		
		double min = Double.MAX_VALUE;

		if (!game.collideWithWall(Direction.LEFT, this) && currentDirection != Direction.RIGHT) 
    		if (game.distance(xValue - 2, xDestiny, yValue, yDestiny) < min) {
    				min = game.distance(xValue - 2, xDestiny, yValue, yDestiny);
    				setNextDirection(Direction.LEFT);
    		}
    		
		if (!game.collideWithWall(Direction.RIGHT, this) && currentDirection != Direction.LEFT) 
			if (game.distance(xValue + 2, xDestiny, yValue, yDestiny) < min) {
					min = game.distance(xValue + 2, xDestiny, yValue, yDestiny);
					setNextDirection(Direction.RIGHT);
			}
			
		if (!game.collideWithWall(Direction.UP, this) && currentDirection != Direction.DOWN )
			if (game.distance(xValue, xDestiny, yValue - 2, yDestiny) < min) {
					min = game.distance(xValue, xDestiny, yValue - 2, yDestiny);
					setNextDirection(Direction.UP);
			}
		
		if (!game.collideWithWall(Direction.DOWN, this) && currentDirection != Direction.UP)
			if (game.distance(xValue, xDestiny, yValue + 2, yDestiny) < min) {
					min = game.distance(xValue, xDestiny, yValue + 2, yDestiny);
					setNextDirection(Direction.DOWN);
			}
	}

	@Override
	public void exitHouse() {

		if (!game.collideWithWall(Direction.UP, this))
			setNextDirection(Direction.UP);
		else setNextDirection(Direction.LEFT);
		
		if (yValue < 7 * 36 )
			isInsideHouse = false;
		
	}

	public void disableRespawnMode() {
		
		loadSprites("/assets/MarioAssets/" + "EnemyTypeD.gif", "/assets/MarioAssets/" + "EnemyTypeD.gif", "/assets/MarioAssets/" + "EnemyTypeD.gif", "/assets/MarioAssets/"+ "EnemyTypeD.gif");
		respawnMode = true;
	}
	
	@Override
	public void disableFrightenedMode() {
		
    	speed = chaseSpeed;
    	loadSprites("/assets/MarioAssets/" + "EnemyTypeD.gif", "/assets/MarioAssets/" + "EnemyTypeD.gif", "/assets/MarioAssets/" + "EnemyTypeD.gif", "/assets/MarioAssets/"+ "EnemyTypeD.gif");
    	frightenedMode = false;	
	}


}
