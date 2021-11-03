package logic;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;

public class LevelBuilder implements Builder {
	
	private List<Entity> enemies;
	private List<Entity> components;
	private List<Entity> walls;
	private Entity player;
	private String backgroundUrl; 


	@Override
	public void createEnemies(List<Entity> enemies) {
		
		this.enemies = enemies;	
	}

	@Override
	public void createComponents(List<Entity> components) {
		
		this.components = components;
	}

	@Override
	public void createPlayer(Entity player) {
		
		this.player = player;
	}

	@Override
	public void createBackground(String backgroundUrl) {
		
		this.backgroundUrl = backgroundUrl;
	}

	@Override
	public Level getResult() {
		
		return new Level(enemies, components, player, walls, backgroundUrl);
	}

	@Override
	public void createWalls(List<Entity> walls) {
		
		this.walls = walls;
	}

}