package javaCore34.function.sample2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

//Пример использования Function в качестве параметра параметризированного метода
public class Main {

    public static void main(String[] args) {
        //-----------------------------------------------------------------------------------------------------
           //  Пример использования Function в качестве параметра параметризированного static метода applyAndCreate:
        //имеется лист:
        List<String> list = List.of("Java", "Python", "Fortran", "C");

        //создали ссылку на реализацию(приходит String, уходит Integer):
        Function<String, Integer> fun = a -> a.length();

        //Создание одного списка на основании другого с пом. static метода applyAndCreate:
        List<Integer> resList = applyAndCreate(fun, list);

        System.out.println(resList);
        //-----------------------------------------------------------------------------------------------------
        //Еще один пример использования Function в качестве параметра ТОГО ЖЕ параметризированного static метода applyAndCreate:
        //у нас есть лист с числами
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        //создали ссылку на реализацию(приходит Integer, уходит BigInteger):
        Function<Integer, BigInteger> fun2 = n -> {
            BigInteger factorial = new BigInteger("1");//создали BigInteger с первоначальным значением- 1
            for (int i = 1; i <= n; i++) {//итерируемся до <= n, где n - это Интеджер на входе в Function- берем из листа с числами
                factorial = factorial.multiply(new BigInteger(Integer.toString(i)));//4!=1*2*3*4=24
            } return factorial;//возвращается факториал
        };
        //вызываем static метод applyAndCreate передавая ему в аргументы другую ссылку на реализацию и другой массив
        //и тк этотметод параметризован-универсален- он отработает
        List<BigInteger> resList2 = applyAndCreate(fun2, numbers);
        System.out.println(resList2);
        //В этом примере используется тот же метод public static <R, T> List<R>
        //applyAndCreate(Function<T, R> fun, List<T> list). Но теперь на основе
        //списка чисел создается список чисел равный факториалу чисел в исходном списке.
        //-----------------------------------------------------------------------------------------------------

    }//закрыли главный метод

    //параметризованный-универсальный static метод, принимает ссылку на реализацию - Function в качестве параметра и list:
    public static <R, T> List<R> applyAndCreate(Function<T, R> fun, List<T> list) {

        List<R> resultList = new ArrayList<>();//создаем новый Аррайлист

        for (T t : list) {//итерируемся по листу, который пришел в аргументы

            //Применяем fun к каждому элементу входящего списка:
            resultList.add(fun.apply(t));//те в новом Аррай листе будут Интеджеры равные длине строки элемента в пришедшем листе
        }
        return resultList;//возвращаем новый Аррайлист
    }
}
