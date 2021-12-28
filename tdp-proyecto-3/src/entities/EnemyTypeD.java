package entities;
import logic.Direction;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorEnemy;

public class EnemyTypeD extends Enemy {

	public EnemyTypeD(int xValue, int yValue, String imageRoute, int speed, Game game) {
	
		super(xValue, yValue, imageRoute, speed, game);
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
		
		int xLeftBottomCorner = 36;				// Valor de X de la esquina inferior izquierda.
		int	yLeftBottomCorner = 17 * 36;		// Valor en Y de la esquina inferior izquierda.
		
		// Si se encuentra a menos de 8 casillas/celdas --> EnemyTypeD viaja hacia la esquina inferior izquierda.
		if (game.distance(xValue, player.getXValue(), yValue, player.getYValue()) <= 8 * 36)
			goToDestiny(xLeftBottomCorner, yLeftBottomCorner);
		// Caso contrario persigue a MainCharacter.
		else
			goToDestiny(playerXValue, playerYValue);
				
	}


	@Override
	public void exitHouse() {
		
		if (!game.collideWithWall(Direction.UP, this))
			setNextDirection(Direction.UP);
		else setNextDirection(Direction.LEFT);
		
		if (yValue <= initialYValue - 36) {
			state = State.CHASING;	
		}
		
	}

	public void disableRespawnMode() {
		
		state = State.CHASING;
		loadSprites(domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute+ "EnemyTypeD.gif");

	}
	
	@Override
	public void disableFrightenedMode() {
	
    	speed = chaseSpeed;
    	loadSprites(domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute+ "EnemyTypeD.gif");
	}


}
