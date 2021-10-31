import Visitors.Visitor;

public abstract class Entity {

	protected int xValue;
	protected int yValue;
	protected Visitor visitor;
	protected GraphicEntity myGraphicEntity;
	
	public void setXValue(int xValue) {
		
		this.xValue = xValue;
	}
	
	public void setYValue(int yValue) {
		
		this.yValue = yValue;
	}
	
	public void setVisitor(Visitor visitor) {
		
		this.visitor = visitor;
	}
	
	public int getXValue() {
		
		return xValue;
	}
	
	public int getYValue() {
		
		return yValue;
	}
	
	public Visitor getVisitor() {
		
		return visitor;
	}
	
	public abstract void accept(Visitor v);

	public GraphicEntity getGraphicEntity() {
		
		return myGraphicEntity;
	}
	
}