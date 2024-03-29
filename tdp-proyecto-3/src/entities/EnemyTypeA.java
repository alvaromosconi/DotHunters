package entities;
import logic.Direction;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;

public class EnemyTypeA extends Enemy {
	
	public EnemyTypeA(int xValue, int yValue, String imageRoute, int speed, Game game) {
		
		super(xValue, yValue, imageRoute, speed, game);
		
		this.currentDirection = Direction.LEFT;
		this.nextDirection = Direction.LEFT;

		this.setState(State.CHASING);

		visitor = new VisitorEnemy(this);	
	}
		
	@Override
	public void accept(Visitor v) {
		
		v.visitEnemy(this);
	}


	@Override
	public void chase() {

		MainCharacter player = game.getPlayer();
		
		int playerXValue = player.getXValue();
		int playerYValue = player.getYValue();
		
		goToDestiny(playerXValue, playerYValue);
	}
	

	@Override
	public void exitHouse() {
	
		state = State.CHASING;	
		setDirection(Direction.LEFT);
		setNextDirection(Direction.LEFT);
	}
	
	@Override
	public void disableRespawnMode() {
		
		loadSprites(domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute+ "EnemyTypeA.gif");

	}
	
	@Override
    public void disableFrightenedMode() {
    	
    	speed = chaseSpeed;
    	loadSprites(domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute+ "EnemyTypeA.gif");

    }


}
