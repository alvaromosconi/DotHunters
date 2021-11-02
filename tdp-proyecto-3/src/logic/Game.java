package logic;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.List;

import entities.Entity;
import gui.GUI;

public class Game {
	
	private Level currentLevel ;
	private GUI myGUI;
	private Entity player;
	private Zone[][] myZones;
	private List<Entity> walls;
	
	public Game () {
			
		initializeLevel();
		initializeZones();
		player = currentLevel.getPlayer();
		myGUI = new GUI(this, currentLevel.getBackgroundUrl());	
		myGUI.addEntity(player);
		
		this.walls = currentLevel.getWalls();
		chargeZonesWithWalls();
		
		myGUI.addEntity(currentLevel.enemies().get(0));
		myGUI.setupBackground(); 
	}
	
	private void initializeZones() {
		
		myZones = new Zone[4][4];
		
//		myZones[0][0] = new Zone(new Point(0,0), new Point (256, 0), new Point(0, 167) , new Point(256, 167));
//		myZones[0][1] = new Zone(new Point(0,0), new Point (256, 0), new Point(0, 167) , new Point(256, 167));
//		myZones[0][0] = new Zone(new Point(0,0), new Point (256, 0), new Point(0, 167) , new Point(256, 167));
		
		int k = 256;
		int o = 167;
		
		int widthMultiplier = 0;
		int heightMultiplier = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) 
				myZones[i][j] = new Zone(new Point(widthMultiplier, heightMultiplier), 
						 	    		 new Point(widthMultiplier + k, heightMultiplier), 
						 	    		 new Point(widthMultiplier, heightMultiplier + o),
						 	    		 new Point(widthMultiplier + k, heightMultiplier + o));

			
			heightMultiplier += o;
			widthMultiplier += k;
		}
	}
	
	private void chargeZonesWithWalls() {
	
		Rectangle r1;
		Rectangle r2;
		
		for (Entity e: walls) {
			
			r1 = new Rectangle(e.getXValue(), e.getYValue(), e.getWidth(), e.getHeight());
			for (int i = 0; i < myZones.length ; i++ )
				for (int j = 0; j < myZones[0].length ; j++) {
					
					if (myZones[i][j] != null) {
						
						r2 = new Rectangle ((int) myZones[i][j].getUpperLeftVertex().getX() , (int) myZones[i][j].getUpperLeftVertex().getY(), myZones[i][j].getWidthOfZone(), myZones[i][j].getHeightOfZone() );
	
						if (r1.intersects(r2)) {
							myGUI.addWall(e);
							myZones[i][j].addEntity(e);
						}
					}	
				}
		}
		
		
	}

	private void initializeLevel() {
		
		Director director = new Director();
		
		LevelBuilder levelBuilder = new LevelBuilder();
		
		director.constructLevelOne(levelBuilder);
		
		currentLevel = levelBuilder.getResult();
		
	}

	public void movePlayer(KeyEvent e) {
		

		Rectangle figure1, figure2;
		boolean intersect = false;
		figure1 = new Rectangle (myGUI.getLabel(player).getBounds());
		
		Zone z = getZone(player.getXValue(), player.getYValue());
		
		

		if (z != null)
			
			for (Entity ent: z.getEntities()) {	
	
					figure2 = new Rectangle (myGUI.getLabel(ent).getBounds());
					intersect = figure1.intersects(figure2);
					if (intersect) 
						ent.accept(player.getVisitor());

			}
		
		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_LEFT : {player.moveLeft();  break;}
			case KeyEvent.VK_RIGHT : {player.moveRight(); break;}
			case KeyEvent.VK_UP : {player.moveUp(); break;}
			case KeyEvent.VK_DOWN : {player.moveDown(); break;}
	
		}
		
				
		myGUI.refreshEntity(player);
		
		
		//System.out.println("BOUNDS jugador: " + figure1.getBounds());
		}
//		System.out.println("BOUNDS wall: " + figure2.getBounds());
	

		
	//	System.out.println("INTERSECTARON ? : " + figure1.intersects(figure2));
		
	

	private Zone getZone(int xValue, int yValue) {
		
		for (int i = 0; i < myZones.length; i++) 
			for (int j = 0; j < myZones[0].length; j++)
				if (myZones[i][j] != null)
					if (myZones[i][j].inside(xValue, yValue))
						return myZones[i][j];
	
		
		return null;
					
		
	}
}
	
	
	
	
	
	
	
	
	
	

