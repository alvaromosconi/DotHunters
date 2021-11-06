package entities;

public abstract class Component extends Entity {
	
	public Component() {
		
		this.width = 12;
		this.height = 12;
	}
	
	protected int value;
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		value = v;
	}
}
