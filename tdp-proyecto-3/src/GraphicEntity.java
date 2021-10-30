
public class GraphicEntity {

	private String imageRoute;
	private int xValue;
	private int yValue;
	private Entity myEntity;
	
	public GraphicEntity(Entity entity, int xValue, int yValue, String imageRoute) {
		
		this.myEntity = entity;
		this.imageRoute = imageRoute;
		this.xValue = xValue;
		this.yValue = yValue;
	}
	
	public Entity getEntity() {
		
		return myEntity;
	}
	
	public int getXValue() {
		
		return xValue;
	}
	
	public int getYValue() {
		
		return yValue;
	}
	
	public String getImageRoute() {
		
		return imageRoute;
	}
	
}
