package javaCore34.streamAPI_1.sample4;

import javaCore34.streamAPI_1.sample3.Cat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Cat cat1 = new Cat("Luska", 5, "White");
        Cat cat2 = new Cat("Umka", 7, "Black");
        Cat cat3 = new Cat("Barsic", 2, "Red");
        Cat cat4 = new Cat("Luska", 3, "Grey");

        List<Cat> list = List.of(cat1, cat2, cat3, cat4);

        int age = 5;////условно финализированная переменная, для использования в анонимном классе и лямбде)

        //создадим только поток catToName. без терминального метода он никогда не запустится
        Stream<String> catToName = list.stream()//Создание потока данных. Присваиваем ссылке типа интерфейс
                .filter(a -> a.getWeight() >= age)//Промежуточный метод для фильтрации(оставит только котов старше 5 лет)
                .map(a -> a.getName());//Промежуточный метод для преобразования(Функшинал создаст Стринги из объектов Cat)

        //Терминальный метод collect запустит поток catToName и выполнит то, что у него в аргументах
        List<String> result = catToName.collect(Collectors.toList());//Collectors.toList() аккумулирует результат в списке Стрингов
        System.out.println(result);
    }
}

/**
 *                   Потоки реализуют «ленивые» вычисления.
 * Для проверки факта реализации «ленивых» вычислений немного изменим метод
 * получения для поля name.
 * public String getName() {
 * System.out.println(name);  //добавим эту строку для логгирования выполнения программы
 * return name;
 * }
 *
 * Stream<Cat> catToName = list.stream().filter(a -> a.getName().length() > 5);
 * До тех пор пока в программе используются промежуточные методы, например filter то
 * запуска кода не происходит (вывода на экран нет).
 * Старт процесса выполнения будет только при применении терминального метода
 * List<Cat> result = catToName.collect(Collectors.toList());
 */
