package entities;
import visitors.Visitor;
import visitors.VisitorWall;

public class Wall extends Entity {

	public Wall(int xValue, int yValue, int width, int height) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.width = width;
		this.height = height;
		
		visitor = new VisitorWall(this);
	}

	
	@Override
	public void accept(Visitor v) {
		
		v.visitWall(this);
	}
	
	public boolean inside(int x, int y) {
		
		return ( (x >= xValue && x <= xValue + width) ||
			     (y >= yValue && y <= yValue + height));

	}
	



}
