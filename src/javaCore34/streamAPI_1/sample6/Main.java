package javaCore34.streamAPI_1.sample6;

import javaCore34.streamAPI_1.sample3.Cat;

import java.util.List;
import java.util.stream.Stream;

//Изменение состояния объектов в потоках
public class Main {
    public static void main(String[] args) {

        //Как уже было сказано, потоки не изменяют структуры данных на основании которых
        //они были созданы. Однако используя промежуточные методы можно изменять объекты
        //которые в них хранятся.

        Cat cat1 = new Cat("Luska", 5, "White");
        Cat cat2 = new Cat("Umka", 7, "Black");
        Cat cat3 = new Cat("Barsic", 2, "Red");
        Cat cat4 = new Cat("Kuzia", 3, "Grey");

        List<Cat> list = List.of(cat1, cat2, cat3, cat4);

        Stream<Cat> catToName = list.stream()//порождаем стрим на списке
                .filter(a -> a.getWeight() < 5)//применяем фильтр с  Predicate
                .peek(a -> a.setName("_" + a.getName()));//Stream<T> peek (Consumer<? super T> action) Выполняет действие над элементов потока с пом Consumer

        //терминальный метод forEach(для всех элементов) запускает стрим catToName и выполняется сам
        catToName.forEach(a -> System.out.println(a));

        //для демонстрации того, что стрим изменил поля объектов класса Cat(прошедших фильтрацию), выведем первоначальный список list
        System.out.println();
        for (Cat cat : list) {
            System.out.println(cat);
        }
    }
}
