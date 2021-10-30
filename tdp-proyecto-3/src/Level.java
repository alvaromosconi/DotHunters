import java.awt.GraphicsConfiguration;
import java.net.URL;
import java.util.List;

public class Level {
	
	private List<Entity> enemies;
	private List<Entity> components;
	private Entity player;
	private String backgroundUrl; 

	public Level(List<Entity> enemies, List<Entity> components, Entity player, String backgroundUrl) {
		
		this.enemies = enemies;
		this.components = components;
		this.player = player;
		this.backgroundUrl = backgroundUrl;
	}

	public String getBackgroundUrl() {
		
		return backgroundUrl;
	}
	
	public Entity getPlayer() {
		
		return player;
	}

}
