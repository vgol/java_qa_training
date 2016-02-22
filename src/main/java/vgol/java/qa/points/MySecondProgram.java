package vgol.java.qa.points;

public class MySecondProgram {

  public static double staticDistance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
  }

  public static void main(String[] args) {
    // Static method example.
    Point first = new Point(-1.1, 2.0);
    Point second = new Point(3.4, 5.1);

    System.out.println("Using the static method: " + staticDistance(first, second));

    // Method of class Point example
    Point third = new Point(-3.1, -4.0);
    Point fourth = new Point(3.5, 0.1);

    System.out.println("Using the method of class Point: " + third.distanceFromPoint(fourth));
  }
}
