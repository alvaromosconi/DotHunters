package visitors;

import entities.Entity;
import entities.MainCharacter;
import entities.Wall;

public class VisitorMainCharacter implements Visitor {

	private Entity player;
	
	public VisitorMainCharacter(MainCharacter player) {
		
		this.player = player;
	}
	
	@Override
	public void visitWall(Wall w) {
		
		player.blockMove();
	}

	@Override
	public void visitMainCharacter(MainCharacter m) {
		// TODO Auto-generated method stub
		
	}

}
