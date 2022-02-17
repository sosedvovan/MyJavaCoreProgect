/**
 * Stream API Часть 7
 * Терминальные методы collect
 * <p>
 * Терминальные методы collect используются для аккумулирования данных потока в
 * различные структуры данных. Это позволяет как хранить множество элементов потока,
 * так и провести группировку результатов.
 * *************************************************************************************
 * Список абстрактных методов collect
 *
 * <R> R collect (Supplier<R> javaCore34.supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
 * Реализует аккумулирующую операцию
 * <p>
 * <R, A> R collect (Collector<? super T, A, R> collector)
 * Реализует аккумулирующую операцию с использованием Collector
 * ************************************************************************************
 */
package javaCore34.streamAPI_7ТерминальныеМетодыCollect.sample1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /**
         * Метод:  <R> R collect (Supplier<R> javaCore34.supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
         * Создает результат аккумулируя элементы потока в структуру данных (чаще всего используется
         * реализация Collection). Первым параметром выступает Supplier javaCore34.supplier ответственный за создание
         * нужной изменяемой структуры данных (List, Set, Queque). Второй параметр BiConsumer accumulator
         * в качестве параметра принимает созданную структуру данных (первый параметр) и элемент потока
         * (второй параметр) и добавляет элемент потока или результат его обработки в структуру данных.
         * Третий параметр BiConsumer combiner используется в параллельных потоках для сбора результатов
         * каждого потока в общий результат.
         * Важным условием должно быть согласование работы accumulator и combiner. Это означает, что
         * выполнение accumulator полностью в одном последовательном потоке должно давать такой же
         * результат как и обработка части потока с помощью accumulator с последующим слиянием этих частей
         * с помощью combiner.
         */
        // Todo: В этом примере метод collect используется для сохранения всех четных чисел из потока в список (ArrayList).

        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> even = list1.stream()
                .filter(a -> a % 2 == 0)
                .collect(ArrayList::new, (a, b) -> a.add(b), (a, b) -> a.addAll(b));

        //                   Схема аккумулирования результата

        //.collect(ArrayList::new, (a,b)->a.add(b), (a,b)->a.addAll(b));
        //где:
        //ArrayList::new       - Ссылка на конструктор ArrayList - генератор нового ArrayList (List, Set, Queque)
        //(a,b)->a.add(b)      - Элемент потока b добавляем в список a, где а это ArrayList, b - элемент потока list1.stream()
        //(a,b)->a.addAll(b))  - Собираем списки a,b с разных потоков в один, если разрешенны параллельные потоки - работает

        // Todo:Применение collect. В этом примере на основании данных потока (Goods) генерируются строки с названием товара,
        //  после чего эти строки собираются в список ( List<String> goodsName).

        List<Goods> list = List.of(new Goods("Apple", 50),
                new Goods("Orange", 70),
                new Goods("Pear", 65),
                new Goods("Cherry", 75));

        List<String> goodsName = list.stream()
                .filter(a -> a.getPrice() > 50)
                .collect(ArrayList::new, (a, b) -> a.add(b.getName()), (a, b) -> a.addAll(b));
        //где:
        //ArrayList::new       - Ссылка на конструктор ArrayList - генератор нового ArrayList (List, Set, Queque)
        //(a,b)->a.add(b)      - Добавляем в список a название товара b, где а это созданный ArrayList, b - элемент потока list1.stream()
        //(a,b)->a.addAll(b))  - Собираем списки a,b с разных потоков в один, если разрешенны параллельные потоки - работает

        //еще один пример в след. семпле




    }
}
