import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
/**
 * The BasicLinePix class is a class that allows the user to draw Line's and Rectangle's of various sizes and colors,
 * 		and save them to a specified text file. 
 * @author JonathanNeff
 *
 */
@SuppressWarnings("serial")
public class BasicLinePix extends JFrame {

	private JPanel drawingPanel; // User draws here
	private JPanel backgroundPanel; // User selects background color here
	private JButton[][] colors; 
	private ArrayList<Color> colorList;
	private ArrayList<Shape> theShapes = new ArrayList<>(); // ArrayList of Shape objects
	
	private JPanel statusBar; // JPanel for holding statusLabel
	private JLabel statusLabel; // Used to show informational messages

	private JFileChooser fileChooser; // JFileChooser used to open and save file type ".txt" 
	private File file; // File selected by the user 
	
	private JMenuBar menuBar; // JMenuBar for holding fileMenu & drawMenu
	private JMenu fileMenu;  // JMenu with JMenuItem "New", "Open", "Save" and "Exit"
	private JMenu settingsMenu; // 
	private JMenu drawMenu;  // JMenu with JMenuItem "Line" and "Rectangle"
	
	private String shapeToDraw = "Line"; // Current Shape to be drawn in the drawingPanel; Line is the default option
	private int totalShapes = 0; // Total number of Shape objects currently drawn in the drawingPanel
	private boolean controlClick = false; // Boolean used for indicating if the control key is pressed  
	
	private EventHandler eh; // Used to handle a variety of events invoked by the user

	public static void main(String[] args) {
		BasicLinePix basicLinePix = new BasicLinePix();
		basicLinePix.setVisible(true);
	}

	/**
	 * Class constructor BasicLinePix generates an instance of the graphics user interface (GUI).
	 * Creates the necessary components of the GUI, then adding them to the JFrame. 
	 */
	public BasicLinePix() {
		// Set the title
		setTitle("Basic Line Pix 1.0");
		// Exit the program on close 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Retrieves the content pane
		Container cp = getContentPane();
		// Set layout of the content pane
		cp.setLayout(new BorderLayout());
		// Create event handler 
		eh = new EventHandler();
		// Create JPanel used for drawingPanel
		drawingPanel = createDrawingPanel();
		
		createColors();
		
		// Add a mouse listener to drawingPanel for responding to a MouseEvent in which the user uses the mouse, 
		//		to interact with a component
		drawingPanel.addMouseListener(eh);
		// Add a mouse motion listener to drawingPanel for responding to mouse-motion events
		drawingPanel.addMouseMotionListener(eh);
		drawingPanel.addKeyListener(eh);
		// Create JPanel used for holding the statusLabel
		statusBar = createStatusBar();
		// Set default text of statusLabel
		statusLabel.setText("Drawing Line's....");
		// Add drawingPanel to content pane
		cp.add(drawingPanel, BorderLayout.CENTER);
		// Add statusBar to content pane
		cp.add(statusBar, BorderLayout.SOUTH);
		// Build JMenu 
		buildMenu();
		pack();
	}
	/**
	 * The buildMenu method creates a JMenuBar for the holding the JMenu's and their JMenuItem's. 
	 */
	private void buildMenu() {
		menuBar = new JMenuBar();  // JMenuBar for holding JMenu "File" and "Draw"
		fileMenu = new JMenu("File");
		drawMenu = new JMenu("Draw");
		settingsMenu = new JMenu("Settings");

		JMenuItem menuItem = new JMenuItem("New"); 
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);
		
		menuItem = new JMenuItem("Background");
		menuItem.addActionListener(eh);
		settingsMenu.add(menuItem);
		
		menuItem = new JMenuItem("Line");
		menuItem.addActionListener(eh);
		drawMenu.add(menuItem);
		
		menuItem = new JMenuItem("Rectangle");
		menuItem.addActionListener(eh);
		drawMenu.add(menuItem);
		
		menuItem = new JMenuItem("PolyLine");
		menuItem.addActionListener(eh);
		drawMenu.add(menuItem);
		
