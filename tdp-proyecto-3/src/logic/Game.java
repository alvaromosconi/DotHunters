package logic;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.css.Rect;

import entities.Component;
import entities.Entity;
import entities.Wall;
import gui.GUI;

public class Game {
	
	private Level currentLevel ;
	private GUI myGUI;
	private Entity player;
	private Zone[][] myZones;
	private List<Entity> walls;
	private List<Entity> components;
	private List<Entity> allEntities = new ArrayList<Entity>();
	
	public Game () {
			
		initializeLevel();
		initializeZones();
		
		player = currentLevel.getPlayer();
		myGUI = new GUI(this, currentLevel.getBackgroundUrl());	
		
		this.walls = currentLevel.getWalls();
		this.components = currentLevel.getComponents();
		

		allEntities.add(player);
		//allEntities.addAll(components);
		
		
		chargeZonesWithEntities();
		chargeZonesWithWalls();
		chargeZonesWithComponents();

		myGUI.addEntity(currentLevel.enemies().get(0));
		myGUI.setupBackground(); 
	}
	
	private void initializeZones() {
		
		myZones = new Zone[4][4];
		
		int k = 297;
		int o = 209;
		
		int widthMultiplier = 0;
		int heightMultiplier = 0;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				
				myZones[i][j] = new Zone(new Point(widthMultiplier, heightMultiplier), 
						 	    		 new Point(widthMultiplier + k, heightMultiplier), 
						 	    		 new Point(widthMultiplier, heightMultiplier + o),
						 	    		 new Point(widthMultiplier + k, heightMultiplier + o));

			
				widthMultiplier += k;
			}
			
			widthMultiplier = 0;
			heightMultiplier += o;
	
		}
		

	}
	
	private void chargeZonesWithWalls() {
	
		Rectangle wallRectangle;
		Rectangle zoneRectangle;
		
		for (Entity e: walls) {
			
			wallRectangle = new Rectangle(e.getXValue(), e.getYValue(), e.getWidth(), e.getHeight());
	
			for (int i = 0; i < myZones.length ; i++)
				
				for (int j = 0; j < myZones[0].length ; j++) {
					
						zoneRectangle = myZones[i][j].getRectangle();
				
						if (wallRectangle.intersects(zoneRectangle) ) {
							myZones[i][j].addEntity(e);
							myGUI.addWall(e);
						}
				}	
		}
		
	}
	
	private void chargeZonesWithComponents() {
		
		Rectangle entityRectangle;
		Rectangle wallRectangle;

		for (Entity e : components) {
	
			entityRectangle = new Rectangle(e.getXValue(), e.getYValue(), e.getWidth(), e.getHeight());
	
			for (Entity w: walls) {
				
				wallRectangle = new Rectangle(w.getXValue(), w.getYValue() , w.getWidth(), w.getHeight() );	
			
				if (entityRectangle.intersects(wallRectangle))  {
						myGUI.addEntity(e);
				}	
			}

		}
	}
	
	private void chargeZonesWithEntities() {
		
		Rectangle entityRectangle;
		Rectangle zoneRectangle;
		
		boolean entityInZone = false;
		
		for (Entity e : allEntities) {
			System.out.println("xd");
			entityRectangle = new Rectangle(e.getXValue(), e.getYValue(), e.getWidth(), e.getHeight());
			
		
			for (int i = 0; i < myZones.length ; i++ )
					
				for (int j = 0; j < myZones[0].length ; j++) {
						
					if (myZones[i][j] != null) {
							
						zoneRectangle = myZones[i][j].getRectangle();
							
						if (entityRectangle.intersects(zoneRectangle) ) {
							 
							if (!entityInZone ) {
								myGUI.addEntity(e);
								entityInZone = true;
							}
							
							else if (e != player) 
								myGUI.addEntity(e);
							
							myZones[i][j].addEntity(e);
						}
					}	
				}
		}
	}

	private void initializeLevel() {
		
		Director director = new Director();
		
		LevelBuilder levelBuilder = new LevelBuilder();
		
		//director.constructLevelOne(levelBuilder);
		//director.constructLevelTwo(levelBuilder);
		director.constructLevelThree(levelBuilder);
		
		currentLevel = levelBuilder.getResult();
		
	}

	public void movePlayer(KeyEvent keyPressed) {
		
		switch (keyPressed.getKeyCode()) {
		
			case KeyEvent.VK_LEFT : {player.setXVelocity(-2); player.setYVelocity(0); break;}
			case KeyEvent.VK_RIGHT : { player.setXVelocity(2); player.setYVelocity(0);  break;}
			case KeyEvent.VK_UP : {player.setYVelocity(-2);  player.setXVelocity(0); break;}
			case KeyEvent.VK_DOWN : {player.setYVelocity(2);  player.setXVelocity(0); break;}
	
		}	
	
		Rectangle entityARectangle, entityBRectangle;
		boolean intersect = false;
		
		entityARectangle = player.getOffsetBounds();
		
		List<Zone> listOfZones = getZone(player);

		for (Zone zone : listOfZones) {
		
			if (listOfZones != null)
			
			for (Entity entity: zone.getEntities()) {	
				
				if (entity != player) {
					
					entityBRectangle = new Rectangle (myGUI.getLabel(entity).getBounds());
					intersect = entityARectangle.intersects(entityBRectangle);
					
					if (intersect) {
						entity.accept(player.getVisitor());
						break;
					}
				}
			}
		}
		
		
		updateZones(player);
		myGUI.refreshEntity(player);
		player.move();
	
	}

	private List<Zone> getZone(Entity e) {
		
		List<Zone> listOfZones = new ArrayList<Zone>();
	
		for (int i = 0; i < myZones.length ; i++ )
			
			for (int j = 0; j < myZones[0].length ; j++) 
				
				if (myZones[i][j].getEntities().contains(e))
					listOfZones.add(myZones[i][j]);
		
		return listOfZones;
	}
	
	
	private void updateZones(Entity entity) {
		
		Rectangle entityRectangle;
		Rectangle zoneRectangle;
		
		entityRectangle = entity.getOffsetBounds();
			
		for (int i = 0; i < myZones.length ; i++ )
			
			for (int j = 0; j < myZones[0].length ; j++) {
					
					if (myZones[i][j] != null) {
						
						zoneRectangle = myZones[i][j].getRectangle();
						
						if (entityRectangle.intersects(zoneRectangle)) 
							myZones[i][j].addEntity(entity);
					
						else 
							if (myZones[i][j].getEntities().contains(entity))
								myZones[i][j].getEntities().remove(entity);
							
					}	
			}
	}

}
	
	
	
	
	
	
	
	
	
	

