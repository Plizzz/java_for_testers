package ru.stqa.pft.sandbox;

public class PointRunner {
    public static void main(String[] args) {
        Point p1 = new Point(-2, 5);
        Point p2 = new Point(4, -7);

        System.out.println(String.format("Расстояние между точками p1 и p2 = %.2f", p1.distance(p2)));
    }
}
