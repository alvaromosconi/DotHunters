package entities;

import logic.Game;
import visitors.Visitor;
import visitors.VisitorActivePotionTypeA;
import visitors.VisitorActivePotionTypeB;
import visitors.VisitorPotionTypeA;

public class ActivePotionTypeB extends Entity {

	public ActivePotionTypeB(int xValue, int yValue, String imageRoute, Game game) {
	
		super(xValue, yValue, imageRoute, game);
		
		this.width = 36;
		this.height = 36;
		
		visitor = new VisitorActivePotionTypeB(this);
	}
	
	
	
	@Override
	public void accept(Visitor v) {
		v.visitActivePotionTypeB(this);
	}



	public void setImageRoute(String string) {
		
		this.imageRoute = string;
		
	}

}