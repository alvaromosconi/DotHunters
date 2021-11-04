package entities;

import visitors.Visitor;
import visitors.VisitorPoweredDot;

public class PoweredDot extends Dot {
	
	public PoweredDot(int xValue, int yValue, int v, String imageRoute) {
		
		this.xValue = xValue;
		
		this.yValue = yValue;
		
		this.imageRoute = imageRoute;
		
		this.value = v;
		
		visitor = new VisitorPoweredDot(this);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitPoweredDot(this);
	}

}
