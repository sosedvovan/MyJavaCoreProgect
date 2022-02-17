package javaCore34.mutableAndImmutableObjects.sample1;

public class Main {

    public static void main(String[] args) {
        MutablePoint point1 = new MutablePoint(1, 1);
        System.out.println(point1);
        point1.setX(3);
        System.out.println(point1);
    }
}
