package entities;
import java.awt.Rectangle;
import java.util.Map;
import logic.Game;
import logic.Direction;
import visitors.Visitor;

public abstract class Entity {

	protected Game game;
	
	protected int width;
	protected int height;
	protected int xValue;
	protected int yValue;

	protected Visitor visitor;
	protected String imageRoute;
	protected Map<Direction, String> sprites;
	
	public Entity(int xValue, int yValue, String imageRoute, Game game) {
		
		this.xValue = xValue;
		this.yValue = yValue;
		this.imageRoute = imageRoute;
		this.game = game;
		
	}
	   
	/*
	 * Establece el nuevo valor de x
	 * @param xValue nuevo valor de xValue
	 */
	public void setXValue(int xValue) {
		
		this.xValue = xValue;
	}
	
	/*
	 * Establece el nuevo valor de y
	 * @param xValue nuevo valor de yValue
	 */
	public void setYValue(int yValue) {
		
		this.yValue = yValue;
	}
	
	/*
	 * Establece el visitor de la entidad
	 * @param visitor nuevo visitor
	 */
	public void setVisitor(Visitor visitor) {
		
		this.visitor = visitor;
	}
	
	/*
	 * @return xValue valor de x de la entidad
	 */
	public int getXValue() {
		
		return xValue;
	}
	
	/*
	 * @return yValue valor de y de la entidad
	 */
	public int getYValue() {
		
		return yValue;
	}
	
	/*
	 * @return imageRoute ruta de la imagen por defecto de la entidad
	 */
	public String getImageRoute() {
		
		return imageRoute;
	}
	
	/*
	 * @return visitor de la entidad
	 */
	public Visitor getVisitor() {
		
		return visitor;
	}
	
	/*  metodo para realizar interacciones entre entidades
	 * @param visitor de la otra entidad
	 */
	public abstract void accept(Visitor v);

	/*
	 * @return width ancho de la entidad
	 */
	public int getWidth() {
			
		return width;	
	}

	/*
	 * @return height alto de la entidad
	 */
	public int getHeight() {
		
		return height;	
	}
	
	/*
	 * @return un rectangulo con los valores de la entidad
	 */
	public Rectangle getRectangle() {
		
		return new Rectangle(xValue, yValue, width, height);
	}

	/*
	 * @return juego actual
	 */
	public Game getGame() {
		return game;
	}
}
