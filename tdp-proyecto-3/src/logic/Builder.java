package logic;
import java.util.List;

import entities.Enemy;
import entities.Entity;
import entities.MainCharacter;

public interface Builder {

	public void createEnemies(List<Enemy> enemies);
	
	public void createComponents(List<Entity> components);
	
	public void createPlayer(MainCharacter player);
	
	public void createBackground(String backgroundUrl);
	
	public Level getResult();

	public void createWalls(List<Entity> walls);
	
	public void createDoorways(List<Entity> doorways);
	
}
