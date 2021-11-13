package logic;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.ActivePotionTypeA;
import entities.Enemy;
import entities.EnemyTypeB;
import entities.Entity;
import entities.Entity.Direction;
import entities.MainCharacter;
import entities.RegularDot;
import entities.Wall;
import gui.GUI;

public class Game {
	
	private Level currentLevel ;
	private GUI myGUI;
	private Zone[][] myZones;
	private Time myTime;

	private Entity player;

	private List<Entity> walls;
	private List<Entity> components;
	private List<Entity> enemies;
	private List<Entity> allEntities;

	public Game () {

		initializeLevel();
		initializeZones();
		
		player = currentLevel.getPlayer();

		myGUI = new GUI(this, currentLevel.getBackgroundUrl());	
		
		this.walls = currentLevel.getWalls();
		this.components = currentLevel.getComponents();
		this.enemies = currentLevel.enemies();
		
		allEntities = new ArrayList<Entity>();
		allEntities.add(player);
	    allEntities.addAll(components);
		allEntities.addAll(enemies);
		
		chargeZonesWithWalls();
		chargeZonesWithEntities();
	
		myTime = new Time(this, 10, player);
		myTime.start();
		
		myGUI.setupBackground(); 
		
		boolean k = true;
		walls.add(new Wall(13*36, 6 * 36, 36, 36));
		
		ghostAi(k);
		chargeZonesWithWalls();
		
		
	}
	
	private synchronized void ghostAi(boolean k) {

		  Thread thread = new Thread(){
			  
			  boolean l = false;
			    
			  public void run(){
			    	
			    while (k) {
			    		

	    			try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				   
	    			Enemy e = (Enemy) enemies.get(0);
	    			
	    			double min = Double.MAX_VALUE;
	    			  	
	    	
	    			/*
	    			 Pregunta si NO va a colisionar con todas las posiciones, si no va a colisionar setea en la variable min la distancia desde
	    			 el fantasma hasta el pacman y setea la direccion en el sentido que pregunto en el collideWithWall.
	    			 
	    			 Al traspasar todos los if queda seteada la direccion en la que el recorrido en linea recta es mas corto
	    			 
	    			 Problema: Estas decisiones solo deberian tomarse en los cuadros donde hay mas de 1 direccion.
	    			 Tambien, el fantasma no deberia ser capaz de retroceder a menos que hay quedado atrapado
	    			 */
	    			if (!collideWithWall(-2, 0, e) && e.getNextDirection() != Direction.RIGHT) 
			    		if (distance(e.getXValue() - 2, player.getXValue(), e.getYValue(), player.getYValue()) < min) {
			    				min = distance(e.getXValue() - 2, player.getXValue(), e.getYValue(), player.getYValue());
			    				e.setDirection(Direction.LEFT);
			    		}
			    		
		    		if (!collideWithWall(2, 0, e) && e.getNextDirection() != Direction.LEFT) 
		    			if (distance(e.getXValue() + 2, player.getXValue(), e.getYValue(), player.getYValue()) < min) {
		    					min = distance(e.getXValue() + 2, player.getXValue(), e.getYValue(), player.getYValue());
		    					e.setDirection(Direction.RIGHT);
		    			}
		    			
		    		if (!collideWithWall(0, -2, e) && e.getNextDirection() != Direction.DOWN )
		    			if (distance(e.getXValue(), player.getXValue(), e.getYValue() - 2, player.getYValue()) < min) {
		    					min = distance(e.getXValue(), player.getXValue(), e.getYValue() - 2, player.getYValue());
		    					e.setDirection(Direction.UP);
		    			}
	    			
	    		
		    		if (!collideWithWall(0, 2, e) && e.getNextDirection() != Direction.UP)
		    			if (distance(e.getXValue(), player.getXValue(), e.getYValue() + 2, player.getYValue()) < min) {
		    					min = distance(e.getXValue(), player.getXValue(), e.getYValue() + 2, player.getYValue());
		    					e.setDirection(Direction.DOWN);
		    			}
	    			
		    		e.setNextDirection(e.getDirection());
	    			
	    			move(e);
		    	}
			 }
		  };
		  
		  thread.start();
	}
		 
	
			  
