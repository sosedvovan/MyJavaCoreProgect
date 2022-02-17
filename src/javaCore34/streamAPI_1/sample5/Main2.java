/**
 * Когда вызывается исключение
 * В случае генерации исключения в одном из промежуточных методов, отменяется
 * выполнение всей цепочки. Опять же напоминание, выполнение цепочки промежуточных
 * методов запускается только вызовом терминального метода.
 * <p>
 * Исключение возникшее в промежуточном методе прерывает выполнение всей
 * цепочки.
 */
package javaCore34.streamAPI_1.sample5;

import javaCore34.streamAPI_1.sample3.Cat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {

    public static void main(String[] args) {

        Cat cat1 = new Cat("Luska", 5, "White");
        Cat cat2 = new Cat(null, 7, "Black");
        Cat cat3 = new Cat("Barsic", 2, "Red");
        Cat cat4 = new Cat("Luska", 3, "Grey");

        List<Cat> list = List.of(cat1, cat2, cat3, cat4);

        //порождаем стрим на списке с 2-мя фильтрами
        Stream<Cat> catToName = list.stream().filter(a -> a.getWeight() < 5).filter(a -> a.getName().length() > 5);

        try {
            List<Cat> result = catToName.collect(Collectors.toList());
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}
