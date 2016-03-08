package vgol.java.qa.sandbox;


public class Equation {

  private int num;

  public Equation(double a, double b, double c) {

    double d = b*b - 4*a*c;
    if (d < 0) {
      num = 0;
    } else {
      if (d == 0) {
        num = 1;
      } else {
        num =2;
      }
    }
  }

  public int rootNum() {
    return this.num;
  }
}
