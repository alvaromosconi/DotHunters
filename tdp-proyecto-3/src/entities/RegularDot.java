package entities;
import logic.Game;
import visitors.Visitor;
import visitors.VisitorRegularDot;

public class RegularDot extends Dot {

	public RegularDot(int xValue, int yValue,int v, String imageRoute, Game game) {
		
		super(game);
		this.xValue = xValue;
		
		this.yValue = yValue;
		
		this.imageRoute = imageRoute;
		
		this.value = v;
		
		this.width = 12;
		this.height = 12;
	
		
		visitor = new VisitorRegularDot(this);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitRegulardDot(this);
	}

}
