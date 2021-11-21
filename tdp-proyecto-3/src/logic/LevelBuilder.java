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
	private MainCharacter player;
	private String backgroundUrl; 
	private List<Entity> doorways;


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
		
		return new Level(enemies, components, player, walls, backgroundUrl, doorways);
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

}
