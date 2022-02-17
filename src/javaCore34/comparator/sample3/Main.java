package javaCore34.comparator.sample3;

import javaCore34.comparator.sample1.Cat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

//Статические Методы в Comparator:
public class Main {
    Cat minAgeCat;

    public static void main(String[] args) {

        //             Статические Методы nullsFirst, nullsLast:
        //В Comparator объявлены статические методы:
        //static <T> Comparator<T> nullsFirst (Comparator<? super T> javaCore34.comparator)
        //static <T> Comparator<T> nullsLast (Comparator<? super T> javaCore34.comparator)
        //Эти методы принимают в качестве параметра реализацию Comparator и возвращают
        //«дружественный» к значению null Comparator. Этот Comparator сначала проверяет ссылки
        //на null и если обе ссылки не равны null вызывает Comparator который был параметром
        //метода. Если же хотя бы один из параметров равен null то возвращает результат вида:
        //● nullsFirst значение null всегда меньше
        //● nullsLast значение null всегда больше

        //            Использование Comparator<T> в стандартной библиотеке:
        //В классе Collections определены статические методы:
        //static <T> T max (Collection<? extends T> coll, Comparator<? super T> comp)
        //static <T> T min (Collection<? extends T> coll, Comparator<? super T> comp)
        //Данные методы позволяют найти максимальный и минимальный элемент в
        //реализации Collection с использованием реализации Comparator (тип которого должен
        //быть совместим с типом элементов в коллекции). И для определения минимума и
        //максимума используется Comparator (для попарного сравнения).

        //         Пример случая когда Comparator генерирует исключение:
        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 5);
        Cat cat5 = new Cat("Kuzia", 2);
        List<Cat> cats = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        cats.add(null);
        Comparator<Cat> comp1 = (a, b) -> Integer.compare(a.getAge(), b.getAge());
        Cat minAgeCat = Collections.min(cats, comp1);

        //В примере будет получено исключение NullPointerException. Так, как в списке есть null,
        //а реализация Comparator предполагает вызова метода getAge(), то при попытке вызова
        //будет получено данное исключение.


        //            Пример получения «дружественного» к null Comparator:

        List<Cat> cats2 = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        cats.add(null);
        Comparator<Cat> comp2 = (a, b) -> Integer.compare(a.getAge(), b.getAge());
        Comparator<Cat> resultComp = Comparator.nullsLast(comp2);//Получение дружественного к null Comparator
        Cat minAgeCat2 = Collections.min(cats2, resultComp);

        //В данном примере с помощью статического метода nullsLast мы получаем
        //«дружественный» к null Comparator. Теперь исключение NullPointerException не будет
        //возбужденно, так как сначала будет проведена проверка на то, что обе ссылки не равны
        //null, а только потом вызван Comparator который был определен для сравнения Cat. В
        //примере это comp1.


        //              Статические методы naturalOrder, reverseOrder:
        //В Comparator объявлены статические методы:
        //static <T extends Comparable<? super T>> Comparator<T> naturalOrder()
        //static <T extends Comparable<? super T>> Comparator<T> reverseOrder()
        //Эти методы используются для создания Comparator на основании реализации
        //интерфейса Comparable для указанных типов данных. Метод naturalOrder создает
        //Comparator, а метод reverseOrder создает «обратный» Comparator.

        //Пример применения naturalOrder:

        List<Integer> list1 = List.of(5, 7, -2, 3, 8);
        Comparator<Integer> comp = Comparator.<Integer>naturalOrder();
        Integer min = Collections.min(list1, comp);
        System.out.println(min);

        //В примере показан Comparator созданный на основе реализации интерфейса
        //Comparable для типа Integer.


        //                         Статические методы comparing:
        //В Comparator объявлены перегруженные статические методы:
        //static <T, U extends Comparable<? super U>> Comparator<T> comparing (Function<? super T, ? extends U> keyExtractor)
        //static <T, U> Comparator<T> comparing (Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator)
        //Эти методы используются для создания Comparator.
        //В случае использования метода с одним параметром (Function) Comparator создается
        //на основании реализации интерфейса Comparable для типа данных которые будут
        //возвращены Function.
        //В случае использования метода с двумя параметрами Comparator создается на
        //основании Comparator (второй параметр) для данных которые вернет реализация Function.

        //                   Пример использования метода comparing:

