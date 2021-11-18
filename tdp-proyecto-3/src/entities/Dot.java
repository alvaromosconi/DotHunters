package entities;

import logic.Game;

public abstract class Dot extends Component {

	public Dot(int xValue, int yValue, String imageRoute, Game game) {
		
		super(xValue, yValue, imageRoute, game);
	}
	
	

}
