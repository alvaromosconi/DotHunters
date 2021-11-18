package entities;

import logic.Game;

public abstract class Component extends Entity {
	
	protected int value;
	
	public Component (int xValue, int yValue, String imageRoute, Game game) {
		super(xValue, yValue, imageRoute, game);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		value = v;
	}
}
