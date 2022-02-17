/**
 *                               Stream API Часть 3
 *                      Промежуточные методы для изменения потока
 *
 * Промежуточные методы для изменения потока предназначены для изменения самого
 * потока данных. Это генерация нового потока данных на основании уже существующего
 * потока, слияние нескольких потоков в один, или создание нового потока.
 *
 * ******************************************************************************************************************
 *                   Список абстрактных промежуточных методов для изменения потока
 *
 * <R> Stream<R> map (Function<? super T, ? extends R> mapper)
 * Изменение типа данных потока на основе Function
 *
 * <R> Stream<R> flatMap (Function<? super T, ? extends Stream<? extends R>> mapper)
 * Создает поток на основе одного элемента текущего потока
 *
 * DoubleStream flatMapToDouble (Function<? super T, ? extends DoubleStream> mapper)
 * Создание примитивной специализации
 *
 * IntStream flatMapToInt (Function<? super T, ? extends IntStream> mapper)
 * Создание примитивной специализации
 *
 * LongStream flatMapToLong (Function<? super T, ? extends LongStream> mapper)
 * Создание примитивной специализации
 *
 * ********************************************************************************************************************
 *
 *                Список статических промежуточных методов для изменения потока
 *
 * static <T> Stream<T> concat (Stream<? extends T> a, Stream<? extends T> b)
 * Конкатенация потоков данных
 *
 * static <T> Stream<T> empty()
 * Возвращает пустой поток
 *
 * static <T> Stream<T> generate (Supplier<? extends T> s)
 * Создает поток где элементы создаются Supplier
 *
 * static <T> Stream<T> iterate (T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
 * Создает поток путем применения UnaryOperator к seed, при условии что Predicate вернет true
 *
 * static <T> Stream<T> iterate (T seed, UnaryOperator<T> f)
 * Создает поток путем применения UnaryOperator к seed
 *
 * static <T> Stream<T> of (T t)
 * Поток их одного элемента
 *
 * static <T> Stream<T> of (T... values)
 * Поток из нескольких элементов
 *
 * static <T> Stream<T> ofNullable (T t)
 * Возвращает последовательный Stream, содержащий
 * единственный элемент, если не равен null, в противном
 * случае возвращает пустой Stream.
 *
 * *****************************************************************************************************************
 */
