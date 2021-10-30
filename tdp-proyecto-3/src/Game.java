
public class Game {
	
	private Level currentLevel ;
	private GUI myGUI;
	private Entity player;
	
	public Game () {
		
		
		initializeLevel();
		player = currentLevel.getPlayer();
		myGUI = new GUI(this, currentLevel.getBackgroundUrl());	
		myGUI.addGraphicEntity(player.getGraphicEntity());
		
	}
	
	private void initializeLevel() {
		
		Director director = new Director();
		
		LevelBuilder levelBuilder = new LevelBuilder();
		
		director.constructLevelOne(levelBuilder);
		
		currentLevel = levelBuilder.getResult();
		
	}
	
	
	
	
	
	
	
	
}
