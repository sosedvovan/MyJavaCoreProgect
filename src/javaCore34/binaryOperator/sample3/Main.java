package javaCore34.binaryOperator.sample3;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

//Метод andThen
public class Main {

    public static void main(String[] args) {

        //                        Метод andThen:
        //В BinaryOperator объявлен метод по умолчанию:
        //default <V> BiFunction<T,T,V> andThen (Function<? super T, ? extends V> after)
        //Этот метод принимает в качестве параметра реализацию Function тип входного
        //параметра которого совместим с типом результата текущей реализации BinaryOperator. В
        //итоге мы получим новую реализацию BiFunction которая будет композицией текущей
        //реализации BinaryOperator и Function. Первым будет вычислен результат текущей
        //реализации BinaryOperator, после чего реализация Function использует результат как
        //входной параметр и на ее основе вернет новое значение.

        //получаем ссылку на реализацию
        BinaryOperator<String> binOp1 = (a, b) -> a + b;//BinaryOperator
        //получаем ссылку на реализацию
        Function<String, Integer> func1 = a -> a.length();//Function
        ////получаем ссылку на реализацию с пом композиции с пом andThen
        BiFunction<String, String, Integer> biFunc = binOp1.andThen(func1);//сначала вычисляется binOp1, после на его результате - func1
        //применяем абстракт интерфейса, вызывая его через ссылку на композицию
        Integer result = biFunc.apply("Hello", "World");
        System.out.println(result);


        //             Статические методы maxBy, minBy:
        //В BinaryOperator объявлены статические методы:
        //static <T> BinaryOperator<T> minBy (Comparator<? super T> javaCore34.comparator)
        //static <T> BinaryOperator<T> maxBy (Comparator<? super T> javaCore34.comparator)
        //Эти статические методы возвращают реализацию BinaryOperator которая возвращает
        //минимальное и соответственно максимальное значение из двух параметров. За сравнение
        //отвечает реализация Comparator который используется в качестве параметра.

        //реализуем компоратор для строк
        Comparator<String> cmp = (a, b) -> Integer.compare(a.length(), b.length());
        //получим ссылку на реализацию BinaryOperator соотнесенным с компоратором, используя его статический метод
        BinaryOperator<String> binOp = BinaryOperator.maxBy(cmp);//Вызов maxBy
        String result2 = binOp.apply("Java", "Python");//запустим абстракт на ссылке типа интерфейс
        System.out.println(result2);
        //В примере показано использование метода maxBy. Реализация Comparator
        //сравнивает строки по длине строки. Полученный BinaryOperator возвращает более
        //длинную строку из двух переданных в качестве параметров.


        //                   Примитивные специализации BinaryOperator:

        //       Название                           Описание
        //IntBinaryOperator            Использует в качестве параметров тип int и возвращает значение типа int.
        //LongBinaryOperator           Использует в качестве параметра тип long и возвращает значение типа long.
        //DoubleBinaryOperator         Использует в качестве параметра тип double и возвращает значение типа double.

        //Эти интерфейсы используются для работы с примитивными типами. Абстрактные
        //методы этих интерфейсов получили сигнатуру по определенному шаблону:
        //{тип} applyAs{Тип} (тип, тип)
        //Например для интерфейса IntBinaryOperator этот метод имеет вид
        //int applyAsInt(int left, int right)

        //                Пример использования IntBinaryOperator:

        IntBinaryOperator binOp3 = Main::gcd;//Реализация IntBinaryOperator
        System.out.println(binOp3.applyAsInt(20, 15));


    }//закрыли главный метод

    //Реализация IntBinaryOperator
    //здесь будем искать наибольший делитель
    public static int gcd(int a, int b) {
        a = Math.abs(a);//возьмем модуль
        b = Math.abs(b);//возьмем модуль
        if (b > a) {//если b > a, то меняем из значения местами
            int temp = a;
            a = b;
            b = temp;
        }
        int r = a % b;//берем остаток от деления на меньшее из двух, пришедших в аргументы чисел
        for (; r != 0; ) {//цикл будет работать, пока r не равно 0
            a = b;//далее логика подбора наибольшего делителя
            b = r;
            r = a % b;
        }
        return b;
    }

}
