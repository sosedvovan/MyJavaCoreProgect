/**
 * Stream API Часть 2.
 * Промежуточные методы для фильтрации данных
 * <p>
 * На этой лекции мы рассмотрим промежуточные методы Stream API предназначенные
 * для фильтрации данных. Под фильтрацией данных стоит понимать выборочное
 * использование требуемых данных (по тому или иному критерию) и отбрасывание данных
 * которые не удовлетворяют данному критерию.
 * <p>
 * Пример фильтрации данных
 * Предположим у нас есть поток целых чисел. И требуется отфильтровать его по
 * критерию, что число должно быть больше нуля. В таком случае после применения метода
 * для фильтрации по этому критерию в потоке останутся только числа больше нуля.
 * Было:       0, 5, -2, 1, -4, 7
 * Применили:  filter (n -> n>0)
 * Стало:      5, 1, 7
 * <p>
 * Список абстрактных промежуточных методов выполняющих фильтрацию данных
 * <p>
 * Метод                                                  Описание
 * Stream<T> filter (Predicate<? super T> javaCore34.predicate)          Фильтрация данных на основе Predicate
 * Stream<T> distinct()                                       Возвращает поток без дубликатов. Использует equals
 * Stream<T> limit (long maxSize)                             Возвращает первые maxSize элементов
 * Stream<T> skip (long n)                                    Пропуск первых n элементов потока
 * <p>
 * Список промежуточных методов по умолчанию выполняющих фильтрацию данных
 * <p>
 * default Stream<T> dropWhile (Predicate<? super T> javaCore34.predicate)
 * Для упорядоченных потоков, возвращает поток в
 * котором останутся только элементы после последнего
 * срабатывания Predicate
 * <p>
 * default Stream<T> takeWhile (Predicate<? super T> javaCore34.predicate) Для упорядоченных потоков, возвращает поток, в
 * котором останутся первые элементы для которых
 * Predicate возвращал true
 */
package javaCore34.streamAPI_2Filter.sample1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        //              Метод filter (Predicate<? super T> javaCore34.predicate)
        //Метод используется для того, что бы отбросить
        //некоторые данные из потока данных. Т.е. из потока отбрасываются элементы для которых
        //Predicate вернет false.

        List<Integer> list = List.of(0, 5, -2, 1, -4, 7);

        List<Integer> resultList = list.stream()//Создание потока данных
                .filter(n -> n > 0)//Фильтрация данных
                .collect(Collectors.toList());//Терминальный метод

        System.out.println(resultList);

        //В примере из списка создается поток целых чисел. Используя метод filter
        //отбрасываем из потока числа меньшие или равные нулю. После этого метода в потоке
        //останутся только числа больше нуля. Поток с помощью терминального метода собирается
        //в список. Результирующий список выводиться на экран.

        //-----------------------------------------------------------------------------------------------------

        //           Последовательное применение filter
        //Так как filter промежуточный метод, то можно создавать цепочку вызовов для более
        //сложной процедуры фильтрации данных.

        List<Integer> list2 = List.of(0, 2, -2, 1, -4, 6);

        List<Integer> resultList2 = list2.stream()
                .filter(n -> n > 0)//Последовательное применение filter
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(resultList2);

        //В результате последовательного применения filter в данном потоке останутся только
        //четные числа больше нуля.

        //----------------------------------------------------------------------------------------------------

        //                    Последовательное применение filter

        Cat cat1 = new Cat("Luska", 5, "White");
        Cat cat2 = new Cat("UmkA", 7, "Black");
        Cat cat3 = new Cat("Barsic", 2, "Red");
        Cat cat4 = new Cat("Kuzia", 3, "Grey");

        Cat[] cats = new Cat[]{cat1, cat2, null, cat3, cat4};//в массиве есть null

        Stream<Cat> catToName = Arrays.stream(cats)
                .filter(Objects::nonNull)//противонульный метод класса Objects убережет от нульПоинтЕксепшн
                .filter(a -> a.getWeight() > 5);

        //терминальный метод запустим в блоке try-catch
        try {
            List<Cat> result = catToName.collect(Collectors.toList());
            System.out.println(result);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}
