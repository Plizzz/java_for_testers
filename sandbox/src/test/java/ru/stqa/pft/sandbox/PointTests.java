package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistanceNull() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);

        Assert.assertEquals(Point.distance(p1, p2), 0.0);
    }

    @Test
    public void testDistanceP1MoreThanP2() {
        Point p1 = new Point(10, 0);
        Point p2 = new Point(0, 0);

        Assert.assertEquals(Point.distance(p1, p2), 10.0);
    }

    @Test
    public void testDistanceP2MoreThanP1() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 10);

        Assert.assertEquals(Point.distance(p1, p2), 10.0);
    }
}
