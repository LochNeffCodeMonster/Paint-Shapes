import java.awt.Color;
import java.awt.Graphics;

/**
 * The class Line is a class used to save the following attributes needed to draw a Line in the graphical user interface. 
 * @author JonathanNeff
 *
 */
public class Line extends Shape {
	
	/**
	 * Attributes:
	 * type    a String indicating what type of Shape a Line object is. 
	 * endX    x-coordinate of end point
	 * endY    y-coordinate of end point 
	 */
	private static String type = "L";
	private int endX, endY;
	
	/**
	 * Class constructor Line generates an instance of a Line, designated by two points in the xy plane of the JPanel drawingPanel, and
	 * 		a designated Color.
	 * @param sx    x-coordinate of start point
	 * @param sy    y-coordinate of start point
	 * @param ex    x-coordinate of end point
	 * @param ey    y-coordinate of end point
	 * @param color     Color of Line object
	 */
	public Line(int sx, int sy, int ex, int ey, String color) {
		super(sx, sy, color);
		endX = ex;
		endY = ey;
	}
	
	/**
	 * Class overload constructor Line generates an instance of a Line, designated by two points in the xy plane of the JPanel drawingPanel,
	 * 		an no designated Color. 
	 * @param sx    x-coordinate of start point
	 * @param sy    y-coordinate of start point
	 * @param ex    x-coordinate of end point
	 * @param ey    y-coordinate of end point
	 */
	public Line(int sx, int sy, int ex, int ey) {
		super(sx,sy);
		endX = ex;
		endY = ey;
	}
	
	/**
	 * The draw method draws an instance of the Line, in the graphical user interface with the designated Color. 
	 */
	public void draw(Graphics g1) {
		g1.setColor(this.myColor);
		g1.drawLine(this.getStartX(), this.getStartY(), endX, endY);
	}
	
	/**
	 * The method getEndX returns the x-coordinate of the end point 
	 * @return endX    x-coordinate of end point 
	 */
	public int getEndX() {
		return endX;
	}
	
	/**
	 * The method getEndY returns the y-coordinate of the end point
	 * @return endY    y-coordinate of end point
	 */
	public int getEndY() {
		return endY;
	}
	
	/**
	 * The method getType returns a String identifying the Shape object, in this case a Line. 
	 */
	public String getType() {
		return type;
	}

	/**
	 * The method toString returns a formatted String used for saving a Line's attributes to a text file,
	 * 		that can be reopened at a later time. 
	 */
	@Override
	public String toString() {
		return type + "," + this.getStartX() + "," + this.getStartY() + "," + endX + "," + endY + "," + this.writeColor();
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}
