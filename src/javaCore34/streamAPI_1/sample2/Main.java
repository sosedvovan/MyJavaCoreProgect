/**
 *                  Знакомство с использованием Stream API
 * Для знакомства с использованием Stream API выделим из всего многообразия
 * промежуточных и терминальных методов только небольшую их часть. И на этапе
 * знакомства будем использовать только их. Впоследствии мы изучим остальные более
 * подробно.
 *
 *                       Некоторые промежуточные методы для знакомства со Stream API
 *                          Метод                                            Описание
 * Stream<T> filter (Predicate<? super T> javaCore34.predicate)             Фильтрация данных на основе Predicate
 * Stream<T> sorted (Comparator<? super T> javaCore34.comparator)           Сортировка данных на основе Comparator
 * Stream<T> sorted()                                            Сортировка данных на основе Comparable
 * <R> Stream<R> map (Function<? super T, ? extends R> mapper)   Изменение типа данных потока на основе Function
 * Stream<T> peek (Consumer<? super T> action)                   Выполняет действие над элементов потока
 * Stream<T> distinct()                                          Возвращает поток без дубликатов. Использует equals.
 *
 *                     подробнее об этих промежуточных методах:
 *  filter (Predicate<? super T> javaCore34.predicate) используется для того, что бы отбросить
 * некоторые данные из потока данных. Т.е. из потока отбрасываются элементы для которых
 * Predicate вернет false.
 * sorted (Comparator<? super T> javaCore34.comparator) упорядочивает элемента потока, что бы они
 * следовали в возрастающем порядке. Отношение порядка устанавливается реализацией
 * Comparator.
 * sorted() упорядочивает элемента потока, что бы они следовали в возрастающем
 * порядке. Отношение порядка устанавливается реализацией Comparable.
 * map (Function<? super T, ? extends R> mapper) преобразуют элементы потока из одного
 * типа в другой. Т.е. к каждому элементу текущего потока применяют реализацию Function,
 * и на основе результата получается другой поток.
 * peek (Consumer<? super T> action) выполняет действие над элементами потока
 * данных. Действие определяется реализацией Consumer.
 * distinct() исключить из потока данных дубликаты. Для сравнения используется метод
 * equals.
 *
 * ***************************************************
 *
 *                          Некоторые терминальные методы для знакомства со Stream API
 *
 *                    Метод                                                     Описание
 * void forEach (Consumer<? super T> action)                         Выполнит действие над каждым элементом потока
 * <R, A> R collect (Collector<? super T, A, R> collector)           Реализует аккумулирующую операцию с использованием Collector
 * Optional<T> max (Comparator<? super T> javaCore34.comparator)                Вернет максимальный элемент из потока данных
 * Optional<T> min (Comparator<? super T> javaCore34.comparator)                Вернет минимальный элемент из потока данных
 *
 *               подробнее об этих терминальных методах:
 *
 * forEach (Consumer<? super T> action) выполнит действие над каждым элементом
 * потока. Действие определяется с помощью реализации Consumer.
 * collect (Collector<? super T, A, R> collector) соберет элементы потока данных с помощью
 * Collector. Для того, что бы собрать данные потока в список, стоит использовать
 * терминальный метод collect которому в качестве параметра передавать результат вызова
 * статического методов класса Collectors.toList(). Использование такого терминального
 * метода, соберет все элемента потока в список тип которого совпадает с типом данных потока.
 * max (Comparator<? super T> javaCore34.comparator) вернет максимальный элемент из потока.
 * Отношение порядка определяется реализацией Comparator.
 * min (Comparator<? super T> javaCore34.comparator) вернет минимальный элемент из потока
 * данных. Отношение порядка определяется реализацией Comparator.
 *
 *
 */



package javaCore34.streamAPI_1.sample2;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Пример использования Stream API:
        //имеется список:
        List<Integer> list = List.of(0, 3, -2, 4, -1, 7);

        list.stream()  //Создание потока данных
                .filter(a -> a > 0)  //Промежуточные методы (выберет только те значения, для кот предикат вернет true)
                .sorted()
                .forEach(a -> System.out.println(a)); //Терминальный метод (для каждого элемента(forEach) Консуммер произведет действие)

        //В этом пример поток данных Stream<Integer> создается на основе List<Integer>.
        //После чего был применен промежуточный метод filter (отбросит отрицательные числа и
        //0), и потом применяется промежуточный метод sorted для сортировки потока.
        //Терминальным методом выступает forEach который выведет каждый элемент потока на экран.



    }//закрыли главный метод
}
