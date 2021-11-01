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
	
	public int getHeigth() {
		
		return heigth;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitWall(this);
		
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}

}
