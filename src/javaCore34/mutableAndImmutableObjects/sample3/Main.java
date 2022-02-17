package javaCore34.mutableAndImmutableObjects.sample3;


//Использование неизменяемого объекта
public class Main {
    public static void main(String[] args) {
        ImmutablePoint point = new ImmutablePoint(new int[]{1, 1});
        System.out.println(point);
        int[] coord = point.getCoord();//Получение значения поля
        coord[0] = 10;//Изменение объекта, на который ссылается поле
        System.out.println(point);//На состояние объекта это не повлияло

        //можно только создать еще один- другой неизменяемый объект с coord[0] = 10, если это надо.
    }

}
