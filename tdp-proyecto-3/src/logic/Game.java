package logic;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import entities.*;
import entities.Character;
import entities.Enemy.State;
import gui.GUI;
import gui.GameOverGUI;
import timeHandlers.Time;

public class Game {

	private Level currentLevel;
	private GUI myGUI;
	private Zone[][] myZones;
	private int score;
	private Thread playerThread;
	private Thread enemiesThread;
	private String domainRoute = "";

	private GameOverGUI myGameOverGUI;

	private boolean frigthenedTimer;
	private boolean gameOver = false;
	private int level = 1;

	Director director;
	LevelBuilder levelBuilder;
	
	private MainCharacter player;

	private List<Wall> walls;
	private List<Entity> components;
	private List<Enemy> enemies;
	private List<Entity> allEntities;
	private List<Entity> doorways;

	private Clip clip = null;

	public Game(String domainRoute) throws UnsupportedAudioFileException, Exception {
		this.domainRoute = domainRoute;
		director = new Director(this);
		levelBuilder = new LevelBuilder();
		initializeLevel();	
		
		automaticMovement();			// Arrancar hilo del jugador.
		enemiesAi();					// Arrancar hilo de los enemigos.
		AudioInputStream audioInputStream;
		java.net.URL url = Game.class.getResource(domainRoute + "back.wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}

	/*
	 * Crea el hilo que controla el movimiento automatico del jugador (Delega en
	 * MainCharacter)
	 */
	private void automaticMovement() {

		playerThread = new Thread() {

			public void run() {
				
				while (!gameOver) {
	
					try {

						Thread.sleep(13);
						move(player);
					}

					catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}
		};

		playerThread.start();

	}

	/*
	 * Crea el hilo que controla a los fantasmas (Delega en Enemy)
	 */

	private void enemiesAi() {

		enemiesThread = new Thread() {

			public void run() {

				while (!gameOver) {

					try {

						Thread.sleep(15);

						for (Enemy enemy : enemies)
							enemy.executeCurrentBehaviour();

					}

					catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}
		};

		enemiesThread.start();
	}

	/*
	 * Inicializa las zonas del juego con su tamaño y ubicacion correspondiente,
	 * usando el tamaño del mapa como referencia.
	 */
	private void initializeZones() {

		myZones = new Zone[4][4];

		int gapX = 243;
		int gapY = 171;

		int widthMultiplier = 0;
		int heightMultiplier = 0;

		for (int i = 0; i < myZones.length; i++) {

			for (int j = 0; j < myZones[0].length; j++) {

				myZones[i][j] = new Zone(new Point(widthMultiplier, heightMultiplier),
						new Point(widthMultiplier + gapX, heightMultiplier),
						new Point(widthMultiplier, heightMultiplier + gapY),
						new Point(widthMultiplier + gapX, heightMultiplier + gapY));

				widthMultiplier += gapX;

			}

			widthMultiplier = 0;
			heightMultiplier += gapY;

		}

	}

	/*
	 * Se añaden las paredes a la o las zonas que correspondan.
	 */

	private void chargeZonesWithWalls() {

		Rectangle wallRectangle;
		Rectangle zoneRectangle;

		for (Entity wall : walls) {

			wallRectangle = wall.getRectangle();

			for (int i = 0; i < myZones.length; i++)

				for (int j = 0; j < myZones[0].length; j++) {

					zoneRectangle = myZones[i][j].getRectangle();

					if (wallRectangle.intersects(zoneRectangle)) {

						myZones[i][j].addEntity(wall);
						myZones[i][j].addWall(wall);
						myGUI.addWall(wall);
					}
				}
		}
	}

	/*
	 * Se añaden las entidades a la o las zonas que correspondan.
	 */
	private void chargeZonesWithEntities() {

		Rectangle entityRectangle;
		Rectangle zoneRectangle;

		for (Entity entity : allEntities) {

			entityRectangle = entity.getRectangle();

			for (int i = 0; i < myZones.length; i++)

				for (int j = 0; j < myZones[0].length; j++) {

					zoneRectangle = myZones[i][j].getRectangle();
					// System.out.println(entityRectangle);
					if (entityRectangle.intersects(zoneRectangle) && getZones(entity).isEmpty()) {

						myGUI.addEntity(entity);
						myZones[i][j].addEntity(entity);
					}
				}
		}

	}

	/*
	 * Se añaden los portales a la o las zonas que correspondan.
	 */
	private void chargeZonesWithDoorWays() {

		Rectangle doorWayRectangle;
		Rectangle zoneRectangle;

		for (Entity doorWay : doorways) {

			doorWayRectangle = doorWay.getRectangle();

			for (int i = 0; i < myZones.length; i++)

				for (int j = 0; j < myZones[0].length; j++) {

					zoneRectangle = myZones[i][j].getRectangle();

					if (doorWayRectangle.intersects(zoneRectangle)) {

						myZones[i][j].addEntity(doorWay);
						myZones[i][j].addDoorWay(doorWay);
						myGUI.addDoorWay(doorWay);
					}
				}
		}
	}

	/*
	 * Inicializador de niveles (Se obtienen del director)
	 * 
	 */
	private void initializeLevel() {

		switch (level) {
			
			case 1: {
				
				this.score = 0;
				director.constructLevelOne(levelBuilder);
				currentLevel = levelBuilder.getResult();
				break;
			}
			
			case 2: {
				
				director.constructLevelTwo(levelBuilder);
				currentLevel = levelBuilder.getResult();
				break;
			}
			
			case 3: {
				
				director.constructLevelThree(levelBuilder);
				currentLevel = levelBuilder.getResult();
				break;
			}
	
		}
		
		initializeZones();				// Inicializar zonas.
		setupGUIandEntities();	
		
	}
	
	/*
	 * Se inicializan las listas (obtenidas del nivel) y se cargan todas las entidades en el juego.
	 */
	private void setupGUIandEntities() {
		
		player = currentLevel.getPlayer();
		
		myGUI = new GUI(this, currentLevel.getBackgroundUrl());
		
		this.walls = currentLevel.getWalls();
		this.components = currentLevel.getComponents();
		this.enemies = currentLevel.enemies();
		this.doorways = currentLevel.getDoorWays();
		
		allEntities = new ArrayList<Entity>();
		allEntities.add(player);
		allEntities.addAll(components);
		allEntities.addAll(enemies);
		
		chargeZonesWithWalls();
		chargeZonesWithEntities();
		chargeZonesWithDoorWays();
		
		// Dibujar todo
		myGUI.setupBackground();
		
	}
	
	/*
	 * Chequea si el jugador consumio todos los Dots y Fruits (En este caso el tamaño 
	 * de la lista de componentes deberia ser menor o igual a 2 ya que como maximo 
	 * estarian las 2 potion)
	 */	
	public void checkIfWin() {

		if (components.size() <= 5) {
			
			level++;
			myGUI.dispose();
	
			initializeLevel();
		}
	}
	
	

	/*
	 * Recibe el input que detecto la GUI y responde acorde al movimiento requerido,
	 * guardando la direccion actual y la siguiente para evitar colisiones con
	 * paredes en los casos que no sean necesarios (como por ejemplo cuando se esta
	 * viajando por un pasillo horizontal y se presiona la tecla hacia arriba)
	 * 
	 * @param keyPressed tecla presionada
	 */
	public synchronized void movePlayer(KeyEvent keyPressed) {

		if (!gameOver)

			switch (keyPressed.getKeyCode()) {

				case KeyEvent.VK_LEFT: {
	
					if (!collideWithWall(Direction.LEFT, player))
						player.setDirection(Direction.LEFT);
					else
						player.setNextDirection(Direction.LEFT);
	
					break;
				}
	
				case KeyEvent.VK_RIGHT: {
	
					if (!collideWithWall(Direction.RIGHT, player))
						player.setDirection(Direction.RIGHT);
	
					else
						player.setNextDirection(Direction.RIGHT);
	
					break;
				}
	
				case KeyEvent.VK_UP: {
	
					if (!collideWithWall(Direction.UP, player))
						player.setDirection(Direction.UP);
					else
						player.setNextDirection(Direction.UP);
	
					break;
				}
	
				case KeyEvent.VK_DOWN: {
	
					if (!collideWithWall(Direction.DOWN, player))
						player.setDirection(Direction.DOWN);
	
					else
						player.setNextDirection(Direction.DOWN);
	
					break;
				}

			}
	}

	/*
	 * Detecta colisiones en la direccion recibida.
	 * 
	 * @param xVelocity velocidad horizontal que se le quiere aï¿½ï¿½adir a el valor de
	 * x actual de la entidad.
	 * 
	 * @param yVelocity velocidad vertical que se le quiere aï¿½adir a el valor y
	 * actual de la entidad.
	 * 
	 * @param entityA entidad que requiere hacer el movimiento.
	 * 
	 * @return verdadero si colisiono con una pared en la direccion requerida, falso
	 * en caso contrario.
	 */

	public synchronized boolean collideWithWall(Direction desiredDirection, Character entityA) {

		Rectangle entityARectangle, entityBRectangle;
		boolean intersect = false;

		entityARectangle = new Rectangle(entityA.getXValue() + (desiredDirection.getXVelocity()) * entityA.getSpeed(),
				entityA.getYValue() + (desiredDirection.getYVelocity() * entityA.getSpeed()), entityA.getWidth(),
				entityA.getHeight());

		List<Zone> listOfZones = getZones(entityA);

		Iterator<Entity> zoneWallsIterator;
		Entity entityB;

		for (Zone zone : listOfZones) {

			zoneWallsIterator = zone.getWalls().iterator();

			while (zoneWallsIterator.hasNext() && !intersect) {

				entityB = zoneWallsIterator.next();
				entityBRectangle = entityB.getRectangle();
				intersect = entityARectangle.intersects(entityBRectangle);
			}

		}

		return intersect;
	}

	/*
	 * Realiza el movimiento y la colision en funcion del movimiento que se
	 * establecio
	 * 
	 * @param entityA entidad que efectua el movimiento
	 */
	public synchronized void move(Character entityA) {

		Rectangle entityARectangle, entityBRectangle;
		boolean intersect = false;

		entityARectangle = entityA.getOffsetBounds();

		List<Zone> listOfZones = getZones(entityA);

		Iterator<Entity> zoneEntitiesIterator;
		Entity entityB;

		for (Zone zone : listOfZones) {

			zoneEntitiesIterator = zone.getEntities().iterator();
			innerloop: while (zoneEntitiesIterator.hasNext()) {

				entityB = zoneEntitiesIterator.next();
				entityBRectangle = entityB.getRectangle();
				intersect = entityARectangle.intersects(entityBRectangle);

				if (intersect) {

					entityB.accept(entityA.getVisitor());

					if (!zone.getEntities().contains(entityB))
						break innerloop;
					else
						myGUI.refreshEntity(entityB);

				}

			}
		}

		// Actualizaciones graficas y/o actualizacion de zonas y posiciones

		myGUI.refreshImage(entityA);
		updateZones(entityA);
		myGUI.refreshEntity(entityA);
		entityA.move();
	}

	/*
	 * Verifica que zonas contienen a la entidad actualmente
	 * 
	 * @param entidad de la cual se quieren obtener las zonas
	 * 
	 * @return zonas a la que pertenece la entidad
	 */
	List<Zone> getZones(Entity entity) {

		List<Zone> listOfZones = new ArrayList<Zone>();

		for (int i = 0; i < myZones.length; i++)

			for (int j = 0; j < myZones[0].length; j++)

				if (myZones[i][j].getEntities().contains(entity))
					listOfZones.add(myZones[i][j]);

		return listOfZones;
	}

	/*
	 * Actualiza las zonas en funcion de un movimiento realizado
	 * 
	 * @param entity entidad que necesita ser agregada en otra zona u eliminada de
	 * la zona actual
	 */
	private void updateZones(Entity entity) {

		Rectangle entityRectangle;
		Rectangle zoneRectangle;

		entityRectangle = entity.getRectangle();

		for (int i = 0; i < myZones.length; i++)

			for (int j = 0; j < myZones[0].length; j++) {

				zoneRectangle = myZones[i][j].getRectangle();

				if (entityRectangle.intersects(zoneRectangle))
					myZones[i][j].addEntity(entity);

				else if (myZones[i][j].getEntities().contains(entity) && !entityRectangle.intersects(zoneRectangle))
					myZones[i][j].getEntities().remove(entity);

			}
	}

	/*
	 *  Se crea la entidad tipo PowerTypeA y se coloca debajo del personaje principal.
	 */
	public void potionTypeAEvent() {

		if (player.havePotionTypeA()) {

			PowerTypeA power = new PowerTypeA(player.getXValue(), player.getYValue(), "/assets/MarioAssets/bomb.png", this);
			allEntities.add(power);

			chargeZonesWithEntities();
			player.havePotionTypeA(false);
		}
	}

	/*
	 * Activa el modo vulnerable de todos los enemigos
	 */

	public void enableFrightenedMode() {

		for (Enemy enemy : enemies) {
			
			if (enemy.getState() != State.RESPAWNING ) {
				enemy.enableFrightenedMode();
				frigthenedTimer = true;
			}
			
		}
		new Time(this, 10000).start();;
	}

	/*
	 * Cambia el estado del juego a perdido.
	 */
	public void gameOver() {

		gameOver = true;

		for (Enemy e : enemies)
			e.setDirection(Direction.STILL);

		player.setDirection(Direction.STILL);

		myGUI.dispose();
		clip.stop();
		clip.close();
		myGameOverGUI = new GameOverGUI(this,domainRoute);
		playerThread.stop();
		enemiesThread.stop();
	}

	
	/*
	 * Cambia el estado de los enemigos de asustados a persecucion.
	 */
	public void disableFrightenedMode() {

		for (Enemy enemy : enemies) {
			
			if (enemy.getState() == State.FRIGHTENED && !frigthenedTimer) {
				
				enemy.disableFrightenedMode();
				enemy.setState(State.CHASING);
			}
		}

	}

	/*
	 * Remueve las entidades comestibles / agarrables de las listas y zonas y delega en la clase GUI 
	 * para eliminarlas graficamente.
	 * @param entityToDestroy entidad a remover
	 */
	
	public void destroyEntity(Entity entityToDestroy) {

		allEntities.remove(entityToDestroy);
		components.remove(entityToDestroy);

		List<Zone> zones = new ArrayList<Zone>();

		for (int i = 0; i < myZones.length; i++)

			for (int j = 0; j < myZones[0].length; j++) 

				if (myZones[i][j].getEntities().contains(entityToDestroy)) 
					zones.add(myZones[i][j]);
			


		for (int i = 0; i < zones.size(); i++)
			zones.get(i).removeEntity(entityToDestroy);

		myGUI.destroyEntity(entityToDestroy);

	}

	/*
	 * Calcula la distancia en linea recta entre los puntos (x1,y1) e (x2,y2)
	 */

	public float distance(int x1, int x2, int y1, int y2) {

		return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	/*
	 * Retorna el puntaje actual
	 */
	public int getScore() {
		return score;
	}

	/*
	 * Establece el puntaje nuevo
	 * @param puntaje nuevo
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/* Metodo que utiliza EnemyTypeB para copiar los movimientos del EnemyTypeA y girarlos 180ï¿½
	 * @return retorna el EnemyTypeA
	 */
	public Enemy getEnemyTypeA() {

		return enemies.get(0);
	}


	/* Metodo que utiliza EnemyTypeA para obtener las posiciones del jugador y encontrar 
	 * la ruta mas adecuada hacia el.
	 * @return retorna el MainCharacter
	 */
	public MainCharacter getPlayer() {

		return player;
	}
    
	/*
	 * @return retorna GUI.
	 */
	public GUI getGUI() {

		return myGUI;
	}
	/*
	 * Modifica el boolean para determinar si un enemigo se encontraba en modo "asustado" y poder reiniciar la duracion de dicho estado
	 * @param f corresponde a si el estado del enemigo es "asustado"
	 */
	public void setFrightenedTimer(boolean f) {
		this.frigthenedTimer = f;
	}
}
