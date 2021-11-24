package entities;

import java.util.ArrayList;

import javax.swing.Timer;

import java.util.List;
import java.util.Random;

import logic.Game;
import logic.Direction;

public abstract class Enemy extends Character {

	protected State state;
	protected int chaseSpeed;
	protected final static int frightenedSpeed = 1;
	protected String domainRoute;

	public Enemy(int xValue, int yValue, String imageRoute, int speed, Game game) {

		super(xValue, yValue, imageRoute, speed, game);
		chaseSpeed = speed;
		state = State.LEAVINGHOUSE;  				 // Estado inicial 
		domainRoute = game.getDomainRoute();
	}

	/*
	 * Estados posibles del enemigo
	 * FRIGHTENED: En este modo el enemigo es vulnerable y se mueve pseudo-aleatoriamente.
	 * CHASING: En este modo el enemigo puede atacar a MainCharacter ( ejecuta un comportamiento establecido segun el tipo de enemigo).
	 * RESPAWNING: En este modo el enemigo es invulnerable y no puede ser afectado por MainCharacter ni tampoco afectarlo.
	 * LEAVINGHOUSE: En este modo el enemigo intenta salir del lugar donde aparece (dentro o fuera de la casa, segun el enemigo)
	 */
	public enum State {
		
		FRIGHTENED,
		CHASING,
		RESPAWNING,
		LEAVINGHOUSE;
	}

	/*
	 * Ejecuta el modo actual y luego efectua el movimiento establecido por el metodo.
	 */
	public void executeCurrentBehaviour() {
		
		switch (state) {
			
			case FRIGHTENED: {
				frightened();
				break;
			}
			
			case LEAVINGHOUSE: {
				exitHouse();
				break;
			}
			
			case CHASING: {
				chase();
				break;
			}
			
			case RESPAWNING: {
				break;
			}
			
			default:
				break;
			}

		setDirection(nextDirection);
		game.move(this);
	}
	
	/*
	 * En base al la posicion (X,Y) del destino el algoritmo obtiene la direccion por la cual seria mas eficiente moverse teniendo en cuenta que:
	 		* Si colisiona con la direccion elegida no es valida y por ende es descartada.
	 		* No se puede elegir la direccion opuesta a la actual (a menos que sea la unica salida)
	 
	 * @param xDestiny valor actual en X del destino
	 * @param xDestiny valor actual en Y del destino
	 */
	
	protected void goToDestiny(int xDestiny, int yDestiny) {
		
		float minDistanceToDestiny, distance;
		
		minDistanceToDestiny = Float.MAX_VALUE;	
		distance = 0;
		
		if (!game.collideWithWall(Direction.LEFT, this) && currentDirection != Direction.RIGHT) {	
			
			distance = game.distance(xValue + (Direction.LEFT.getXVelocity() * speed), xDestiny, yValue, yDestiny);
			
			if (distance < minDistanceToDestiny) {
			
				minDistanceToDestiny = distance;	
				setNextDirection(Direction.LEFT);
			}
		}

		if (!game.collideWithWall(Direction.RIGHT, this) && currentDirection != Direction.LEFT) {
			
			distance = game.distance(xValue + (Direction.RIGHT.getXVelocity() * speed), xDestiny, yValue, yDestiny);
			
			if (distance < minDistanceToDestiny) {
				
				minDistanceToDestiny = distance;	
				setNextDirection(Direction.RIGHT);		
			}
		}
					
		if (!game.collideWithWall(Direction.UP, this) && currentDirection != Direction.DOWN ) {
			
			distance = game.distance(xValue, xDestiny, yValue + (Direction.UP.getYVelocity() * speed), yDestiny);
			
			if (distance < minDistanceToDestiny) {
				
				minDistanceToDestiny = distance;	
				setNextDirection(Direction.UP);		
			}		
		}
		
		if (!game.collideWithWall(Direction.DOWN, this) && currentDirection != Direction.UP) {
			
			distance = game.distance(xValue, xDestiny, yValue + (Direction.DOWN.getYVelocity() * speed), yDestiny);
			
			if (distance < minDistanceToDestiny) {
				
				minDistanceToDestiny = distance;	
				setNextDirection(Direction.DOWN);		
			}	
		}
		
	}

