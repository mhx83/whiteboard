import model.Rectangle;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleTest {
    private Rectangle rectangle;

    @Before
    public void setUp() {
        rectangle = new Rectangle(25, 35, 75, 95, Color.BLUE, Color.RED, 3);
    }

    @Test
    public void testRectangleProperties() {
        assertEquals("Start X should be 25", 25, rectangle.getStartX());
        assertEquals("Start Y should be 35", 35, rectangle.getStartY());
        assertEquals("End X should be 75", 75, rectangle.getEndX());
        assertEquals("End Y should be 95", 95, rectangle.getEndY());

        assertEquals("Color should be BLUE", Color.BLUE, rectangle.getColor());
        assertEquals("Fill Color should be RED", Color.RED, rectangle.getFillColor());

        assertEquals("Stroke width should be 3", 3, rectangle.getStrokeWidth());
    }
}
