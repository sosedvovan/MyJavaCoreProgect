/**
 *                            Stream API Часть 8
 *                             Класс Collectors
 *
 * Класс Collectors содержит большое количество статических методов возвращающих
 * реализацию интерфейса Collector. Методы этого класса значительно упрощают
 * использование аккумулирующих терминальных методов.
 * *********************************************************************************************************
 *                      Методы для сбора в Collection
 *
 * static <T, C extends Collection<T>> Collector<T, ?, C> toCollection (Supplier<C> collectionFactory)
 * Собирает элементы потока в реализацию Collection
 *
 * static <T> Collector<T, ?, List<T>> toList()
 *  Собирает элементы потока в List
 *
 * static <T> Collector<T, ?, Set<T>> toSet()
 *  Собирает элементы потока в Set
 *
 * static <T> Collector<T, ?, List<T>> toUnmodifiableList()
 *  Собирает элементы в неизменяемый List
 *
 * static <T> Collector<T, ?, Set<T>> toUnmodifiableSet()
 *  Собирает элементы в неизменяемый Set
 *  ******************************************************************************************************
 *                                      Методы для сбора в Map
 *
 * static <T, K, U>Collector<T, ?, Map<K, U>> toMap (Function<? super T, ? extends K>
 * keyMapper, Function<? super T, ? extends U> valueMapper)
 * Собирает элементы потока в Map применяя keyMapper для генерации ключа
 * и valueMapper для генерации значений
 *
 * static <T, K, U> Collector<T, ?, Map<K, U>> toMap (Function<? super T, ? extends K>
 * keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U>
 * mergeFunction)
 * Собирает элементы потока в Map применяя keyMapper для генерации ключа
 * и valueMapper для генерации значений. Для слияния эквивалентных
 * результатов используют mergeFunction
 *
 * static <T, K, U, M extends Map<K, U>> Collector<T, ?, M> toMap (Function<? super T, ?
 * extends K> keyMapper, Function<? super T, ? extends U> valueMapper,
 * BinaryOperator<U> mergeFunction, Supplier<M> mapFactory)
 * Собирает элементы потока в Map применяя keyMapper для генерации ключа
 * и valueMapper для генерации значений. Для слияния эквивалентных
 * результатов используют mergeFunction. В какую именно карту собирать
 * результат определяется mapFactory
 *
 * static <T, K, U>Collector<T, ?, Map<K, U>> toUnmodifiableMap (Function<? super T, ?
 * extends K> keyMapper, Function<? super T, ? extends U> valueMapper)
 * Собирает элементы потока в неизменяемый Map применяя keyMapper для
 * генерации ключа и valueMapper для генерации значений.
 *
 * static <T, K, U>Collector<T, ?, Map<K, U>> toUnmodifiableMap (Function<? super T, ?
 * extends K> keyMapper, Function<? super T, ? extends U> valueMapper,
 * BinaryOperator<U> mergeFunction)
 * Собирает элементы потока в неизменяемый Map применяя keyMapper для
 * генерации ключа и valueMapper для генерации значений. Для слияния
 * эквивалентных результатов используют mergeFunction
 *
 * static <T, K, U>Collector<T, ?, ConcurrentMap<K, U>> toConcurrentMap (Function<?
 * super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper)
 * Собирает элементы потока в потокобезопасную Map применяя keyMapper для
 * генерации ключа и valueMapper для генерации значений
 *
 * static <T, K, U> Collector<T, ?, ConcurrentMap<K, U>> toConcurrentMap (Function<?
 * super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper,
 * BinaryOperator<U> mergeFunction)
 * Собирает элементы потока в потокобезопасный Map применяя keyMapper для
 * генерации ключа и valueMapper для генерации значений. Для слияния
 * эквивалентных результатов используют mergeFunction
 *
 * static <T, K, U, M extends ConcurrentMap<K, U>> Collector<T, ?, M> toConcurrentMap
 * (Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U>
 * valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapFactory)
 * Собирает элементы потока в потокобезопасный Map применяя keyMapper для
 * генерации ключа и valueMapper для генерации значений. Для слияния
 * эквивалентных результатов используют mergeFunction. В какую именно карту
 * собирать результат определяется mapFactory
 * ************************************************************************************************************
 *                            Методы для группировки данных в Map
 *
 *  static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy (Function<? super T, ? extends
 * K> classifier)
 * Проводит операцию группировки с помощью classifier в Map
 *
 * static <T, K, D, A, M extends Map<K, D>> Collector<T, ?, M> groupingBy (Function<? super
 * T, ? extends K> classifier, Supplier<M> mapFactory, Collector<? super T, A, D>
 * downstream)
 * Проводит операцию группировки в Map с помощью classifier генерируя ключи
 * карты (создается mapFactory). В качестве значения используется результат
 * работы коллектора downstream
 *
 * static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy (Function<? super T, ? extends
 * K> classifier, Collector<? super T, A, D> downstream)
 * Проводит операцию группировки в Map с помощью classifier генерируя ключи
 * карты. В качестве значения используется результат работы коллектора
 * downstream
 *
 * static <T, K> Collector<T, ?, ConcurrentMap<K, List<T>>> groupingByConcurrent
 * (Function<? super T, ? extends K> classifier)
 * Проводит операцию группировки с помощью classifier в потокобезопасный
 * Map
 *
 * static <T, K, A, D, M extends ConcurrentMap<K, D>> Collector<T, ?, M>
 * groupingByConcurrent (Function<? super T, ? extends K> classifier, Supplier<M>
 * mapFactory, Collector<? super T, A, D> downstream)
 * Проводит операцию группировки в потокобезопасный Map с помощью
 * classifier генерируя ключи карты (создается mapFactory). В качестве значения
 * используется результат работы коллектора downstream
 *
 * static <T, K, A, D> Collector<T, ?, ConcurrentMap<K, D>> groupingByConcurrent
 * (Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream)
 * Проводит операцию группировки в потокобезопасный Map с помощью
 * classifier генерируя ключи карты. В качестве значения используется результат
 * работы коллектора downstream
 * ********************************************************************************************
 *                              Методы для бинарной группировки данных в Map
 *
 * static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningBy (Predicate<? super T>
 * javaCore34.predicate)
 * Проводит бинарную группировку в Map на основе javaCore34.predicate. Значения
 * результирующей карты записываются в виде List
 *
 * static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningBy (Predicate<? super T>
 * javaCore34.predicate, Collector<? super T, A, D> downstream
 * Проводит бинарную группировку. Значения результирующей карты создаются
 * на основе downstream
 * *************************************************************************************************
 *                                        Методы для аккумулирования данных
 *
 * static <T> Collector<T, ?, Optional<T>> reducing (BinaryOperator<T> op) Вернет реализацию для аккумулирования данных
 * с помощью BinaryOperator
 *
 * static <T> Collector<T, ?, T> reducing (T identity, BinaryOperator<T> op) Вернет реализацию для аккумулирования
 * данных с помощью BinaryOperator. Начальное
 * значение задается явно в виде identity
 *
 * static <T, U> Collector<T, ?, U> reducing (U identity, Function<? super T, ?
 * extends U> mapper, BinaryOperator<U> op)
 * Вернет реализацию для аккумулирования данных с помощью BinaryOperator. Начальное
 * значение задается с помощью identity. Преобразование элементов потока можно
 * произвести с помощью Function
 *
 *Реализации Collector возвращаемые данными методами по сути дублируют уже существующий
 * терминальный метод reduce.
 * *********************************************************************************************************
 *                   Методы для изменения методов сбора
 *
 * static <T, A, R, RR> Collector<T, A, RR> collectingAndThen (Collector<T, A,
 * R> downstream, Function<R, RR> finisher)
 * Возвращает Collector на основании Collector выступающего в качестве параметра к
 * которому применена Function
 *
 * static <T, A, R> Collector<T, ?, R> filtering (Predicate<? super T> javaCore34.predicate,
 * Collector<? super T, A, R> downstream)
 * Вернет Collector на основании Collector выступающего в качестве параметра, который
 * соберет только те элементы потока для которых Predicate вернет true
 *
 * static <T, U, A, R> Collector<T, ?, R> flatMapping (Function<? super T, ?
 * extends Stream<? extends U>> mapper, Collector<? super U, A, R>
 * downstream)
 * Вернет Collector на основании Collector выступающего в качестве параметра, который
 * собирает элементы потоков созданных с помощью Function на основании данных потока
 * *************************************************************************************
 *
 *                                           Методы для получения статистики
 *
 * static <T> Collector<T, ?, Double> averagingDouble (ToDoubleFunction<? super T> mapper)
 * Вернет Collector который вычисляет среднеарифметическое элементов, полученных из потока с
 * помощью mapper
 *
 * static <T> Collector<T, ?, Double> averagingInt (ToIntFunction<? super T> mapper)
 * Вернет Collector который вычисляет среднеарифметическое элементов, полученных из потока с
 * помощью mapper
 *
 * static <T> Collector<T, ?, Double> averagingLong (ToLongFunction<? super T> mapper)
 * Вернет Collector который вычисляет среднеарифметическое элементов, полученных из потока с
 * помощью mapper
 *
 * static <T> Collector<T, ?, Long> counting()
 * Вернет Collector считающий количество элементов в потоке
 *
 * static <T> Collector<T, ?, Double> summingDouble (ToDoubleFunction<? super T> mapper)
 * Вернет Collector который вычисляет сумму элементов, полученных из потока с помощью mapper.
 *
 * static <T> Collector<T, ?, Integer> summingInt (ToIntFunction<? super T> mapper)
 * Вернет Collector который вычисляет сумму элементов, полученных из потока с помощью mapper.
 *
 * static <T> Collector<T, ?, Long> summingLong (ToLongFunction<? super T> mapper)
 * Вернет Collector который вычисляет сумму элементов, полученных из потока с помощью mapper.
 *
 * static <T> Collector<T, ?, DoubleSummaryStatistics> summarizingDouble (ToDoubleFunction<? super
 * T> mapper)
 * Вернет Collector возвращающий сборную статистику полученную на основании элементов потока,
 * созданного с помощью mapper
 *
 * static <T> Collector<T, ?, IntSummaryStatistics> summarizingInt (ToIntFunction<? super T> mapper)
 * Вернет Collector возвращающий сборную статистику полученную на основании элементов потока,
 * созданного с помощью mapper
 *
 * static <T> Collector<T, ?, LongSummaryStatistics> summarizingLong (ToLongFunction<? super T>
 * mapper)
 * Вернет Collector возвращающий сборную статистику полученную на основании элементов потока,
 * созданного с помощью mapper
 *
 * static <T> Collector<T, ?, Optional<T>> maxBy (Comparator<? super T> javaCore34.comparator)
 * Вернет Collector возвращающий Optional с максимальных элементов потока
 *
 * static <T> Collector<T, ?, Optional<T>> minBy (Comparator<? super T> javaCore34.comparator)
 * Вернет Collector возвращающий Optional с минимальным элементов потока
 * ***********************************************************************************************
 *                                 Методы для строковых Collector
 *
 * static Collector<CharSequence, ?, String> joining()
 * Вернет Collector который объединит элементы потока в строку
 *
 * static Collector<CharSequence, ?, String> joining (CharSequence delimiter)
 * Вернет Collector который объединит элементы потока в строку разделяя
 * указанным delimiter
 *
 * static Collector<CharSequence, ?, String> joining (CharSequence delimiter,
 * CharSequence prefix, CharSequence suffix)
 * Вернет Collector который объединит элементы потока в строку разделяя
 * указанным delimiter. В начале результирующей строки будет добавлен prefix, в
 * конце suffix.
 *
 *
 */

