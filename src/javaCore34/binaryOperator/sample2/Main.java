package javaCore34.binaryOperator.sample2;

import java.util.List;
import java.util.function.BinaryOperator;

//Пример использования BinaryOperator в качестве параметра метода
public class Main {

    public static void main(String[] args) {
        //имеется список
        List<Integer> list1 = List.of(1, 2, 3);
        //инициализируем ссылку на реализацию абстракта функционального интерфейса:
        BinaryOperator<Integer> binOp1 = (a, b) -> a + b;//Реализация лямбдой
        //вызываем наш статик метод, передавая в аргументы в том числе и BinaryOperator
        Integer s1 = reduce(list1, binOp1, 0);
        System.out.println(s1);//6  -  сумма элементов списка

        List<String> list2 = List.of("Hello", "Java", "world");
        BinaryOperator<String> binOp2 = (a, b) -> a + " " + b;
        String s2 = reduce(list2, binOp2, "");
        System.out.println(s2);//Hello Java world    - из элементов списка получили одну строку
    }

    public static <T> T reduce(List<T> list, BinaryOperator<T> binOp, T startValue) {//в аргументах BinaryOperator в качестве параметра
        T result = startValue;//завели суммирующий счетчик,инициализировав заданным начальным значением из аргументов метода
        for (T element : list) {//итерируемся по листу, который пришел в аргументы
            result = binOp.apply(result, element);//Применяем BinaryOperator, Реализованный лямбдой
        }
        return result;//возвращаем счетчик с результатом свертки
    }

    //       Объяснение метода reduce, описанного ранее:
    //Метод реализует функцию свертки списка.
    //Свертка списка (англ. folding, также известна как reduce, accumulate) - функция высшего
    //порядка, которая производит преобразование структуры данных к единственному
    //атомарному значению при помощи заданной функции. Операция свёртки часто
    //используется в функциональном программировании при обработке списков.
    //Функция свёртки обычно принимает три аргумента: комбинирующую функцию f,
    //начальное значение start и структуру данных seq (список — в простейшем варианте).
    //Именно эта функция реализована методом:
    //public static <T> T reduce(List<T> list, BinaryOperator<T> binOp, T startValue) {...}
    //где:
    //List<T> - список
    //BinaryOperator<T> - комбинирующая функция
    //startValue — начальное значение
}
