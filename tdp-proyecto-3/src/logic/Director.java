package logic;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import entities.EnemyTypeA;
import entities.EnemyTypeB;
import entities.EnemyTypeC;
import entities.EnemyTypeD;
import entities.Entity;
import entities.FruitTypeA;
import entities.MainCharacter;
import entities.PoweredDot;
import entities.RegularDot;
import entities.Wall;

public class Director {

	String regularDotRoute = "/assets/regularDot.png";
	String poweredDotRoute = "/assets/poweredDot.png";
	String potionTypeARoute = "/assets/potion1.png";
	String potionTypeBRoute = "/assets/potion2.png";
	String fruitTypeARoute = "/assets/fruit1.png";
	
	public void constructLevelOne(Builder b) {
		
		
		List<Entity> enemies = new ArrayList<Entity>(4);
		List<Entity> components = new ArrayList<Entity>();
		List<Entity> walls = new ArrayList<Entity>();
		
		
		Entity player = new MainCharacter(572, 396, "/assets/mario1.gif");
		
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
	
	
		Entity poweredDot1 = new PoweredDot(56, 56, 20, poweredDotRoute);
		Entity poweredDot2 = new PoweredDot(1090, 56, 20, poweredDotRoute);
		Entity poweredDot3 = new PoweredDot(56, 758, 20, poweredDotRoute);
		Entity poweredDot4 = new PoweredDot(1100, 758, 20, poweredDotRoute);
		
		Entity potion1 = new PoweredDot(308, 308, 30, potionTypeARoute);
		Entity potion2 = new PoweredDot(856, 330, 30, potionTypeBRoute);
		
		Entity fruit1 = new FruitTypeA(572, 408, 50, fruitTypeARoute);
	
		
		components.addAll(Arrays.asList(poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit1));	
	
		
	
		// agrego los dots en todo el mapa
		for (int i = 1; i < 26; i++) 
			
			for (int j = 1; j < 19; j++)  {
				
			
				Entity regularDot = new RegularDot(i * 35-25, j * 44-25, 10, regularDotRoute);
				components.add(regularDot);

			}
		
		
		Entity ZonaSinDots = new Wall(396, 220, 396, 220);
		Rectangle2D sDots = ZonaSinDots.getRectangle();
		
		// elimino los que colisionan con la pared
		Iterator<Entity> i1 = components.iterator();
		Entity e1 = i1.next();
		
		while (i1.hasNext()) {
			
			Rectangle2D r1 = e1.getRectangle();

			for (Entity w: walls) {
					
				Rectangle2D r2 = w.getRectangle();
				if (r1.intersects(r2) || r1.intersects(sDots)) {
					i1.remove();
					break;
				}	
				
			}
				
			e1 = i1.next();
		}
	
		
		
		Entity enemy1 = new EnemyTypeA(484, 308, "/assets/EnemyTypeA.gif");
		Entity enemy2 = new EnemyTypeB(528, 308, "/assets/EnemyTypeB.gif");
		Entity enemy3 = new EnemyTypeC(616, 308, "/assets/EnemyTypeC.gif");
		Entity enemy4 = new EnemyTypeD(660, 308, "/assets/EnemyTypeD.gif");
		
		enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
	
		
		b.createBackground("/assets/background.png");
	    b.createEnemies(enemies);
	    b.createWalls(walls);
	    b.createComponents(components);
	    b.createPlayer(player);
	
		
	}
	
	
	
