package entities;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import logic.Game;
import logic.Direction;
import visitors.Visitor;

public abstract class Entity {

	protected Game game;
	
	protected int width;
	protected int height;
	protected int xValue;
	protected int yValue;

	protected Visitor visitor;
	protected String imageRoute;
	protected Map<Direction, String> sprites;
	
	public Entity(int xValue, int yValue, String imageRoute, Game game) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
		this.game = game;
		
	}
	    
	
	public void setXValue(int xValue) {
		
		this.xValue = xValue;
	}
	
	public void setYValue(int yValue) {
		
		this.yValue = yValue;
	}
	
	public void setVisitor(Visitor visitor) {
		
		this.visitor = visitor;
	}
	
	public int getXValue() {
		
		return xValue;
	}
	
	public int getYValue() {
		
		return yValue;
	}
	
	public void setWidth(int width) {
		
		this.width = width;
	}
	
	public void setHeight(int height) {
		
		this.height = height;
	}
	
	public String getImageRoute() {
		
		return imageRoute;
	}
	
	public Visitor getVisitor() {
		
		return visitor;
	}
	
	public abstract void accept(Visitor v);


	public int getWidth() {
			
		return width;	
	}

	public int getHeight() {
		
		return height;	
	}
	
	public Rectangle getRectangle() {
		
		return new Rectangle(xValue, yValue, width, height);
	}

	public Game getGame() {
		return game;
	}
}