        List<Cat> cats3 = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        Function<Cat, String> func = (a) -> a.getName();//Function//функция возврвщает String, а именно имена котов
        Comparator<String> comp3 = (a, b) -> a.compareTo(b);//Comparator для результата Function//компоратор- сравнивает по String
        Comparator<Cat> resultComp2 = Comparator.comparing(func, comp3);//Создание Comparator//результирующий компаратор,- сравнивает по String имена котов

         minAgeCat = Collections.min(cats3, resultComp2);//создать объект другого класса (Cat) в методе не получилось- вынесли в поле

        //В данном примере показано получение Comparator<Cat> на основе
        //Function<Cat,String> и Comparator<String>. Результирующий Comparator будет выполнять
        //сравнение по именам (за это отвечает Function) в алфавитном порядке ( за это отвечает
        //Comparator<String>).


        //             Статические методы comparingDouble, comparingInt, comparingLong:
        //В Comparator объявлены статические методы:
        //static <T> Comparator<T> comparingDouble (ToDoubleFunction<? super T> keyExtractor)
        //static <T> Comparator<T> comparingInt (ToIntFunction<? super T> keyExtractor)
        //static <T> Comparator<T> comparingLong (ToLongFunction<? super T> keyExtractor)
        //Эти методы принимают в качестве параметра реализацию примитивной
        //специализации Functional (соответствующего типа) тип входного параметра которого
        //совместим с типом возвращаемого Comparator. Возвращаемый Comparator создается на
        //основе сравнения примитивных типов которые будут возвращены этими примитивными
        //специализациями.

        //          Использование Comparator<T> в стандартной библиотеке:
        //В классе Collections определен статический метод:
        //static <T> void sort (List<T> list, Comparator<? super T> c)
        //Данный метод позволит произвести сортировку списка. Критерий сравнения при
        //сортировке задается с помощью реализации Comparator совместимого типа. Часто
        //применяется для сортировки списка объектов пользовательского типа.

        //Пример использования метода comparingInt:

        List<Cat> cats4 = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        ToIntFunction<Cat> fun = a -> a.getAge();//ToIntFunction
        Comparator<Cat> resultComp3 = Comparator.comparingInt(fun);//Comparator
        Collections.sort(cats4, resultComp3);//Сортировка списка с помощью Comparator

    }//закрыли главный метод
}

/**
 *                  Статические методы интерфейса Comparator<T>:
 *
 * static <T> Comparator<T> nullsFirst (Comparator<? super T> javaCore34.comparator)
 * Создает «дружественный» к наличию null Comparator. null значение
 * всегда меньше.
 *
 * static <T> Comparator<T> nullsLast (Comparator<? super T> javaCore34.comparator)
 * Создает «дружественный» к наличию null Comparator. null значение
 * всегда больше.
 *
 * static <T extends Comparable<? super T>> Comparator<T> naturalOrder()
 * Вернет Comparator на основе реализации Comparable<T> для
 * объектов указанного типа
 *
 * static <T extends Comparable<? super T>> Comparator<T> reverseOrder()
 * Вернет «обратный» компаратор для Comparator на основе
 * реализации Comparable<T> для объектов указанного типа
 *
 * static <T, U extends Comparable<? super U>> Comparator<T> comparing (Function<? super T, ? extends U> keyExtractor)
 * Вернет Comparator на основании сравнения (метод compareTo)
 * результатов работы Function (параметр)
 *
 * static <T, U> Comparator<T> comparing (Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator)
 * Вернет Comparator на основании сравнения (метод compareTo)
 * результатов работы Function (параметр) и Comparator для них
 *
 * static <T> Comparator<T> comparingDouble (ToDoubleFunction<? super T> keyExtractor)
 * Вернет Comparator на основе сравнения примитивного типа double
 * полученного с помощью ToDoubleFunction (параметр метода)
 *
 * static <T> Comparator<T> comparingInt (ToIntFunction<? super T> keyExtractor)
 * Вернет Comparator на основе сравнения примитивного типа int
 * полученного с помощью ToIntFunction (параметр метода)
 *
 * static <T> Comparator<T> comparingLong (ToLongFunction<? super T> keyExtractor)
 * Вернет Comparator на основе сравнения примитивного типа long
 * полученного с помощью ToLongFunction (параметр метода)
 *
 *
 */
