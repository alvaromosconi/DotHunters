import java.net.URL;
import java.util.List;

public interface Builder {

	public void createEnemies(List<Entity> enemies);
	
	public void createComponents(List<Entity> components);
	
	public void createPlayer(Entity player);
	
	public void createBackground(String backgroundUrl);
	
	public Level getResult();
	
}
