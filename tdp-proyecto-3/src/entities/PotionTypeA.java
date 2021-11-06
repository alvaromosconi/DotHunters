package entities;

import visitors.Visitor;
import visitors.VisitorPotionTypeA;
import visitors.VisitorPoweredDot;

public class PotionTypeA extends Potion {

	public PotionTypeA(int xValue, int yValue,int v, String imageRoute) {
		
		this.xValue = xValue;
		
		this.yValue = yValue;
		
		this.imageRoute = imageRoute;
		
		this.value = v;
		
		visitor = new VisitorPotionTypeA(this);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitPotionTypeA(this);
	}

}