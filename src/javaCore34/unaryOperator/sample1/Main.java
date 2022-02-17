package javaCore34.unaryOperator.sample1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

//Функциональный интерфейс
//UnaryOperator, наследник Function<T, T>
public class Main {

    public static void main(String[] args) {

        //           Абстрактный метод интерфейса UnaryOperator<T>:
        //В качестве абстрактного метода интерфейса UnaryOperator<T> выступает метод T apply (T t).
        // Его реализация проводит операцию над объектом ссылка на который
        //выступает в качестве параметра и возвращает результат такого же типа. Так, как
        //интерфейс функциональный то его можно реализовать всеми средствами для реализации
        //обычных интерфейсов так и с помощью лямбда функций и ссылок на методы.

        //        Пример реализации UnaryOperator с помощью класса:

        //ссылке типа интерфейс присваиваем реализацию абстракта UnaryOperator
        UnaryOperator<String> unOp1 = new UOp();
        //через эту ссылку вызываем абстракт
        System.out.println(unOp1.apply("Hello World"));//Использование метода этого интерфейса


        //       Пример реализации UnaryOperator с помощью лямбда функции и ссылки на метод:

        UnaryOperator<Integer> unOp11 = (a) -> -a;//Реализация с помощью лямбда функции //меняем знак Интеджера
        UnaryOperator<Integer> unOp2 = Main::negative;//Реализация с помощью ссылки на метод //меняем знак Интеджера
        System.out.println(unOp11.apply(3));
        System.out.println(unOp2.apply(3));

        //       Подробнее о реализации с помощью лямбда функции:
        //сопоставляем UnaryOperator<Integer> fun1 = a -> -a;   с   Методом интерфейса:   Integer apply (Integer t)
        //где первая а это аргумент абстракта - (Integer t), вторая -а это Результат Integer - созданный на основе параметра


        //           UnaryOperator<T> в стандартной библиотеке:
        //В интерфейсе List<T> определен метод
        //default void replaceAll (UnaryOperator<E> operator)
        //Данный метод заменяет все элементы списка, применяя к каждому элементу списка
        //реализацию UnaryOperator.

        //          Использование метода replaceAll:
        //В примере все элементы списка целых чисел заменяются на их удвоенные значения.

        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3));
        UnaryOperator<Integer> unOp3 = a -> 2 * a;
        list1.replaceAll(unOp3);//Использование метода replaceAll- все элементы массива умножили на 2
        System.out.println(list1);


        //                    Метод andThen:
        //В UnaryOperator объявлен метод по умолчанию:
        //default <V> Function<T, V> andThen (Function<? super T, ? extends V> after))
        //Этот метод принимает в качестве параметра реализацию Function тип входного
        //параметра которого совместим с типом результата текущей реализации UnaryOperator. В
        //итоге мы получим новую реализацию Function которая будет композицией текущей
        //реализации UnaryOperator и Function который будет использован в качестве параметра.
        //Первым будет вычислен результат текущей реализации UnaryOperator, после чего
        //реализация Function использует результат как входной параметр и на ее основе вернет
        //новое значение.

        //            Пример использования метода andThen:
        UnaryOperator<String> unOp = a -> a.strip();//UnaryOperator
        Function<String, Integer> fun = a -> a.length();//Function
        Function<String, Integer> result = unOp.andThen(fun);//Использование andThen
        System.out.println(result.apply(" hello "));
        //В примере показана композиция из UnaryOperator и реализации Function. Сначала
        //реализация UnaryOperator отбросит пробелы в строке, после чего полученная строка
        //будет использована в Function. те UnaryOperator можно соединять с Function, тк он его наследник


        //                     Метод compose:
        //В UnaryOperator объявлен метод по умолчанию:
        //default <V> Function<V, T> compose (Function<? super V, ? extends T> before)
        //Этот метод принимает в качестве параметра реализацию Function тип результата
        //которого совместим с типом результата текущей реализации UnaryOperator. В итоге мы
        //получим новую реализацию Function которая будет композицией текущей реализации
        //UnaryOperator и Function. Первым будет вычислен результат текущей реализации Function,
        //после чего реализация UnaryOperator использует результат как входной параметр и на ее
        //основе вернет новое значение.

        //        Пример использования метода compose:
        Function<String, Integer> fun5 = a -> a.length();
        UnaryOperator<Integer> unOp5 = a -> 2 * a;
        Function<String, Integer> result5 = unOp5.compose(fun5);
        System.out.println(result.apply("hello"));
        //В примере показана композиция из UnaryOperator и реализации Function. Сначала
        //реализация Function вычислит длину строки, после чего полученное целое число будет
        //удвоенно реализацией UnaryOperator.


        //           Статические методы identity:
        //В UnaryOperator объявлены статический метод:
        //static <T> UnaryOperator<T> identity()
        //Этот статический метод возвращает реализацию UnaryOperator которая возвращает
        //значение переданное в качестве параметра.

        //          Использование метода identity:
        UnaryOperator<Integer> unOp6 = UnaryOperator.identity();
        System.out.println(unOp6.apply(5));
        //В примере показана работа метода identity который вернет реализацию UnaryOperator
        //возвращающий значение равное входящему параметру.


        //              Примитивные специализации UnaryOperator:

        //        Название                      Описание
        //IntUnaryOperator                 Использует в качестве параметра тип int и возвращает значение типа int.
        //LongUnaryOperator                Использует в качестве параметра тип long и возвращает значение типа long.
        //DoubleUnaryOperator              Использует в качестве параметра тип double и возвращает значение типа double.

        //Эти интерфейсы используются для работы с примитивными типами. Абстрактные
        //методы этих интерфейсов получили сигнатуру по определенному шаблону:
        //         {тип} applyAs{Тип} (тип)
        //         Например для интерфейса IntUnaryOperator этот метод имеет вид
        //         int applyAsInt(int operand)

        //         Пример использования метода IntUnaryOperator:
        IntUnaryOperator unOp7 = Main::factorial;//IntUnaryOperator
        System.out.println(unOp7.applyAsInt(5));


        //          Другие методы примитивных специализаций UnaryOperator:
        //Примитивные специализации содержат методы для получения композиций. Т.е.
        //интерфейсы IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator содержат
        //следующие методы:
        //Методы по умолчанию:
        //● andThen
        //● compose
        //Статические методы:
        //● identity
        //Методы по умолчанию возвращают композицию реализаций. Различаются только
        //типом используемого параметра характерном для своего типа примитивной
        //специализации. Так например для IntUnaryOperator эти методы имеют вид:
        //default IntUnaryOperator andThen (IntUnaryOperator after)
        //default IntUnaryOperator compose (IntUnaryOperator before)
        //static IntUnaryOperator identity()

        //     Пример использования IntUnaryOperator:
        IntUnaryOperator unOp111 = a -> a + 1;
        IntUnaryOperator unOp222 = a -> 2 * a;
        IntUnaryOperator result2 = unOp111.andThen(unOp222);
        System.out.println(result2.applyAsInt(3));
        //Пример композиции реализаций IntUnaryOperator. При использовании метода andThen
        //сначала выполнится текущая реализация, после чего результат используется в качестве
        //входного параметра для реализации IntUnaryOperator которая использовалась в качестве
        //параметра метода andThen.

    }//закрыли метод Main

    //Реализация абстракта с помощью static метода
    public static Integer negative(Integer number) {
        return -number;
    }

    //Реализация абстракта с помощью static метода. Метод реализующий IntUnaryOperator.
    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        } return result;

    }//закрыли главный класс



}
    //Реализация абстракта с помощью класса
    class UOp implements UnaryOperator<String> {
        @Override
        public String apply(String t) {
            return t.toLowerCase();
        }
    }



