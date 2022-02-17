package javaCore34.comparator.sample2;

import javaCore34.comparator.sample1.Cat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToIntFunction;

//Методы по умолчанию интерфейса Comparator<T>
public class Main {

    public static void main(String[] args) {

        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 5);
        Cat cat5 = new Cat("Kuzia", 2);

        //               Метод по умолчанию reversed()
        //В интерфейсе Comparator объявлен метод по умолчанию:
        //default Comparator<T> reversed()
        //Этот метод возвращает «обратный» Comparator на основании текущего. Под
        //обратным Comparator подразумевается возвращающий противоположный результат
        //сравнения. Т.е. если текущий возвращает положительное число то обратный
        //отрицательное и т. д. Обратный Comparator позволяет проводить сортировку по убыванию
        //в случае необходимости.
        Cat[] cats = new Cat[]{cat1, cat2, cat3, cat4, cat5};
        Comparator<Cat> comp2 = Main::catAgeCompare;//ссылка на метод-реализацию абстракта, с ним будет сортировка в порядке возрастания
        Comparator<Cat> reversedComp = comp2.reversed();//теперь перевернули и будет сортировка по возрасту в порядке убывания
        Arrays.sort(cats, reversedComp);
        for (Cat cat : cats) {
            System.out.println(cat);
        }

        //                          Метод thenComparing:
        //В Comparator объявлен метод по умолчанию:
        //default Comparator<T> thenComparing (Comparator<? super T> other)
        //Этот метод принимает в качестве параметра реализацию Comparator тип входного
        //параметра которого совместим с типом текущей реализации Comparator. Возвращает
        //составной Comparator работающий следующим образом — сначала вызывается метод
        //compare текущей реализации, если он возвращает отличное от нуля значение, то это
        //значение и возвращается в качестве результата составного Comparator. Но если метод
        //текущего вернет 0, тогда будет вызван метод compare реализации Comparator
        //выступающей в качестве параметра.
        //Этот метод позволяет создавать цепочки сравнений. Логика цепочек такова если
        //первое сравнение вернет, что это одинаковые объекты то следует вызвать метод
        //сравнения по цепочке далее.
        Cat[] cats2 = new Cat[]{cat1, cat2, cat3, cat4, cat5};
        Comparator<Cat> comp3 = (a, b) -> Integer.compare(a.getAge(), b.getAge());
        Comparator<Cat> comp4 = (a, b) -> a.getName().compareTo(b.getName());
        Comparator<Cat> comp5 = comp3.thenComparing(comp4);//Составной Comparator
        Arrays.sort(cats2, comp5);
        //В данном примере реализован составной Comparator. Текущий (comp1) производит
        //сравнение по возрасту, параметр (comp2) по имени. Составной будет проводить
        //сравнение по возрасту, но если возраст котов окажется одинаков, то произведет
        //сравнение по имени.


        //            Метод thenComparing(Function<? super T, ? extends U> keyExtractor):
        //В Comparator объявлен метод по умолчанию:
        //default <U extends Comparable<? super U>>Comparator<T> thenComparing (Function<? super T, ? extends U> keyExtractor)
        //Этот метод принимает в качестве параметра реализацию Function тип входного
        //параметра которого совместим с типом текущей реализации Comparator. Возвращает
        //составной Comparator. Второй компаратор создается с помощью сравнения результатов
        //работы Function. Т.е. Function на основе сравниваемых объектов создает данные другого
        //типа и при вызове метода compareTo() для этих данных (вот для этого U extends
        //Comparable).

        Comparator<Cat> comp1 = (a, b) -> Integer.compare(a.getAge(), b.getAge());
        Comparator<Cat> resultComp = comp1.thenComparing(c -> c.getName());
        Arrays.sort(cats, resultComp);

        //В данном примере реализован составной Comparator. Текущий (comp1) производит
        //сравнение по возрасту. Второй Comparator реализован на основании сравнения того, что
        //вернет реализация Function которая использовалась в качестве параметра. В примере
        //Function на основании Cat генерирует строку с его именем. Так, что полученный второй
        //компаратор создается путем сравнения строк методом compareTo (метод интерфейса
        //Comparable).
        //те это тоже цепочка сравнений объекта. если comp1 вернет 0, то в параметрах уже есть компаратор,
        //который сравнивает по имени

        //Подробнее о предыдущем примере:
        //а вараметрах имеем:(c -> c.getName()) - это Function<Cat,String>
        //где первое с это Cat, а c.getName() это String
        //компилятор здесь из (c -> c.getName()) автоматически создаст компаратор:
        //Comparator<Cat> comp2 = (a,b) -> a.getName().compareTo(b.getName())

        //Второй компаратор создается следующим образом, сначала для двух сравниваемых
        //объектов (Cat) вызывается Function(выступает параметром) и потом для полученных
        //данных выполняется сравнение с помощью метода compareTo (принадлежит интерфейсу
        //Comparable). В примере реализация Function создает на основе Cat строку, и только потом
        //происходит сравнение этих строк. Для строк метод compareTo() сравнивает их по
        //алфавиту.


