package entities;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;

public class EnemyTypeB extends Enemy {

	public EnemyTypeB(int xValue, int yValue, String imageRoute, Game game) {
		
		super(game);
		
		this.xValue = initialXValue
				= xValue;
	
		this.yValue = initialYValue
			    = yValue;
		
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
		
		Entity enemyA = game.getEnemyTypeA();
		
		
		switch(enemyA.nextDirection) {
		
			case UP: {
				
				if (!game.collideWithWall(0, 2, this) && currentDirection != Direction.UP)
					setNextDirection(Direction.DOWN);
			
				break;
			}
				
			case DOWN: {
				
				if (!game.collideWithWall(0, -2, this) && currentDirection != Direction.DOWN )
					setNextDirection(Direction.UP);
			
				break;
			}
			
			case LEFT: {
				
				if (!game.collideWithWall(2, 0, this) && currentDirection != Direction.LEFT) 
					setNextDirection(Direction.RIGHT);
			
				break;
			}
			
			case RIGHT: {
				
				if (!game.collideWithWall(-2, 0, this) && currentDirection != Direction.RIGHT) 
					setNextDirection(Direction.LEFT);

				break;
			}
			
			default: 
		
				break;
				
				
				
		}
		
		if (game.collideWithWall(nextXVelocity, nextYVelocity, this))
			if (!game.collideWithWall(0, -2, this))
				setNextDirection(Direction.UP);
			else
				if (!game.collideWithWall(-2, 0, this))
					setNextDirection(Direction.LEFT);
			else
				if (!game.collideWithWall(0, 2, this))
					setNextDirection(Direction.DOWN);
					
		
		
		setDirection(nextDirection);
		
		game.move(this);
	}
		

	

	@Override
	public void scatter() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void exitHouse() {
	
		if (!game.collideWithWall(0, -2, this))
			setNextDirection(Direction.UP);
		else setNextDirection(Direction.RIGHT);
		
		setDirection(nextDirection);
		
		game.move(this);
		
		if (yValue < 7 * 36 )
			isInsideHouse = false;
 		
	}

}
