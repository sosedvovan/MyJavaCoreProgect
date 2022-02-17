package javaCore34.streamAPI_8КлассCollectors.sample5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Todo Метод collectingAndThen
public class Main {

    public static void main(String[] args) {

        //Метод:
        //static <T, A, R, RR> Collector<T, A, RR> collectingAndThen (Collector<T, A, R> downstream, Function<R, RR> finisher)
        //Возвращает реализацию Collector следующим образом. Сначала происходит сбор элементов
        //потока с помощью Collector downstream, после чего к результату сбора применяется Function finisher.

        Collector<Integer, ?, List<Integer>> downstream = Collectors.toList();
        Function<List<Integer>, List<String>> finisher = a -> {
            List<String> result = new ArrayList<>();
            for (Integer element : a) {
                result.add((Integer.toString(element)).repeat(element));
            } return result;
        };
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        List<String> result = list.stream()
                .collect(Collectors.collectingAndThen(downstream, finisher));

        System.out.println(result);//[1, 22, 333, 4444, 55555]

        //В данном примере создается Collector собирающий элементы потока Integer в список. Function
        //создает на основании List<Integer> List<String>. Результирующий Collector создает создает список
        //строк на основании потока целых чисел.
        //--------------------------------------------------------------------------------------------

        //                                    Метод filtering
        //Метод:
        //static <T, A, R> Collector<T, ?, R> filtering (Predicate<? super T> javaCore34.predicate, Collector<? super T, A, R> downstream)
        //Возвращает реализацию Collector следующим образом. Сбор элементов потока с помощью
        //Collector downstream производится только для тех элементов потока для которых Predicate вернет
        //true.

        //Todo В данном примере создается Collector собирающий элементы потока Integer в список. Predicate
        // вернет true для элементов больше 3. Результирующий Collector собирает в список только те элементы
        // потока которые больше 3.

        List<Integer> list2 = List.of(1, 2, 3, 4, 5);

        //Predicate вернет true для элементов больше 3
        Predicate<Integer> predicate = a -> a > 3;

        //Результирующий Collector
        Collector<Integer, ?, List<Integer>> collector = Collectors.toList();

        List<Integer> result2 = list2.stream()
                .collect(Collectors.filtering(predicate, collector));

        System.out.println(result2);//[4, 5]
        //-----------------------------------------------------------------------------------------

        //                                      Метод flatMapping
        //Метод:
        //static <T, U, A, R> Collector<T, ?, R> flatMapping (Function<? super T, ? extends Stream<? extends U>> mapper, Collector<? super U, A, R> downstream)
        //Возвращает реализацию Collector собирающей элементы потоков порожденных с помощью
        //mapper.

        //Todo В данном примере создаются Collector собирающий элементы потоков порожденных с помощью
        // mapper (на основе строки генерирует поток строк из символов). Эти потоки упаковываются в один и
        // его элементы собираются dowstream (собирает элементы потока в карту).

        List<String> language = List.of("Python", "Java", "Fortran");

        //порождаться стрим на Аррее Стрингов, где каждая буква это элемент Аррея
        Function<String, Stream<String>> mapper = a -> Arrays.stream(a.split(""));

        BinaryOperator<Integer> mergeFunction = (a, b) -> a + b;

        Collector<String, ?, Map<String, Integer>> downstream2 = Collectors.toMap(Function.identity(), a -> 1,mergeFunction);

        Map<String, Integer> result3 = language.stream().collect(Collectors.flatMapping(mapper, downstream2));

        System.out.println(result3);//{P=1, a=3, r=2, t=2, F=1, v=1, h=1, y=1, J=1, n=2, o=2}

        //---------------------------------------------------------------------------------------------------

        //
    }
}
