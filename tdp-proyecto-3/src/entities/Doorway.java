package entities;

import visitors.Visitor;
import visitors.VisitorDoorway;

public class Doorway extends Entity {

	public Doorway(int xValue, int yValue) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		width = 36;
		height = 36;
		visitor = new VisitorDoorway(this);
	}
	
	
	@Override
	public void accept(Visitor v) {
		v.visitDoorway(this);
		
	}

}
