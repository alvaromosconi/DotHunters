package logic;

import java.util.Random;

public enum Direction {
	
	UP (0, -1),
	DOWN (0, 1),
	RIGHT (1, 0),
	LEFT (-1, 0),
	STILL (0, 0);

    static Random rnd = new Random();
    
    private int xVelocity,
                yVelocity;

    Direction(int i, int j) {
    	
    	xVelocity = i;
    	yVelocity = j;
    }
    
    public int getXVelocity() {
    	return xVelocity;
    }
    
    public int getYVelocity() {
    	return yVelocity;
    }
    
//    public static void changeSpeed(int speed) {
//    	UP.yVelocity = -speed;
//    	DOWN.yVelocity = speed;
//    	LEFT.xVelocity = -speed;
//    	RIGHT.xVelocity = speed;
//    }
    
}
    