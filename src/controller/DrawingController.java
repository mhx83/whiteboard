package controller;

import model.*;
import model.Rectangle;
import model.Shape;
import view.CanvasPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing drawing operations on a canvas.
 * This class handles input events, shape creation, and manages the drawing process.
 */
public class DrawingController {
    private CanvasPanel canvas;

    private ShapeType currentShapeType;
    private Shape currentShape;
    private int startX, startY;
    private List<Point> freehandPoints = new ArrayList<>(); // to store freehand points
    private boolean isFreehandMode = false; // tag the freehand mode

    /**
     * Constructor that initializes the controller with a canvas.
     * @param canvas The canvas panel associated with this controller.
     */
    public DrawingController(CanvasPanel canvas) {
        this.canvas = canvas;
        this.currentShapeType = ShapeType.LINE; // default shape
    }

    /**
     * Sets the freehand drawing mode.
     * @param enabled true to enable freehand mode, false to disable it.
     */
    public void setFreehandMode(boolean enabled) {
        isFreehandMode = enabled;
        if (!enabled) {
            processFreehandDrawing();
            freehandPoints.clear(); // clear the points when disable freehand mode
        }
    }

    /**
     * Processes the accumulated freehand points to create connected line segments.
     */
    private void processFreehandDrawing() {
        for (int i = 0; i < freehandPoints.size() - 1; i++) {
            Point start = freehandPoints.get(i);
            Point end = freehandPoints.get(i + 1);
            canvas.addShape(new Line(start.x, start.y, end.x, end.y, canvas.getCurrentColor(), canvas.getStrokeWidth()));
        }
        canvas.repaint();
    }

    /**
     * Handles mouse pressed events for starting new shapes or adding to freehand drawing.
     * @param x x-coordinate of the mouse event.
     * @param y y-coordinate of the mouse event.
     */
    public void mousePressed(int x, int y) {
        if (isFreehandMode) {
            freehandPoints.clear();
            freehandPoints.add(new Point(x, y));
        } else {
            startX = x;
            startY = y;
            createShape(x, y, x, y);
        }
    }

    /**
     * Handles mouse dragged events to update the current shape or continue freehand drawing.
     * @param x x-coordinate of the mouse event.
     * @param y y-coordinate of the mouse event.
     */
    public void mouseDragged(int x, int y) {
        if (isFreehandMode) {
            Point lastPoint = freehandPoints.get(freehandPoints.size() - 1);
            Point newPoint = new Point(x, y);
            freehandPoints.add(newPoint);
            canvas.addShape(new Line(lastPoint.x, lastPoint.y, newPoint.x, newPoint.y, canvas.getCurrentColor(), canvas.getStrokeWidth()));
            canvas.repaint();
        } else if (currentShape != null) {
            currentShape.setEndX(x);
            currentShape.setEndY(y);
            canvas.repaint();
        }
    }

    /**
     * Handles mouse released events to finalize the current shape or end a freehand drawing segment.
     * @param x x-coordinate of the mouse event.
     * @param y y-coordinate of the mouse event.
     */
    public void mouseReleased(int x, int y) {
        if (isFreehandMode) {
            if (freehandPoints.size() > 1) {
                processFreehandDrawing();
            }
            freehandPoints.clear();
        } else if (currentShape != null) {
            currentShape.setEndX(x);
            currentShape.setEndY(y);
            canvas.addShape(currentShape);
            currentShape = null;
            canvas.repaint();
        }
    }

    private void createShape(int x1, int y1, int x2, int y2) {
        Color color = canvas.getCurrentColor();
        Color fillColor = canvas.getFillColor();
        int strokeWidth = canvas.getStrokeWidth();

        switch (currentShapeType) {
            case LINE:
                currentShape = new Line(x1, y1, x2, y2, color, strokeWidth);
                break;
            case RECTANGLE:
                currentShape = new Rectangle(x1, y1, x2, y2, color, fillColor, strokeWidth);
                break;
            case CIRCLE:
                currentShape = new Circle(x1, y1, x2, y2, color, fillColor, strokeWidth);
                break;
            case ELLIPSE:
                currentShape = new Ellipse(x1, y1, x2, y2, color, fillColor, strokeWidth);
                break;
        }
    }

    public void setCurrentShapeType(ShapeType type) {
        if (currentShapeType != type) {
            this.currentShapeType = type;
            isFreehandMode = false;
            if (currentShape != null) {
                canvas.addShape(currentShape);
                currentShape = null;
            }
            canvas.repaint();
        }
    }

    /**
     * Exports the current canvas to an image file.
     */
    public void exportImage() {
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        canvas.paint(g2d);
        g2d.dispose();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        fileChooser.addChoosableFileFilter(filter);
        int userSelection = fileChooser.showSaveDialog(canvas);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.toString().toLowerCase().endsWith(".png")) {
                file = new File(file.toString() + ".png");
            }
            try {
                ImageIO.write(image, "PNG", file);
                JOptionPane.showMessageDialog(canvas, "Image saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(canvas, "Error saving image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public List<Shape> getShapes() {
        return canvas.getShapes();
    }

    public void clearShapes() {
        canvas.clearShapes();
    }
}
