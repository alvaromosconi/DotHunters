import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Director {

public void constructLevelOne(Builder b) {
	
	List<Entity> enemies = new ArrayList<Entity>(4);
	List<Entity> components = new ArrayList<Entity>(200);
	List<Entity> walls = new ArrayList<Entity>(10);
	Entity player = new MainCharacter(400, 400, 1, "/assets/mario1.gif");
	
	for (int i = 0; i < components.size(); i++) {
		
		Component comp = new RegularDot();
		components.add(comp);
	}
	
	Entity enemy1 = new EnemyTypeA();
	Entity enemy2 = new EnemyTypeB();
	Entity enemy3 = new EnemyTypeC();
	Entity enemy4 = new EnemyTypeD();

	enemies.addAll(Arrays.asList(enemy1, enemy2, enemy3, enemy4));
	
	b.createBackground("/assets/background.png");
    b.createEnemies(enemies);
    b.createComponents(components);
    b.createPlayer(player);
	
}


}
