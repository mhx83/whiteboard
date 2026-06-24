package view;

import controller.DrawingController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The main panel for the drawing application that includes the drawing canvas
 * and toolbars for interacting with the canvas.
 */
public class DrawingBoard extends JPanel {
    private DrawingController controller;
    private CanvasPanel canvas;

    /**
     * Constructs the DrawingBoard, setting up the canvas, controller, and user interface components.
     */
    public DrawingBoard() {
        setLayout(new BorderLayout());
        canvas = new CanvasPanel();
        controller = new DrawingController(canvas); // Create the controller
        canvas.setController(controller); // Set the controller
        initControls();
        setupListeners();
        add(canvas, BorderLayout.CENTER); // Add the canvas to the center
    }

    /**
     * Initializes the controls including toolbars and buttons for the user interface.
     */
    private void initControls() {
        setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // Prevent the toolbar from being movable

        JButton exportButton = new JButton("Export Image");
        JButton freehandButton = new JButton("Freehand Mode");
        JButton clearButton = new JButton("Clear");

        exportButton.addActionListener(e -> controller.exportImage());
        freehandButton.addActionListener(e -> controller.setFreehandMode(true));
        clearButton.addActionListener(e -> controller.clearShapes());

        toolBar.add(exportButton, 0);
        toolBar.add(freehandButton, 1);
        toolBar.add(clearButton, 2);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14)); // 设置字体

        JPanel shapesPanel = new JPanel();

        ButtonGroup buttonGroup = new ButtonGroup();

        String[] labels = {"Line", "Rectangle", "Circle", "Ellipse"};

        for (String label : labels) {
            JToggleButton button = new JToggleButton(label);
            button.addActionListener(e -> controller.setCurrentShapeType(ShapeType.valueOf(label.toUpperCase())));
            buttonGroup.add(button);
            shapesPanel.add(button);
            if (label.equals("Line")) {
                button.setSelected(true);
            }
        }
        tabbedPane.addTab("Shapes", shapesPanel);

        JPanel colorsPanel = new JPanel();
        JButton colorButton = new JButton("Border Color");
        colorButton.addActionListener(e -> chooseColor());
        colorsPanel.add(colorButton);

        JButton fillButton = new JButton("Fill Color");
        fillButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Choose fill color", canvas.getFillColor());
            if (newColor != null) {
                canvas.setFillColor(newColor);
                repaint();
            }
        });
        colorsPanel.add(fillButton);

        JSlider strokeSlider = new JSlider(1, 10, 1);
        strokeSlider.setMajorTickSpacing(1);
        strokeSlider.setPaintTicks(true);
        strokeSlider.setPaintLabels(true);
        strokeSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                canvas.setStrokeWidth(source.getValue());
            }
        });
        colorsPanel.add(new JLabel("Line Thickness"));
        colorsPanel.add(strokeSlider);
        tabbedPane.addTab("Colors & Strokes", colorsPanel);

        toolBar.add(tabbedPane);

        add(toolBar, BorderLayout.NORTH);
    }

    /**
     * Sets up listeners for mouse events to handle drawing operations on the canvas.
     */
    private void setupListeners() {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), canvas);
                controller.mousePressed(p.x, p.y);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), canvas);
                controller.mouseReleased(p.x, p.y);
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), canvas);
                controller.mouseDragged(p.x, p.y);
            }
        });
    }

    /**
     * Sets the controller for this drawing board.
     * @param controller the controller to set
     */
    public void setController(DrawingController controller) {
        this.controller = controller;
    }

    /**
     * Opens a color chooser dialog to select a new border color.
     */
    private void chooseColor() {
        Color newColor = JColorChooser.showDialog(this, "Choose a color", canvas.getCurrentColor());
        if (newColor != null) {
            canvas.setCurrentColor(newColor);
        }
    }

    /**
     * Main method to launch the drawing application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Drawing Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new DrawingBoard());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
