package entities;

import visitors.Visitor;
import visitors.VisitorPotionTypeB;

public class PotionTypeB extends Potion {

	public PotionTypeB(int xValue, int yValue,int v, String imageRoute) {
		
		this.xValue = xValue;
		
		this.yValue = yValue;
		
		this.imageRoute = imageRoute;
		
		this.value = v;
		
		visitor = new VisitorPotionTypeB(this);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitPotionTypeB(this);
	}

}