	/*
	 * Ejecuta el comportamiento "asustado" comun a todos los enemigos.
	 */
	protected void frightened() {

		Random random = new Random();
		
		List<Direction> possibleDirections = getPossibleDirections(getOppositeDirection());  // (la direccion opuesta es descartada)

		if (xValue % 36 == 0 && yValue % 36 == 0) {
			
			if (possibleDirections.isEmpty())			   // Si no hay direcciones posibles (quedo atrapado) darse la vuelta 
				setNextDirection(getOppositeDirection());
			else if (possibleDirections.size() > 1)		   // Si hay mas de 2 direcciones posibles obtener una al azar
				setNextDirection(possibleDirections.get(random.nextInt(possibleDirections.size())));
			else										  // Caso contrario, hay una sola direccion posible, utilizar tal direccion.
				setNextDirection(possibleDirections.get(0));
		}

	}
	
	/*
	 * Se genera una lista con todas las direcciones posibles (excepto la opuesta a la actual)
	 * @return Lista de direcciones posibles
	 */
	protected List<Direction> getPossibleDirections(Direction oppositeDirection) {

		List<Direction> possibleDirections = new ArrayList<Direction>();

		if (!game.collideWithWall(Direction.LEFT, this) && oppositeDirection != Direction.LEFT)
			possibleDirections.add(Direction.LEFT);
		if (!game.collideWithWall(Direction.RIGHT, this) && oppositeDirection != Direction.RIGHT)
			possibleDirections.add(Direction.RIGHT);
		if (!game.collideWithWall(Direction.UP, this) && oppositeDirection != Direction.UP)
			possibleDirections.add(Direction.UP);
		if (!game.collideWithWall(Direction.DOWN, this) && oppositeDirection != Direction.DOWN)
			possibleDirections.add(Direction.DOWN);

		return possibleDirections;
	}

	/*
	 * Ejecuta el comportamiento "persecucion" (Es redefinido en cada enemigo)
	 */
	protected abstract void chase();

	/*
	 * Ejecuta el comportamiento "salir de la casa" (Es redefinido en cada enemigo)
	 */
	protected abstract void exitHouse();

	/*
	 * Desactiva el modo "reaparicion" (Es redefinido en cada enemigo)
	 */
	public abstract void disableRespawnMode();

	/*
	 * Desactiva el modo "asustado" (Es redefinido en cada enemigo)
	 */
	public abstract void disableFrightenedMode();
	
	/*
	 * Activa el modo "reaparicion", cambia el estado y carga los sprites correspondientes.
	 */
	public void enableRespawnMode() {
		
		state = State.RESPAWNING;
		loadSprites(domainRoute + "respawnMode.gif", domainRoute + "respawnMode.gif",
				domainRoute + "respawnMode.gif", domainRoute + "respawnMode.gif");
	}

	/*
	 * Activa el modo "asustado", cambia el estado, cambia la velocidad y carga los sprites correspondientes.
	 */
	public void enableFrightenedMode() {
		
		state = State.FRIGHTENED;
		speed = frightenedSpeed;
		loadSprites(domainRoute + "frightenedFront.gif", domainRoute + "frightenedBack.gif",
				domainRoute + "frightenedFront.gif", domainRoute + "frightenedFront.gif");
	}
	
	/*
	 * Se establece el estado deseado
	 */
	public void setState(State state) {
		
		this.state = state;
	}
	
	/*
	 * Retorna el estado actual del enemigo
	 * @return estado actual del enemigo
	 */
	public State getState() {
		
		return state;
	}
}
