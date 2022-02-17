package javaCore34.functionalInterfaceAndContainingMethod.sample5;

//Использование ссылки на конструктор в случае массивов
//В случае когда нужно использовать ссылку на конструктор массивов используется синтаксис вида тип [] ::new

public class Main {

    public static void main(String[] args) {

        //Получение ссылки на конструктор массива
        Generator gen = int[]::new;

        Object a = gen.createNewObject(10);

        System.out.println(a.getClass());//class [I

    }
}
