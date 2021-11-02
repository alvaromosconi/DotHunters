package entities;
import visitors.Visitor;

public abstract class Entity implements Movement {

	protected int xValue;
	protected int yValue;
	protected Visitor visitor;
	protected String imageRoute;
	protected boolean canMove;
	
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
	
	public String getImageRoute() {
		
		return imageRoute;
	}
	
	public Visitor getVisitor() {
		
		return visitor;
	}
	
	public abstract void accept(Visitor v);

	public void blockMove() {
		
		canMove = false;
	}

	public int getWidth() {
		return xValue; 
		
	}

	public int getHeight() {
		return xValue;
		
	}

	public void freeMove() {
		canMove = true;
		
	}
	
	
}
