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
		
		int playerXValue = player.getXValue(),
			playerYValue = player.getYValue();
		
		// En base a la direccion actual del MainCharacter, EnemyTypeC elige su proximo destino (X,Y)
		// 36 = Size de una celda en el juego, por eso al obtener el xDestiny o yDestiny se le suma la diferencia para que sea divisible por 36. 
		switch(player.currentDirection) {
		
			case UP: {
				
				xDestiny = playerXValue - (2 * 36) + (playerXValue - (2 * 36)) % 36 ;
				yDestiny = playerYValue - (4 * 36) + (playerYValue - 4 * 36) % 36 ;		
				
				break;
			}
				
			case DOWN:  {
				
				xDestiny = playerXValue;
				yDestiny = playerYValue + (4 * 36) + (playerYValue + (4 * 36)) % 36 ;
			
				break;
			}
			case LEFT: {
				
				xDestiny = playerXValue - (4 * 36) + (playerXValue - (4 * 36)) % 36 ;
				yDestiny = playerYValue;
					
				break;
			}
			
			case RIGHT: {
				
				xDestiny = playerXValue + (4 * 36) + (playerXValue + (4 * 36)) % 36 ;
				yDestiny = playerYValue;

				break;
			}
			
			default: 
			
				break;
		}
		
		
		goToDestiny(xDestiny, yDestiny);
	}

	@Override
	public void exitHouse() {
		
		if (!game.collideWithWall(Direction.UP, this))
			setNextDirection(Direction.UP);
		else setNextDirection(Direction.LEFT);
		
		if (yValue < 7 * 36 )
			state = State.CHASING;	
		
	}
	
	@Override
	public void disableRespawnMode() {
		state = State.CHASING;
		loadSprites("/assets/MarioAssets/" + "EnemyTypeC.gif", "/assets/MarioAssets/" + "EnemyTypeC.gif", "/assets/MarioAssets/" + "EnemyTypeC.gif", "/assets/MarioAssets/"+ "EnemyTypeC.gif");

	}
	
	@Override
    public void disableFrightenedMode() {
    	speed = chaseSpeed;
    	loadSprites("/assets/MarioAssets/" + "EnemyTypeC.gif", "/assets/MarioAssets/" + "EnemyTypeC.gif", "/assets/MarioAssets/" + "EnemyTypeC.gif", "/assets/MarioAssets/"+ "EnemyTypeC.gif");
    
    }

}
