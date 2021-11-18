package entities;

import logic.Game;

public abstract class Fruit extends Component {
	
	public Fruit(int xValue, int yValue, String imageRoute, Game game) {
		
		super(xValue, yValue, imageRoute, game);
		
	}
	
}
