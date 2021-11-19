package entities;
import logic.Direction;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;


public class EnemyTypeC extends Enemy {

	public EnemyTypeC(int xValue, int yValue, String imageRoute, int speed, Game game) {
		
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
		
		MainCharacter player = game.getPlayer();
		
		int xDestiny = 0,
		      yDestiny = 0;

		switch(player.currentDirection) {
		
			case UP: {
				
				xDestiny = player.getXValue() - (2 * 36) + (player.getXValue() - (2 * 36)) % 36 ;
				yDestiny = player.getYValue() - (4 * 36) + (player.getYValue() - 4 * 36) % 36 ;		
				
				break;
			}
				
			case DOWN:  {
				
				xDestiny = player.getXValue();
				yDestiny = player.getYValue() + (4 * 36) + (player.getYValue() + (4 * 36)) % 36 ;
			
				break;
			}
			case LEFT: {
				
				xDestiny = player.getXValue() - (4 * 36) + (player.getXValue() - (4 * 36)) % 36 ;
				yDestiny = player.getYValue();
					
				break;
			}
			
			case RIGHT: {
				
				xDestiny = player.getXValue() + (4 * 36) + (player.getXValue() + (4 * 36)) % 36 ;
				yDestiny = player.getYValue();

				break;
			}
			
			default: 
			
				break;
		}
		
		
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
		
		setDirection(nextDirection);
		
		game.move(this);
	}

	@Override
	public void exitHouse() {
		

		if (!game.collideWithWall(Direction.UP, this))
			setNextDirection(Direction.UP);
		else setNextDirection(Direction.LEFT);
		
		setDirection(nextDirection);
		
		game.move(this);
		
		if (yValue < 7 * 36 )
			isInsideHouse = false;
		
	}
	
    public void disableFrightenedMode() {
    	speed = chaseSpeed;
    	loadSprites("/assets/MarioAssets/" + "EnemyTypeC.gif", "/assets/MarioAssets/" + "EnemyTypeC.gif", "/assets/MarioAssets/" + "EnemyTypeC.gif", "/assets/MarioAssets/"+ "EnemyTypeC.gif");
    	frightenedMode = false;
    }

}
