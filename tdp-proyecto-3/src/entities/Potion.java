package entities;

import logic.Game;

public abstract class Potion extends Component {

	public Potion(Game game) {
		super(game);
		this.width = 24;
		this.height = 24;
	}
}