package javaCore34.streamAPI_8КлассCollectors.sample1;

import java.io.File;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //                      Методы для сбора в Collection
        //Todo  В данном примере использован метод toList() для сбора элементов потока в реализацию List.

        List<Integer> listNumber = List.of(1, 2, 3, 4, 5);
        List<Integer> result = listNumber.stream()
                .filter(a-> a%2==0)
                .collect(Collectors.toList());
        System.out.println(result);
        //---------------------------------------------------------------------------------------------------------

        //Todo Пример применения метода toMap
        //В этом примере демонстрируется применение метода toMap. Ключи результирующей карты
        //генерируются на основании данных потока с помощью Function.identity() т. е. элементы потока
        //становятся ключами карты. Значения создаются также на основе элементов потока, если элемент
        //потока четный то это строка «even» если не четный то «odd».

        List<Integer> listNumber2 = List.of(1, 2, 3, 4, 5);
        Map<Integer, String> result2 = listNumber2.stream()
                .collect(Collectors.toMap(Function.identity(), a->(a%2==0)?"even":"odd"));

       //                       ак работает метод toMap:

        //   {key : value}  Это ключ - значение в мапе
        //valueMapper делает a->(a%2==0)?"even":"odd"  те здесь значение может быть или even или odd
        //keyMapper  делает Function.identity()
        //Этот метод возвращает реализацию Collector собирающий элементы потока в карту. С помощью
        //keyMapper создаются ключи на основании элементов потока, каждому созданному ключу
        //соответствует значение генерируются с помощью valueMapper на основании элемента потока.

        //------------------------------------------------------------------------------------------------------------

        //                           Более сложный пример метода toMap

        //Todo В данном примере элементы потока собираются в карту где элементами потока являются два
        // списка (один для четных второй для нечетных чисел). Если ключи получаются одинаковыми то
        // получается новый список путем объединения списков.

        //имеется лист Интеджеров
        List<Integer> listNumber3 = List.of(1, 2, 3, 4, 5);

        //получаем Function для ключа мапы- из каждого элемента списка получаем строку
        // (те ключ в самой результирующей мапе - это String)
        Function<Integer, String> keyMapper = a -> (a % 2 == 0) ? "even" : "odd";

        //получаем Function для значения мапы - каждый элемент списка добавляется в новый список
        // (те значением в самой результирующей мапе - это List<Integer>)
        Function<Integer, List<Integer>> valueMapper = a -> List.of(a);

        //получаем BinaryOperator - работает со List<Integer>, берет 2-а списка(с одинаковым ключем походу) и объединяет их
        BinaryOperator<List<Integer>> mergeFunction = (a, b) -> {
            List<Integer> result3 = new ArrayList<>(a);
            result.addAll(b);
            return result;
        };

        //Supplier создает новую структуру для хранения самого конечного результата
        Supplier<Map<String, List<Integer>>> supplier = HashMap::new;

        //запускаем поток на нашем первоначальном списке
        Map<String, List<Integer>> result3 = listNumber3.stream()
                //в toMap() подаем созданные реализации:
                .collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction, supplier));

        //------------------------------------------------------------------------------------------------------------

        //                           Методы для группировки данных в Map

        //                     Пример применения методов группировки в Map
        //Todo В данном примере произведена группировка чисел на четные и не четные. Т.е. все элементы
        // потока которые благодаря classifier отображаются на один и тот же ключ, автоматически в список
        // связанный с этим ключом.  groupingBy()

        List<Integer> listNumber4 = List.of(1, 2, 3, 4, 5);
        Map<String, List<Integer>> result4 = listNumber4.stream()
                .collect(Collectors.groupingBy(a -> (a % 2 == 0) ? "even" : "odd"));
        System.out.println(result4);//{even=[2, 4], odd=[1, 3, 5]}

        //------------------------------------------------------------------------------------------------------

        //Todo Пример группировки с помощью groupBy() - распределяем файлы по их расширению
        //В данном примере произведена группировка объектов типа File (задают адрес файла в
        //файловой системе) по расширению файла. Т.е. функция classifier каждому файлу ставит в
        //соответствие строку с его расширением. Файлы с одинаковым расширением пакуются в список
        //(значение в результирующей карте).

        //получаем Function, кот из файла делает строку с расширением этого файла
        Function<File, String> classifier = a -> a.getName().substring(a.getName().lastIndexOf(".") + 1);

        //породили поток файлов
        Map<String, List<File>> groupByExt = Arrays.stream(new File("sample_folder").listFiles())
                //метод groupingBy() создал мапу в кот ключи созданны с пом Function<File, String> classifier
                //а в ее значениях списки элементов на основании кот получали ключи соответственно
                .collect(Collectors.groupingBy(classifier));

        System.out.println(groupByExt);
        //получим мапу: {jpg=[sample_folder\1.jpg], txt=[sample_folder\1.txt, sample_folder\2.txt], pdf=[sample_folder\1.pdf]}

        //----------------------------------------------------------------------------------------------------------------












    }
}
