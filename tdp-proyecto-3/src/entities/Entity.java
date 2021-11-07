package entities;
import java.awt.Point;
import java.awt.Rectangle;

import visitors.Visitor;

public abstract class Entity {

	protected int xValue;
	protected int yValue;
	protected Visitor visitor;
	protected String imageRoute;
	protected int xVelocity;
	protected int yVelocity;
	protected int width;
	protected int height;
	
	public void setXValue(int xValue) {
		
		this.xValue = xValue;
	}
	
	public void setYValue(int yValue) {
		
		this.yValue = yValue;
	}
	
	public void setVisitor(Visitor visitor) {
		
		this.visitor = visitor;
	}
	
	public void setXVelocity(int xVelocity) {
		
		this.xVelocity = xVelocity;
	}
	
	public void setYVelocity(int yVelocity) {
		
		this.yVelocity = yVelocity;
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
	
	public int getXVelocity() {
		
		return yVelocity;
	}
	
	public int getYVelocity() {
		
		return xVelocity;
	}
	
	public void move() {
		
		xValue += xVelocity;
		yValue += yVelocity;
	}

	public Rectangle getOffsetBounds() {
	    return new Rectangle(xValue + xVelocity, yValue + yVelocity, 21, 36);
	}	
	
	public Point getCenterOfRectangle() {
		
		int centerX;
		int centerY;
		
		centerX = (int) (xValue + width) / 2;
		centerY = (int) (yValue + height) / 2;
		
	    
	    return new Point(centerX, centerY);
		
	}
	
	public Rectangle getRectangle() {
		
		return new Rectangle(xValue, yValue, width, height);
	}
	
	
}
