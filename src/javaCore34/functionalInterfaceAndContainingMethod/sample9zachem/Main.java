package javaCore34.functionalInterfaceAndContainingMethod.sample9zachem;

//Продемонстрируем, что Использование ссылок на методы может сократить объемы кода:
//те не надо создавать доп классы или анонимные классы

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] names = new String[]{"Anna", "Ira", "Alexander", "Katia"};

        Arrays.sort(names, Main::compareStringLength);//Ссылка на метод в качестве реализации Comparator

        System.out.println(Arrays.toString(names));

    }

    public static int compareStringLength(String a, String b) {
        return a.length() - b.length();
    }
}