package javaCore34.streamAPI_3ПромежуточныеМетодыДляИзмененияПотока.sample1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        //            Метод <R> Stream<R> map (Function<? super T, ? extends R> mapper)
        //
        //используется для создания нового потока на основании данных существующего потока. Элементы нового
        //потока формируются на основе элементов существующего потока путем применения
        //реализации Function.

        //                       Пример использования map

        List<String> list = List.of("Java", "Python", "Fortarn", "C");
        Stream<Integer> stream = list.stream().map(n -> n.length());
        stream.forEach(n -> System.out.println(n));

        //В данном примере происходит изменение типа потока данных. На основании списка
        //создается поток строк, но применение метода map создает на его основе поток Integer. На
        //основании каждого элемента в потоке строк, генерируется целое число равное длине
        //строки.

        //-------------------------------------------------------------------------------------------------------

        //                           еще Пример использования map

        Cat cat1 = new Cat("Luska", 5, "White");
        Cat cat2 = new Cat("UmkA", 7, "Black");
        Cat cat3 = new Cat("Barsic", 2, "Red");
        Cat cat4 = new Cat("Kuzia", 3, "Grey");
        Cat[] cats = new Cat[] { cat1, cat2, cat3, cat4 };
        List<String> result = Arrays.stream(cats)//Создание потока данных
                .filter(n -> n.getWeight() < 5)//Промежуточный метод для фильтрации
                .map(n -> n.getName())//Промежуточный метод для преобразования
                .collect(Collectors.toList());//Терминальный метод
        System.out.println(result);

        //В данном примере происходит изменение типа потока данных. На основании списка
        //создается Stream<Cat>. Метод filter оставляет в нем только кошек легче 5 кг, после чего с
        //помощью map изменяется тип потока с типа Cat на тип String.

        //--------------------------------------------------------------------------------------------------------

        //                                        Метод flatMap

        //<R> Stream<R> flatMap (Function<? super T, ? extends Stream<? extends R>> mapper) метод
        //который создает на основании каждого элемента данных текущего потока, новый поток
        //при этом укладывая созданные потоки в единый.

        //в конструктор singer1 передаем имя певца и массив с его песнями, кот сразу и инициализируем
        Singer singer1 = new Singer("Freddie Mercury", new String[] { "We Are the Champions", "Somebody to Love" });
        Singer singer2 = new Singer("David Bowie",new String[] { "Space Oddity", "Let Me Sleep Beside You", "Suffragette City" });
        Singer singer3 = new Singer("James Paul McCartney", new String[] { "Can’t Buy Me Love", "Another Girl" });

        //создаем массив rockStars из объектов Singer
        Singer[] rockStars = new Singer[] { singer1, singer2, singer3 };

        List<String> song = Arrays.stream(rockStars)//порождаем поток на массиве rockStars
                .flatMap(n-> Arrays.stream(n.getSongs()))//Метод flatMap
                .collect(Collectors.toList());
        System.out.println(song);
        //Вывод: [We Are the Champions, Somebody to Love, Space Oddity, Let Me Sleep Beside You, Suffragette City, Can’t Buy Me Love, Another Girl]

        //В данном примере происходит изменение типа потока данных. На основании потока
        //исполнителей создается поток строк с названием песен. Причем на основании каждого
        //исполнителя создается отдельный поток строк. Но эти потоки упаковываются в единый
        //поток.
        //ТО БЫЛ ПОТОК ОБЪЕКТОВ Singer(ОН ИСЧЕЗ), А СТАЛ ПОТОК ИХ ПОЛЯ songs

        //----------------------------------------------------------------------------------------------------------

        //                     Методы flatMapToDouble, flatMapToInt, flatMapToLong
        //Методы:
        //DoubleStream flatMapToDouble (Function<? super T, ? extends DoubleStream> mapper)
        //IntStream flatMapToInt (Function<? super T, ? extends IntStream> mapper)
        //LongStream flatMapToLong (Function<? super T, ? extends LongStream> mapper)
        //Данные методы порождают потоки примитивного типа (примитивные специализации Stream) на
        //основании каждого элемента текущего потока. Порожденные потоки «укладываются» в общий
        //порожденный поток.

        String[] array = new String[] { "C", "Java", "Fortran" };
        IntStream stream2 = Arrays.stream(array).flatMapToInt(n -> n.codePoints());
        stream2.forEach(n -> System.out.println(n));

        //В примере на основе каждого элемента базового потока Stream<String> создается поток целых
        //чисел (кодировка каждого символа в этой строке) и созданные потоки укладываются в один
        //результирующий поток.
        //ТО БЫЛ ПОТОК ОБЪЕКТОВ String: "C", "Java", "Fortran" (ОН ИСЧЕЗ), А СТАЛ ПОТОК ИХ кодировок (codePoints())

        //-----------------------------------------------------------------------------------------------------------

        //                            Статический метод concat
        //Метод static <T> Stream<T> concat (Stream<? extends T> a, Stream<? extends T> b)
        //Данный метод применяется для конкатенации потоков. Т.е. объединение данных двух потоков в
        //один поток. Если используются потоки с сохранением порядка то данные потока b следуют после
        //данных потока a.

        List<Integer> list1 = List.of(0, 2, 4, 6);
        List<Integer> list2 = List.of(1, 3, 5, 7);
        Stream<Integer> stream3 = list1.stream();
        Stream<Integer> stream4 = list2.stream();
        Stream<Integer> concatStream = Stream.concat(stream3, stream4);//Объединение потоков
        concatStream.forEach(n -> System.out.println(n));

       // В примере показана конкатенация двух потоков целых чисел. На основании списков целых чисел
        //создаются два потока, которые и объединяются в один с помощью статического метода concat.
        //СНАЧАЛА БУДЕТ ВЫВОД ИЗ ПЕРВОГО ПОТОКА, ПОТОМ ИЗ ВТОРОГО

        //-------------------------------------------------------------------------------------------------------------

        //                              Статический метод empty()
        //Метод static <T> Stream<T> empty()
        //Данный метод применяется для создания пустого потока. Создается поток в котором отсутствуют
        //элементы данных.

        Stream<Integer> stream5 = Stream.empty();//Создание пустого потока данных
        stream5.forEach(n -> System.out.println(n));

       // В примере продемонстрировано создание пустого потока. В чем можно убедится использовав
       // любой из терминальных методов.

        //--------------------------------------------------------------------------------------------------------

        //                 Статический метод generate (Supplier<? extends T> s)
        //Метод static <T> Stream<T> generate (Supplier<? extends T> s)
        //Данный метод применяется для создания бесконечного потока данных на основании данных
        //генерируемых с помощью реализации Supplier. Внимание созданный поток бесконечен, поэтому в
        //случае необходимости собрать данных потока в структуру данных его необходимо ограничить
        //(например с помощью метода limit).

        //поток порождаем с пом метода generate, в аргументы которого передаем Supplier-(генератор чего-либо), запуская статик метод- см ниже
        Stream<Integer> stream6 = Stream.generate(getRandomNumber(1, 10));
        //перед терминальным методом применяем ограничитель limit(10)-первые 10 элементов, иначе бесконечно
        List<Integer> list3 = stream6.limit(10).collect(Collectors.toList());
        System.out.println(list3);

        //--------------------------------------------------------------------------------------------------------

        //         Статический метод iterate (T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
        //
        //Метод static <T> Stream<T> iterate (T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
        //Данный метод применяется для создания потока данных на основании данных генерируемых с
        //помощью применения реализации UnaryOperator к seed, данные будут генерироваться до тех пор пока
        //Predicate возвращает true. Важное замечание что next применяется к результату работы предыдущего
        //вызова этого же метода. seed это начальное значение задаваемое явно.

        Stream<Integer> stream7 = Stream.iterate(1, n -> n <= 10, n -> n + 1);
        stream7.forEach(n -> System.out.println(n));
        //итерируемся с пом метода iterate
        //Начальное значение: 1
        //Условие продолжения: n -> n <= 10 --- Предикат
        //Изменение на каждом: шаге n -> n + 1 --- UnaryOperator (extend Function)

        //С помощью метода iterate получаем Stream<Integer>. В качестве начального значения
        //используется значение 1. На каждом шаге это значение увеличивается на единицу. Условием
        //продолжения является условие что значение меньше 10. По сути этот метод создает поток по
        //аналогии с циклом for. И переменная цикла и становится элементом данных потока.

        //-------------------------------------------------------------------------------------------------------------

        //             Статический метод iterate (T seed, UnaryOperator<T> f)

        //Метод static <T> Stream<T> iterate (T seed, UnaryOperator<T> f)
        //Данный метод применяется для создания бесконечного потока данных на основании данных
        //генерируемых с помощью применения реализации UnaryOperator к seed. Важное замечание что next
        //применяется к результату работы предыдущего вызова этого же метода. seed это начальное значение
        //задаваемое явно. Создаваемый поток бесконечен, поэтому его стоит ограничивать (например с
        //помощью метода limit).

        Stream<String> stream8 = Stream.iterate("A", n -> n + n);//iterate без проверки условия
        stream8.limit(4).forEach(n -> System.out.println(n));
        //Вывод:
        //A
        //AA
        //AAAA
        //AAAAAAAA

        //С помощью метода iterate получаем Stream<String>. В качестве начального значения
        //используется значение «A». На каждом шаге к строке дописывается результат прошлого вызова. Но
       // условие прекращения генерации отсутствует. Поэтому такой вид потока нуждается в ограничении.

        //------------------------------------------------------------------------------------------------------

        //                                         Методы of
        //Методы
        //static <T> Stream<T> of (T t)
        //static <T> Stream<T> of (T... values)
        //Данные методы создают поток или на основании одного элемента или на основании нескольких
        //элементов. По сути это просто еще один способ создания потока на основании имеющихся данных.

        Stream<String> stream9 = Stream.of("Hello", "World"); //Создание потока на основании данных
        List<String> list9 = stream9.collect(Collectors.toList());
        System.out.println(list9);

        //В данном примере создается поток строк на основании двух элементов «Hello», «World».

        //--------------------------------------------------------------------------------------------------------

        //Метод ofNullable (T t)
        //Метод
        //static <T> Stream<T> ofNullable (T t)
        //Данный метод создает поток на основании одного элемента. Если он не равен null то поток будет
        //содержать единственный элемент, в противном случае будет получен пустой поток.

        Stream<String> stream11 = Stream.ofNullable("Hello");
        stream11.forEach(n -> System.out.println(n));
        Stream<String> stream22 = Stream.ofNullable(null);
        stream22.forEach(n -> System.out.println(n));

       // В примере создаются два потока. Первый содержит один элемент, а второй получается пустым.


    }//закрыли главный метод

    //реализация Supplier с пом локального класса. метод возвращает объкт класса, который implements Supplier<Integer>
    //и генерит случайные числа в заданном диапазоне
    public static Supplier<Integer> getRandomNumber(int start, int end) {

        class RandGen implements Supplier<Integer> {//локальный класс- имеет доступ к аргументам метода
            @Override
            public Integer get() {
                return (int) (start + Math.random() * (end - start) + 1);
            }
        } return new RandGen();
    }

}//закрыли главный класс
