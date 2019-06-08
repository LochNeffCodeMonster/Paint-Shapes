import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public abstract class Shape {
	
	/**
	 * Attributes:
	 * startX    x-coordinate of start point
	 * startY    y-coordinate of start point
	 * myColor    Color of Shape object
	 * colors    an Array of various Color objects 
	 */
	private int startX, startY;
	protected Color myColor;
	private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};
    
	/**
	 * The class constructor Shape generates an instance of Shape, containing a start point and designated Color. 
	 * @param sx    x-coordinate of start point
	 * @param sy    y-coordinate of start point
	 * @param color    Color of Shape object
	 */
	public Shape(int sx, int sy, String color) {
		startX = sx;
		startY = sy;
		myColor = translateColor(color);
	}
	
	/**
	 * The class overload constructor Shape generates an instance of Shape, containing a start point and generating a Color 
	 * 		for the Shape object. 
	 * @param sx    x-coordinate of start point
	 * @param sy    y-coordinate of start point
	 */
	public Shape(int sx, int sy) {
		startX = sx;
		startY = sy;
		myColor = createColor();
	}
	
	/**
	 * The abstract draw method draw's an instance of a Shape object, from the implementations details in the Child class. 
	 * @param g    
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * The abstract getType method returns the type of a shape a Shape object is, defined in the Child class. 
	 * @return
	 */
	public abstract String getType();
    
    public abstract boolean contains(int x, int y);
    
    /**
     * The createColor method randomly selects a Color object from the Array colors. 
     * @return myColor    Color object randomly selected from colors. 
     */
    public Color createColor() {
    		// Randomly select a color
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(colors.length);
        myColor = colors[randomNumber];
        return myColor;
    }	
    
    /**
     * The translateColor method translates a String indicating a Color, that was read in from a file. 
     * 		Then returning the Color object equivalent to this String. 
     * @param color    String indicating the color of a Shape object.
     * @return myColor    Color object of the Shape object.
     */
    public Color translateColor(String color) {
    		if(color.equalsIgnoreCase("RED")) {
    			myColor = Color.RED;
    		}
    		else if(color.equalsIgnoreCase("GREEN")) {
    			myColor = Color.GREEN;
    		}
    		else if(color.equalsIgnoreCase("BLUE")) {
    			myColor = Color.BLUE;
    		}
    		else if(color.equalsIgnoreCase("MAGENTA")) {
    			myColor = Color.MAGENTA;
    		}
    		return myColor;
    }
    
    /**
     * The getStartX method returns the x-coordinate of the start point
     * @return startX    x-coordinate of start point
     */
    public int getStartX() {
    		return startX;
    }
    
    /**
     * The getStartY method returns the y-coordinate of the start point
     * @return startY    y-coordinate of start point
     */
    public int getStartY() {
    		return startY;
    }
    
    /**
     * The getColor method returns the Color of the Shape object. 
     * @return myColor    Color object associated with Shape object.
     */
    public Color getColor() {
    		return myColor;
    }
    
    /**
     * The writeColor method translates a Color to a String, that is written to a file. 
     * @return
     */
    public String writeColor() {
    		String color;
    		
    		if(myColor.equals(Color.RED)) {
    			return color = "RED";
    		}
    		else if(myColor.equals(Color.GREEN)) {
    			return color = "GREEN";
    		}
    		else if(myColor.equals(Color.BLUE)) {
    			return color = "BLUE";
    		}
    		else if(myColor.equals(Color.MAGENTA)) {
    			return color = "MAGENTA";
    		}
    		return null;
    }
}