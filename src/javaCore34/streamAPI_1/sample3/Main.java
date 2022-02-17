package javaCore34.streamAPI_1.sample3;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //Пример использования Stream:
        Cat cat1 = new Cat("Luska", 5, "White");
        Cat cat2 = new Cat("Umka", 7, "Black");
        Cat cat3 = new Cat("Barsic", 2, "Red");
        Cat cat4 = new Cat("Luska", 3, "Grey");

        List<Cat> list = List.of(cat1, cat2, cat3, cat4);

        int age = 5; //условно финализированная переменная, для использования в анонимном классе и лямбде)

        List<Cat> result = list.stream() //Создание потока данных. ожидаем, что вернется список
                .filter(a -> a.getWeight() >= age)//Промежуточные методы. В предикате исп условно финализированную переменную
                .collect(Collectors.toList());//Терминальный метод:
        //collect (Collector<? super T, A, R> collector) соберет элементы потока данных с помощью Collector
        //  Для того, что бы собрать данные потока в список, стоит использовать
        // терминальный метод collect которому в качестве параметра передавать результат вызова
        // статического методов класса Collectors.toList(). Использование такого терминального
        // метода, соберет все элемента потока в список тип которого совпадает с типом данных потока.
        System.out.println(result);
    }
}