		menuItem = new JMenuItem("Oval");
		menuItem.addActionListener(eh);
		drawMenu.add(menuItem);
		
		menuBar.add(fileMenu); // Add fileMenu to menuBar
		menuBar.add(drawMenu); // Add drawMenu to menuBar
		menuBar.add(settingsMenu); // Add settingsMenu to menuBar
		
		setJMenuBar(menuBar); // Set JMenuBar to menuBar
	}
	
	/**
	 * The createDrawingPanel method creates a JPanel used for drawing Shape objects
	 * @return dP    the JPanel used for drawing Shape objects
	 */
	private JPanel createDrawingPanel() {
		JPanel dP = new JPanel();
		dP.setPreferredSize(new Dimension(1000, 800));
		dP.setBackground(Color.YELLOW);
		return dP;
	}
	
	private JPanel createBackgroundPanel() {
		JPanel bP = new JPanel();
		bP.setPreferredSize(new Dimension(200, 200));
		bP.setLayout(new GridLayout(5, 5, 1, 1));
		bP.setBackground(Color.GRAY);
		
		// Create background color options
		colors = new JButton[3][5];
		int localCount = 0;
		for(int row = 0; row < 3; row++) {
			localCount +=1;
			for(int col = 0; col < 5; col++) {
				localCount += 1;
				colors[row][col].setBackground(colorList.get(localCount)); 
			}
		}
		return bP;
	}
			
	private ArrayList<Color> createColors() {
		colorList = new ArrayList<Color>();
		Integer[] red = {255, 255, 138, 65, 0, 0, 127, 0, 0, 255, 255, 255, 64, 176, 255};
		Integer[] green = {20, 0, 43, 105, 0, 191, 255, 255, 255, 255, 165, 99, 224, 196, 43};
		Integer[] blue = {147, 255, 226, 225, 255, 255, 212, 255, 0, 0, 0, 71, 208, 222, 212};
		for(int i = 0; i < red.length; i++) {
			Color newColor = new Color(red[i], green[i], blue[i]);
			colorList.add(newColor);
		}
		return colorList;	
	}
	
	/**
	 * The createStatusBar method creates a JPanel used for holding the statusLabel,
	 * 		which is used for showing informational messages. 
	 * @return panel    the JPanel used for holding the statusLabel
	 */
	private JPanel createStatusBar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		statusLabel = new JLabel("No message");
		panel.add(statusLabel, BorderLayout.CENTER);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		return panel;
	}
	
	/**
	 * The createJFileChooser method creates a JPanel used for displaying an instance of JFileChooser,  
	 * 		and all the files the user can select from.  
	 */
	private void createJFileChooser() {
		JPanel display = new JPanel();
		String directoryPath = Paths.get(".").toAbsolutePath().normalize().toString();
		fileChooser = new JFileChooser(directoryPath);
		fileChooser.setDialogTitle("Shape File");
		display.setPreferredSize(new Dimension(278, 179));
		display.setLayout(null);
		fileResponse();
	}
	
	/**
	 * The fileResponse method verifies the user has selected a valid file, and if so, initiate file to be read. 
	 */
	private void fileResponse() {
		int returnValue = fileChooser.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			// Read the file	
			try { 
				readShapes(file);
				} catch (Exception error) {
			}
		}
		// Reset the fileChooser for the next time it's shown.
        fileChooser.setSelectedFile(null);
	}
	
	/**
	 * The readShapes method reads in the Shape objects from the file. 
	 * @param filename    name of File to be read in by the Scanner. 
	 */
	private void readShapes(File filename) {
		ArrayList<String> scannedShapes = new ArrayList<>();
		System.out.println("Reading file "+ filename);
		Scanner shapeScanner = null;
		try {
	        shapeScanner = new Scanner(filename, "UTF-8");
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		while (shapeScanner.hasNext()) {
			scannedShapes.add(shapeScanner.nextLine());
			}
		
		int lineCounter = 0;
		for(String s: scannedShapes) {
			Scanner lineScanner = new Scanner(s);
			
			
			if(lineCounter == 0) {
				totalShapes = Integer.parseInt(lineScanner.next().trim());
				lineCounter++;
			}
			else if (lineCounter > 0) {
				lineScanner.useDelimiter(",");
				String type = lineScanner.next().trim();
				int startX = Integer.parseInt(lineScanner.next().trim());
				int startY = Integer.parseInt(lineScanner.next().trim());
				int endXOrWidth = Integer.parseInt(lineScanner.next().trim());
				int endYOrHeight = Integer.parseInt(lineScanner.next().trim());
				String color = lineScanner.next().trim();
				lineCounter++;	
			
				if(type.equalsIgnoreCase("L")) {
					theShapes.add(new Line(startX, startY, endXOrWidth, endYOrHeight, color));
				}
			
				else if(type.equalsIgnoreCase("R")) {
					theShapes.add(new Rectangle(startX, startY, endXOrWidth, endYOrHeight, color));
				}
			}
			lineScanner.close();
		}
	}
	
	/**
	 * The writeShapes method displays an instance of JFileChooser, allowing the user to save the current state 
	 *     of the GUI to a specified file. 
	 */
	private void writeShapes() {
		String directoryPath = Paths.get(".").toAbsolutePath().normalize().toString();
		JFileChooser fileSaveChooser = new JFileChooser(new File(directoryPath));
		fileSaveChooser.setDialogTitle("Shape Saver");   

		int userSelection = fileSaveChooser.showSaveDialog(null);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileSaveChooser.getSelectedFile();
		    FileWriter shapeWriter;
		    
			try {
				shapeWriter = new FileWriter(fileToSave, true);
				BufferedWriter buffer = new BufferedWriter(shapeWriter);
	    			PrintWriter shapeStream = new PrintWriter(buffer); 
	    			shapeStream.println(totalShapes);
	    			for(Shape s: theShapes) {
	    				shapeStream.println(s.toString());
	    			}
	    			shapeStream.close();
	    			System.out.println("Overwrote: " +fileToSave);
			} catch (IOException e) {
				System.err.println("Error: File: " +fileToSave+ " not found!");
				e.printStackTrace();
			}
		    
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			}
	}
	
	/**
	 * The closestShape method determines the closest Shape object to a specified deletion point in the GUI. 
	 * @param x    x-coordinate of the specified deletion point
	 * @param y    y-coordinate of the specified deletion point
	 */
	private void closestShape(int x, int y) {
		double shortestDistance = 9999;
		double lDistance = 9999;
		double rDistance = 9999;
		int indexCounter = 0;
		int shapeIndex = 0;
		
		for(Shape s: theShapes) {
			// If the Shape s is a Line
			if(s.getType().equalsIgnoreCase("L")) {
				int sX = s.getStartX(); // Start x-coordinate
				int sY = s.getStartY(); // Start y-coordinate
				int eX = ((Line) s).getEndX(); // End x-coordinate
				int eY = ((Line) s).getEndY(); // End y-coordinate
				lDistance = getDistance(x, y, sX, sY, eX, eY); // Get distance
				if(lDistance < shortestDistance) {
					shortestDistance = lDistance;
					shapeIndex = indexCounter;
				}
				indexCounter++;
			}
			
			// If the Shape s is a Rectangle
			else if(s.getType().equalsIgnoreCase("R")) {
				int leftX = s.getStartX(); // Left x-coordinate
				int topY = s.getStartY(); // Top y-coordinate
				int width = ((Rectangle) s).getWidth(); // Width of Rectangle
				int height = ((Rectangle) s).getHeight(); // Height of Rectangle
				int rightX = leftX + width; // Right x-coordinate 
				int bottomY = topY + height; // Bottom y-coordinate
				
				// If the Rectangle contains the point
				if(x > leftX && x < rightX && y > topY && y < bottomY) {
					double distanceX = Math.min(x - leftX, rightX - x);
					double distanceY = Math.min(y - topY, bottomY - y);
					rDistance = Math.min(distanceX, distanceY);
				}
				
				// If the point is perpendicular to a side of the Rectangle
				
				//Left-side
				else if(y > topY && y < bottomY && x < leftX) {
					rDistance = getDistance(x, y, leftX, topY, leftX, bottomY);
				}
				//Right-side
				else if(y > topY && y < bottomY && x > rightX) {
					rDistance = getDistance(x, y, rightX, topY, rightX, bottomY);
				}
				//Top-side
				else if(x > leftX && x < rightX && y < topY) {
					rDistance = getDistance(x, y, leftX, topY, rightX, topY);
				}
				//Bottom-side
				else if(x > leftX && x < rightX && y > bottomY) {
					rDistance = getDistance(x, y, leftX, bottomY, rightX, bottomY);
				}
				
				//If the point is not perpendicular to a side of the Rectangle. 
				
				//Left-top
				else if(x < leftX && y < topY) {
					rDistance = Math.sqrt(Math.pow((leftX - x), 2) + Math.pow((topY - y), 2));
				}
				//Left-bottom
				else if(x < leftX && y > bottomY) {
					rDistance = Math.sqrt(Math.pow((leftX - x), 2) + Math.pow((y - bottomY), 2));
				}
				//Right-top
				else if(x > rightX && y < topY) {
					rDistance = Math.sqrt(Math.pow((x - rightX), 2) + Math.pow((topY - y), 2));
				}
				//Right-bottom
				else if(x > rightX && y > bottomY) {
					Math.sqrt(Math.pow((x - rightX), 2) + Math.pow((y - bottomY), 2));
				}
				
				if(rDistance < shortestDistance) {
					shortestDistance = rDistance;
					shapeIndex = indexCounter;
				}	
				indexCounter++;
			}
		}
	removeShape(shapeIndex);
	}
	
	/**
	 * The getDistance method contains a formula used for calculating the distance between a point, 
	 * 		and two other points that fall on a line. 
	 * @param x    x-coordinate of the specified deletion point
	 * @param y    y-coordinate of the specified  deletion point
	 * @param sX    x-coordinate for first point on the line
	 * @param sY    y-coordinate for first point on the line
	 * @param eX    x-coordinate for second point on the line
	 * @param eY    y-coordinate for second point on the line
	 * @return distance    the closest distance between the specified deletion point and the line formed by two points. 
	 */
	private double getDistance(int x, int y, int sX, int sY, int eX, int eY) {
		double topHalf = Math.abs(((eY-sY)*x) - ((eX-sX)*y) + (eX*sY) - (eY*sX));
		double bottomHalf = Math.sqrt(Math.pow((eY-sY), 2) + Math.pow((eX-sX),2));
		double distance = topHalf/bottomHalf;
		return distance;
	}
	
	/**
	 * The removeShape method removes a Shape object at the specified index from the ArrayList theShapes
	 * @param shapeIndex    index value position of shape to be removed from the ArrayList theShapes
	 */
	private void removeShape(int shapeIndex) {
		theShapes.remove(shapeIndex);
		totalShapes--;
		repaint();
	}

	/**
	 * The paint method overrides the paint method defined in JFrame
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics g1 = drawingPanel.getGraphics();

		// Send a message to each Shape object in the drawing to
		// draw itself on g1
		for(Shape s: theShapes) {
			s.draw(g1);
		}
	}
	
	/**
	 * The inner class EventHandler handles various action events invoked by the user. 
	 * @author JonathanNeff
	 *
	 */
	private class EventHandler implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

		private int startX, startY; // Line's start coordinates
		private int lastX, lastY; // Line's most recent end point
		private int lastWidth, lastHeight; // Rectangle's most recent width and height

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// Exit program when JMenuItem "Exit" is selected
			if (arg0.getActionCommand().equals("Exit")) {
				statusLabel.setText("Exiting program...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
			
			// Clear canvas when JMenuItem "New" is selected
			else if (arg0.getActionCommand().equals("New")) {
				statusLabel.setText("New canvas...");
				theShapes.clear();
				repaint();
			}
			
			// Create JFileChooser to open a user selected file, when JMenuItem "Open" is selected
			else if (arg0.getActionCommand().equals("Open")) {
				statusLabel.setText("File Opened...");
				createJFileChooser();
				repaint();
			}
			
			// Create JFileChooser to save a user selected file, when JMenuItem "Save" is selected
			else if (arg0.getActionCommand().equals("Save")) {
				statusLabel.setText("File Saved...");
				writeShapes();
			}
			
			else if (arg0.getActionCommand().equals("Background")) {
				statusLabel.setText("Choose Background Color...");
				ColorSamplePopup Jon = new ColorSamplePopup();
				
			}
			
			// Set canvas to draw a Line, when JMenuItem "Line" is selected
			else if (arg0.getActionCommand().equals("Line")) {
				statusLabel.setText("Drawing Line's...");
				shapeToDraw = "Line";
			}
			
			// Set canvas to draw a Rectangle, when JMenuItem "Rectangle" is selected
			else if (arg0.getActionCommand().equals("Rectangle")) {
				statusLabel.setText("Drawing Rectangle's...");
				shapeToDraw = "Rectangle";
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.isControlDown() == true && totalShapes > 0) {
				controlClick = true;
				int x = e.getX();
				int y = e.getY();
				closestShape(x, y); 
			}
			
			else if(shapeToDraw.equalsIgnoreCase("Line")) {
				
				// Get starting x and y coordinates
				startX = e.getX();
				startY = e.getY();

				// Initialize lastX, lastY
				lastX = startX;
				lastY = startY;
			}
			
			else if(shapeToDraw.equalsIgnoreCase("Rectangle")) {
				
				// Get starting x and y coordinates
				startX = e.getX();
				startY = e.getY();
				
				// Initialize lastWidth, lastHeight
				lastWidth = 0;
				lastHeight = 0;
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			// Implement rubber-band cursor
			Graphics g = drawingPanel.getGraphics();
			g.setColor(Color.black);
			g.setXORMode(drawingPanel.getBackground());

			if(shapeToDraw.equalsIgnoreCase("Line")) {
				// REDRAW the line that was drawn
				// most recently during this drag
				// XOR mode means that yellow pixels turn black
				// essentially erasing the existing line
				g.drawLine(startX, startY, lastX, lastY);

				// draw line to current mouse position
				// XOR mode: yellow pixels become black
				// black pixels, like those from existing lines, temporarily become
				// yellow
				g.drawLine(startX, startY, e.getX(), e.getY());
				lastX = e.getX();
				lastY = e.getY();
			}
			
			else if(shapeToDraw.equalsIgnoreCase("Rectangle")) {
				// REDRAW the rectangle that was drawn
				// most recently during this drag
				// XOR mode means that yellow pixels turn black
				// essentially erasing the existing line
				g.drawRect(startX, startY, lastWidth, lastHeight);
				
				// draw rectangle to current mouse position
				// XOR mode: yellow pixels become black
				// black pixels, like those from existing lines, temporarily become
				// yellow
				g.drawRect(startX, startY, Math.abs(e.getX()-startX), Math.abs(e.getY()-startY));
				lastWidth = Math.abs(e.getX() - startX);
				lastHeight = Math.abs(e.getY() - startY);
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(controlClick == true) {
				controlClick = false;
				arg0.consume();
			}
			
			else if(shapeToDraw.equalsIgnoreCase("Line")) {
				theShapes.add(new Line(startX, startY, arg0.getX(), arg0.getY()));
				totalShapes++;
				repaint();
			}
			
			else if(shapeToDraw.equalsIgnoreCase("Rectangle")) {
				theShapes.add(new Rectangle(startX, startY, arg0.getX()-startX, arg0.getY()-startY));
				totalShapes++;
				repaint();
			}
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			System.out.println("You're out of hints!");
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
			
		}
		
	}
}