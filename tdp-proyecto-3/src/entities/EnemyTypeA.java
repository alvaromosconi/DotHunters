package entities;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;

public class EnemyTypeA extends Enemy {
	
	public EnemyTypeA(int xValue, int yValue, String imageRoute, Game game) {
		
		super(game);
		
		this.xValue = initialXValue
					= xValue;
		
		this.yValue = initialYValue
				    = yValue;
		
		this.currentDirection = Direction.LEFT;
		this.nextDirection = Direction.LEFT;
		
		this.imageRoute = imageRoute;

		isInsideHouse=false;
		visitor = new VisitorEnemy(this);
		
	}
	
	
	@Override
	public void accept(Visitor v) {
		
		v.visitEnemy(this);
	}


	@Override
	public void chase() {

		Entity player = game.getPlayer();
		double min = Double.MAX_VALUE;
		
		if (!game.collideWithWall(-2, 0, this) && currentDirection != Direction.RIGHT) 
    		if (game.distance(xValue - 2, player.getXValue(), yValue, player.getYValue()) < min) {
    				min = game.distance(xValue - 2, player.getXValue(), yValue, player.getYValue());
    				setNextDirection(Direction.LEFT);
    		}
    		
		if (!game.collideWithWall(2, 0, this) && currentDirection != Direction.LEFT) 
			if (game.distance(xValue + 2, player.getXValue(), yValue, player.getYValue()) < min) {
					min = game.distance(xValue + 2, player.getXValue(), yValue, player.getYValue());
					setNextDirection(Direction.RIGHT);
			}
			
		if (!game.collideWithWall(0, -2, this) && currentDirection != Direction.DOWN )
			if (game.distance(xValue, player.getXValue(), yValue - 2, player.getYValue()) < min) {
					min = game.distance(xValue, player.getXValue(), yValue - 2, player.getYValue());
					setNextDirection(Direction.UP);
			}
		
		if (!game.collideWithWall(0, 2, this) && currentDirection != Direction.UP)
			if (game.distance(xValue, player.getXValue(), yValue + 2, player.getYValue()) < min) {
					min = game.distance(xValue, player.getXValue(), yValue + 2, player.getYValue());
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
		// TODO Auto-generated method stub
		
	}
	





}
