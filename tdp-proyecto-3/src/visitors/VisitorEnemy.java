package visitors;

import entities.*;
import entities.Enemy.State;
import logic.Direction;
import logic.Game;
import timeHandlers.PowerTimer;
import timeHandlers.RespawnTimer;

public class VisitorEnemy implements Visitor {

	
	private Enemy enemy;
	
	public VisitorEnemy(Enemy enemy) {
		
		this.enemy = enemy;
	}
	
	@Override
	public void visitWall(Wall w) {
	
		if (enemy.getState() != State.LEAVINGHOUSE)
		enemy.setDirection(Direction.STILL);
	}

	@Override
	public void visitMainCharacter(MainCharacter m) {

	}

	
	@Override
	public void visitFruitTypeA(Fruit f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFruitTypeB(Fruit f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFruitTypeC(Fruit f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPotionTypeA(Potion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPotionTypeB(Potion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPoweredDot(PoweredDot p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visitRegulardDot(RegularDot p) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void visitActivePotionTypeA(PowerTypeA power) {
		
		Game myGame = enemy.getGame();

		// Setear imagen del power
		power.setImageRoute(myGame.getDomainRoute()+ "explosion.gif");
		// Refrescar imagen
		myGame.getGUI().refreshImage(power);

		// Timer que controla que el gif se ejecute solo una vez.
		new PowerTimer(power,1000).start();
		
		// Dejar inmovil al enemigo
		enemy.setDirection(Direction.STILL);
		enemy.setNextDirection(Direction.STILL);
		
		// Si se encontraba en estado "asustado" desactivarlo
		if (enemy.getState() == entities.Enemy.State.FRIGHTENED)
			enemy.disableFrightenedMode();		
		
		// Activar modo "reaparicion"
		enemy.enableRespawnMode();
		// Esperar x cantidad de tiempo antes de volver a establecer los enemigos en estado "persecucion"
		new RespawnTimer(enemy, myGame.getLevel().getRespawnTime()).start();
		
		// Establecer posiciones iniciales
		enemy.setCharacterInInitialPosition();
	}

	@Override
	public void visitDoorway(Doorway doorway) {
	
		int size = 36;
		
		float xPosition = enemy.getXValue();
		
		// Si se salio de los limites del mapa quiere decir que visito un Doorway ( se debe teletransportar la entidad al limite doorway contrario)
		
		if (xPosition < 0) 
			enemy.setXValue(26 * size);
		
		if (xPosition > 26 * size) 
			enemy.setXValue(0);
		
	}

	@Override
	public void visitEnemy(Enemy enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitActivePotionTypeB(PowerTypeB a) {
		// TODO Auto-generated method stub
	}


}