	// Calcula la distancia en linea recta entre el punto (x1,y1) e (x2,y2)
	private double distance(int x1, int x2, int y1, int y2) {
		
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	private void initializeZones() {
		
		myZones = new Zone[4][4];
		
		int gapX = 243;
		int gapY = 171;
		
		int widthMultiplier = 0;
		int heightMultiplier = 0;
		
		for (int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 4; j++) {
				
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
	

	private void initializeLevel() {
		
		Director director = new Director();
		
		LevelBuilder levelBuilder = new LevelBuilder();
		
		director.constructLevelOne(levelBuilder);
		//director.constructLevelTwo(levelBuilder);
		//director.constructLevelThree(levelBuilder);
		
		currentLevel = levelBuilder.getResult();
		
	}

	public synchronized void movePlayer(KeyEvent keyPressed) {
		
		switch (keyPressed.getKeyCode()) {
			
			case KeyEvent.VK_LEFT : { 
				
				if (!collideWithWall(-2, 0, player)) 
					player.setDirection(Direction.LEFT);
				else  
					player.setNextDirection(Direction.LEFT);
				
				break;		
			}
			
			case KeyEvent.VK_RIGHT : {
				
				if (!collideWithWall(2, 0, player)) 	
					player.setDirection(Direction.RIGHT);
				
				else 
					player.setNextDirection(Direction.RIGHT);
					
				break;		
			}
			
			case KeyEvent.VK_UP : {
				
				if (!collideWithWall(0, -2, player)) 
					player.setDirection(Direction.UP);
				else 
					player.setNextDirection(Direction.UP);
				
				break;		
			}
			
			case KeyEvent.VK_DOWN : {
				
				if (!collideWithWall(0, 2, player)) 
					player.setDirection(Direction.DOWN);
				
				else
					player.setNextDirection(Direction.DOWN);
				
				break;		
			}
			
		}	
	
	}
	
	public boolean collideWithWall(int xVelocity, int yVelocity, Entity entityA) {
		
		Rectangle entityARectangle, entityBRectangle;
		boolean intersect = false;
		
		entityARectangle = new Rectangle(entityA.getXValue() + xVelocity, entityA.getYValue() + yVelocity, entityA.getWidth(), entityA.getHeight());
		
		List<Zone> listOfZones = getZones(entityA);
		
		Iterator<Entity> zoneWallsIterator;
		Entity entityB;
		
		for (Zone zone : listOfZones) {
			
			zoneWallsIterator = zone.getWalls().iterator();
			entityB = zoneWallsIterator.next();
		
			while (zoneWallsIterator.hasNext() && !intersect) {
				
				entityBRectangle = entityB.getRectangle();
				intersect = entityARectangle.intersects(entityBRectangle);	
				entityB = zoneWallsIterator.next();
			}
			
		}
		
		return intersect;
	}
	

	public synchronized void move(Entity entityA) {
		
		Rectangle entityARectangle, entityBRectangle;
		boolean intersect = false;
		
		entityARectangle = entityA.getOffsetBounds();
		
		List<Zone> listOfZones = getZones(entityA);
		
		Iterator<Entity> zoneEntitiesIterator;
		Entity entityB;
		

		for (Zone zone : listOfZones) {
			
			zoneEntitiesIterator = zone.getEntities().iterator();
				
			while (zoneEntitiesIterator.hasNext() ) {
					
				entityB = zoneEntitiesIterator.next();
				entityBRectangle = entityB.getRectangle();
				intersect = entityARectangle.intersects(entityBRectangle);	
					
				if (intersect) {
					entityB.accept(entityA.getVisitor());
					myGUI.refreshEntity(entityB);
					break;
				} 
				
			}
		}
	
		myGUI.refreshImage(entityA);
		updateZones(entityA);
		myGUI.refreshEntity(entityA);
		entityA.move();

	}
	
	
	private List<Zone> getZones(Entity entity) {
		
		List<Zone> listOfZones = new ArrayList<Zone>();
	
		for (int i = 0; i < myZones.length ; i++ )
			
			for (int j = 0; j < myZones[0].length ; j++) 
				
				if (myZones[i][j].getEntities().contains(entity))
					listOfZones.add(myZones[i][j]);
		
		return listOfZones;
	}
	

	private void updateZones(Entity entity) {
		
		Rectangle entityRectangle;
		Rectangle zoneRectangle;
		
		entityRectangle = entity.getOffsetBounds();
			
		for (int i = 0; i < myZones.length ; i++ )
			
			for (int j = 0; j < myZones[0].length ; j++) {
						
				zoneRectangle = myZones[i][j].getRectangle();
						
				if (entityRectangle.intersects(zoneRectangle)) 
					myZones[i][j].addEntity(entity);
					
				else if (myZones[i][j].getEntities().contains(entity))
					myZones[i][j].getEntities().remove(entity);
							
			}	
	}
	
	public Entity getPlayer() {
		
		return player;
	}
	
	public void potionTypeAEvent() {
		MainCharacter aux = (MainCharacter) player;
		List<Zone> listOfZones = getZones(aux);
		if (aux.getPotionTypeA()){
			Entity power = new ActivePotionTypeA(2*36,36,"/assets/MarioAssets/EnemyTypeB.gif");
			allEntities.add(power);
			//for (Zone zone : listOfZones) {
				//zone.addEntity(power);
			//}		
			System.out.println("asd");
			//myGUI.addEntity(power);
			//myGUI.refreshImage(power);
			chargeZonesWithEntities();
		}
	}
	
}
	