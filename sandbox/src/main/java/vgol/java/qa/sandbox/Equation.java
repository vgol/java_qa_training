package vgol.java.qa.sandbox;


public class Equation {

  private int num;

  public Equation(double a, double b, double c) {

    double d = b*b - 4*a*c;

    if (a != 0) {
      if (d < 0) {
        num = 0;
      } else if (d == 0) {
        num = 1;
      } else {
        num = 2;
      }
    } else {

      if (b != 0) {
        num = 1;
      }

      else if (c != 0){
        num = 0;
      }

      else {
        num = -1;
      }
    }

  }

  public int rootNum() {
    return this.num;
  }
}
