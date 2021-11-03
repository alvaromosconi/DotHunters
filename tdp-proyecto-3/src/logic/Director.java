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
	Entity w3 = new Wall(66, 80, 275, 21);
	Entity w4 = new Wall(189, 100, 54, 123);
	Entity w5 = new Wall(66, 152, 90, 73);
	//No muestra los rectangulos fuera de la zona 0,0
	Entity w6 = new Wall(66, 273, 175, 32);
	Entity w7 = new Wall(241, 310, 80, 62);
	Entity w8 = new Wall(0, 359, 127, 22);
	Entity w9 = new Wall(0, 431, 127, 27);
	Entity w10 = new Wall(0, 460, 28, 228);
	Entity w11 = new Wall(80, 509, 82, 28);
	Entity w12 = new Wall(48, 587, 86, 21);
	Entity w13 = new Wall(48, 659, 640, 365);
	Entity w14 = new Wall(162, 423, 26, 112);
	Entity w15 = new Wall(169, 587, 65, 22);
	Entity w16 = new Wall(198, 421, 146, 32);
	Entity w17 = new Wall(232, 502, 52, 106);
	Entity w18 = new Wall(283, 147, 59, 110);
	Entity w19 = new Wall(280, 307, 61, 65);
	Entity w20 = new Wall(285, 551, 170, 58);
	Entity w21 = new Wall(321, 453, 23, 48);
	Entity w22 = new Wall(341, 147, 46, 33);
	Entity w23 = new Wall(387, 79, 249, 101); //Cuadrado grande de arriba
	
	
	
	walls.add(w1);
	walls.add(w2);
	walls.add(w3);
	//walls.add(w4);
	walls.add(w5);
	
	//Los que no muestra
	walls.add(w6);
	walls.add(w7);
	walls.add(w8);
	walls.add(w9);
	walls.add(w10);
	walls.add(w11);
	walls.add(w12);
	walls.add(w13);
	walls.add(w14);
	walls.add(w15);
	walls.add(w16);
	walls.add(w17);
	walls.add(w18);
	walls.add(w19);
	walls.add(w20);
	walls.add(w21);
	walls.add(w22);
	walls.add(w23);
	
	
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
