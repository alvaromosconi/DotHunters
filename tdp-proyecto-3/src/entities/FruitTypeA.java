package entities;

import logic.Game;
import visitors.Visitor;
import visitors.VisitorFruitTypeA;

public class FruitTypeA extends Fruit {

	public FruitTypeA(int xValue, int yValue,int v, String imageRoute, Game game) {
	
		super(xValue, yValue, imageRoute, game);
		
		this.value = v;
		
		visitor = new VisitorFruitTypeA(this);
	}

	@Override
	public void accept(Visitor v) {
		v.visitFruitTypeA(this);
	}

}