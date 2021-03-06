/**
 *                    Stream API Часть 6
 *             Аккумулирующие терминальные методы
 *
 * Часть терминальные методов используются для аккумулирования результатов на
 * основании потока данных. Т.е. начиная с некоторого начального значения к каждому
 * элементу потока применяется аккумулирующая функция.
 * ********************************************************************************************
 *    Список абстрактных терминальных методов (те это те методы кот можем встроить в stream)
 *
 * Optional<T> reduce (BinaryOperator<T> accumulator)
 * Реализует аккумулирующую операцию
 *
 * T reduce (T identity, BinaryOperator<T> accumulator)
 * Реализует аккумулирующую операцию
 *
 * <U> U reduce (U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
 * Реализует аккумулирующую операцию
 * *********************************************************************************************
 */

package javaCore34.streamAPI_6АккумулирующиеТерминальныеМетоды.sample1;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class Main {

    public static void main(String[] args) {

        //          Метод: Optional<T> reduce (BinaryOperator<T> accumulator)

        //Создает результат аккумулируя элементы потока. В качестве базового элемента берется первый
        //элемент потока и новый результат получается применение accumulator к базовому и следующему
        //элементу потока. Вычисленный результат становиться базовым и вычисления повторяются для
        //следующего элемента.

        //имеется список, надо получить сумму всех четных элементов
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        //получим реализацию BinaryOperator
        BinaryOperator<Integer> bop = (a, b) -> a + b;

        Optional<Integer> sum = list.stream()//создадим поток, возвращает Optional
                .filter(a->a%2==0)//отсеим четные
                .reduce(bop);//в reduce подадим реализацию BinaryOperator-те будем суммировать элементы
        System.out.println(sum.get());//20

        //В этом примере метод reduce используется для получения суммы всех четных чисел из потока.
        //BinaryOperator реализует сумму двух элементов. После filter в потоке остаются только четные числа.
        //Базовым элементом выбирается первый элемент в потоке. Это число 2, далее происходит
        //накопление с использованием сложения.

        //[2, 4, 6, 8]
        //Выбираем в качестве начального значения value = 2 //value - дополнительная аккумулирующая переменная
        //[2, 4, 6, 8]
        //Новое значение value = value + 4 = 6
        //[2, 4, 6, 8]
        //Новое значение value = value + 6 = 12
        //[2, 4, 6, 8]
        //Новое значение value = value + 8 = 20
        //20

        //------------------------------------------------------------------------------------------------

        //                        Еще одно применение reduce

        //имеется список строк, надо найти самую длинную
        List<String> worlds = List.of("Java", "Fortran", "Python", "C++");
        //получим реализацию BinaryOperator
        BinaryOperator<String> bop2 = (a, b) -> a.length() > b.length() ? a : b;
        //создадим поток, возвращает Optional
        Optional<String> result = worlds.stream()
                .reduce(bop2);//в reduce подадим реализацию BinaryOperator-возвращает более длинный элемент из двух
        System.out.println(result.get());//Fortran

        //В этом примере метод reduce используется для поиска максимального элемента в потоке (самую
        //длинную строку). BinaryOperator реализуется как вернуть из двух значений то длинна которого
        //больше. Таким образом используя reduce можно реализовать поиск максимального и минимального
        //элемента в потоке.

        //----------------------------------------------------------------------------------------------------

        //                Метод T reduce (T identity, BinaryOperator<T> accumulator)

        //Создает результат аккумулируя элементы потока. В качестве базового элемента берется первый
        //параметр метода и новый результат получается применение accumulator к базовому и следующему
        //элементу потока. Вычисленный результат становиться базовым и вычисления повторяются для
        //следующего элемента. Разница с предыдущим методом в том какой элемент выбирается в качестве
        //базового.

        //имеется лист, надо объединить его элементы в одну строку, добавив в начало базовый элемент "Hello"
        List<String> list2 = List.of("world", "!");
        //породим поток, возвращает String
        String result2 = list2.stream()
                .reduce("Hello", (a, b) -> a + " " + b);//reduce принимает базовый элемент и Фанкшионал
        System.out.println(result2);//Hello world !

        //В примере реализована конкатенация элементов потока. В качестве начального элемента
        //используется строка «Hello».

        //--------------------------------------------------------------------------------------------------


    }
}
