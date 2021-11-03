package entities;

import visitors.*;

public class FruitTypeB extends Fruit {

	public FruitTypeB(int xValue, int yValue,int v, String imageRoute) {
		super(xValue, yValue, imageRoute);
		
		this.value = v;
		
		visitor = new VisitorFruitTypeB(this);
	}

	@Override
	public void accept(Visitor v) {
		v.visitFruitTypeB(this);
	}

}