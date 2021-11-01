package logic;
import java.util.ArrayList;
import java.util.List;

import entities.Component;
import entities.EnemyTypeA;
import entities.Entity;
import entities.MainCharacter;
import entities.RegularDot;
import entities.Wall;

public class Director {

public void constructLevelOne(Builder b) {
	
	List<Entity> enemies = new ArrayList<Entity>(4);
	List<Entity> components = new ArrayList<Entity>(200);
	List<Entity> walls = new ArrayList<Entity>();
	
	
	Entity player = new MainCharacter(400, 400, "/assets/mario1.gif");
	
	
	Entity w1 = new Wall(0, 0, 31, 356);
	Entity w2 = new Wall(0, 0, 1024, 29);
	walls.add(w1);
	walls.add(w2);
	
	for (int i = 0; i < components.size(); i++) {
		
		Component comp = new RegularDot();
		components.add(comp);
	}
	
	Entity enemy1 = new EnemyTypeA(400, 500, "/assets/EnemyTypeA.gif");
//	Entity enemy2 = new EnemyTypeB();
//	Entity enemy3 = new EnemyTypeC();
//	Entity enemy4 = new EnemyTypeD();
	enemies.add(enemy1);

//	enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
	
	b.createBackground("/assets/background.png");
    b.createEnemies(enemies);
    b.createComponents(components);
    b.createPlayer(player);
    b.createWalls(walls);
	
}


}
