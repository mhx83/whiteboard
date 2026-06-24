package model;

import java.awt.*;

/**
 * Represents an ellipse shape in a whiteboard application.
 */
public class Ellipse extends Shape {
    private Color color;
    private Color fillColor;
    private int strokeWidth;

    /**
     * Constructs a new Ellipse instance.
     *
     * @param startX      the x-coordinate of the start point
     * @param startY      the y-coordinate of the start point
     * @param endX        the x-coordinate of the end point
     * @param endY        the y-coordinate of the end point
     * @param color       the outline color of the ellipse
     * @param fillColor   the fill color of the ellipse
     * @param strokeWidth the stroke width of the ellipse's outline
     */
    public Ellipse(int startX, int startY, int endX, int endY, Color color, Color fillColor, int strokeWidth) {
        super(startX, startY, endX, endY);
        this.color = color;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Draws this ellipse on the given graphics context.
     * Calculates the width and height of the ellipse based on the difference
     * between the start and end coordinates. Determines the top-left corner
     * of the bounding rectangle of the ellipse and uses these values to draw
     * the ellipse with the specified colors and stroke width.
     *
     * @param g the graphics context on which to draw the ellipse
     */
    @Override
    public void draw(Graphics g) {
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(fillColor);
        g2d.fillOval(x, y, width, height);

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.drawOval(x, y, width, height);
    }

    public Color getColor() {
        return this.color;
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }
}
