package logic;
import java.awt.GraphicsConfiguration;
import java.net.URL;
import java.util.List;

import entities.Entity;
import entities.Wall;

public class Level {
	
	private List<Entity> enemies;
	private List<Entity> components;
	private Entity player;
	private String backgroundUrl; 
	private Entity wall;

	public Level(List<Entity> enemies, List<Entity> components, Entity player, Entity wall, String backgroundUrl) {
		
		this.enemies = enemies;
		this.components = components;
		this.player = player;
		this.backgroundUrl = backgroundUrl;
		this.wall = wall;
	}

	public String getBackgroundUrl() {
		
		return backgroundUrl;
	}
	
	public Entity getPlayer() {
		
		return player;
	}

	public Entity getWall() {
		
		return wall;
	}

}
