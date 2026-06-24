package model;

import java.awt.*;

/**
 * Represents a circle shape in a whiteboard application.
 */
public class Circle extends Shape {
    private Color color;
    private Color fillColor;
    private int strokeWidth;

    /**
     * Constructs a new Circle instance.
     *
     * @param startX      the x-coordinate of the start point
     * @param startY      the y-coordinate of the start point
     * @param endX        the x-coordinate of the end point
     * @param endY        the y-coordinate of the end point
     * @param color       the outline color of the circle
     * @param fillColor   the fill color of the circle
     * @param strokeWidth the stroke width of the circle's outline
     */
    public Circle(int startX, int startY, int endX, int endY, Color color, Color fillColor, int strokeWidth) {
        super(startX, startY, endX, endY);
        this.color = color;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Draws this circle on the given graphics context.
     * The method calculates the circle's radius based on the distance between
     * the start and end points. It also determines the center of the circle
     * and uses the radius to draw the circle from its calculated top-left corner.
     *
     * @param g the graphics context on which to draw the circle
     */
    @Override
    public void draw(Graphics g) {
        int radius = (int) (Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)) / 2);

        int centerX = (startX + endX) / 2;
        int centerY = (startY + endY) / 2;

        int x = centerX - radius;
        int y = centerY - radius;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(fillColor);
        g2d.fillOval(x, y, 2 * radius, 2 * radius);

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.drawOval(x, y, 2 * radius, 2 * radius);
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

