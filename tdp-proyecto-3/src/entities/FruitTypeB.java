package entities;

import logic.Game;
import visitors.*;

public class FruitTypeB extends Fruit {

	public FruitTypeB(int xValue, int yValue,int v, String imageRoute, Game game) {
		
		super(xValue, yValue, imageRoute, game);
		
		this.value = v;
		
		visitor = new VisitorFruitTypeB(this);
	}

	@Override
	public void accept(Visitor v) {
		v.visitFruitTypeB(this);
	}

}