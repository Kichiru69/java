package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point  (double x, double y) {

    this.x = x;
    this.y = y;
  }

  public double distance(Point p) {
   double dx = p.x - this.x;
   double dy = p.y - this.y;
   double distance = Math.sqrt(dx * dx + dy * dy);
   return distance;
 }

}
