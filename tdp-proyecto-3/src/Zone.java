import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Zone {

	
	
	private List<Entity> myEntities;
	private Point upperLeftVertex;
	private Point upperRightVertex;
	private Point bottomLeftVertex;
	private Point bottomRightVertex;
	
	public Zone(Point upperLeftVertex, Point upperRightVertex, Point bottomLeftVertex, Point bottomRightVertex) {
		
		myEntities = new ArrayList<Entity>();
		
		this.upperLeftVertex = upperLeftVertex;
		this.upperRightVertex = upperRightVertex;
		this.bottomLeftVertex = bottomLeftVertex;
		this.bottomRightVertex = bottomRightVertex;
		
	}
	
	public void addEntity(Entity entity) {
		
		myEntities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		
		myEntities.remove(entity);
	}
	
	public Point getUpperLeftVertex() {
		
		return upperLeftVertex;
	}
	
	public Point getUpperRightVertex() {
		
		return upperRightVertex;
	}
	
	public Point getBottomLeftVertex() {
		
		return bottomLeftVertex;
	}
	
	public Point getBottomRightVertex() {
		
		return bottomRightVertex;
	}
	
	
	
	
	
}
