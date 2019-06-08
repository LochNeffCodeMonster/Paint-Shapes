import java.awt.Color;
import java.awt.Graphics;

/**
 * The Rectangle class is a class used to save the following attributes needed to draw a Rectangle in the graphic user interface (GUI). 
 * @author JonathanNeff
 *
 */
public class Rectangle extends Shape {
	
	/**
	 * Attributes:
	 * type    a String indicating what type of Shape a Line object is
	 * width    width of the Rectangle
	 * height    height of the Rectangle 
	 */
	private static String type = "R";
	private int width, height;
	
	/**
	 * The class constructor Rectangle generates an instance of a Rectangle, designated by a point in the xy plane of the JPanel drawingPanel,
	 * 		and the width, height and designated Color. 
	 * @param sx    x-coordinate of start point
	 * @param sy    y-coordinate of start point
	 * @param w    width of Rectangle
	 * @param h    height of Rectangle
	 * @param color    Color of Rectangle object
	 */
	public Rectangle(int sx, int sy, int w, int h, String color) {
		super(sx, sy, color);
		width = w; 
		height = h;
	}
	
	/**
	 * The class overload constructor Rectangle generates an instance of a Rectangle, designated by a point in the xy plane of the JPanel drawingPanel,
	 * 		and the width and height. 
	 * @param sx    x-coordinate of start point
	 * @param sy    y-coordinate of start point
	 * @param w    width of Rectangle
	 * @param h    height of Rectangle
	 */
	public Rectangle(int sx, int sy, int w, int h) {
		super(sx, sy);
		width = w;
		height = h;
	}
	
	/**
	 * The draw method draws an instance of the Rectangle, in the GUI with the designated Color.  
	 */
	public void draw(Graphics g1) {
		g1.setColor(this.myColor);
		g1.drawRect(this.getStartX(), this.getStartY(), width, height);
	}
	
	/**
	 * The getWidth method returns the width of a Rectangle object. 
	 * @return width    width of Rectangle object.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * The getHeight method returns the height of a Rectangle object. 
	 * @return height    height of Rectangle object. 
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * The getType method returns a String identifying the Shape object, in this case a Rectangle. 
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * The toString method returns a formatted String used for saving a Rectangle's attributes to a text file,
	 * 		that can be reopened at a later time.
	 */
	@Override
	public String toString() {
		return type + "," + this.getStartX() + "," + this.getStartY() + "," + width + "," + height + "," + this.writeColor();
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
