import java.util.*;
import java.awt.*;
import processing.core.*;
import processing.event.*;

public class DrawingSurface extends PApplet {
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private boolean clicked;
	private float rotX, rotY;
	private float rate = .015f;

	// The 7 colors on a cube. nonVisible is the color facing the inside of the cube
	// that people can't see
	private Color nonVisible = Color.darkGray.brighter();
	private Color red = Color.red.darker();
	private Color blue = Color.blue.brighter();
	private Color green = Color.green.darker();
	private Color yellow = Color.yellow.darker();
	private Color orange = Color.orange.darker();
	private Color white = Color.white;

	public static void main(String[] args) {
		// Makes the window using DrawingSurface as the PApplet
		PApplet.main("DrawingSurface");
	}

	public void settings() {
		// Sets the size of the window, and specifies that its 3D
		size(500, 500, P3D);
	}

	public void draw() {
		clear();
		background(128);

		// Arbitrary numbers picked after some guess and check
		float xLoc = width / 6f;
		float yLoc = height / 6f;
		float zLoc = 0;

		// Set the "center" of the sketch - used for all movements like additional
		// translations or rotations
		translate(xLoc * 3, yLoc * 4, zLoc);

		// Rotate the canvas based on values from either mouse or key input
		rotateX(rotX);
		rotateY(rotY);

		// Following sections just make the individual cubes with specified colors.
		// Organized into sections to see how things are similar. Sections based off
		// what you see when you run the program

		// Top Left Section
		multiColoredBox(red, nonVisible, nonVisible, blue, nonVisible, green, -xLoc, -yLoc, 0);
		multiColoredBox(nonVisible, white, nonVisible, blue, nonVisible, green, -xLoc, -yLoc, -95);

		// Top Right Section
		multiColoredBox(red, nonVisible, nonVisible, blue, yellow, nonVisible, 10, -yLoc, 0);
		multiColoredBox(nonVisible, white, nonVisible, blue, yellow, nonVisible, 10, -yLoc, -95);

		// Bottom Left Section
		multiColoredBox(red, nonVisible, orange, nonVisible, nonVisible, green, -xLoc, 10, 0);
		multiColoredBox(nonVisible, white, orange, nonVisible, nonVisible, green, -yLoc, 10, -95);

		// Bottom Right Section
		multiColoredBox(red, nonVisible, orange, nonVisible, yellow, nonVisible, 10, 10, 0);
		multiColoredBox(nonVisible, white, orange, nonVisible, yellow, nonVisible, 10, 10, -95);

		// Arrow Key input for changing the perspective on the cube
		if (checkKey(RIGHT))
			rotY += rate;
		else if (checkKey(LEFT))
			rotY -= rate;
		if (checkKey(UP))
			rotX += rate;
		else if (checkKey(DOWN))
			rotX -= rate;
	}

	// Adds the key to the array list
	public void keyPressed() {
		keys.add(keyCode);
	}

	// Removes key from array list
	public void keyReleased() {
		while (checkKey(keyCode))
			keys.remove(new Integer(keyCode));
	}

	// Checks if given key code is in the array list
	private boolean checkKey(int i) {
		return keys.contains(i);
	}

	// Having clicked = true can help control the mouse clicks and events
	public void mousePressed() {
		clicked = true;
	}

	// Changes perspective of cube based on the mouse drags
	public void mouseDragged() {
		if (clicked) {
			rotY += (pmouseY - mouseY) * rate;
			rotX += (pmouseX - mouseX) * rate;
		}
	}

	public void mouseReleased() {
		clicked = false;
	}

	// Params in order: front of cube, back, bottom, top, right, left, x to
	// translate by, y, z
	public void multiColoredBox(Color f1, Color f2, Color f3, Color f4, Color f5, Color f6, float x, float y, float z) {

		// Push style and matrix prevent any processing changes (eg. translate) from
		// affecting shapes made after this method is called
		pushStyle();
		pushMatrix();

		// Translate the box from the center of sketch (defined in draw())
		translate(x, y, z);
		noStroke();

		// Make object bigger
		scale(45);

		// Following sections all make the box together using 6 quadrilaterals. Each
		// quadrilateral is made from 4 vertices which the following sections take care
		// of for the commented face

		// Front
		beginShape(QUADS);
		fill(f1.getRGB());
		vertex(-1, -1, 1);
		vertex(1, -1, 1);
		vertex(1, 1, 1);
		vertex(-1, 1, 1);
		endShape();

		// Back
		beginShape(QUADS);
		fill(f2.getRGB());
		vertex(1, -1, -1);
		vertex(-1, -1, -1);
		vertex(-1, 1, -1);
		vertex(1, 1, -1);
		endShape();

		// Bottom
		beginShape(QUADS);
		fill(f3.getRGB());
		vertex(-1, 1, 1);
		vertex(1, 1, 1);
		vertex(1, 1, -1);
		vertex(-1, 1, -1);
		endShape();

		// Top
		beginShape(QUADS);
		fill(f4.getRGB());
		vertex(-1, -1, -1);
		vertex(1, -1, -1);
		vertex(1, -1, 1);
		vertex(-1, -1, 1);
		endShape();

		// Right
		beginShape(QUADS);
		fill(f5.getRGB());
		vertex(1, -1, 1);
		vertex(1, -1, -1);
		vertex(1, 1, -1);
		vertex(1, 1, 1);
		endShape();

		// Left
		beginShape(QUADS);
		fill(f6.getRGB());
		vertex(-1, -1, -1);
		vertex(-1, -1, 1);
		vertex(-1, 1, 1);
		vertex(-1, 1, -1);
		endShape();

		// Removes all changes made to processing things (eg. translate) so new shapes
		// made after method call aren't affected
		popStyle();
		popMatrix();
	}

}
