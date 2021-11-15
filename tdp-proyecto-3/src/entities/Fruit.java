package entities;

import logic.Game;

public abstract class Fruit extends Component {
	
	public Fruit(int xValue, int yValue, String imageRoute, Game game) {
		super(game);
		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
		this.width = 36;
		this.height = 36;
		
	}
	
}
