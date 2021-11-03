package entities;

import visitors.*;

public class FruitTypeC extends Fruit {

	public FruitTypeC(int xValue, int yValue,int v, String imageRoute) {
		super(xValue, yValue, imageRoute);
		
		this.value = v;
		
		visitor = new VisitorFruitTypeC(this);
	}

	@Override
	public void accept(Visitor v) {
		v.visitFruitTypeC(this);
	}

}
