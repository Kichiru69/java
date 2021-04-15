package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {
    Point p1 = new Point(15,22);
    Point p2 = new Point(33,44);
    System.out.println("p1 = " + p1.x + ", " + p1.y);
    System.out.println("p2 = " + p2.x + ", " + p2.y);
    System.out.println("Расстояние между точками = " + p1.distance(p2));
    System.out.println("Расстояние между точками = " + distance(p1, p2));

  }

  public static double distance(Point p1, Point p2) {
    double dx = p1.x - p2.x;
    double dy = p1.y - p2.y;
    return Math.sqrt(dx * dx + dy * dy);
  }

}
