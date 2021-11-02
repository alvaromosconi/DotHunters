package logic;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;

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
	
	public List<Entity> getEntities() {
		
		return myEntities;
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
	
	public Point getCenterOfRectangle() {
		
		int centerX;
		int centerY;
		
		centerX = (int) (upperRightVertex.getX() + upperLeftVertex.getX()) / 2;
		centerY = (int) (upperRightVertex.getY() + bottomRightVertex.getY()) / 2;
		
	    
	    return new Point(centerX, centerY);
		
	}
	
	public int getWidthOfZone() {
		
		return  (int) (upperRightVertex.getX() - upperLeftVertex.getX());
	}
	
	public int getHeightOfZone() {
		
		return 	(int) (bottomLeftVertex.getY() - upperRightVertex.getY());
	}
	
	public boolean inside(int x, int y) {
		
		return ( (x >= upperLeftVertex.getX() && x <= upperRightVertex.getX()) &&
			     (y >= upperLeftVertex.getY() && y <= bottomLeftVertex.getY()));

	}
	
	
	
	
	
	
	
}
