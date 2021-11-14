package logic;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import entities.*;

public class Director {
	
	private String domainRoute = "/assets/MarioAssets/";
	private String regularDotRoute = domainRoute +"regularDot.png";
	private String poweredDotRoute = domainRoute +"poweredDot.png";
	private String potionTypeARoute = domainRoute +"potion1.png";
	private String potionTypeBRoute = domainRoute +"potion2.png";
	private String fruitTypeARoute = domainRoute +"fruit1.png";
	private String routeOfMaze;
	private int size = 36;
	
	public void constructLevelOne(Builder b) {
		
		
		List<Entity> enemies = new ArrayList<Entity>(4);
		List<Entity> components = new ArrayList<Entity>();
		routeOfMaze = "/assets/MazeLevel1.txt";
		List<Entity> walls = loadAllWalls(routeOfMaze);
		List<Entity> doorways = new ArrayList<Entity>(2);
				
		Entity player = new MainCharacter(13 * size, 9 * size, domainRoute +"PlayerDown.gif");
		player.loadSprites(domainRoute +"PlayerUP.gif", domainRoute +"PlayerDown.gif", domainRoute +"PlayerRight.gif", domainRoute +"PlayerLeft.gif");
		
		Entity poweredDot1 = new PoweredDot(size + 6 , size + 6, 20, poweredDotRoute);
		Entity poweredDot2 = new PoweredDot(size * 25 + 6, size + 6, 20, poweredDotRoute);
		Entity poweredDot3 = new PoweredDot(size + 6, size * 17 + 6, 20, poweredDotRoute);
		Entity poweredDot4 = new PoweredDot(size * 25 + 6, size * 17 + 6, 20, poweredDotRoute);
		
		Entity potion1 = new PotionTypeA(7 * size + 6, 7 * size + 6, 30, potionTypeARoute);
		Entity potion2 = new PotionTypeB(19 * size + 12, 7 * size + 12, 30, potionTypeBRoute);
		
		Entity fruit1 = new FruitTypeA(size * 15 + 30, size * 12 + 15, 50, fruitTypeARoute);
		
		Entity doorway1 = new Doorway(0 * size, 9 * size);
		Entity doorway2 = new Doorway(26 * size, 9 * size);
		doorways.addAll(Arrays.asList(doorway1,doorway2));
		
		components.addAll(Arrays.asList(poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit1));	
	
		loadAllRegularDots(components, walls);
		
		Entity enemy1 = new EnemyTypeA(12 * size, 5 * size, domainRoute +"EnemyTypeA.gif");
		Entity enemy2 = new EnemyTypeB(12 * size, 7 * size, domainRoute +"EnemyTypeB.gif");
		Entity enemy3 = new EnemyTypeC(14 * size, 7 * size, domainRoute +"EnemyTypeC.gif");
		Entity enemy4 = new EnemyTypeD(15 * size, 7 * size, domainRoute +"EnemyTypeD.gif");
		
		enemy1.loadSprites(domainRoute +"EnemyTypeA.gif", domainRoute +"EnemyTypeA.gif", domainRoute +"EnemyTypeA.gif", domainRoute +"EnemyTypeA.gif");
		enemy2.loadSprites(domainRoute +"EnemyTypeB.gif", domainRoute +"EnemyTypeB.gif", domainRoute +"EnemyTypeB.gif", domainRoute +"EnemyTypeB.gif");
		enemy3.loadSprites(domainRoute +"EnemyTypeC.gif", domainRoute +"EnemyTypeC.gif", domainRoute +"EnemyTypeC.gif", domainRoute +"EnemyTypeC.gif");
		enemy4.loadSprites(domainRoute +"EnemyTypeD.gif", domainRoute +"EnemyTypeD.gif", domainRoute +"EnemyTypeD.gif", domainRoute +"EnemyTypeD.gif");
		
		enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
	
		
		b.createBackground(domainRoute +"MazeLevel1.png");
	    b.createEnemies(enemies);
	    b.createWalls(walls);
	    b.createComponents(components);
	    b.createPlayer(player);
	    b.createDoorways(doorways);
	
	}

	
	
