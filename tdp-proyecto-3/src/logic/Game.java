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
		chargeZones();
		
		myGUI.addEntity(currentLevel.enemies().get(0));
		myGUI.setupBackground(); 
	}
	
	private void initializeZones() {
		
		myZones = new Zone[4][4];
		
		myZones[0][0] = new Zone(new Point(0,0), new Point (256, 0), new Point(0, 167) , new Point(256, 167));
		
	}
	
	private void chargeZones() {
	
		Point p;
		Rectangle r;
		
		for (Entity e: walls) {
			
			p = new Point (e.getXValue(), e.getYValue());
			for (int i = 0; i < myZones.length ; i++ )
				for (int j = 0; j < myZones[0].length ; j++) {
					
					if (myZones[i][j] != null) {
						
						r = new Rectangle (0, 0, myZones[i][j].getWidthOfZone(), myZones[i][j].getHeightOfZone() );
	
						if (r.contains(p)) {
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

		
		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_LEFT : {player.moveLeft();  break;}
			case KeyEvent.VK_RIGHT : {player.moveRight(); break;}
			case KeyEvent.VK_UP : {player.moveUp(); break;}
			case KeyEvent.VK_DOWN : {player.moveDown(); break;}
		
		}
		

		myGUI.refreshEntity(player);
		
		
		
		figure1 = new Rectangle (myGUI.getLabel(player).getBounds());
		Zone z = getZone(player.getXValue(), player.getYValue());
		
		if (z != null) {

		for (Entity ent: z.getEntities()) {	

			if (ent != null) {

				figure2 = new Rectangle (myGUI.getLabel(ent).getBounds());
				if (figure1.intersects(figure2)) {
		
					ent.accept(player.getVisitor());
				}
			}
		}


		//System.out.println("BOUNDS jugador: " + figure1.getBounds());
		}
//		System.out.println("BOUNDS wall: " + figure2.getBounds());
	

		
	//	System.out.println("INTERSECTARON ? : " + figure1.intersects(figure2));
		
	}

	private Zone getZone(int xValue, int yValue) {
		
		for (int i = 0; i < myZones.length; i++) 
			for (int j = 0; j < myZones[0].length; j++)
				if (myZones[i][j] != null)
					if (myZones[i][j].inside(xValue, yValue))
						return myZones[i][j];
	
		
		return null;
					
		
	}
}
	
	
	
	
	
	
	
	
	
	