/**
 *                  Функциональный интерфейс UnaryOperator:
 *
 *                       UnaryOperator в Java:
 * UnaryOperator<T> - функциональный интерфейс появившийся в Java 8. Является
 * наследником функционального интерфейса Function<T,T>. Описывает операцию над
 * параметром и возврат результата такого же типа. Для работы необходимо импортировать
 * пакет java.util.javaCore34.function.UnaryOperator
 *
 *                  Список методов интерфейса UnaryOperator<T>:
 *
 * T apply(T t)
 * Производит операцию на основе объекта, ссылка на
 * который выступает в качестве параметра, и возвращает
 * результат такого же типа.
 *
 * default <V> Function<T, V> andThen (Function<? super T, ? extends V> after))
 * Возвращает композицию реализации UnaryOperator и
 * Function. Сначала будет выполнена текущая реализация
 * UnaryOperator а результат будет использован в качестве
 * входящего параметра реализации Function.
 *
 * default <V> Function<V, T> compose (Function<? super V, ? extends T> before)
 * Возвращает композицию реализации UnaryOperator и
 * Function. Сначала будет выполнена текущая реализация
 * Function а результат будет использован в качестве
 * входящего параметра реализации UnaryOperator.
 *
 * static <T> UnaryOperator<T> identity()
 * Возвращает UnaryOperator возвращающий значение
 * переданное в качестве параметра.
 *
 */
