package entities;

import logic.Game;
import visitors.Visitor;
import visitors.VisitorPotionTypeA;
import visitors.VisitorPoweredDot;

public class PotionTypeA extends Potion {

	public PotionTypeA(int xValue, int yValue, String imageRoute, Game game) {
		
		super(xValue, yValue, imageRoute, game);

		
		visitor = new VisitorPotionTypeA(this);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitPotionTypeA(this);
	}

}
