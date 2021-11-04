package entities;
import visitors.Visitor;
import visitors.VisitorWall;

public class Wall extends Entity {

	private int width;
	private int heigth;

	public Wall(int xValue, int yValue, int width, int heigth) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.width = width;
		this.heigth = heigth;
		
		visitor = new VisitorWall(this);
	}

	public int getWidth() {
		
		return width;
	}
	
	public int getHeight() {
		
		return heigth;
	}
	
	@Override
	public void accept(Visitor v) {
		
		v.visitWall(this);
	}
	
	public boolean inside(int x, int y) {
		
		return ( (x >= xValue && x <= xValue + width) &&
			     (y >= yValue && y <= yValue + height));

	}
	



}
