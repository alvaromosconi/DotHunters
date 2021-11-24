package logic;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import entities.*;

public class Director {
	
	private Game game;
	
	// Declaracion de rutas
	private String domainRoute;
	private String regularDotRoute;
	private String poweredDotRoute = domainRoute + "poweredDot.png";
	private String potionTypeARoute = domainRoute + "potion1.png";
	private String potionTypeBRoute = domainRoute + "potion2.png";
	private String fruitTypeARoute = domainRoute + "fruit1.png";
	private String fruitTypeBRoute = domainRoute + "fruit2.png";
	private String fruitTypeCRoute = domainRoute + "fruit3.png";
	private String routeOfMaze;
	
	// Tamaño estandar de las entidades del juego (puede variar)
	private int size = 36;
	
	/*
	 * Constructor clase Director
	 * @param game 
	 */
	public Director(Game game) {
		
		this.game = game;
		this.domainRoute = game.getDomainRoute();
		
		this.regularDotRoute = domainRoute + "regularDot.png";
		this.poweredDotRoute = domainRoute + "poweredDot.png";
		this.potionTypeARoute = domainRoute + "potion1.png";
		this.potionTypeBRoute = domainRoute + "potion2.png";
		this.fruitTypeARoute = domainRoute + "fruit1.png";
		this.fruitTypeBRoute = domainRoute + "fruit2.png";
		this.fruitTypeCRoute = domainRoute + "fruit3.png";
	}

