package entities;

public abstract class Component extends Entity {
	protected int value;
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		value = v;
	}
}
