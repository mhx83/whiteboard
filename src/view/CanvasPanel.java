package view;

import controller.DrawingController;
import model.Shape;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The CanvasPanel class serves as the drawing area in a whiteboard application.
 * It extends JPanel and manages the shapes drawn on the canvas.
 */
public class CanvasPanel extends JPanel {
    private List<Shape> shapes = new ArrayList<>();
    private Color currentColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private int strokeWidth = 1;
    private DrawingController controller; // 引用控制器

    /**
     * Constructs a new CanvasPanel.
     * Initializes the panel with a preferred size and background color.
     * The preferred size is set to 600x400 pixels, and the background color is set to white,
     * providing a clear and defined area for drawing shapes.
     */
    public CanvasPanel() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
    }

    public void setController(DrawingController controller) {
        this.controller = controller;
    }

    /**
     * Adds a shape to the canvas and requests a repaint.
     * This method is used to add a new shape to the list of shapes maintained by this panel,
     * ensuring it will be displayed on the next repaint.
     *
     * @param shape The shape to be added to the canvas.
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int width) {
        this.strokeWidth = width;
    }

    /**
     * Overridden method to paint shapes on the panel.
     * It draws all shapes from the controller's list and the current shape in progress.
     *
     * @param g the Graphics object used for drawing operations.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : controller.getShapes()) {
            shape.draw(g);
        }
        Shape currentShape = controller.getCurrentShape();
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }


}
