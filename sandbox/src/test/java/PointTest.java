import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance(){
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 1);
        Assert.assertEquals(point1.distance(point2), 1);
        Assert.assertEquals(point1.distance(point1), 0);
        Point point3 = new Point(2.5, -7);
        Assert.assertEquals(point2.distance(point3), 8.381527307120106);

    }
}