	public void constructLevelTwo(Builder b) {
		
		List<Entity> enemies = new ArrayList<Entity>(4);
		List<Entity> components = new ArrayList<Entity>(200);
		routeOfMaze = "/assets/MazeLevel2.txt";
		List<Entity> walls = loadAllWalls(routeOfMaze);
		List<Entity> doorways = new ArrayList<Entity>(4);
		
		
		Entity player = new MainCharacter(13 * size, 7 * size, domainRoute +"PlayerDown.gif");
		player.loadSprites(domainRoute +"PlayerUP.gif", domainRoute +"PlayerDown.gif", domainRoute +"PlayerRight.gif", domainRoute +"PlayerLeft.gif");

		Entity poweredDot1 = new PoweredDot(size + 6 , size + 6, 20, poweredDotRoute);
		Entity poweredDot2 = new PoweredDot(size * 25 + 6, size + 6, 20, poweredDotRoute);
		Entity poweredDot3 = new PoweredDot(size + 6, size * 17 + 6, 20, poweredDotRoute);
		Entity poweredDot4 = new PoweredDot(size * 25 + 6, size * 17 + 6, 20, poweredDotRoute);
		
		Entity potion1 = new PotionTypeA(7 * size + 6, 7 * size + 6, 30, potionTypeARoute);
		Entity potion2 = new PotionTypeB(19 * size + 12, 7 * size + 12, 30, potionTypeBRoute);
		
		Entity fruit1 = new FruitTypeA(size * 15 + 30, size * 12 + 15, 50, fruitTypeARoute);
		
		Entity doorway1 = new Doorway(0 * size, 3 * size);
		Entity doorway2 = new Doorway(0 * size, 15 * size);
		Entity doorway3 = new Doorway(26 * size, 3 * size);
		Entity doorway4 = new Doorway(26 * size, 15 * size);
		doorways.addAll(Arrays.asList(doorway1, doorway2, doorway3, doorway4));
	    
		components.addAll(Arrays.asList(poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit1));	
	
		loadAllRegularDots(components, walls);
		
		Entity enemy1 = new EnemyTypeA(13 * size, 3 * size, domainRoute +"EnemyTypeA.gif");
		Entity enemy2 = new EnemyTypeB(12 * size, 5 * size, domainRoute +"EnemyTypeB.gif");
		Entity enemy3 = new EnemyTypeC(14 * size, 5 * size, domainRoute +"EnemyTypeC.gif");
		Entity enemy4 = new EnemyTypeD(15 * size, 5 * size, domainRoute +"EnemyTypeD.gif");
		
		enemy1.loadSprites(domainRoute +"EnemyTypeA.gif", domainRoute +"EnemyTypeA.gif", domainRoute +"EnemyTypeA.gif", domainRoute +"EnemyTypeA.gif");
		enemy2.loadSprites(domainRoute +"EnemyTypeB.gif", domainRoute +"EnemyTypeB.gif", domainRoute +"EnemyTypeB.gif", domainRoute +"EnemyTypeB.gif");
		enemy3.loadSprites(domainRoute +"EnemyTypeC.gif", domainRoute +"EnemyTypeC.gif", domainRoute +"EnemyTypeC.gif", domainRoute +"EnemyTypeC.gif");
		enemy4.loadSprites(domainRoute +"EnemyTypeD.gif", domainRoute +"EnemyTypeD.gif", domainRoute +"EnemyTypeD.gif", domainRoute +"EnemyTypeD.gif");
		
		enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
	
		
		b.createBackground(domainRoute +"MazeLevel2.png");
	    b.createEnemies(enemies);
	    b.createWalls(walls);
	    b.createComponents(components);
	    b.createPlayer(player);
	    b.createDoorways(doorways);
	}
	
	
	public void constructLevelThree(Builder b) {
		
		List<Entity> enemies = new ArrayList<Entity>(4);
		List<Entity> components = new ArrayList<Entity>(200);
		routeOfMaze = "/assets/MazeLevel3.txt";
		List<Entity> walls = loadAllWalls(routeOfMaze);
		List<Entity> doorways = new ArrayList<Entity>(4);
		
		
		Entity player = new MainCharacter(13 * size, 11 * size, domainRoute +"PlayerDown.gif");
		player.loadSprites(domainRoute +"PlayerUP.gif", domainRoute +"PlayerDown.gif", domainRoute +"PlayerRight.gif", domainRoute +"PlayerLeft.gif");
			    
	    Entity poweredDot1 = new PoweredDot(size + 6 , size + 6, 20, poweredDotRoute);
		Entity poweredDot2 = new PoweredDot(size * 25 + 6, size + 6, 20, poweredDotRoute);
		Entity poweredDot3 = new PoweredDot(size + 6, size * 17 + 6, 20, poweredDotRoute);
		Entity poweredDot4 = new PoweredDot(size * 25 + 6, size * 17 + 6, 20, poweredDotRoute);
		
		Entity potion1 = new PotionTypeA(8 * size + 6, 8 * size + 6, 30, potionTypeARoute);
		Entity potion2 = new PotionTypeB(18 * size + 12, 8 * size + 12, 30, potionTypeBRoute);
		
		Entity fruit1 = new FruitTypeA(size * 15 + 20, size * 15 + 15, 50, fruitTypeARoute);
		
		Entity doorway1 = new Doorway(0 * size, 4 * size);
		Entity doorway2 = new Doorway(0 * size, 14 * size);
		Entity doorway3 = new Doorway(26 * size, 4 * size);
		Entity doorway4 = new Doorway(26 * size, 14 * size);
		doorways.addAll(Arrays.asList(doorway1, doorway2, doorway3, doorway4));
	    
		components.addAll(Arrays.asList(poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit1));	
	
		loadAllRegularDots(components, walls);
		
		Entity enemy1 = new EnemyTypeA(13 * size, 5 * size, domainRoute +"EnemyTypeA.gif");
		Entity enemy2 = new EnemyTypeB(12 * size, 7 * size, domainRoute +"EnemyTypeB.gif");
		Entity enemy3 = new EnemyTypeC(14 * size, 7 * size, domainRoute +"EnemyTypeC.gif");
		Entity enemy4 = new EnemyTypeD(15 * size, 7 * size, domainRoute +"EnemyTypeD.gif");
		
		enemy1.loadSprites(domainRoute +"EnemyTypeA.gif", domainRoute +"EnemyTypeA.gif", domainRoute +"EnemyTypeA.gif", domainRoute +"EnemyTypeA.gif");
		enemy2.loadSprites(domainRoute +"EnemyTypeB.gif", domainRoute +"EnemyTypeB.gif", domainRoute +"EnemyTypeB.gif", domainRoute +"EnemyTypeB.gif");
		enemy3.loadSprites(domainRoute +"EnemyTypeC.gif", domainRoute +"EnemyTypeC.gif", domainRoute +"EnemyTypeC.gif", domainRoute +"EnemyTypeC.gif");
		enemy4.loadSprites(domainRoute +"EnemyTypeD.gif", domainRoute +"EnemyTypeD.gif", domainRoute +"EnemyTypeD.gif", domainRoute +"EnemyTypeD.gif");
		
		enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
	
		
		b.createBackground(domainRoute +"MazeLevel3.png");
	    b.createEnemies(enemies);
	    b.createWalls(walls);
	    b.createComponents(components);
	    b.createPlayer(player);
	    b.createDoorways(doorways);
		
	}
	
	
	private void loadAllRegularDots(List<Entity> components, List<Entity> walls) {
		
		for (int i = 2; i < 27; i++) 
			
			for (int j = 1; j < 19; j++)  {
				
				Entity regularDot = new RegularDot(i * size - 27, j * size - 27, 10, regularDotRoute);
				components.add(regularDot);
			}
		
		
		Entity ZonaSinDots = new Wall(9 * size, 5 * size, 9 * size, 5 * size);
		Rectangle2D sDots = ZonaSinDots.getRectangle();
		
		// elimino los que colisionan con la pared
		Iterator<Entity> i1 = components.iterator();
		Entity e1;
		
		while (i1.hasNext()) {
			
			e1 = i1.next();
			
			Rectangle2D r1 = e1.getRectangle();

			for (Entity w: walls) {
					
				Rectangle2D r2 = w.getRectangle();
				if (r1.intersects(r2) || r1.intersects(sDots)) {
					i1.remove();
					break;
				}	
				
			}
				
		}
	}

	private List<Entity> loadAllWalls(String routeOfMaze) {
		
		List<Entity> walls = new ArrayList<Entity>();
		
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
					 
					 Entity w = new Wall(posX * size, posY * size, width * size, height * size);
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