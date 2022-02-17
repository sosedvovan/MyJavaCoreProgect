package javaCore34.lambdas.sample7;

import java.util.Arrays;

//Использование лямбда функций для сокращения кода:

public class Main {

    public static void main(String[] args) {

        String[] names = new String[]{"Anna", "Ira", "Alexander", "Katia"};

        //те вместо создания класса, имплементящего компоратор, вместо использования анонимного класса, или ссылки на метод
        //мы воспользовались лямбдой:
        Arrays.sort(names, (a, b) -> a.length() - b.length()); //Реализация Comparator с помощью лямбда функции

        System.out.println(Arrays.toString(names));

    }
}