	public void constructLevelTwo(Builder b) {
		
		List<Entity> enemies = new ArrayList<Entity>(4);
		List<Entity> components = new ArrayList<Entity>(200);
		List<Entity> walls = new ArrayList<Entity>();
		
		
		Entity player = new MainCharacter(572, 308, "/assets/mario1.gif");
		
		Entity nw1 = new Wall(0, 0, 572, 44);
		Entity nw2 = new Wall(0, 44, 44, 88);
		Entity nw3 = new Wall(88, 88, 220, 44);
		Entity nw4 = new Wall(88, 176, 44, 220);
		Entity nw5 = new Wall(0, 176, 44 , 264);
		Entity nw6 = new Wall(352, 88, 44, 220);
		Entity nw7 = new Wall(176, 176, 132, 44);
		Entity nw8 = new Wall(264, 220, 44, 176);
		Entity nw9 = new Wall(176, 352, 88, 44);
		Entity nw10 = new Wall(176, 264, 44, 44);
		
		Entity sw1 = new Wall(0, 792, 572, 44);
		Entity sw2 = new Wall(0, 440, 44, 220);
		Entity sw3 = new Wall(44, 440, 176, 44);
		Entity sw4 = new Wall(88, 528, 132, 44);
		Entity sw5 = new Wall(88, 616, 220, 44);
		Entity sw6 = new Wall(88, 704, 308, 44);
		Entity sw7 = new Wall(0, 704, 44, 132);
		Entity sw8 = new Wall(264, 440, 44, 132);
		Entity sw9 = new Wall(308, 528, 132, 44);
		Entity sw10 = new Wall(352, 440, 88, 44);
		
		Entity n1 = new Wall(352, 440, 88, 44);
		Entity n2 = new Wall(440, 88, 308, 44);
		Entity n3 = new Wall(440, 176, 132, 44);
		Entity n4 = new Wall(440, 220, 44, 88);
		Entity n5 = new Wall(484, 264, 264, 44);
		Entity n6 = new Wall(616, 176, 88, 44); 
		Entity n7 = new Wall(704, 176, 44, 88);
		
		Entity s1 = new Wall(440, 704, 308, 44);
		Entity s2 = new Wall(396, 352, 88, 44);
		Entity s3 = new Wall(484, 352, 44, 220);
		Entity s4 = new Wall(352, 616, 220, 44);
		Entity s5 = new Wall(572, 352, 44, 220);
		Entity s6 = new Wall(616, 616, 220, 44);
		Entity s7 = new Wall(660, 352, 44, 220);
		Entity s8 = new Wall(704, 352, 88, 44);
		
		Entity ne1 = new Wall(572, 0, 616, 44);
		Entity ne2 = new Wall(1144, 44, 44, 88);
		Entity ne3 = new Wall(880, 88, 220, 44);
		Entity ne4 = new Wall(792, 88, 44, 220	);
		Entity ne5 = new Wall(880, 176, 132, 44);
		Entity ne6 = new Wall(880, 352, 132, 44);
		Entity ne7 = new Wall(880, 220, 44, 132);
		Entity ne8 = new Wall(968, 264, 44, 44);
		Entity ne9 = new Wall(1056, 176, 44, 220);
		Entity ne10 = new Wall(1144, 176, 44, 264);
		
		Entity se1 = new Wall(572, 792, 616, 44);
		Entity se2 = new Wall(880, 440, 44, 132);
		Entity se3 = new Wall(748, 528, 132, 44);
		Entity se4 = new Wall(748, 440, 88, 44);
		Entity se5 = new Wall(968, 440, 220, 44);
		Entity se6 = new Wall(1144, 440, 44, 220);
		Entity se7 = new Wall(1144, 704, 44, 132);
		Entity se8 = new Wall(880, 616, 220, 44);
		Entity se9 = new Wall(968, 528, 132, 44);
		Entity se10 = new Wall(792, 704, 308, 44);
		
		
		
		walls.addAll(Arrays.asList(nw1, nw2, nw3, nw4, nw5, nw6, nw7, nw8, nw9, nw10,
				   sw1, sw2, sw3, sw4, sw5, sw6, sw7, sw8, sw9, sw10,
				   n1, n2, n3, n4, n5, n6, n7,
				   s1, s2, s3, s4, s5, s6, s7, s8,
				   ne1, ne2, ne3, ne4, ne5, ne6, ne7, ne8, ne9, ne10,
				   se1, se2, se3, se4, se5, se6, se7, se8, se9, se10));
		
		Entity poweredDot1 = new PoweredDot(56, 56, 20, poweredDotRoute);
		Entity poweredDot2 = new PoweredDot(1090, 56, 20, poweredDotRoute);
		Entity poweredDot3 = new PoweredDot(56, 758, 20, poweredDotRoute);
		Entity poweredDot4 = new PoweredDot(1100, 758, 20, poweredDotRoute);
		
		Entity potion1 = new PoweredDot(308, 308, 30, potionTypeARoute);
		Entity potion2 = new PoweredDot(856, 330, 30, potionTypeBRoute);
		
		Entity fruit1 = new FruitTypeA(572, 571, 50, fruitTypeARoute);
	
		
		components.addAll(Arrays.asList(poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit1));	
	
	
		//cantidad de celdas disponibles = cantidad de celdas totales - cantidad de celdas ocupadas por otros componentes / personajes
		
		
		// agrego los dots en todo el mapa
				for (int i = 1; i < 26; i++) 
					
					for (int j = 1; j < 19; j++)  {
						
					
						Entity regularDot = new RegularDot(i*44, j* 44, 10, regularDotRoute);
						components.add(regularDot);

					}
				
				
				Entity ZonaSinDots = new Wall(396, 132, 396, 220);
				Rectangle2D sDots = ZonaSinDots.getRectangle();
				
				// elimino los que colisionan con la pared
				Iterator<Entity> i1 = components.iterator();
				Entity e1 = i1.next();
				
				while (i1.hasNext()) {
					
					Rectangle2D r1 = e1.getRectangle();

					for (Entity w: walls) {
							
						Rectangle2D r2 = w.getRectangle();
						if (r1.intersects(r2) || r1.intersects(sDots)) {
							i1.remove();
							break;
						}	
						
					}
						
					e1 = i1.next();
				}
			
	
		Entity enemy1 = new EnemyTypeA(484, 220, "/assets/EnemyTypeA.gif");
		Entity enemy2 = new EnemyTypeB(528, 220, "/assets/EnemyTypeB.gif");
		Entity enemy3 = new EnemyTypeC(616, 220, "/assets/EnemyTypeC.gif");
		Entity enemy4 = new EnemyTypeD(660, 220, "/assets/EnemyTypeD.gif");
		
		enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
	
		
		b.createBackground("/assets/background2.png");
	    b.createEnemies(enemies);
	    b.createWalls(walls);
	    b.createComponents(components);
	    b.createPlayer(player);
	}
	
	
	public void constructLevelThree(Builder b) {
		
		List<Entity> enemies = new ArrayList<Entity>(4);
		List<Entity> components = new ArrayList<Entity>(200);
		List<Entity> walls = new ArrayList<Entity>();
		
		
		Entity player = new MainCharacter(572, 440, "/assets/mario1.gif");
		
		int y = 44; // Cuando se acomode la imagen sacar o ponerlo en 0
		Entity nw1 = new Wall(0, y + 0, 572, 44);
		Entity nw2 = new Wall(0, y + 44, 44, 132);
		Entity nw3 = new Wall(0, y + 220, 44, 308);
		Entity nw4 = new Wall(88, y + 44, 44, 132);
		Entity nw5 = new Wall(176, y + 88, 44, 88);
		Entity nw6 = new Wall(220, y + 88, 132, 44);
		Entity nw7 = new Wall(88, y + 220, 264, 44);
		Entity nw8 = new Wall(264, y + 176, 88, 44);
		Entity nw9 = new Wall(88, y + 308, 44, 220);
		Entity nw10 = new Wall(176, y + 308, 220, 44);
		Entity nw11 = new Wall(176, y + 396, 44, 44);
		Entity nw12 = new Wall(264, y + 396, 132, 44);
		
		Entity sw1 = new Wall(0, y + 704, 572, 44);
		Entity sw2 = new Wall(176, y + 484, 264, 44);
		Entity sw3 = new Wall(220, y + 528, 264, 44);
		Entity sw4 = new Wall(0, y + 572, 44, 132);
		Entity sw5 = new Wall(88, y + 572, 88, 44);
		Entity sw6 = new Wall(88, y + 616, 352, 44);
		
		Entity n1 = new Wall(396, y + 88, 176, 44);
		Entity n2 = new Wall(616, y + 88, 176, 44);
		Entity n3 = new Wall(396, y + 176, 396, 44);
		Entity n4 = new Wall(396, y + 264, 176, 44);
		Entity n5 = new Wall(616, y + 264, 176, 44);
		Entity n6 = new Wall(440, y + 308, 44, 88); 
		Entity n7 = new Wall(704, y + 308, 44, 88);
		Entity n8 = new Wall(484, y + 352, 220, 44);
		
		Entity s1 = new Wall(484, y + 440, 44, 176);
		Entity s2 = new Wall(572, y + 440, 44, 220);
		Entity s3 = new Wall(660, y + 440, 44, 176);
		Entity s4 = new Wall(484, y + 660, 44, 44);
		Entity s5 = new Wall(660, y + 660, 44, 44);
		
		Entity ne1 = new Wall(572, y + 0, 616, 44);
		Entity ne2 = new Wall(836, y + 88, 176, 44);
		Entity ne3 = new Wall(968, y + 132, 44, 44);
		Entity ne4 = new Wall(1056, y + 44, 44, 132	);
		Entity ne5 = new Wall(1144, y + 44, 44, 132	);
		Entity ne6 = new Wall(1144, y + 220, 44, 308);
		Entity ne7 = new Wall(836, y + 176, 88, 44);
		Entity ne8 = new Wall(836, y + 220, 264, 44);
		Entity ne9 = new Wall(1056, y + 308, 44, 220);
		Entity ne10 = new Wall(792, y + 308, 220, 44);
		Entity ne11 = new Wall(792, y + 396, 132, 44);
		Entity ne12 = new Wall(968, y + 396, 44, 44);
		
		Entity se1 = new Wall(572, y + 704, 616, 44);
		Entity se2 = new Wall(704, y + 528, 264, 44);
		Entity se3 = new Wall(748, y + 484, 264, 44);
		Entity se4 = new Wall(748, y + 616, 352, 44);
		Entity se5 = new Wall(1012, y + 572, 88, 44);
		Entity se6 = new Wall(1144, y + 572, 44, 132);
		
		
		
		walls.addAll(Arrays.asList(nw1, nw2, nw3, nw4, nw5, nw6, nw7, nw8, nw9, nw10, nw11, nw12,
				   sw1, sw2, sw3, sw4, sw5, sw6,
				   n1, n2, n3, n4, n5, n6, n7, n8,
				   s1, s2, s3, s4, s5,// s6, s7, s8,
				   ne1, ne2, ne3, ne4, ne5, ne6, ne7, ne8, ne9, ne10, ne11, ne12,
				   se1, se2, se3, se4, se5, se6
				   ));
		
		
		Entity poweredDot1 = new PoweredDot(56, 88, 20, poweredDotRoute);
		Entity poweredDot2 = new PoweredDot(1090, 88, 20, poweredDotRoute);
		Entity poweredDot3 = new PoweredDot(56, 703, 20, poweredDotRoute);
		Entity poweredDot4 = new PoweredDot(1090, 703, 20, poweredDotRoute);
		
		Entity potion1 = new PoweredDot(308, 308, 30, potionTypeARoute);
		Entity potion2 = new PoweredDot(856, 330, 30, potionTypeBRoute);
		
		Entity fruit1 = new FruitTypeA(572, 703, 50, fruitTypeARoute);
	
		
		components.addAll(Arrays.asList(poweredDot1, poweredDot2, poweredDot3, poweredDot4, potion1, potion2, fruit1));	
	
	
		//cantidad de celdas disponibles = cantidad de celdas totales - cantidad de celdas ocupadas por otros componentes / personajes
		
		
		// agrego los dots en todo el mapa
				for (int i = 1; i < 26; i++) 
					
					for (int j = 1; j < 19; j++)  {
						
					
						Entity regularDot = new RegularDot(i*44, j* 44, 10, regularDotRoute);
						components.add(regularDot);

					}
				
				
				Entity ZonaSinDots = new Wall(396, 264, 396, 220);
				Rectangle2D sDots = ZonaSinDots.getRectangle();
				
				// elimino los que colisionan con la pared
				Iterator<Entity> i1 = components.iterator();
				Entity e1 = i1.next();
				
				while (i1.hasNext()) {
					
					Rectangle2D r1 = e1.getRectangle();

					for (Entity w: walls) {
							
						Rectangle2D r2 = w.getRectangle();
						if (r1.intersects(r2) || r1.intersects(sDots)) {
							i1.remove();
							break;
						}	
						
					}
						
					e1 = i1.next();
				}
		
	
		Entity enemy1 = new EnemyTypeA(484, 352, "/assets/EnemyTypeA.gif");
		Entity enemy2 = new EnemyTypeB(528, 352, "/assets/EnemyTypeB.gif");
		Entity enemy3 = new EnemyTypeC(616, 352, "/assets/EnemyTypeC.gif");
		Entity enemy4 = new EnemyTypeD(660, 352, "/assets/EnemyTypeD.gif");
		
		enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
	
		
		b.createBackground("/assets/background3.png");
	    b.createEnemies(enemies);
	    b.createWalls(walls);
	    b.createComponents(components);
	    b.createPlayer(player);
		
	}




}
