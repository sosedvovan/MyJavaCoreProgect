package javaCore34.streamAPI_2Filter.sample2;

import javaCore34.streamAPI_2Filter.sample1.Cat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        //                       Метод Stream<T> distinct()  (ПРОТИВ ДУБЛЕЙ В СПИСКЕ)
        //Метод distinct() используется для того, что бы отбросить эквивалентные элементы из
        //потока данных. Пр сути применение этого метода отбросит дубликаты из потока данных.
        //Эквивалентность определяется на основании метода equals() (не забывайте его
        //переопределять для ваших классов).

        //                          Пример применения метода distinct

        List<Integer> list = List.of(0, 5, -2, 0, 3, 1, 1, -4, 7);

        list.stream().distinct().forEach(n -> System.out.println(n));

        //В базовом списке есть дубли. Однако применение промежуточного метода distinct
        //удалит из потока дубликаты, оставив в потоке уникальные значения.

        //-----------------------------------------------------------------------------------------------------

        //                         О важности переопределения equals

        Cat cat1 = new Cat("Luska", 5, "White");
        Cat cat2 = new Cat("Luska", 5, "White");//объект- дублер
        Cat cat3 = new Cat("Barsic", 2, "Red");
        Cat cat4 = new Cat("Kuzia", 3, "Grey");

        Cat[] cats = new Cat[] { cat1, cat2, cat3, cat4 };

        //породим стрим из массива, применим метод ПРОТИВ ДУБЛЕЙ В СПИСКЕ:
        Stream<Cat> catToName = Arrays.stream(cats).distinct();
        //теперь терминальный метод:
        catToName.forEach(n -> System.out.println(n));
        //важно, чтобы equals был переопределен для правильной работы кода. Иначе будут сравниваться ссылки
        //на объекты, а не поля у объектов.

        //В списке есть два эквивалентных объекта, но так как метод equals в классе Cat не
        //реализован, то используется реализация equals по умолчанию. В результате метод distinct
        //не удаляет эквивалентные объекты с потока. Это связанно с тем, что реализация equals
        //по умолчанию сравнивает не состояние объектов а адреса ссылок. УЖЕ ПЕРЕОПРЕДЕЛИЛИ equals.

        //------------------------------------------------------------------------------------------------

        //                    Метод Stream<T> limit (long maxSize)
        //Метод limit (long maxSize) используется для того, что бы оставить в потоке первые
        //maxSize элементов. Этот метод имеет смысл для потоков сохраняющих порядок или
        //упорядоченных потоков.

        //                       Пример использования limit

        List<Integer> list2 = List.of(0, 5, -2, 0, 3, 1, 1, -4, 7);

        list2.stream()
                .filter(n -> n >= 0)
                .sorted()//массив отсортируется согласно Компарабле класса Интеджер
                .limit(3)//далее пройдут только первые 3-и элемента
                .forEach(n -> System.out.println(n));//Терминальный метод потока

        //В этом пример поток данных Stream<Integer> создается на основе List<Integer>.
        //После чего был применен промежуточный метод filter (отбросит отрицательные числа), и
        //потом применяется промежуточный метод sorted для сортировки потока. После чего
        //используя метод limit поток ограничивается первыми 3 элементами. Терминальным
        //методом выступает forEach который выведет каждый элемент потока на экран.

        //---------------------------------------------------------------------------------------------------

        //                   Метод Stream<T> skip (long n)
        //Метод skip (long n) используется для того, что бы пропустить в потоке первые n
        //элементов. Этот метод имеет смысл для потоков сохраняющих порядок или
        //упорядоченных потоков.

        //                     Пример использования skip

        List<Integer> list3 = List.of(0, 5, -2, 0, 3, 1, 1, -4, 7);
        list3.stream()
                .filter(n -> n >= 0)
                .sorted()
                .skip(4)//Пропуск первых 4 элементов
                .forEach(n -> System.out.println(n));//Терминальный метод

        //В этом пример поток данных Stream<Integer> создается на основе List<Integer>.
        //После чего был применен промежуточный метод filter (отбросит отрицательные числа), и
        //потом применяется промежуточный метод sorted для сортировки потока. После чего
        //используя метод skip пропускается первые 4 элемента потока. Терминальным методом
        //выступает forEach который выведет каждый элемент потока на экран.

        //--------------------------------------------------------------------------------------------

        //        Метод default Stream<T> dropWhile (Predicate<? super T> javaCore34.predicate)
        //Метод по умолчанию default Stream<T> dropWhile (Predicate<? super T> javaCore34.predicate)
        //используется удаления первой порции элементов потока для которых реализация
        //Predicate вернет true.

        //            Пример использования dropWhile

        List<Integer> list4 = List.of(0, 5, -2, 0, 3, 1, 1, -4, 7);
        list4.stream()
                .dropWhile(n->n>=0)
                .forEach(n -> System.out.println(n));

        //В данном примере реализация Predicate который используется в методе dropWhile
        //станет равен true на первом положительном числе в потоке, и удалит все элементы
        //потока до первого элемента для которого Predicate вернет false. Таким образом из потока
        //будут удаленны первые два числа.

        //--------------------------------------------------------------------------------------------------

        //       Метод default Stream<T> takeWhile (Predicate<? super T> javaCore34.predicate)
        //Метод по умолчанию takeWhile (Predicate<? super T> javaCore34.predicate) используется для того
        //что бы оставить в потоке первую порцию элементов потока для которых реализация
        //Predicate вернет true. Остальные элементы потока будут отброшены

        //           Пример использования takeWhile

        List<Integer> list5 = List.of(0, 5, -2, 0, 3, 1, 1, -4, 7);

        list5.stream()
                .takeWhile(n->n>=0)
                .forEach(n -> System.out.println(n));

        //В данном примере реализация Predicate который используется в методе takeWhile
        //станет равен true на первом положительном числе в потоке, и оставит в потоке все
        //элементы потока до первого элемента для которого Predicate вернет false. Т.е. первые два
        //числа.


    }
}
