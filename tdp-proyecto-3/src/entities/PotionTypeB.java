package entities;

import logic.Game;
import visitors.Visitor;
import visitors.VisitorPotionTypeB;

public class PotionTypeB extends Potion {

	public PotionTypeB(int xValue, int yValue, String imageRoute, Game game) {
		
		super(xValue, yValue, imageRoute, game);
		
		
		visitor = new VisitorPotionTypeB(this);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitPotionTypeB(this);
	}

}
