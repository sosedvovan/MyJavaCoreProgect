package javaCore34.mutableAndImmutableObjects.sample2;

public class Main {

    public static void main(String[] args) {
        ImmutablePoint point1 = new ImmutablePoint(2, 4);
        ImmutablePoint point2 = point1.setX(4);//Создание нового объекта на основании текущего
        System.out.println(point1);
        System.out.println(point2);
        System.out.println(point1 == point2);//Разные объекты
    }
}
