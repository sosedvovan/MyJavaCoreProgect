package javaCore34.сomparable.sample1;

import java.util.Comparator;
import java.util.function.Function;

//Связь с другими способа установки отношения полного порядка:
public class Main3 {

    public static void main(String[] args) {

        //Пример использования Comparable<T> для создания Comparator<T>:

        Function<String, Integer> fun = a -> a.length();
        Comparator<String> cmp = Comparator.comparing(fun);
        System.out.println(cmp.compare("Java", "Python"));//-1 тк слово Python длинее Java

        //В данном примере Comparator<String> создается на основе Function<String, Integer>.
        //Сначала с помощью Function генерируется целое число (равное длине строки). И уже на
        //основании отношения естественного порядка для Integer генерируется Comparator. Т.е.
        //итоговый Comparator<String> будет сравнивать две строки по длине.

        //для того, чтобы это работало, должен быть заданн естественный порядок в классе, объекты которого
        //сравниваются с пом. implements Comparable<>.
        // в этом случае сравниваются строки, а класс String implements Comparable<String>.

    }
}

/**
 *   В стандартной библиотеке Java существует несколько способов установки полного
 * порядка для элементов. Это уже описанное отношение естественного порядка и
 * реализация интерфейса Comparator<T>. Довольно часто они связанны между собой.
 * Например можно получить Comparator<T> используя Comparable<T>. Для этого
 * используется статический метод интерфейса Comparator:
 *
 * static <T, U extends Comparable<? super U>> Comparator<T> comparing (Function<? super T, ? extends U> keyExtractor)
 *
 * Comparator создается на основании реализации интерфейса Comparable для типа
 * данных которые будут возвращены Function. Таким образом реализация Function должна
 * возвращать объект класса реализующий Comparable (для них установлено отношение
 * естественного порядка).
 */
