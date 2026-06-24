package model;

import java.awt.*;

/**
 * Represents a line shape in a whiteboard application.
 */
public class Line extends Shape {
    private Color color;
    private int strokeWidth;

    /**
     * Constructs a new Line instance.
     *
     * @param startX      the x-coordinate of the start point of the line
     * @param startY      the y-coordinate of the start point of the line
     * @param endX        the x-coordinate of the end point of the line
     * @param endY        the y-coordinate of the end point of the line
     * @param color       the color of the line
     * @param strokeWidth the stroke width of the line
     */
    public Line(int startX, int startY, int endX, int endY, Color color, int strokeWidth) {
        super(startX, startY, endX, endY);
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Draws this line on the given graphics context.
     * Uses the starting and ending coordinates to draw a straight line
     * between the two points with the specified color and stroke width.
     *
     * @param g the graphics context on which to draw the line
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.drawLine(startX, startY, endX, endY);
    }

    public void setEndPoint(int x2, int y2) {
        this.endX = x2;
        this.endY = y2;
    }

    public Color getColor() {
        return this.color;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }
}

