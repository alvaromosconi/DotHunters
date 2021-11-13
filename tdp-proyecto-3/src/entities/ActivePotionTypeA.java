package entities;

import visitors.Visitor;
import visitors.VisitorActivePotionTypeA;
import visitors.VisitorPotionTypeA;

public class ActivePotionTypeA extends Entity {

	public ActivePotionTypeA(int xValue, int yValue, String imageRoute) {
		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
		
		this.width = 36;
		this.height = 36;
		
		visitor = new VisitorActivePotionTypeA(this);
	}
	
	
	
	@Override
	public void accept(Visitor v) {
		v.visitActivePotionTypeA(this);
	}

}
