import Visitors.Visitor;

public class MainCharacter extends Character {

	public MainCharacter(int xValue, int yValue, int direction, String imageRoute) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.direction = direction;
		myGraphicEntity = new GraphicEntity(this, xValue, yValue, imageRoute);
		
	}
	
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
