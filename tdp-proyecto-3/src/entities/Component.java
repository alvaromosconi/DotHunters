package entities;

import logic.Game;

public abstract class Component extends Entity {
	
	public Component(Game game) {
		super(game);
	}

	protected int value;
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		value = v;
	}
}