	/*
	 * Crea todas las entidades del juego para el nivel 1.
	 */
	public void constructLevelOne(Builder builder)  {
		
		System.out.println(regularDotRoute);
		System.out.println(poweredDotRoute);
		routeOfMaze = "/assets/MazeLevel1.txt";
		
		// Obtencion de las walls a traves de un archivo
		List<Wall> walls = loadAllWalls(routeOfMaze);
		
		List<Enemy> enemies = new ArrayList<Enemy>(4);
		List<Entity> components = new ArrayList<Entity>();
		List<Entity> doorways = new ArrayList<Entity>(2);
		List<Entity> zonaSinDots = new ArrayList<Entity>();
		
		// Creacion del jugador
		MainCharacter player = new MainCharacter(13 * size, 9 * size, domainRoute + "PlayerDown.gif", 2, game);
		player.loadSprites(domainRoute + "PlayerUP.gif", domainRoute + "PlayerDown.gif", domainRoute + "PlayerRight.gif", domainRoute + "PlayerLeft.gif");
		
		// Creacion de los dots
		Dot poweredDot1 = new PoweredDot(size + 6 , size + 6, 20, poweredDotRoute, game);
		Dot poweredDot2 = new PoweredDot(size * 25 + 6, size + 6, 20, poweredDotRoute, game);
		Dot poweredDot3 = new PoweredDot(size + 6, size * 17 + 6, 20, poweredDotRoute, game);
		Dot poweredDot4 = new PoweredDot(size * 25 + 6, size * 17 + 6, 20, poweredDotRoute, game);
		
		// Creacion de las potions
		Potion potion1 = new PotionTypeA(7 * size, 7 * size, potionTypeARoute, game);
		Potion potion2 = new PotionTypeB(19 * size , 7 * size, potionTypeBRoute, game);
		
		// Creacion de las frutas
		Fruit fruit1 = new FruitTypeA(size * 13, size * 1 , 50, fruitTypeARoute, game);
		
		// Creacion de los portales
		Entity doorway1 = new Doorway(0 * size, 9 * size, game);
		Entity doorway2 = new Doorway(26 * size, 9 * size, game);
		
		// Creacion de los enemigos
		Enemy enemy1 = new EnemyTypeA(12 * size, 5 * size, domainRoute + "EnemyTypeA.gif", 2, game);
		Enemy enemy2 = new EnemyTypeB(12 * size, 7 * size, domainRoute + "EnemyTypeB.gif", 1, game);
		Enemy enemy3 = new EnemyTypeC(14 * size, 7 * size, domainRoute + "EnemyTypeC.gif", 2, game);
		Enemy enemy4 = new EnemyTypeD(15 * size, 7 * size, domainRoute + "EnemyTypeD.gif", 2, game);

		enemy1.loadSprites(domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif");
		enemy2.loadSprites(domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif");
		enemy3.loadSprites(domainRoute + "EnemyTypeC.gif", domainRoute + "EnemyTypeC.gif", domainRoute + "EnemyTypeC.gif", domainRoute + "EnemyTypeC.gif");
		enemy4.loadSprites(domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif");
		
		// Cargado de listas
		enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
		doorways.addAll(Arrays.asList(doorway1, doorway2));		
		
		Wall contornoCasaEnemies = new Wall(9 * size, 5 * size, 9 * size, 5 * size, game);
		zonaSinDots.addAll(Arrays.asList(contornoCasaEnemies, poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit1));
				
		// Creacion de todos los regular dots (Se crean en donde NO haya paredes y otros componentes)
		loadAllRegularDots(components, walls, zonaSinDots);
		components.addAll(Arrays.asList(poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit1));
		
		builder.createBackground(domainRoute + "MazeLevel1.png");
	    builder.createEnemies(enemies);
	    builder.createWalls(walls);
	    builder.createComponents(components);
	    builder.createPlayer(player);
	    builder.createDoorways(doorways);
	    builder.setFrightenedStateTime(10000);
	    builder.setRespawnTime(15000);
	    builder.setPowerTypeBTime(3500);
	}
	
	public void constructLevelTwo(Builder builder) {
	
		routeOfMaze = "/assets/MazeLevel2.txt";
		// Obtencion de las walls a traves de un archivo
		List<Wall> walls = loadAllWalls(routeOfMaze);
				
		List<Enemy> enemies = new ArrayList<Enemy>(4);
		List<Entity> components = new ArrayList<Entity>();
		List<Entity> doorways = new ArrayList<Entity>(4);
		List<Entity> zonaSinDots = new ArrayList<Entity>();		
		
		// Creacion del jugador
		MainCharacter player = new MainCharacter(13 * size, 7 * size, domainRoute + "PlayerDown.gif", 2, game);
		player.loadSprites(domainRoute + "PlayerUP.gif", domainRoute + "PlayerDown.gif", domainRoute + "PlayerRight.gif", domainRoute + "PlayerLeft.gif");

		// Creacion de los dots
		Dot poweredDot1 = new PoweredDot(size + 6 , size + 6, 20, poweredDotRoute, game);
		Dot poweredDot2 = new PoweredDot(size * 25 + 6, size + 6, 20, poweredDotRoute, game);
		Dot poweredDot3 = new PoweredDot(size + 6, size * 17 + 6, 20, poweredDotRoute, game);
		Dot poweredDot4 = new PoweredDot(size * 25 + 6, size * 17 + 6, 20, poweredDotRoute, game);
		
		// Creacion de las potions
		Potion potion1 = new PotionTypeA(5 * size, 5 * size, potionTypeARoute, game);
		Potion potion2 = new PotionTypeB(21 * size, 5 * size, potionTypeBRoute, game);
		
		// Creacion de las frutas
		Fruit fruit2 = new FruitTypeB(size * 13, size * 1 , 50, fruitTypeBRoute, game);
		
		// Creacion de los portales
		Entity doorway1 = new Doorway(0 * size, 3 * size, game);
		Entity doorway2 = new Doorway(0 * size, 15 * size, game);
		Entity doorway3 = new Doorway(26 * size, 3 * size, game);
		Entity doorway4 = new Doorway(26 * size, 15 * size, game);
		
		// Creacion de los enemigos
		Enemy enemy1 = new EnemyTypeA(12 * size, 3 * size, domainRoute + "EnemyTypeA.gif", 2, game);
		Enemy enemy2 = new EnemyTypeB(12 * size, 5 * size, domainRoute + "EnemyTypeB.gif", 1, game);
		Enemy enemy3 = new EnemyTypeC(14 * size, 5 * size, domainRoute + "EnemyTypeC.gif", 2, game);
		Enemy enemy4 = new EnemyTypeD(15 * size, 5 * size, domainRoute + "EnemyTypeD.gif", 2, game);

		enemy1.loadSprites(domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif");
		enemy2.loadSprites(domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif");
		enemy3.loadSprites(domainRoute + "EnemyTypeC.gif", domainRoute + "EnemyTypeC.gif", domainRoute + "EnemyTypeC.gif", domainRoute + "EnemyTypeC.gif");
		enemy4.loadSprites(domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif");
		
		// Cargado de listas
		enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
		doorways.addAll(Arrays.asList(doorway1, doorway2, doorway3, doorway4));		
			
		Wall contornoCasaEnemies = new Wall(9 * size, 3 * size, 9 * size, 7 * size, game);
		zonaSinDots.addAll(Arrays.asList(contornoCasaEnemies, poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit2));
		
		// Creacion de todos los regular dots (Se crean en donde NO haya paredes y otros componentes)
		loadAllRegularDots(components, walls, zonaSinDots);
		components.addAll(Arrays.asList(poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit2));
		
		
		builder.createBackground(domainRoute + "MazeLevel2.png");
	    builder.createEnemies(enemies);
	    builder.createWalls(walls);
	    builder.createComponents(components);
	    builder.createPlayer(player);
	    builder.createDoorways(doorways);
	    builder.setFrightenedStateTime(8000);
	    builder.setRespawnTime(12500);
	    builder.setPowerTypeBTime(2500);
	}
	
	
	public void constructLevelThree(Builder builder) {
		
		routeOfMaze = "/assets/MazeLevel3.txt";
		
		// Obtencion de las walls a traves de un archivo
		List<Wall> walls = loadAllWalls(routeOfMaze);
		
		List<Enemy> enemies = new ArrayList<Enemy>(4);
		List<Entity> components = new ArrayList<Entity>();
		List<Entity> doorways = new ArrayList<Entity>(4);
		List<Entity> zonaSinDots = new ArrayList<Entity>();
	
		// Creacion del jugador
		MainCharacter player = new MainCharacter(13 * size, 11 * size, domainRoute + "PlayerDown.gif", 2, game);
		player.loadSprites(domainRoute + "PlayerUP.gif", domainRoute + "PlayerDown.gif", domainRoute + "PlayerRight.gif", domainRoute + "PlayerLeft.gif");
		
		// Creacion de los dots
		Dot poweredDot1 = new PoweredDot(size + 6 , size + 6, 20, poweredDotRoute, game);
		Dot poweredDot2 = new PoweredDot(size * 25 + 6, size + 6, 20, poweredDotRoute, game);
		Dot poweredDot3 = new PoweredDot(size + 6, size * 17 + 6, 20, poweredDotRoute, game);
		Dot poweredDot4 = new PoweredDot(size * 25 + 6, size * 17 + 6, 20, poweredDotRoute, game);
		
		// Creacion de las potions
		Potion potion1 = new PotionTypeA(8 * size, 6 * size, potionTypeARoute, game);
		Potion potion2 = new PotionTypeB(18 * size , 6 * size, potionTypeBRoute, game);
		
		// Creacion de las frutas
		Fruit fruit3 = new FruitTypeC(size * 13, size * 1 , 50, fruitTypeCRoute, game);
		
		// Creacion de los portales
		Entity doorway1 = new Doorway(0 * size, 4 * size, game);
		Entity doorway2 = new Doorway(0 * size, 14 * size, game);
		Entity doorway3 = new Doorway(26 * size, 4 * size, game);
		Entity doorway4 = new Doorway(26 * size, 14 * size, game);
		
		// Creacion de los enemigos
		Enemy enemy1 = new EnemyTypeA(12 * size, 5 * size, domainRoute + "EnemyTypeA.gif", 2, game);
		Enemy enemy2 = new EnemyTypeB(12 * size, 7 * size, domainRoute + "EnemyTypeB.gif", 1, game);
		Enemy enemy3 = new EnemyTypeC(14 * size, 7 * size, domainRoute + "EnemyTypeC.gif", 2, game);
		Enemy enemy4 = new EnemyTypeD(15 * size, 7 * size, domainRoute + "EnemyTypeD.gif", 2, game);

		enemy1.loadSprites(domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif", domainRoute + "EnemyTypeA.gif");
		enemy2.loadSprites(domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif", domainRoute + "EnemyTypeB.gif");
		enemy3.loadSprites(domainRoute + "EnemyTypeC.gif", domainRoute + "EnemyTypeC.gif", domainRoute + "EnemyTypeC.gif", domainRoute + "EnemyTypeC.gif");
		enemy4.loadSprites(domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif", domainRoute + "EnemyTypeD.gif");
		
		
		// Cargado de listas
		enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
		doorways.addAll(Arrays.asList(doorway1, doorway2, doorway3, doorway4));		
				
		Wall contornoCasaEnemies = new Wall(9 * size, 5 * size, 9 * size, 5 * size, game);
		zonaSinDots.addAll(Arrays.asList(contornoCasaEnemies, poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit3));
						
		// Creacion de todos los regular dots (Se crean en donde NO haya paredes y otros componentes)
		loadAllRegularDots(components, walls, zonaSinDots);
		components.addAll(Arrays.asList(poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit3));
	
		builder.createBackground(domainRoute + "MazeLevel3.png");
	    builder.createEnemies(enemies);
	    builder.createWalls(walls);
	    builder.createComponents(components);
	    builder.createPlayer(player);
	    builder.createDoorways(doorways);
	    builder.setFrightenedStateTime(6500);
	    builder.setRespawnTime(10);
	    builder.setPowerTypeBTime(2000);
	}
	
/*
 * Automatizaciion de la carga de dots; elimina los que colisionan con paredes y estan en la zona que rode la casa
 * @param componentes lista de componentes
 * @param wallls lista de paredes
 * @param zoneWithoutDots zona que rodea la casa (no debe tener dots)
 */
private void loadAllRegularDots(List<Entity> components, List<Wall> walls, List<Entity> zonaSinDots) {
		
		
		for (int i = 2; i < 27; i++) 
			
			for (int j = 1; j < 19; j++)  {
				
				RegularDot regularDot = new RegularDot(i * size - 27, j * size - 27, 10, regularDotRoute, game);
				components.add(regularDot);
			}
		
		// Se eliminan los que colisionan con la paredes.
		Iterator<Entity> componenentIterator = components.iterator();
		Entity currentComponent;
		
		while (componenentIterator.hasNext()) {
			
			currentComponent = componenentIterator.next();
			
			Rectangle2D currentComponentRectangle = currentComponent.getRectangle();

			for (Wall wall: walls) {
					
				Rectangle2D wallRectangle = wall.getRectangle();
				if (currentComponentRectangle.intersects(wallRectangle)) {
					componenentIterator.remove();
					break;
				}	
				
			}		
		}
		
		// Se eliminan los que colisionan con la zona de la casa de los enemies , powerDots, potions y fruits.
				
		Iterator<Entity> componentIterator = components.iterator();
		Entity currentComponent2;
				
			while (componentIterator.hasNext()) {
					
				currentComponent2 = componentIterator.next();
					
				Rectangle2D currentComponent2Rectangle = currentComponent2.getRectangle();

				for (Entity currentZone: zonaSinDots) {
							
					Rectangle2D currentZoneRectangle = currentZone.getRectangle();
					if (currentComponent2Rectangle.intersects(currentZoneRectangle)) {
						componentIterator.remove();
						break;
					}	
						
				}
						
			}
	}

	/*
	 * Carga de paredes automatizada
	 * @param routeOfMaze ruta del archivo que contiene las paredes
	 */
	private List<Wall> loadAllWalls(String routeOfMaze) {
		
		List<Wall> walls = new ArrayList<Wall>();
		
		try {
		
			InputStream stream = Director.class.getResourceAsStream(routeOfMaze);
			BufferedReader buferLector = new BufferedReader(new InputStreamReader(stream));
						
			String currentLine;
			int posX, posY, width, height;
			
			while (buferLector.ready()) {
				
				if (! (currentLine = buferLector.readLine()).equals("/000")) {
					
					 StringTokenizer tokens = new StringTokenizer(currentLine);
					 posX = Integer.parseInt(tokens.nextToken());
					 posY = Integer.parseInt(tokens.nextToken());
					 width = Integer.parseInt(tokens.nextToken());
					 height = Integer.parseInt(tokens.nextToken());
					 
					 Wall w = new Wall(posX * size, posY * size, width * size, height * size, game);
					 walls.add(w);
					 
				}
			}
			
			buferLector.close();
			
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return walls;
	}


}