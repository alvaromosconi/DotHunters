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
	
	
	Entity player = new MainCharacter(400, 396, "/assets/mario1.gif");
	
	
	
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
	
	
	Entity n1 = new Wall(396, 88, 396, 44);
	Entity n2 = new Wall(308, 176, 572, 44);
	Entity n3 = new Wall(440, 264, 132, 44);
	Entity n4 = new Wall(440, 308, 44, 88);
	Entity n5 = new Wall(484, 352, 264, 44);
	Entity n6 = new Wall(704, 308, 44, 88); 
	Entity n7 = new Wall(616, 264, 132, 44);
	
	Entity s1 = new Wall(440, 440, 308, 44);
	Entity s2 = new Wall(440, 484, 44, 88);
	Entity s3 = new Wall(484, 528, 44, 44);
	Entity s4 = new Wall(572, 528, 44, 220);
	
	Entity ne1 = new Wall(836, 88, 264, 44);
	Entity ne2 = new Wall(924, 132, 44, 176);
	Entity ne3 = new Wall(836, 264, 132, 44);
	Entity ne4 = new Wall(792, 264, 44, 132);
	Entity ne5 = new Wall(836, 352, 176, 44);
	Entity ne6 = new Wall(1056, 352, 132, 44);
	Entity ne7 = new Wall(1012, 176, 88, 132);
	Entity ne8 = new Wall(1144, 0, 44, 352);
	Entity ne9 = new Wall(572, 0, 572, 44);
	
	Entity se1 = new Wall(704, 484, 44, 88);
	Entity se2 = new Wall(660, 528, 44, 44);
	Entity se3 = new Wall(660, 616, 176, 44);
	Entity se4 = new Wall(792, 572, 132, 44);
	Entity se5 = new Wall(792, 440, 176, 88);
	Entity se6 = new Wall(968, 440, 44, 44);
	Entity se7 = new Wall(1056, 440, 132, 44);
	Entity se8 = new Wall(1144, 484, 44, 352);
	Entity se9 = new Wall(1012, 528, 88, 44);
	Entity se10 = new Wall(968, 572, 132, 88);
	Entity se11 = new Wall(1012, 704, 132, 44);
	Entity se12 = new Wall(660, 704, 308, 44);
	Entity se13 = new Wall(880, 660, 44, 44);
	Entity se14 = new Wall(572, 792, 572, 44);
	
	
	
	walls.addAll(Arrays.asList(nw1, nw2, nw3, nw4, nw5, nw6, nw7, nw8, nw9, nw10, 
							   sw1, sw2, sw3, sw4, sw5, sw6, sw7, sw8, sw9, sw10, sw11, sw12, 
							   n1, n2, n3, n4, n5, n6, n7,
							   s1, s2, s3, s4,
							   ne1, ne2, ne3, ne4, ne5, ne6, ne7, ne8, ne9,
							   se1, se2, se3, se4, se5, se6, se7, se8, se9, se10, se11, se12, se13,se14));

	for (int i = 0; i < components.size(); i++) {
		
		//Component comp = new RegularDot();
		//components.add(comp);
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
