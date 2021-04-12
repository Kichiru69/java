package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {
    Point p = new Point(38, 92);

    System.out.println("Расстояние между точкой " + p.p1 + " и точкой " + p.p2 + " = " + p.distance());

  }
}
