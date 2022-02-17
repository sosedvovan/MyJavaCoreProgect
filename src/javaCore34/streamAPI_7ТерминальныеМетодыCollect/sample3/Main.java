/**
 *
 *                                       Интерфейс Collector  (ОН НЕ ФУНКЦИОНАЛЬНЫЙ)
 *
 * Предназначен для описания процедуры накопления элементов потока в структуру данных (Чаще
 * всего реализация Collection или Map). При этом элементы потока могут как изменятся.
 * Важно! Для работы используются четыре метода.
 * ● javaCore34.supplier - создать структуру данных
 * ● accumulator — поместить элемент потока в структуру данных
 * ● сombiner - слияние структур данных созданных в разных потоках
 * ● finisher — заключительное преобразование структуры данных (при необходимости)
 * Также есть Collector.Characteristics (статический вложенный класс) - набор характеристик
 * которые может использовать компилятор для оптимизации выполнения.
 * При последовательном потоке с помощью javaCore34.supplier будет создана структура данных, после чего
 * будет применен accumulator к каждому элементу потока для помещения в структуру данных. Для
 * параллельного потока в каждом потоке с помощью javaCore34.supplier будет создана структура данных,
 * accumulator соберет данные потока, combiner соберет структуры данных во всех потоках в одну. И для
 * последовательного и для параллельных потоков к результирующей структуре данных применяется
 * finisher.
 * ***************************************************************************************
 * Для согласованности работы в последовательных и параллельных потоках методы Collector
 * должны быть согласованны. Они должны удовлетворять условиям идентичности и ассоциативности.
 * Условие идентичности - объединение любого частичного результата (накопленного в одном
 * потоке) с пустой структурой данных должно дать структуру данных эквивалентную накопленной.
 * Условие ассоциативности — разделение на параллельные потоки с последующей сборкой
 * должно давать одинаковый результат в независимости от порядка сборки результата.
 * Если реализация Collector не содержит в Collector.Characteristics значение UNORDERED то
 * требование ассоциативности можно ослабить. Т.е. главное, что бы совпадали элементы структур
 * данных, но не их порядок.
 * *******************************************************************************************
 * При реализации этого интерфейса следует придерживается следующих рекомендаций:
 * ● Первый параметр accumulator (результат работы javaCore34.supplier), оба параметра метода combiner
 * (результаты работы accumulator), и результат переданный finisher (результат combiner) должны быть
 * только результатом предыдущего метода.
 * ● Методы не должны изменять состояние используемых объектов, а только передавать их по цепочке
 * вызовов дальше или вернуть как результат работы collector.
 * ● Если результат передается в combiner или finisher и при этом не возвращается из данных методов,
 * то этот объект не должен больше использоваться.
 * ● Если результат передается в combiner или finisher он не должен больше передаваться в accumulator
 * ● Для не потокобезопасных реализаций результаты javaCore34.supplier, accumulator, combiner должны быть
 * ограниченны(локальны) в пределах одного потока. Это позволит не использовать дополнительной
 * синхронизации.
 * ● Для потокобезопасных реализаций можно из нескольких accumulator собирать результат в одну и
 * туже структуру данных (внимание она должна быть синхронизирована). Параллельная реализация
 * должна применяться только в том случае, если коллектор имеет характеристики
 * Collector.Characteristics.UNORDERED или если исходные данные неупорядочены.
 * ***********************************************************************************************
 *                      Список абстрактных методов Collector
 *
 * BiConsumer<A, T> accumulator() Возврат реализации для accumulator
 *
 * BinaryOperator<A> combiner() Возврат реализации для combiner
 *
 * Supplier<A> javaCore34.supplier() Возврат реализации javaCore34.supplier
 *
 * Function<A, R> finisher() Вернуть реализацию finisher
 *
 * Set<Collector.Characteristics> characteristics() Вернуть множество характеристик
 * ***********************************************************************************************
 *                       Список статических методов Collector
 *
 * static <T, A, R> Collector<T, A, R> of (Supplier<A> javaCore34.supplier, BiConsumer<A, T> accumulator,
 * BinaryOperator<A> combiner, Function<A, R> finisher, Collector.Characteristics... characteristics)
 * Вернет реализацию Collector на основе реализаций интерфейсов переданных в качестве параметра
 *
 * static <T, R> Collector<T, R, R> of (Supplier<R> javaCore34.supplier, BiConsumer<R, T> accumulator,
 * BinaryOperator<R> combiner, Collector.Characteristics... characteristics)
 * Вернет реализацию Collector на основе реализаций интерфейсов переданных в качестве параметра
 *******************************************************************************************************
 *                     Перечисление Collector.Characteristics
 *
 * CONCURRENT
 * Указание на то что структура данных потокобезопасна и может быть
 * использована несколькими accumulator одновременно
 *
 * IDENTITY_FINISH
 * Указание на то что finisher не выполняет действий. И его можно пропустить
 *
 * UNORDERED
 * Указывает на то, что можно не сохранять порядок при сборе элементов в структуру данных
 * *******************************************************************************************************
 *                             Принцип работы реализаций Collector
 *
 * -> accumulator -> javaCore34.supplier[ ] -> [ ...] ->       combiner -> [ ......] -> finisher ->[ ......]
 * -> accumulator -> javaCore34.supplier[ ] -> [ ...] -> ^^^^^^ второй поток вливается в  общий combiner
 */
package javaCore34.streamAPI_7ТерминальныеМетодыCollect.sample3;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;

public class Main {

    public static void main(String[] args) {

        // Todo: Использование реализации Collector. В этой реализации Collector accumulator добавляет в результирующий
        //  список только элементы удовлетворяющие Predicate, метод finisher сортирует полученный список.

        //имеется список
        List<Integer> list = List.of(0, -2, 3, 4, 2, -4, 7, 5, 0);

        //получим реализацию предиката
        Predicate<Integer> predicate = (a) -> a > 0;//true только для положительных чисел

        //Получим реализацию Collector. В конструктор класса имплементящего Collector, отправим реализованный javaCore34.predicate
        Collector<Integer, List<Integer>, List<Integer>> collector = new NumberClassificator(predicate);

        //имея реализацию collector,передадим ее в метод collect() от stream , который ее и ожидает
        List<Integer> result = list.stream()
                .collect(collector);

        System.out.println(result);

    }


}
