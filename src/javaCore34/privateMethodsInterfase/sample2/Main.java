package javaCore34.privateMethodsInterfase.sample2;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Vaska", 5);
        Cat cat2 = new Cat("Umka", 3);
        Cat cat3 = new Cat("Kuzia", 7);
        Cat cat4 = new Cat("Barsic", 2);

        //массив котов, содержащий null, который не получится сортировать обычным компаратором
        Cat[] cats = new Cat[]{cat1, cat2, null, cat3, cat4};

        //получаем ссылку на класс-компаратор(можно было с пом анонимнымного в методе ---Arrays.sort(cats, comp);---)
        Comparator<Cat> comp = new CatWeightComparator();

        //Получение безопасного Comparator, без него здесь будет NullPointerException.
       comp = NullFriendlyComparator.rangeNullFirst(comp);

        Arrays.sort(cats, comp);//Сортировка массива содержащего null, без NullPointerException
        for (int i = 0; i < cats.length; i++) {
            System.out.println(cats[i]);
        }
    }
}

/**
 * Объяснение применения private static метода в
 * интерфейсе
 *
 * Целью демонстрации было создание интерфейса который с помощью статических
 * методов позволял вернуть безопасную относительно наличия null версию Comparator.
 * Обычно в реализации Comparator не учитывают наличия null в сортируемых
 * последовательностях, что приводит к генерации NullPointerException при сортировке.
 * Интерфейс NullFriendlyComparator с помощью статических методов позволяет
 * вернуть безопасную версию любой реализации Comparator. В этой реализации будет
 * использоваться private static метод nullSafeCompare который сначала проводит сравнение
 * ссылок. Однако за пределами этого интерфейса видимость этого метода не нужна, что и
 * реализуется с помощью модификатора private.
 */
