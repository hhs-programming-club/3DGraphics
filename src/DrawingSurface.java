import java.util.*;
import java.awt.*;
import processing.core.*;
import processing.event.*;

public class DrawingSurface extends PApplet {
	private ArrayList<Integer> keys;
	private boolean clicked;
	private float x, y;
	private float rate = 0.015f;
	private Color nonVisible = Color.darkGray.brighter();

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
		pushMatrix();
		translate(width / 2.0f, height / 2.0f, -100);
		rotateY(y);
		rotateX(x);
		noStroke();
		multiColoredBox(Color.red, nonVisible, nonVisible, Color.blue, nonVisible, Color.green);
		popMatrix();

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
			x += (pmouseY - mouseY) * rate;
			y += (mouseX - pmouseX) * rate;
		}
	}

	public void mouseWheel(MouseEvent e) {

	}

	public void mouseReleased() {
		clicked = false;
	}

	// Params in order: front of cube, back, bottom, top, right, left
	public void multiColoredBox(Color f1, Color f2, Color f3, Color f4, Color f5, Color f6) {
		pushStyle();
		scale(90);
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
	}

}
