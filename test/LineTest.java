import model.Line;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LineTest {
    private Line line;

    @Before
    public void setUp() {
        line = new Line(0, 0, 100, 100, Color.BLACK, 1);
    }

    @Test
    public void testLineProperties() {
        assertEquals("Start X should be 0", 0, line.getStartX());
        assertEquals("Start Y should be 0", 0, line.getStartY());
        assertEquals("End X should be 100", 100, line.getEndX());
        assertEquals("End Y should be 100", 100, line.getEndY());

        assertEquals("Color should be BLACK", Color.BLACK, line.getColor());
        assertEquals("Stroke width should be 1", 1, line.getStrokeWidth());
    }
}
