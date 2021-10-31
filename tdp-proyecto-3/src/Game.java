import java.awt.event.KeyEvent;

public class Game {
	
	private Level currentLevel ;
	private GUI myGUI;
	private Entity player;
	private Zone[][] myZones;
	
	public Game () {
			
		initializeLevel();
		initializeZones();
		player = currentLevel.getPlayer();
		myGUI = new GUI(this, currentLevel.getBackgroundUrl());	
		myGUI.addEntity(player);
		
		
	}
	
	private void initializeZones() {
		
		myZones = new Zone[4][4];
		
		//Zone[0][0] = new Zone()
		
	}

	private void initializeLevel() {
		
		Director director = new Director();
		
		LevelBuilder levelBuilder = new LevelBuilder();
		
		director.constructLevelOne(levelBuilder);
		
		currentLevel = levelBuilder.getResult();
		
	}

	public void movePlayer(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		
			case KeyEvent.VK_LEFT : {player.moveLeft(); break;}
			case KeyEvent.VK_RIGHT : {player.moveRight(); break;}
			case KeyEvent.VK_UP : {player.moveUp(); break;}
			case KeyEvent.VK_DOWN : {player.moveDown(); break;}
		
		}
		
		myGUI.refreshEntity(player);
	}
	
	
	
	
	
	
	
	
	
	
}
