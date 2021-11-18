package entities;

import logic.Game;

public abstract class Potion extends Component {

	public Potion(int xValue, int yValue, String imageRoute, Game game) {
		
		super(xValue, yValue, imageRoute, game);
		this.width = 24;
		this.height = 24;
	}
}
