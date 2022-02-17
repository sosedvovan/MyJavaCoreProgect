package javaCore34.StreamAPI_9ТерминальныеМетодыВыполняющиеДействияСЭлементамиПотока.sample2;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Todo Метод: Object[] toArray() Вернет массив на основании элементов потока.
        // В этом примере элементы потока собираются в массив Object.

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Object[] result = numbers.stream().filter(a -> a % 2 == 0).toArray();

        System.out.println(Arrays.toString(result));

        //-----------------------------------------------------------------------------------------------

        //Todo Метод: <A> A[] toArray (IntFunction<A[]> generator)
        // Вернет массив на основании элементов потока. Параметром метода является бинарная
        // специализация IntFunction возвращающая массив нужного типа. Чаще всего используют ссылку на конструктор.

        List<String> names = List.of("Alexander", "Olga", "Inna", "Katia");
        String[] result2 = names.stream().limit(2).toArray(String[]::new);
        System.out.println(Arrays.toString(result2));

        //В примере показан сбор элементов потока в массив String[]. В качестве реализации
        //IntFunction используется ссылка на конструктор массива String (конструктор массива
        //принимает значение int).


    }
}
