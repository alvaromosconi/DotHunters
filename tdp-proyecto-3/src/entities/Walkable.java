package entities;

import visitors.Visitor;

public class Walkable extends Entity {

	public Walkable(int xValue, int yValue) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		width = 36;
		height = 36;
	}
	
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
