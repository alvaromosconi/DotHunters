package logic;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;

import java.awt.Point;
import java.awt.Rectangle;

public class Zone {

	private List<Entity> myEntities;
	private List<Entity> myWalls;
	private Point upperLeftVertex;
	private Point upperRightVertex;
	private Point bottomLeftVertex;
	private Point bottomRightVertex;
	private List<Entity> myDoorWays;
	
	public Zone(Point upperLeftVertex, Point upperRightVertex, Point bottomLeftVertex, Point bottomRightVertex) {
		
		myEntities = new ArrayList<Entity>();
		myWalls = new ArrayList<Entity>();
		myDoorWays = new ArrayList<Entity>();
		this.upperLeftVertex = upperLeftVertex;
		this.upperRightVertex = upperRightVertex;
		this.bottomLeftVertex = bottomLeftVertex;
		this.bottomRightVertex = bottomRightVertex;
		
	}
	
	public List<Entity> getEntities() {
		
		return myEntities;
	}
	
	public List<Entity> getWalls() {
		
		return myWalls;
	}
	
	public void addWall(Entity entity) {
		
		myWalls.add(entity);
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
	
	public Zone getCurrentZone(int x, int y) {
		
		Zone toReturn = null;
		
		if ( (x >= upperLeftVertex.getX() && x <= upperRightVertex.getX()) &&
			     (y >= upperLeftVertex.getY() && y <= bottomLeftVertex.getY()))
			toReturn = this;
		
		return toReturn;
	
	}
	
	public Rectangle getRectangle() {
		
		return new Rectangle((int) upperLeftVertex.getX(), (int) upperLeftVertex.getY(), getWidthOfZone(), getHeightOfZone());
	}

	public void addDoorWay(Entity entity) {
		
		myDoorWays.add(entity);
		
	}
	
	
	
	
	
	
	
	
}
