package logic;
import java.net.URL;
import java.util.List;

import entities.Entity;

public class Level {
	
	private List<Entity> enemies;
	private List<Entity> walls;
	private List<Entity> components;
	private Entity player;
	private String backgroundUrl; 
	private List<Entity> doorways;

	public Level(List<Entity> enemies, List<Entity> components, Entity player, List<Entity> walls, String backgroundUrl, List<Entity> doorways) {
		
		this.enemies = enemies;
		this.player = player;
		this.backgroundUrl = backgroundUrl;
		this.walls = walls;
		this.components = components;
		this.doorways = doorways;
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
	
	public List<Entity> getComponents() {
		
		return components;
	}
	
	public List<Entity> enemies() {
		
		return enemies;
	}
	
	public List<Entity> getDoorWays() {
		
		return doorways;
	}

}
