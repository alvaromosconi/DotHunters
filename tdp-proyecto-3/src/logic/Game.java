package logic;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import entities.*;
import entities.Character;
import gui.GUI;
import gui.GameOverGUI;
import logic.Direction;

public class Game {
	
	private Level currentLevel ;
	private GUI myGUI;
	private Zone[][] myZones;
	private int score;
	
	private Thread playerThread;
	private Thread enemiesThread;
	
	private GameOverGUI myGameOverGUI;

	private boolean gameOver = false;

	private MainCharacter player;

	private List<Entity> walls;
	private List<Entity> components;
	private List<Enemy> enemies;
	private List<Entity> allEntities;
	private List<Entity> doorways;

	public Game () {

		initializeLevel();
		initializeZones();
		
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
		
		myGUI.setupBackground(); 
		
		automaticMovement();
	//	enemiesAi();
	}

	
	/*
	 * Crea el hilo que controla el movimiento automatico del jugador
	 */
	private void automaticMovement() {
		
		 playerThread = new Thread() {
			    
			  public void run() {
			    	
			    while (!gameOver) {
			    		

	    			try {
	    				
	    	
	    				Thread.sleep(13);
	    				
	    				move(player);
	    							
	    				if (player.getNextDirection() != Direction.STILL ) 
	    				
	    					if (!collideWithWall(player.getNextDirection(), player)) {
	    						player.setDirection(player.getNextDirection());
	    						player.setNextDirection(Direction.STILL);
	    					}
			  			 
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
	 * Crea el hilo que controla a los fantasmas
	 */
	
	private void enemiesAi() {

		  enemiesThread = new Thread(){
			    
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
	 * Calcula la distancia en linea recta entre el punto (x1,y1) e (x2,y2)
	 */
	
	public int distance(int x1, int x2, int y1, int y2) {
		
		return (int) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	/*
	 * Inicializa las zonas del juego con su tamaño y ubicacion correspondiente, usando el tamaño del mapa como referencia.
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
		
		for (Entity wall: walls) {
			
			wallRectangle = wall.getRectangle();
	
			for (int i = 0; i < myZones.length ; i++)
				
				for (int j = 0; j < myZones[0].length ; j++) {
					
					zoneRectangle = myZones[i][j].getRectangle();
				
					if (wallRectangle.intersects(zoneRectangle) ) {
							
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
			
			for (int i = 0; i < myZones.length ; i++ )
					
				for (int j = 0; j < myZones[0].length ; j++) {
							
					zoneRectangle = myZones[i][j].getRectangle();
							
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
		
		for (Entity doorWay: doorways) {
			
			doorWayRectangle = doorWay.getRectangle();
	
			for (int i = 0; i < myZones.length ; i++)
				
				for (int j = 0; j < myZones[0].length ; j++) {
					
					zoneRectangle = myZones[i][j].getRectangle();
				
					if (doorWayRectangle.intersects(zoneRectangle) ) {
							
						myZones[i][j].addEntity(doorWay);
						myZones[i][j].addDoorWay(doorWay);
						myGUI.addDoorWay(doorWay);
					}
				}	
		}
	}

	/*
	 * Se inicializa el nivel, obteniendolo del director.
	 */
	private void initializeLevel() {
		
		this.score = 0;
		
		Director director = new Director(this);
		
		LevelBuilder levelBuilder = new LevelBuilder();
		
		director.constructLevelOne(levelBuilder);
//		director.constructLevelTwo(levelBuilder);
//		director.constructLevelThree(levelBuilder);
		
		currentLevel = levelBuilder.getResult();
		
	}

	
	/*
	 * Recibe el input que detecto la GUI y responde acorde al movimiento requerido, guardando la direccion actual y la siguiente
	 *  para evitar colisiones con paredes en los casos que no sean necesarios (como por ejemplo cuando se esta viajando por un pasillo horizontal
	 *  y se presiona la tecla hacia arriba) 
	 */
	public synchronized void movePlayer(KeyEvent keyPressed) {
				
		if (!gameOver)
			
			switch (keyPressed.getKeyCode()) {
				
				case KeyEvent.VK_LEFT : { 
					
					if (!collideWithWall(Direction.LEFT, player)) 
						player.setDirection(Direction.LEFT);
					else  
						player.setNextDirection(Direction.LEFT);
					
					break;		
				}
				
				case KeyEvent.VK_RIGHT : {
					
					if (!collideWithWall(Direction.RIGHT, player)) 	
						player.setDirection(Direction.RIGHT);
					
					else 
						player.setNextDirection(Direction.RIGHT);
						
					break;		
				}
				
				case KeyEvent.VK_UP : {
					
					if (!collideWithWall(Direction.UP, player)) 
						player.setDirection(Direction.UP);
					else 
						player.setNextDirection(Direction.UP);
					
					break;		
				}
				
				case KeyEvent.VK_DOWN : {
					
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
	 * @param xVelocity velocidad horizontal que se le quiere añadir a el valor de x actual de la entidad.
	 * @param yVelocity velocidad vertal que se le quiere añadir a el valor y actual de la entidad.
	 * @param entityA entidad que requiere hacer el movimiento.
	 * @return verdadero si colisiono con una pared en la direccion requerida, falso en caso contrario. 
	 */
	
	public synchronized boolean collideWithWall(Direction desiredDirection, Character entityA) {
		
		Rectangle entityARectangle, entityBRectangle;
		boolean intersect = false;
		
		entityARectangle = new Rectangle(entityA.getXValue() + (desiredDirection.getXVelocity()) * entityA.getSpeed(), entityA.getYValue() + (desiredDirection.getYVelocity() * entityA.getSpeed()), entityA.getWidth(), entityA.getHeight());
		
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
	 * Realiza el movimiento y la colision en funcion del movimiento que se establecio
	 * @param entityA entidad que efectua el mvoimiento 
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
			innerloop:
			while (zoneEntitiesIterator.hasNext() ) {
					
				entityB = zoneEntitiesIterator.next();
				entityBRectangle = entityB.getRectangle();
				intersect = entityARectangle.intersects(entityBRectangle);	
					
				if (intersect) {
					myGUI.refreshEntity(entityB);
					entityB.accept(entityA.getVisitor());	
					
					if(!zone.getEntities().contains(entityB))
						break innerloop;
			
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
	 * @param entidad de la cual se quieren obtener las zonas
	 * @return zonas a la que pertenece la entidad
	 */
	List<Zone> getZones(Entity entity) {
		
		List<Zone> listOfZones = new ArrayList<Zone>();
	
		for (int i = 0; i < myZones.length ; i++ )
			
			for (int j = 0; j < myZones[0].length ; j++) 
				
				if (myZones[i][j].getEntities().contains(entity))
					listOfZones.add(myZones[i][j]);
		
		return listOfZones;
	}
	

	/* 
	 * Actualiza las zonas en funcion de un movimiento realizado
	 * @param entity entidad que necesita ser agregada en otra zona u eliminada de la zona actual
	 */
	private void updateZones(Entity entity) {
		
		Rectangle entityRectangle;
		Rectangle zoneRectangle;
		
		entityRectangle = entity.getRectangle();
			
		for (int i = 0; i < myZones.length ; i++ )
			
			for (int j = 0; j < myZones[0].length ; j++) {
						
				zoneRectangle = myZones[i][j].getRectangle();
						
				if (entityRectangle.intersects(zoneRectangle)) 
					myZones[i][j].addEntity(entity);
					
				else if (myZones[i][j].getEntities().contains(entity) && !entityRectangle.intersects(zoneRectangle) )
					myZones[i][j].getEntities().remove(entity);
							
			}	
	}
	
	public MainCharacter getPlayer() {
		
		return player;
	}
	
	public void potionTypeAEvent() {	
		
		if (player.getPotionTypeA()){		
			Entity power = new ActivePotionTypeA(player.getXValue(), player.getYValue(),"/assets/MarioAssets/bomb.png", this);
			allEntities.add(power);
//			myGUI.addEntity(power);
//			myGUI.refreshImage(power);	
			chargeZonesWithEntities();
		}
	}
	
	public GUI getGUI() {
		
		return myGUI;
	}

	
	/*
	 * Activa el modo vulnerable de todos los enemigos
	 */
	
	public void enableFrightenedMode() {
		
		for (Entity e: enemies) {
			((Enemy) e).enableFrightenedMode();		
		}
		
		new Time(this, 10000).start();
		
	}

	/*
	 * Cambia el estado del juego a perdido.
	 */
	
	public void gameOver() {
	
		gameOver = true;
		
		for (Enemy e: enemies) 		
			e.setDirection(Direction.STILL);
	
		player.setDirection(Direction.STILL);
		
		
		myGUI.dispose();
		myGameOverGUI = new GameOverGUI(this);
		playerThread.stop();
		enemiesThread.stop();
	}
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public Enemy getEnemyTypeA() {
		
		return enemies.get(0);
	}


	public void disableFrightenedMode() {
		
		
		for (Enemy enemy: enemies) 
			enemy.disableFrightenedMode();		
	
	}


	public synchronized void destroyEntity(Entity entityToDestroy) {
		
		allEntities.remove(entityToDestroy);
		components.remove(entityToDestroy);
		
		List<Zone> zones = new ArrayList<Zone>();
		
		for (int i = 0; i < myZones.length ; i++ )
			
			for (int j = 0; j < myZones[0].length ; j++) {
				
				if (myZones[i][j].getEntities().contains(entityToDestroy)) {
					zones.add(myZones[i][j]);
				}
				
			}
		System.out.println(zones.size());
		if (!zones.isEmpty())
			for (int i = 0; i < zones.size(); i++)
				zones.get(i).removeEntity(entityToDestroy);
				
		
		myGUI.destroyEntity(entityToDestroy);
		
	}
	
	public void checkIfWin() {
		
		if (components.size() <= 2)
			System.out.println("Gano");
			
	}
	
}
	