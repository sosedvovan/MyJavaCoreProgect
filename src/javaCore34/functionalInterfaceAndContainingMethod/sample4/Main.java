package javaCore34.functionalInterfaceAndContainingMethod.sample4;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Использование ссылки на конструктор:

        //Получение (и соотнесение) ссылки на конструктор Аррайлиста(обращаемся к конструктору через имя класса)
        Generator gen = ArrayList::new;

        //абстрактный метод интерфейса запустит конструктор Аррайлиста
        Object a = gen.createNewObject();

        //проверим, что получили Аррайлист:
        System.out.println(a.getClass());//class java.util.ArrayList
    }
}
