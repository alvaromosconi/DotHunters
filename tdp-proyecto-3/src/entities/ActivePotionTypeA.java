package entities;

import logic.Game;
import visitors.Visitor;
import visitors.VisitorActivePotionTypeA;
import visitors.VisitorPotionTypeA;

public class ActivePotionTypeA extends Entity {

	public ActivePotionTypeA(int xValue, int yValue, String imageRoute, Game game) {
	
		super(xValue, yValue, imageRoute, game);
		
		this.width = 36;
		this.height = 36;
		
		visitor = new VisitorActivePotionTypeA(this);
	}
	
	
	
	@Override
	public void accept(Visitor v) {
		v.visitActivePotionTypeA(this);
	}



	public void setImageRoute(String string) {
		
		this.imageRoute = string;
		
	}

}
