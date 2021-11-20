package entities;

import logic.Game;

public abstract class Potion extends Entity {

	public Potion(int xValue, int yValue, String imageRoute, Game game) {
		
		super(xValue, yValue, imageRoute, game);
		this.width = 36;
		this.height = 36;
	}
}
