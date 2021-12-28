package entities;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorRegularDot;

public class RegularDot extends Dot {

	public RegularDot(int xValue, int yValue,int v, String imageRoute, Game game) {
		
		super(xValue, yValue, imageRoute, game);
		
		this.value = v;
		
		this.width = 18;
		this.height = 18;
	
		
		visitor = new VisitorRegularDot(this);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitRegulardDot(this);
	}

}
