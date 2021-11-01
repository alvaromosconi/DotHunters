package logic;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import entities.Entity;
import gui.GUI;

public class Game {
	
	private Level currentLevel ;
	private GUI myGUI;
	private Entity player;
	private Zone[][] myZones;
	private Entity wall;
	
	public Game () {
			
		initializeLevel();
		initializeZones();
		player = currentLevel.getPlayer();
		myGUI = new GUI(this, currentLevel.getBackgroundUrl());	
		myGUI.addEntity(player);
		
		wall = currentLevel.getWall();
		myGUI.addWall(currentLevel.getWall());
			
	}
	
	private void initializeZones() {
		
		myZones = new Zone[4][4];
		
		myZones[0][0] = new Zone(new Point(0,0), new Point (256, 0), new Point(0, 167) , new Point(256, 167));
		
		myZones[0][0].addEntity(wall);
	}

	private void initializeLevel() {
		
		Director director = new Director();
		
		LevelBuilder levelBuilder = new LevelBuilder();
		
		director.constructLevelOne(levelBuilder);
		
		currentLevel = levelBuilder.getResult();
		
	}

	public void movePlayer(KeyEvent e) {
	
		
		Rectangle figure1, figure2;
		figure1 = myGUI.getLabel(player).getBounds();
		figure2 = myGUI.getLabel(wall).getBounds();
		
		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_LEFT : {player.moveLeft(); if (figure1.intersects(figure2)) wall.accept(player.getVisitor()); break;}
			case KeyEvent.VK_RIGHT : {player.moveRight(); break;}
			case KeyEvent.VK_UP : {player.moveUp(); break;}
			case KeyEvent.VK_DOWN : {player.moveDown(); break;}
		
		}
		
		System.out.println(figure2.intersects(figure1));
		myGUI.refreshEntity(player);
	}
	
	
	
	
	
	
	
	
	
	
}
