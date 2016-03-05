package vgol.java.qa.points;

import org.testng.annotations.Test;
import org.testng.Assert;


public class PointsTests {

    @Test
    public void staticDistance() {
        Point a = new Point(34.1, -2.0);
        Point b = new Point(3.44, 5.5);

        Assert.assertEquals(MySecondProgram.staticDistance(a, b), 31.563, 0.001);
    }

    @Test
    public void distanceFromPoint() {
        Point a = new Point(4.3, 7.0);
        Point b = new Point(3.11, -1.1);

        Assert.assertEquals(a.distanceFromPoint(b), 8.186, 0.001);
    }
}
