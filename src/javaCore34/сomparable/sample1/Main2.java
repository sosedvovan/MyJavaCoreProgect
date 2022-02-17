package javaCore34.сomparable.sample1;

import java.util.Arrays;

//Где в стандартной библиотеке используется Comparable:
public class Main2 {
    public static void main(String[] args) {

        //Сортировка массива объектов класса Cat:
        //Массив для сортировки:
        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 5);
        Cat cat5 = new Cat("Kuzia", 2);
        Cat[] cats = new Cat[] { cat1, cat2, cat3, cat4, cat5 };

        for (Cat cat : cats) {
            System.out.println(cat);
        }
        System.out.println();

        //Сортировка с помощью отношения естественного порядка:
        Arrays.sort(cats);

        for (Cat cat : cats) {
            System.out.println(cat);

        }

    }
}

/**
 *              Где в стандартной библиотеке используется Comparable:
 * Например для сортировки массивов используется методы класса Arrays:
 * static void sort (Object[] a)
 * static void sort (Object[] a, int fromIndex, int toIndex)
 * Сортировка указанного массива объектов в порядке возрастания в соответствии с
 * отношением естественного порядка его элементов. Все элементы в массиве должны
 * реализовывать интерфейс Comparable. Кроме того, все элементы в массиве должны быть
 * взаимно сопоставимы (то есть a.compareTo(b) не должен вызывать ClassCastException для
 * любых элементов a и b в массиве).
 * И хотя в явном виде этот интерфейс нигде не фигурирует, но для сортировки
 * используется отношение естественного порядка. Оно же в свою очередь устанавливается
 * с помощью Comparable. Т.е. отсортировать этими методами можно только объекты класса
 * реализующего интерфейс Comparable.
 *
 * и, получается, чтобы не было проблем в коллекциях, при добавлении в них котов, желательно, чтобы реализация compareTo
 * согласовывался с переопределением equals, тк коллекции могут использовать оба этих метода одновременно.
 */
