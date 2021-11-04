package entities;

public abstract class Component extends Entity {
	
	public Component() {
		
		this.width = 44;
		this.height = 44;
	}
	
	protected int value;
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		value = v;
	}
}
