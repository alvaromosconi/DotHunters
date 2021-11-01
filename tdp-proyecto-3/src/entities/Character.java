package entities;

public abstract class Character extends Entity {

	protected int direction;
	
	public void setDirection(int direction) {
		
		this.direction = direction;
	}
	
	public int getDirection() {
		
		return direction;
	}
}
