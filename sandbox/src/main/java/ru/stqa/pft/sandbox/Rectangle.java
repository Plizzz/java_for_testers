package ru.stqa.pft.sandbox;

class Rectangle {
    double a;
    double b;

    Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    double area() {
        return this.a * this.b;
    }
}
