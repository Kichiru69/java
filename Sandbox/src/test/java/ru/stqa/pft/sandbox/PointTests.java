package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDinstance() {
    Point p1 = new Point(15,22);
    Point p2 = new Point(33,44);
    Assert.assertEquals(p1.distance(p2), 28.42534080710379);

  }

}
