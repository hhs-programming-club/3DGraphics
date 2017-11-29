import java.util.*;
import java.awt.*;
import processing.core.*;
import processing.event.*;

public class DrawingSurface extends PApplet {
	private ArrayList<Integer> keys;
	private boolean clicked;
	private float x, y;
	private float rate = .015f;
	private Color nonVisible = Color.darkGray.brighter();
	private Color red = Color.red.darker();
	private Color blue = Color.blue.brighter();
	private Color green = Color.green.darker();
	private Color yellow = Color.yellow.darker();
	private Color orange = Color.orange.darker();
	private Color white = Color.white;

	public DrawingSurface() {
		keys = new ArrayList<Integer>();
	}

	public static void main(String[] args) {
		PApplet.main("DrawingSurface");
	}

	public void settings() {
		size(500, 500, P3D);
	}

	public void draw() {
		clear();
		background(128);

		float xLoc = width / 6f;
		float yLoc = height / 6f;
		float zLoc = 0;
		 translate(xLoc*3, yLoc*4, zLoc);

//		camera(70f, this.y, this.x, xLoc * 4, yLoc * 4, zLoc, 0.0f, 1.0f, 0.0f);
		 rotateX(this.x);
		rotateY(this.y);
		 
		//Top Left Section
		multiColoredBox(red, nonVisible, nonVisible, blue, nonVisible, green, -xLoc, -yLoc, 0);
		multiColoredBox(nonVisible, white, nonVisible, blue, nonVisible, green, -xLoc, -yLoc, -95);
		
		//Top Right Section
		multiColoredBox(red, nonVisible, nonVisible, blue, yellow, nonVisible,  10, -yLoc, 0);
		multiColoredBox(nonVisible, white, nonVisible, blue, yellow, nonVisible,  10, -yLoc, -95);

		//Bottom Left Section
		multiColoredBox(red, nonVisible, orange, nonVisible, nonVisible, green, -xLoc, 10, 0);
		multiColoredBox(nonVisible, white, orange, nonVisible, nonVisible, green, -yLoc, 10, -95);

		//Bottom Right Section
		multiColoredBox(red, nonVisible, orange, nonVisible, yellow, nonVisible, 10, 10, 0);
		multiColoredBox(nonVisible, white, orange, nonVisible, yellow, nonVisible, 10, 10, -95);

		if (checkKey(RIGHT))
			y += rate;
		else if (checkKey(LEFT))
			y -= rate;
		if (checkKey(UP))
			x += rate;
		else if (checkKey(DOWN))
			x -= rate;
	}

	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while (checkKey(keyCode))
			keys.remove(new Integer(keyCode));
	}

	private boolean checkKey(int i) {
		return keys.contains(i);
	}

	public void mousePressed() {
		clicked = true;
		// Add code here to constrain click/drag area
		// Add code here to add functions when mouse is just clicked
	}

	public void mouseDragged() {
		if (clicked) {
			// Code for when mouse is dragged here
			y += (pmouseY - mouseY) * rate;
			x += (pmouseX - mouseX) * rate;
		}
	}

	public void mouseWheel(MouseEvent e) {

	}

	public void mouseReleased() {
		clicked = false;
	}

	// Params in order: front of cube, back, bottom, top, right, left
	public void multiColoredBox(Color f1, Color f2, Color f3, Color f4, Color f5, Color f6, float x, float y, float z) {
		pushStyle();
		pushMatrix();
		translate(x, y, z);
		noStroke();
		scale(45);
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
		popStyle();
		popMatrix();
	}

}
