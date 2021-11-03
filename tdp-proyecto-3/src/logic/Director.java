package logic;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	
	Entity nw1 = new Wall(0, 0, 44, 396);
	Entity nw2 = new Wall(44, 0, 550, 44);
	Entity nw3 = new Wall(0, 352, 132, 44);
	Entity nw4 = new Wall(88, 176, 88, 132);
	Entity nw5 = new Wall(88, 88, 264, 44);
	Entity nw6 = new Wall(220, 88, 44, 220);
	Entity nw7 = new Wall(220, 264, 132, 44);
	Entity nw8 = new Wall(352, 264, 44, 132);
	Entity nw9 = new Wall(176, 352, 176, 44);
	Entity nw10 = new Wall(44, 352, 88, 44);

	Entity sw1 = new Wall(0, 440, 44, 396);
	Entity sw2 = new Wall(44, 440, 88, 44);
	Entity sw3 = new Wall(44, 704, 132, 44);
	Entity sw4 = new Wall(88, 528, 88, 132);
	Entity sw5 = new Wall(220, 484, 176, 44);
	Entity sw6 = new Wall(176, 572, 44, 88);
	Entity sw7 = new Wall(176, 440, 220, 44);
	Entity sw8 = new Wall(44, 792, 550, 44);
	Entity sw9 = new Wall(264, 572, 132, 44);
	Entity sw10 = new Wall(352, 616, 176, 44);
	Entity sw11 = new Wall(220, 704, 308, 44);
	Entity sw12 = new Wall(264, 660, 44, 44);
	
	
	Entity ne1 = new Wall(550, 0, 596, 44);
	
	walls.addAll(Arrays.asList(nw1, nw2, nw3, nw4, nw5, nw6, nw7, nw8, nw9, nw10, sw1, sw2, sw3, sw4, sw5, sw6, sw7, sw8, sw9, sw10, sw11, sw12, ne1));	

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
