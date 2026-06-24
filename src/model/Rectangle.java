package model;

import java.awt.*;

/**
 * Represents a rectangle shape in a whiteboard application.
 */
public class Rectangle extends Shape {
    private Color color;
    private Color fillColor;
    private int strokeWidth;

    /**
     * Constructs a new Rectangle instance.
     *
     * @param startX      the x-coordinate of the start point of the rectangle
     * @param startY      the y-coordinate of the start point of the rectangle
     * @param endX        the x-coordinate of the end point of the rectangle
     * @param endY        the y-coordinate of the end point of the rectangle
     * @param color       the outline color of the rectangle
     * @param fillColor   the fill color of the rectangle
     * @param strokeWidth the stroke width of the rectangle's outline
     */
    public Rectangle(int startX, int startY, int endX, int endY, Color color, Color fillColor, int strokeWidth) {
        super(startX, startY, endX, endY);
        this.color = color;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Draws this rectangle on the given graphics context.
     * Calculates the top-left corner and dimensions based on the start and end points.
     * It uses these to draw the rectangle with specified outline and fill colors,
     * and a specified stroke width.
     *
     * @param g the graphics context on which to draw the rectangle
     */
    @Override
    public void draw(Graphics g) {
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(fillColor);
        g2d.fillRect(x, y, width, height);

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.drawRect(x, y, width, height);
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

