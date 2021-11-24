package logic;
import java.util.List;

import entities.Enemy;
import entities.Entity;
import entities.MainCharacter;
import entities.Wall;

public class LevelBuilder implements Builder {
	
	private List<Enemy> enemies;
	private List<Entity> components;
	private List<Wall> walls;
	private List<Entity> doorways;
	private MainCharacter player;
	private String backgroundUrl; 
	private int respawnTime;
	private int frightenedStateTime;
	private int powerTypeBTime;

	@Override
	public void createEnemies(List<Enemy> enemies) {
		
		this.enemies = enemies;	
	}

	@Override
	public void createComponents(List<Entity> components) {
		
		this.components = components;
	}

	@Override
	public void createPlayer(MainCharacter player) {
		
		this.player = player;
	}

	@Override
	public void createBackground(String backgroundUrl) {
		
		this.backgroundUrl = backgroundUrl;
	}

	@Override
	public Level getResult() {
		
		return new Level(enemies, components, player, walls, backgroundUrl, doorways, respawnTime, frightenedStateTime, powerTypeBTime);
	}

	@Override
	public void createWalls(List<Wall> walls) {
		
		this.walls = walls;
	}

	@Override
	public void createDoorways(List<Entity> doorways) {
		// TODO Auto-generated method stub
		this.doorways = doorways;		
	}
	
	@Override
	public void setRespawnTime(int milisec) {
		
		this.respawnTime = milisec;
	}
	
	@Override
	public void setFrightenedStateTime(int milisec) {
		
		this.frightenedStateTime = milisec;
	}
	
	@Override
	public void setPowerTypeBTime(int milisec) {
		
		this.powerTypeBTime = milisec;
	}
}
