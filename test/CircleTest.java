import model.Circle;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CircleTest {
    private Circle circle;

    @Before
    public void setUp() {
        circle = new Circle(10, 20, 30, 40, Color.RED, Color.BLUE, 5);
    }

    @Test
    public void testCircleProperties() {
        // Test coords
        assertEquals("Start X should be initialized to 10", 10, circle.getStartX());
        assertEquals("Start Y should be initialized to 20", 20, circle.getStartY());
        assertEquals("End X should be initialized to 30", 30, circle.getEndX());
        assertEquals("End Y should be initialized to 40", 40, circle.getEndY());

        // Test color
        assertEquals("Color should be RED", Color.RED, circle.getColor());
        assertEquals("Fill Color should be BLUE", Color.BLUE, circle.getFillColor());

        // Test stroke width
        assertEquals("Stroke width should be 5", 5, circle.getStrokeWidth());
    }
}
