package visitors;

import entities.Doorway;
import entities.Enemy;
import entities.Fruit;
import entities.MainCharacter;
import entities.Potion;
import entities.PowerTypeA;
import entities.PowerTypeB;
import entities.PoweredDot;
import entities.RegularDot;
import entities.Wall;
import entities.Enemy.State;
import logic.Direction;
import logic.Game;
import timeHandlers.RespawnTimer;

public class VisitorMainCharacter implements Visitor {

	private MainCharacter player;
	private int size = 36;
	
	public VisitorMainCharacter(MainCharacter player) {
		
		this.player = player;
	}
	
	@Override
	public void visitWall(Wall w) {
	
		player.setDirection(Direction.STILL);
	}

	@Override
	public void visitMainCharacter(MainCharacter m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFruitTypeA(Fruit fruit) {

		Game myGame = fruit.getGame();
		myGame.destroyEntity(fruit);
		modifyScore(100);
		myGame.checkIfWin();
		
	}

	@Override
	public void visitFruitTypeB(Fruit fruit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFruitTypeC(Fruit fruit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPotionTypeA(Potion p) {
		
		Game myGame = p.getGame();
		myGame.getSoundHandler().playSpecificSound(myGame.getDomainRoute() + "powerup.wav");
		player.havePotionTypeA(true);
		myGame.destroyEntity(p);
		myGame.getGUI().dropBomb();
	}

	@Override
	public void visitPotionTypeB(Potion potion) {
		
		Game myGame = potion.getGame();
		// Si la posicion entre las entidades es igual
		if (potion.getXValue() == player.getXValue() && potion.getYValue() == player.getYValue()) {
			
			// Destruir entidad
			myGame.destroyEntity(potion);
			
			// Detener al jugador para no mezclar distintas velocidades y provocar colisiones no deseadas
			player.setDirection(Direction.STILL);
			player.setNextDirection(Direction.STILL);
			
			// Aumento de velocidad
			player.setSpeed(4);
			
			// Timer que elimina el poder luego de x tiempo
			new Thread() {
				public void run() {
					
					try {
						Thread.sleep(myGame.getLevel().getPowerTypeBTime());
						player.setSpeed(2);
					}
					
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}.start();
		}	
	}

	@Override
	public void visitPoweredDot(PoweredDot dot) {
		
		Game myGame = dot.getGame();
		
		myGame.enableFrightenedMode();		// Colocar a los fantasmas en estado "asustado"
		myGame.destroyEntity(dot);			// Destruir entidad
		modifyScore(50);					// Aumentar puntaje
		myGame.checkIfWin();				// Chequear si gano	
	}

	@Override
	public void visitRegulardDot(RegularDot dot) {
		
		Game myGame = dot.getGame();
	
		myGame.destroyEntity(dot);			// Destruir entidad
		modifyScore(10);					// Aumentar puntaje	
		myGame.checkIfWin();				// Chequear si gano
	}

	@Override
	public void visitActivePotionTypeA(PowerTypeA a) {		
	
	}

	@Override
	public void visitDoorway(Doorway doorway) {
		
		
		float xPosition = player.getXValue();
		
		// Si se salio de los limites del mapa quiere decir que visito un Doorway ( se debe teletransportar la entidad al limite doorway contrario)
		
		if (xPosition < 0) 
			player.setXValue(26 * size);
		
		if (xPosition > 26 * size) 
			player.setXValue(0);
		
	}
	
	private void modifyScore(int plus) {
		
		int newScore = player.getGame().getScore() + plus;			// Establecer nuevo puntaje
		player.getGame().setScore(newScore);
	}


	@Override
    public void visitEnemy(Enemy enemy) {

		Game myGame = enemy.getGame();
        // Calculo para que la colision no sea inmediata al intersectarse los rectangulos
        if (Math.abs(enemy.getXValue() - player.getXValue()) < 18 && Math.abs(enemy.getYValue() - player.getYValue()) < 18)

            // Si el estado actual del enemigo es "asustado"
            if (enemy.getState() == State.FRIGHTENED) {

            	// Trasladar enemigo a su posicion inicial
                enemy.setXValue(enemy.getInitialXValue());
                enemy.setYValue(enemy.getInitialYValue());
                
                enemy.disableFrightenedMode();			// Desactivar estado "asustado"
                enemy.enableRespawnMode();				// Activar estado "reaparicion"
               
                // Dejar inmovil al enemigo
                enemy.setDirection(Direction.STILL);
                enemy.setNextDirection(Direction.STILL);

                // Esperar x cantidad de tiempo antes de volver a establecer los enemigos en estado "persecucion"
                new RespawnTimer(enemy, myGame.getLevel().getRespawnTime()).start();
                
                modifyScore(200);
            }

            // Caso contrario, si el enemigo no se encuentra en estado de "reaparicion" acabar el juego.
            else if (enemy.getState() != State.RESPAWNING){

            	myGame.resetPositions();
            	
            	player.getGame().loseLife();					// Controlador de vidas
            	player.getGame().getGUI().updateLives();		// Actualizar graficamente vidas disponibles
            	     							  	           	
            }
    }

	@Override
	public void visitActivePotionTypeB(PowerTypeB a) {
		// TODO Auto-generated method stub
		
	}

}
