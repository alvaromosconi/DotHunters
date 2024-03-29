package entities;

import logic.Game;
import visitors.Visitor;
import visitors.VisitorPoweredDot;

public class PoweredDot extends Dot {
	
	public PoweredDot(int xValue, int yValue, int v, String imageRoute, Game game) {
		
		super(xValue, yValue, imageRoute, game);
		
		this.value = v;
		
		this.width = 24;
		this.height = 24;
		
		visitor = new VisitorPoweredDot(this);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitPoweredDot(this);
	}

}
