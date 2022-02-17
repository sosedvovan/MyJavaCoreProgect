package javaCore34.streamAPI_1.sample7Todo;

import javaCore34.streamAPI_1.sample3.Cat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //Todo:Используя Stream API и List<Cat> верните самое длинное имя кота в этом списке.

        Cat cat1 = new Cat("Luska", 5, "White");
        Cat cat2 = new Cat("Umka", 7, "Black");
        Cat cat3 = new Cat("Barsic", 2, "Red");
        Cat cat4 = new Cat("Kuzia", 3, "Grey");

        List<Cat> list = List.of(cat1, cat2, cat3, cat4);

        Optional<Cat> catMaxName = list.stream()//порождаем стрим на списке
                .max ((a, b) -> a.getName().length() - b.getName().length());
        System.out.println(catMaxName.get());

        //max (Comparator<? super T> javaCore34.comparator) вернет максимальный элемент из потока.
        // * Отношение порядка определяется реализацией Comparator.

        //-----------------------------------------------------------------------------------------------
        //Todo:Используя Stream API и List<Integer> выделите только нечетные числа, отсортируйте их
        // по возрастанию и соберите в новый список.

        List<Integer> listInt = List.of(0, 3, -2, 4, -1, 7);

        List<Integer> result =  listInt.stream()//порождаем стрим на листе
                .filter(a -> a % 2 != 0)//промежуточный метод. выбирает только четные
                .sorted()//промежуточный метод. сортировка с пом Comparable класса Integer
                .collect(Collectors.toList());//аккумуляция результата в список
        System.out.println(result);

        //-------------------------------------------------------------------------------------------
        //Todo:Используя Stream API верните адрес файла с максимальным размером в заданном каталоге.
        // см. как работать с файлами для начала
    }
}
