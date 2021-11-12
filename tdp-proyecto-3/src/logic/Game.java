package logic;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.Entity;
import entities.Entity.Direction;
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
				
				if (!collideWithWall(-2,0)) {
					
					player.setVelocity(-2, 0);
					player.setDirection(Direction.LEFT);
				} 
				
				else  {
					player.setNextVelocity(-2, 0);
					player.setNextDirection(Direction.LEFT);
				}
				break;		
			}
			
			case KeyEvent.VK_RIGHT : {
				
				if (!collideWithWall(2, 0)) {
					
					player.setVelocity(2, 0);
					player.setDirection(Direction.RIGHT);
				} 
				
				else {
					player.setNextVelocity(2, 0);
					player.setNextDirection(Direction.RIGHT);
				}
					
				break;		
			}
			
			case KeyEvent.VK_UP : {
				
				if (!collideWithWall(0, -2)) {
					
					player.setVelocity(0, -2);
					player.setDirection(Direction.UP);
				} 
				
				else {
					player.setNextVelocity(0, -2);
					player.setNextDirection(Direction.UP);
				}
				break;		
			}
			
			case KeyEvent.VK_DOWN : {
				
				if (!collideWithWall(0, 2)) {
					
					player.setVelocity(0, 2);
					player.setDirection(Direction.DOWN);
				} 
				
				else {
					player.setNextVelocity(0, 2);
					player.setNextDirection(Direction.DOWN);
				}
				break;		
			}
			
		}	
	
	}
	
	public boolean collideWithWall(int xVelocity, int yVelocity) {
		
		Rectangle entityARectangle, entityBRectangle;
		boolean intersect = false;
		
		entityARectangle = new Rectangle(player.getXValue() + xVelocity, player.getYValue() + yVelocity, player.getWidth(), player.getHeight());
		
		List<Zone> listOfZones = getZones(player);
		
		Iterator<Entity> zoneWallsIterator;
		Entity entity;
		
		for (Zone zone : listOfZones) {
			
			zoneWallsIterator = zone.getWalls().iterator();
			entity = zoneWallsIterator.next();
		
			while (zoneWallsIterator.hasNext() && !intersect) {
				
				entityBRectangle = entity.getRectangle();
				intersect = entityARectangle.intersects(entityBRectangle);	
				entity = zoneWallsIterator.next();
			}
			
		}
		
		return intersect;
	}
	

	public synchronized void move() {
		
		Rectangle entityARectangle, entityBRectangle;
		boolean intersect = false;
		
		entityARectangle = player.getOffsetBounds();
		
		List<Zone> listOfZones = getZones(player);
		
		Iterator<Entity> zoneEntitiesIterator;
		Entity entity;
		

		for (Zone zone : listOfZones) {
			
			zoneEntitiesIterator = zone.getEntities().iterator();
				
			while (zoneEntitiesIterator.hasNext() ) {
					
				entity = zoneEntitiesIterator.next();
				entityBRectangle = entity.getRectangle();
				intersect = entityARectangle.intersects(entityBRectangle);	
					
				if (intersect) {
					entity.accept(player.getVisitor());
					myGUI.refreshEntity(entity);
					break;
				} 
				
			}
		}
	
		myGUI.refreshImage(player);
		updateZones(player);
		myGUI.refreshEntity(player);
		player.move();

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
	

	
}
	