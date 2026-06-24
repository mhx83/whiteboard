package model;

import java.awt.*;

/**
 * Abstract base class for shapes in a whiteboard application.
 * This class provides a framework for defining shapes with a start and end point,
 * which are common properties for various types of shapes like lines, rectangles, circles, etc.
 * Each specific shape class will extend this abstract class and implement its own drawing logic.
 */
public abstract class Shape {
    protected int startX, startY; // Start point coordinates
    protected int endX, endY;     // End point coordinates

    /**
     * Constructs a Shape instance with specified start and end coordinates.
     *
     * @param startX the x-coordinate of the start point
     * @param startY the y-coordinate of the start point
     * @param endX the x-coordinate of the end point
     * @param endY the y-coordinate of the end point
     */
    public Shape(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * Draws this shape on the provided graphics context.
     * This method must be implemented by each subclass to define how the shape is drawn.
     *
     * @param g the graphics context on which to draw the shape
     */
    public abstract void draw(Graphics g);

    // Getter and setter methods if necessary
    public int getStartX() { return this.startX; }
    public int getStartY() { return this.startY; }
    public int getEndX() { return this.endX; }
    public int getEndY() { return this.endY;}

    public void setStartX(int x) { this.startX = x; }
    public void setStartY(int y) { this.startY = y; }
    public void setEndX(int x) { this.endX = x; }
    public void setEndY(int y) { this.endY = y; }
}

