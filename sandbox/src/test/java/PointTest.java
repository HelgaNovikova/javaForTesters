import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void positiveCoordinates(){
        Point point1 = new Point(10, 19.3);
        Point point2 = new Point(2.5, 7);
        Assert.assertEquals(point1.distance(point2), 14.406248644251564);
    }

    @Test
    public void negativeCoordinates(){
        Point point1 = new Point(-10, -19.3);
        Point point2 = new Point(-2.5, -7);
        Assert.assertEquals(point1.distance(point2), 14.406248644251564);
    }

    @Test
    public void theSameCoordinates(){
        Point point1 = new Point(9, -4.4);
        Point point2 = new Point(9, -4.4);
        Assert.assertEquals(point1.distance(point2), 0);
        Assert.assertEquals(point1.distance(point1), 0);
    }
}
