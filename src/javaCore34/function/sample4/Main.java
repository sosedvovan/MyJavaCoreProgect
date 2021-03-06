package javaCore34.function.sample4;

import java.util.function.BiFunction;
import java.util.function.Function;

//Бинарная специализация Function в виде BiFunction
public class Main {


    //            Абстрактный метод интерфейса BiFunction<T,U,R>:
    //В качестве абстрактного метода интерфейса BiFunction<T,U,R> выступает метод R
    //apply (T t, U u). Его реализация и должна вернуть ссылку на объект (типа R) созданный на
    //основе объекта, ссылка на который выступает первым параметром в методе (ссылка типа
    //T) и объекта ссылка на который выступает вторым параметром (ссылка типа U). По сути
    //является специализацией для использования двух входящих параметров
    public static void main(String[] args) {
        //параметризированной ссылке на реализацию параметризированного интерфейса присвоим:
        BiFunction<String, Character, Integer> countLetter = Main::count;//Реализация ссылкой на static метод count (ищи внизу)
        //через ссылку на параметризированный интерфейс запустим абстракт этого интерфейса-
        System.out.println(countLetter.apply("Hello", 'l'));//-передав в параметры 2-а объекта- как того требует сигнатура
        //получим  Вывод: 2    - те 2-а чара 'l' в слове "Hello".

        //----------------------------------------------------------------------------------------------------
        //                                      Метод andThen:
        //В BiFunction объявлен метод по умолчанию:
        //default <V> BiFunction<T, U, V> andThen (Function<? super R, ? extends V> after)
        //Этот метод принимает в качестве параметра реализацию Function тип входного
        //параметра которого совместим с типом результата текущей реализации BiFunction. В итоге
        //мы получим новую реализацию BiFunction которая будет композицией двух функций.
        //Первой будет вычисляться текущая функция, после чего вторая функция использует
        //результат текущей как входной параметр и на ее основе вернет новое значение.

        //                               Пример применения andThen:

        //BiFunction fun1 -на входе (в методе apply(text, reg)) две строки, на выходе массив строк:
        BiFunction<String, String, String[]> fun1 = (a, b) -> a.split(b);//первую строку a (String text = "Java the best";)
                                             //разбиваем по пробелам с помощью регулярного выражения b (String reg = "\\s";)

        //Function fun2 -на входе массив строк из предидущего выхода, на выходе- строка:
        Function<String[], String> fun2 = a -> String.join("", a);//join-соединяет элементы массива в строку без пробелов ("")
        //в методе andThen на результате работы fun1 выполняется еще и fun2

        //Использование andThen: fun1-разобьет строку по пробелам, потом fun2 соберет строку БЕЗ пробелов -> убрали пробелы
        BiFunction<String, String, String> fun3 = fun1.andThen(fun2);
        String text = "Java      the best";
        String reg = "\\s+"; //разбить по одному или большему кол-ву пробелов- регулярное выражение
       // String reg2 = "";
        String result2 = fun3.apply(text, reg);
        System.out.println(result2);
    }


   //реализуем абстрактный метод интерфейса BiFunction<T,U,R> (из строки и чара получим Интеджер)
    public static Integer count(String letter, Character l) {
        int result = 0;//счетчик
        char[] ls = letter.toCharArray();//строку поделим на чары и поместим в массив
        for (int i = 0; i < ls.length; i++) {
            if (ls[i] == l) {//каждый элемент массива проверим на равенство с чаром, который подали в аргументы метода
                result++;//если есть равенство- плюсуем счетчик
            }
        } return result;//вернем Интеджер- счетчик
    }

    //                      Пояснение реализации BiFunction:
    //рассмотрим ссылку:    BiFunction<String, Character, Integer> countLetter
    //String и Character в нашем примере- входяшие аргументы в абстракте и методе count его реализующем
    //Integer- это возвращаемый объект в абстракте и методе count его реализующем
    //то BiFunction принимает 2-а объекта и на их основе создается третий.


}

/**
 * Function имеет бинарную специализацию (binary specializations) в виде
 * функционального интерфейса BiFunction<T, U, R>. Бинарная специализация это вариация
 * интерфейса описывающая методы принимающие два параметра. Т.е. теперь результат
 * который вернет реализация BiFunction вычисляется на основе двух параметров T t и U u.
 *
 *                      Список методов интерфейса BiFunction<T, U,R>:
 *           Метод                                                             Описание
 * R apply (T t, U u)                                      Создание объекта типа R, на основании объектов
 *                                                         ссылки (T t, U u) на которые используются в качестве
 *                                                         параметра.
 *
 * default <V> BiFunction<T, U, V> andThen (Function<? super R, ? extends V> after)
 *                                                         Создание композиции из реализаций BiFunction.
 *                                                         Первой вызывается текущая реализация, а второй
 *                                                         используемая в качестве параметра.
 *                                                         есть отличия от небинарной реализации этого метода.
 */
