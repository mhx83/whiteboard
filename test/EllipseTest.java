import model.Ellipse;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EllipseTest {
    private Ellipse ellipse;

    @Before
    public void setUp() {
        ellipse = new Ellipse(5, 10, 15, 20, Color.GREEN, Color.YELLOW, 2);
    }

    @Test
    public void testEllipseProperties() {
        assertEquals("Start X should be 5", 5, ellipse.getStartX());
        assertEquals("Start Y should be 10", 10, ellipse.getStartY());
        assertEquals("End X should be 15", 15, ellipse.getEndX());
        assertEquals("End Y should be 20", 20, ellipse.getEndY());

        assertEquals("Color should be GREEN", Color.GREEN, ellipse.getColor());
        assertEquals("Fill Color should be YELLOW", Color.YELLOW, ellipse.getFillColor());

        assertEquals("Stroke width should be 2", 2, ellipse.getStrokeWidth());
    }
}
