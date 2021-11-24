package logic;
import java.util.List;

import entities.Enemy;
import entities.Entity;
import entities.MainCharacter;
import entities.Wall;

public class Level {
	
	private List<Enemy> enemies;
	private List<Wall> walls;
	private List<Entity> components;
	private List<Entity> doorways;
	private MainCharacter player;
	private String backgroundUrl;
	private int respawnTime;
	private int frightenedStateTime;
	private int powerTypeBTime;

	public Level(List<Enemy> enemies, List<Entity> components, MainCharacter player, List<Wall> walls, String backgroundUrl, List<Entity> doorways, int respawnTime, int frightenedStateTime, int powerTypeBTime) {
		
		this.enemies = enemies;
		this.player = player;
		this.backgroundUrl = backgroundUrl;
		this.walls = walls;
		this.components = components;
		this.doorways = doorways;
		this.respawnTime = respawnTime;
		this.frightenedStateTime = frightenedStateTime;
		this.powerTypeBTime = powerTypeBTime;
	}

	public String getBackgroundUrl() {
		
		return backgroundUrl;
	}
	
	public MainCharacter getPlayer() {
		
		return player;
	}

	public List<Wall> getWalls() {
		
		return walls;
	}
	
	public List<Entity> getComponents() {
		
		return components;
	}
	
	public List<Enemy> enemies() {
		
		return enemies;
	}
	
	public List<Entity> getDoorWays() {
		
		return doorways;
	}
	
	public int getRespawnTime() {
		
		return respawnTime;
	}
	
	public int getFrightenedStateTime() {
		
		return frightenedStateTime;
	}
	
	public int getPowerTypeBTime() {
		
		 return powerTypeBTime;
	}

}
