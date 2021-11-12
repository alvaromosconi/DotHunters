package entities;

import enemies.ChaseBehaviour;
import enemies.FrightenedBehaviour;
import enemies.ScatterBehaviour;

public abstract class Enemy extends Entity implements ChaseBehaviour, ScatterBehaviour, FrightenedBehaviour {
	
	public Enemy() {
		this.width = 36;
		this.height = 36;
	}
	
	
	public int necessaryVerticalMovements(int y) {

        int difference = y - this.yValue;

        return difference / 36;

    }

    public int necessaryHorizontalMovements(int x) {

        int difference = x - this.xValue;

        return difference / 36;
    }
	
}