        //     Метод thenComparing (Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator):
        //
        //В Comparator объявлен метод по умолчанию:
        //default <U> Comparator<T> thenComparing (Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator)
        //Этот метод принимает в качестве параметра реализацию Function тип входного
        //параметра которого совместим с типом текущей реализации Comparator и Comparator тип
        //входного параметра которого совместим с типом возвращаемого значения Function.
        //Возвращает составной Comparator. Второй компаратор создается как Comparator (второй
        //параметр метода) для результата работы Function (первый параметр метода).

        //Пример использования:

        Comparator<Cat> comp6 = (a, b) -> Integer.compare(a.getAge(), b.getAge());
        Comparator<Cat> resultComp2 = comp6.thenComparing(Main::catToString, Main::compareStringLength);
        //где Main::catToString это Реализация Function
        //а Main::compareStringLength это Реализация Comparator

        //Второй Comparator создается так: сначала Function применяется к объектам
        //сравнения(Cat) и к результату работы Function применяют Comparator (второй параметр).


        //                 Методы thenComparingDouble , thenComparingInt,
        //                                thenComparingLong:

        //В Comparator объявлены методы по умолчанию:
        //default Comparator<T> thenComparingDouble (ToDoubleFunction<? super T> keyExtractor)
        //default Comparator<T> thenComparingInt (ToIntFunction<? super T> keyExtractor)
        //default Comparator<T> thenComparingLong (ToLongFunction<? super T> keyExtractor)
        //Эти методы принимают в качестве параметра реализацию примитивную
        //специализацию Functional (соответствующего типа) тип входного параметра которого
        //совместим с типом текущей реализации Comparator. Возвращает составной Comparator.
        //Второй компаратор создается на основе сравнения примитивных типов которые будут
        //возвращены этими примитивными специализациями.

        //Пример применения:

        Comparator<Cat> comp7 = (a, b) -> a.getName().compareTo(b.getName());
        ToIntFunction<Cat> fun = a -> a.getAge();
        Comparator<Cat> resultComp3 = comp7.thenComparingInt(fun);

        //В примере показана работа метода по умолчанию thenComparingInt. Полученный
        //составной компаратор состоит из текущего Comparator (comp7) и Comparator полученный
        //сравнением значений типа int которые создаются реализацией ToIntFunction (fun)
        //генерирующий на основе Cat значение типа int.
        //то это так же цепочка срванений- сначала по имени кота, потом по его возрасту


        //


    }//закрыли метод main


    //метод, реализующий абстракт корпоратора для ссылки на метод
    public static String catToString(Cat cat) {
        return cat.getName();
    }

    //метод, реализующий абстракт корпоратора для ссылки на метод
    public static int compareStringLength(String a, String b) {
        if (a.length() > b.length()) {
            return 1;
        }
        if (a.length() < b.length()) {
            return -1;
        }
        return 0;
    }

    //метод, реализующий абстракт корпоратора для ссылки на метод
    public static int catAgeCompare(Cat a, Cat b) {
        if (a.getAge() > b.getAge()) {
            return 1;
        }
        if (a.getAge() < b.getAge()) {
            return -1;
        }
        return 0;
    }

}//закрыли класс Main


/**
 * Методы по умолчанию интерфейса Comparator<T>:
 * <p>
 * default Comparator<T> reversed()
 * Вернет Comparator обратный текущему.
 * <p>
 * default Comparator<T> thenComparing (Comparator<? super T> other)
 * Вернет Comparator созданный на основе текущего
 * Comparator и выступающего в качестве параметра.
 * <p>
 * default <U extends Comparable<? super U>> Comparator<T> thenComparing (Function<? super T, ? extends U> keyExtractor)
 * Вернет Comparator созданной на основе текущего и
 * компаратора для ключа созданного с помощью функции
 * используемой в качестве параметра.
 * <p>
 * default <U> Comparator<T> thenComparing (Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator)
 * Вернет Comparator созданный на основе текущего, и
 * ключа сравнения созданного на основе функции и
 * компаратора для него выступающего в качестве
 * параметров.
 * <p>
 * default Comparator<T> thenComparingDouble(ToDoubleFunction<? super T> keyExtractor)
 * Вернет Comparator созданный на основе текущего и
 * функции для получения ключа сортировки типа double
 * <p>
 * default Comparator<T> thenComparingInt (ToIntFunction<? super T> keyExtractor)
 * Вернет Comparator созданный на основе текущего и
 * функции для получения ключа сортировки типа int
 * <p>
 * default Comparator<T> thenComparingLong(ToLongFunction<? super T> keyExtractor)
 * Вернет Comparator созданный на основе текущего и
 * функции для получения ключа сортировки типа long
 */
