package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDinstance() {
    Point p = new Point(38, 92);
    Assert.assertEquals(p.distance(), 54.0);

  }

}
