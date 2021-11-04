package entities;
import visitors.Visitor;
import visitors.VisitorRegularDot;

public class RegularDot extends Dot {

	public RegularDot(int xValue, int yValue,int v, String imageRoute) {
		
		this.xValue = xValue;
		
		this.yValue = yValue;
		
		this.imageRoute = imageRoute;
		
		this.value = v;
	
		
		visitor = new VisitorRegularDot(this);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitRegulardDot(this);
	}

}
