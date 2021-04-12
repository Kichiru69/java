package ru.stqa.pft.sandbox;

public class Point {

  public double p1;
  public double p2;

  public Point(double p1, double p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  public double distance() {
    return Math.sqrt((p2 - p1) * (p2 - p1));

  }


}
