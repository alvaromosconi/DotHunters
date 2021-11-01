package logic;
import java.net.URL;
import java.util.List;

import entities.Entity;

public class Level {
	
	private List<Entity> enemies;
	private List<Entity> walls;
	private Entity player;
	private String backgroundUrl; 

	public Level(List<Entity> enemies, List<Entity> components, Entity player, List<Entity> walls, String backgroundUrl) {
		
		this.enemies = enemies;
		this.player = player;
		this.backgroundUrl = backgroundUrl;
		this.walls = walls;
	}

	public String getBackgroundUrl() {
		
		return backgroundUrl;
	}
	
	public Entity getPlayer() {
		
		return player;
	}

	public List<Entity> getWalls() {
		
		return walls;
	}
	
	public List<Entity> enemies() {
		
		return enemies;
	}

}
