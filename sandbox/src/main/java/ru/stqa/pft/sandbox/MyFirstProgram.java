package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Alexander");

    Square square = new Square(10);
    System.out.println(String.format("Площадь квадрата со стороной %.2f = %.2f", square.i, square.area()));

    Rectangle rect = new Rectangle(4, 7);
    System.out.println(String.format("Площадь прямоугольника со сторонами %.2f и %.2f = %.2f", rect.a, rect.b, rect.area()));
  }

  private static void hello(String somebody) {
    System.out.println(String.format("Hello, %s!", somebody));
  }
}