package entities;

import enemies.ChaseBehaviour;
import enemies.FrightenedBehaviour;
import enemies.ScatterBehaviour;

public abstract class Enemy extends Entity implements ChaseBehaviour, ScatterBehaviour, FrightenedBehaviour {
	public Enemy() {
		this.width = 36;
		this.height = 36;
	}
	
}
