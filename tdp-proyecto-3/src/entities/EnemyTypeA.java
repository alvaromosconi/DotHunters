package entities;
import logic.Direction;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;

public class EnemyTypeA extends Enemy {
	
	public EnemyTypeA(int xValue, int yValue, String imageRoute, int speed, Game game) {
		
		super(xValue, yValue, imageRoute, speed, game);
		
		initialXValue = xValue;
	    initialYValue = yValue;
		
		this.currentDirection = Direction.LEFT;
		this.nextDirection = Direction.LEFT;

		isInsideHouse = false;
		
		visitor = new VisitorEnemy(this);
		
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
    		if (game.distance(xValue + (Direction.LEFT.getXVelocity() * speed), player.getXValue(), yValue, player.getYValue()) < min) {
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
		
		setDirection(nextDirection);
		
		game.move(this);
		
	}



	@Override
	public void scatter() {
		
		
		
		
	}


	@Override
	public void exitHouse() {
	
		isInsideHouse = false;
	}
	

    public void disableFrightenedMode() {
    	speed = chaseSpeed;
    	loadSprites("/assets/MarioAssets/" + "EnemyTypeA.gif", "/assets/MarioAssets/" + "EnemyTypeA.gif", "/assets/MarioAssets/" + "EnemyTypeA.gif", "/assets/MarioAssets/"+ "EnemyTypeA.gif");
    	frightenedMode = false;
    }



}
