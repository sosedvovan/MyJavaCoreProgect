package javaCore34.streamAPI_5ТерминальныеМетодыГенерирующиеЗезультатНаОснованииДанныхПотока.sample2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        //              Методы findAny и findFirst
        //Существует два метода возвращающие элемент потока (при условии что в потоке
        //есть элементы).

        //Optional<T> findAny() - вернет Optional с любым элементом потока если элемент в
        //потоке есть, или пустой Optional если в потоке нет элементов.

        //Optional<T> findFirst() - вернет Optional с первым элементом потока если элемент в
        //потоке есть, или пустой Optional если в потоке нет элементов.

        //Метод findAny — хорошо показывает себя в неупорядоченных потоках, так же в
        //параллельных потоках данных.

        //                      Пример применения

        List<Integer> numbers = List.of(0, 8, 4, 6, 3, 10, 5);

        //порождаем поток на numbers и фильтруем его
        Stream<Integer> stream = numbers.stream().filter(n -> n % 2 == 1);

        //findFirst() вернет Optional с первым элементом потока
        Optional<Integer> result = stream.findFirst();

        System.out.println(result.get());

        //В примере мы получаем Optional<Integer> с первым нечетным элементом в потоке
        //(все четные элементы были отброшены из потока с помощью filter).

        //-----------------------------------------------------------------------------------------------------

        //                           Метод count
        //long count() - вернет количество элементов в потоке.

       //                              Пример применения

        List<Integer> numbers2 = List.of(0, 8, 4, 6, 3, 10, 5);

        Stream<Integer> stream2 = numbers2.stream().filter(n -> n % 2 == 1);

        long odd = stream2.count();

        System.out.println(odd);

        //В примере используя терминальный метод count() подсчитываем количество
        //элементов в потоке. По сути считаем сколько в потоке нечетных чисел.

        //------------------------------------------------------------------------------------------------------



    }
}
