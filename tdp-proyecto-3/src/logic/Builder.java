package logic;
import java.util.List;

import entities.Entity;

public interface Builder {

	public void createEnemies(List<Entity> enemies);
	
	public void createComponents(List<Entity> components);
	
	public void createPlayer(Entity player);
	
	public void createBackground(String backgroundUrl);
	
	public Level getResult();

	public void createWall(Entity w1);
	
}
