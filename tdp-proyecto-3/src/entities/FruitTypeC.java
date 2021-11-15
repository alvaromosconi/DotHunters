package entities;

import logic.Game;
import visitors.*;

public class FruitTypeC extends Fruit {

	public FruitTypeC(int xValue, int yValue,int v, String imageRoute, Game game) {
		super(xValue, yValue, imageRoute, game);
		
		this.value = v;
		
		visitor = new VisitorFruitTypeC(this);
	}

	@Override
	public void accept(Visitor v) {
		v.visitFruitTypeC(this);
	}

}
